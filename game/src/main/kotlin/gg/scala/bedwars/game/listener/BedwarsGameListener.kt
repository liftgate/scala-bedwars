package gg.scala.bedwars.game.listener

import gg.scala.bedwars.game.ScalaBedwarsGame
import gg.scala.bedwars.game.event.BedwarsBedDestroyEvent
import gg.scala.bedwars.shared.team.BedwarsCgsGameTeam
import gg.scala.cgs.common.CgsGameEngine
import gg.scala.cgs.common.player.handler.CgsGameDisqualificationHandler
import gg.scala.cgs.common.teams.CgsGameTeamService
import gg.scala.commons.annotations.Listeners
import gg.scala.flavor.inject.Inject
import net.evilblock.cubed.util.CC
import net.evilblock.cubed.util.bukkit.Constants
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.block.BlockPlaceEvent
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent
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
    fun onRejoin(event: PlayerJoinEvent)
    {
        val team = (CgsGameTeamService.getTeamOf(event.player) as BedwarsCgsGameTeam)
        if (team.bedDestroyed) CgsGameDisqualificationHandler.disqualifyPlayer(event.player)
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

        CgsGameEngine.INSTANCE.sendMessage("")
        CgsGameEngine.INSTANCE.sendMessage(CC.B_WHITE + "Bed Destroyed ${CC.GRAY}${Constants.DOUBLE_ARROW_RIGHT} ${event.team.name}${CC.RED}'s bed has been destroyed${
            if (event.destroyer != null) " by " + event.destroyer.displayName else ""
        }!")
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

                event.block.type = Material.AIR
                event.isCancelled = true

                BedwarsBedDestroyEvent(
                    CgsGameTeamService.teams[meta[0].asInt()] as BedwarsCgsGameTeam,
                    event.player
                ).callEvent()
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
                event.entity.teleport(team.spawnPoint)
            }
        }
    }
}
