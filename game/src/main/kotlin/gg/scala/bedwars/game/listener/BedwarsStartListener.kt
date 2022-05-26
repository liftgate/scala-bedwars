package gg.scala.bedwars.game.listener

import gg.scala.bedwars.game.generator.BedwarsItemGenerator
import gg.scala.bedwars.game.generator.impl.BedwarsTeamItemGenerator
import gg.scala.bedwars.shared.team.BedwarsCgsGameTeam
import gg.scala.cgs.common.CgsGameEngine
import gg.scala.cgs.common.teams.CgsGameTeamService
import gg.scala.commons.annotations.Listeners
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

@Listeners
object BedwarsStartListener : Listener
{
    @EventHandler
    fun onStart(e: CgsGameEngine.CgsGameStartEvent) {
        CgsGameTeamService.teams.values.map { it as BedwarsCgsGameTeam }.forEach {
            it.spawnPoint!!.world.getBlockAt(it.spawnPoint!!.subtract(0.0, 1.0, 0.0)).type = Material.BEDROCK

            if (it.participants.size > 0) BedwarsTeamItemGenerator(it.spawnPoint!!)
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