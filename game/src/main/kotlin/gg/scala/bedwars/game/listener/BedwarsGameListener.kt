package gg.scala.bedwars.game.listener

import gg.scala.bedwars.game.ScalaBedwarsGame
import gg.scala.bedwars.game.death.BedwarsRespawnRunnable
import gg.scala.bedwars.game.event.BedwarsBedDestroyEvent
import gg.scala.bedwars.shared.BedwarsCgsStatistics
import gg.scala.bedwars.shared.team.BedwarsCgsGameTeam
import gg.scala.cgs.common.CgsGameEngine
import gg.scala.cgs.common.player.handler.CgsGameDisqualificationHandler
import gg.scala.cgs.common.player.handler.CgsPlayerHandler
import gg.scala.cgs.common.teams.CgsGameTeamService
import gg.scala.commons.annotations.Listeners
import gg.scala.flavor.inject.Inject
import net.evilblock.cubed.util.CC
import net.evilblock.cubed.util.bukkit.Constants
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.entity.ArmorStand
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.block.BlockPlaceEvent
import org.bukkit.event.entity.ItemSpawnEvent
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.player.PlayerInteractAtEntityEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.metadata.FixedMetadataValue

@Listeners
object BedwarsGameListener : Listener
{
    @Inject
    lateinit var plugin: ScalaBedwarsGame

    @EventHandler
    fun onQuit(event: PlayerQuitEvent)
    {
        val team = (CgsGameTeamService.getTeamOf(event.player) as BedwarsCgsGameTeam)

        if (team.bedDestroyed)
        {
            CgsGameDisqualificationHandler.disqualifyPlayer(event.player)
        }
    }

    @EventHandler
    fun onRejoin(
        event: CgsGameEngine.CgsGameParticipantReconnectEvent
    )
    {
        if (!event.connectedWithinTimeframe)
        {
            return
        }

        val team = (CgsGameTeamService.getTeamOf(event.participant) as BedwarsCgsGameTeam?)
            ?: return

        if (!team.bedDestroyed)
        {
//            BedwarsRespawnRunnable(event.participant)
//                .runTaskTimer(this.plugin, 1L, 20L)
        }
    }

    @EventHandler
    fun onInteract(
        event: PlayerInteractAtEntityEvent
    )
    {
       if (event.rightClicked is ArmorStand)
       {
           event.isCancelled = true
       }
    }

    @EventHandler
    fun onPlace(event: BlockPlaceEvent)
    {
        event.blockPlaced.setMetadata("placed", FixedMetadataValue(plugin, true))
    }

    @EventHandler
    fun onBreak(event: BedwarsBedDestroyEvent)
    {
        event.team.bedDestroyed = true

        CgsGameEngine.INSTANCE.playSound(Sound.ENDERDRAGON_GROWL)

        if (event.destroyer != null) {
            (CgsGameEngine.INSTANCE.getStatistics(CgsPlayerHandler.find(event.destroyer)!!) as BedwarsCgsStatistics).bedsBroken.inc()
        }

        CgsGameEngine.INSTANCE.sendMessage("")
        CgsGameEngine.INSTANCE.sendMessage(CC.B_WHITE + "Bed Destroyed ${CC.GRAY}${Constants.DOUBLE_ARROW_RIGHT} ${event.team.name}'s${CC.RED} bed has been destroyed${
            if (event.destroyer != null) " by " + event.destroyer.displayName else ""
        }${CC.RED}!")
        CgsGameEngine.INSTANCE.sendMessage("")

        event.team.alive.filter { Bukkit.getPlayer(it) == null }.forEach {
            CgsGameDisqualificationHandler.disqualifyPlayer(it)
        }

        if (
            event.team.alive
                .mapNotNull { Bukkit.getPlayer(it) }
                .isEmpty()
        )
        {
            event.team.broadcastElimination()
        }
    }

    @EventHandler
    fun onItem(
        event: ItemSpawnEvent
    )
    {
        if (
            event.entity.itemStack.type
                .name.contains("BED")
        )
        {
            event.isCancelled = true
        }
    }

    @EventHandler
    fun onBreak(event: BlockBreakEvent)
    {
        if (event.block.type == Material.BED_BLOCK)
        {
            if (event.block.hasMetadata("team"))
            {
                val meta = event.block
                    .getMetadata("team")

                val teamId = meta[0].asInt()

                val team = CgsGameTeamService.getTeamOf(event.player) as BedwarsCgsGameTeam?

                if (team == null)
                {
                    event.isCancelled = true
                    return
                }

                if (team.id == teamId)
                {
                    event.isCancelled = true
                    event.player.sendMessage("${CC.RED}You cannot break your own team's bed!")
                    return
                }

                BedwarsBedDestroyEvent(
                    CgsGameTeamService.teams[meta[0].asInt()] as BedwarsCgsGameTeam,
                    event.player
                ).callEvent()
            } else
            {
                event.isCancelled = true
            }
        } else if (!event.block.hasMetadata("placed"))
        {
            event.player.sendMessage("${CC.RED}You can only break blocks placed by players!")
            event.isCancelled = true
        }
    }

    @EventHandler
    fun onDeath(event: PlayerDeathEvent)
    {
        if (
            event.entity
                .hasMetadata("spectator")
        )
        {
            return
        }

        val team = CgsGameTeamService.getTeamOf(event.entity) as BedwarsCgsGameTeam?

        if (team != null)
        {
            if (team.bedDestroyed)
            {
                CgsGameDisqualificationHandler.disqualifyPlayer(
                    player = event.entity, broadcastNotification = true, setSpectator = true
                )

                if (team.alive.isEmpty())
                {
                    team.broadcastElimination()
                }
            } else
            {
                BedwarsRespawnRunnable(event.entity)
                    .runTaskTimer(
                        this.plugin, 1L, 20L
                    )
            }
        }
    }
}
