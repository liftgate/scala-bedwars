package gg.scala.bedwars.game.listener

import gg.scala.bedwars.shared.team.BedwarsCgsGameTeam
import gg.scala.cgs.common.CgsGameEngine
import gg.scala.cgs.common.player.handler.CgsGameDisqualificationHandler
import gg.scala.cgs.common.teams.CgsGameTeamService
import gg.scala.commons.annotations.Listeners
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent

@Listeners
object BedwarsGameListener : Listener
{
    @EventHandler
    fun onDeath(e: PlayerDeathEvent) {
        if (e.entity.hasMetadata("spectator")) return
        val team = CgsGameTeamService.getTeamOf(e.entity) as BedwarsCgsGameTeam?

        if (team != null) {
            if (team.bedDestroyed) {
                CgsGameDisqualificationHandler.disqualifyPlayer(
                    player = e.entity, broadcastNotification = true, setSpectator = true
                )
            }
        }

        CgsGameTeamService.teams.values.map { it as BedwarsCgsGameTeam }.forEach {
            it.spawnPoint!!.world.getBlockAt(it.spawnPoint!!.subtract(0.0, 1.0, 0.0)).type = Material.BEDROCK

            // add this back once bed functionalize exists
            //if (it.participants.size <= 0)
                it.bedDestroyed = true
            //else
            it.participants.forEach { id ->
                Bukkit.getPlayer(id).teleport(it.spawnPoint)
            }
        }
    }
}