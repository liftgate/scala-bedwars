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
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.entity.Player
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
    @Inject lateinit var plugin: ScalaBedwarsGame

    @EventHandler
    fun onQuit(e: PlayerQuitEvent) {
        val team = (CgsGameTeamService.getTeamOf(e.player) as BedwarsCgsGameTeam?)
        if (team != null && team.bedDestroyed) CgsGameDisqualificationHandler.disqualifyPlayer(e.player)
    }

    @EventHandler
    fun onRejoin(e: PlayerJoinEvent) {
        val team = (CgsGameTeamService.getTeamOf(e.player) as BedwarsCgsGameTeam?)

        if (team != null) {
            e.player.playerListName = (if (e.player.hasMetadata("spectator")) CC.GRAY else team.color.toString()) + e.player.displayName

            if (team.bedDestroyed) CgsGameDisqualificationHandler.disqualifyPlayer(e.player)
        }
    }

    @EventHandler
    fun onPlace(e: BlockPlaceEvent) {
        e.blockPlaced.setMetadata("placed", FixedMetadataValue(plugin, true))
    }

    @EventHandler
    fun onBreak(e: BedwarsBedDestroyEvent) {
        e.team.bedDestroyed = true

        CgsGameEngine.INSTANCE.playSound(Sound.ENDERDRAGON_GROWL)
        CgsGameEngine.INSTANCE.sendMessage("")
        CgsGameEngine.INSTANCE.sendMessage(CC.B_WHITE + "Bed Destroyed")
        CgsGameEngine.INSTANCE.sendMessage("")
        CgsGameEngine.INSTANCE.sendMessage("${e.team.name}${CC.RED}'s bed has been destroyed!")
        if (e.destroyer != null) CgsGameEngine.INSTANCE.sendMessage(CC.RED + "Destroyed by " + e.destroyer.displayName)
        CgsGameEngine.INSTANCE.sendMessage("")

        e.team.alive.filter { Bukkit.getPlayer(it) == null }.forEach {
            CgsGameDisqualificationHandler.disqualifyPlayer(it)
        }
        if (e.team.alive.mapNotNull { Bukkit.getPlayer(it) }.isEmpty()) {
            e.team.broadcastElimination()
        }
    }

    @EventHandler
    fun onBreak(e: BlockBreakEvent) {
        if (e.block.type == Material.BED_BLOCK) {
            if (e.block.hasMetadata("team")) {
                val meta = e.block.getMetadata("team")
                BedwarsBedDestroyEvent(CgsGameTeamService.teams[meta[0].asInt()] as BedwarsCgsGameTeam, e.player).callEvent()
            }
        } else if (!e.block.hasMetadata("placed")) {
            e.player.sendMessage("${CC.RED}You can only break blocks placed by players!")
            e.isCancelled = true
        }
    }

    @EventHandler
    fun onDeath(e: PlayerDeathEvent) {
        if (e.entity.hasMetadata("spectator")) return
        val team = CgsGameTeamService.getTeamOf(e.entity) as BedwarsCgsGameTeam?

        if (team != null) {
            if (team.bedDestroyed) {
                CgsGameDisqualificationHandler.disqualifyPlayer(
                    player = e.entity, broadcastNotification = true, setSpectator = true
                )
                if (team.alive.isEmpty()) team.broadcastElimination()
            } else e.entity.teleport(team.spawnPoint)
        }
    }
}