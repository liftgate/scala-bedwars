package gg.scala.bedwars.game.listener

import gg.scala.bedwars.game.generator.BedwarsItemGenerator
import gg.scala.bedwars.game.generator.impl.BedwarsDiamondItemGenerator
import gg.scala.bedwars.game.generator.impl.BedwarsTeamItemGenerator
import gg.scala.bedwars.shared.arena.BedwarsArena
import gg.scala.bedwars.shared.team.BedwarsCgsGameTeam
import gg.scala.cgs.common.CgsGameEngine
import gg.scala.cgs.common.information.arena.CgsGameArenaHandler
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
        (CgsGameArenaHandler.arena as BedwarsArena).diamondGenerators.forEach {
            BedwarsDiamondItemGenerator(it)
        }

        CgsGameTeamService.teams.values.map { it as BedwarsCgsGameTeam }.forEach {
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