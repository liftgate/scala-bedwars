package gg.scala.bedwars.shared.team

import gg.scala.bedwars.shared.arena.BedwarsArena
import gg.scala.cgs.common.CgsGameEngine
import gg.scala.cgs.common.information.arena.CgsGameArenaHandler
import gg.scala.cgs.common.teams.CgsGameTeam
import net.evilblock.cubed.util.CC
import net.evilblock.cubed.util.bukkit.ColorUtil
import org.bukkit.ChatColor
import org.bukkit.DyeColor
import org.bukkit.Location

open class BedwarsCgsGameTeam(id: Int) : CgsGameTeam(id) {

    // diamond upgrades yes
    val name: String get() {
        return color.toString() + color.name.lowercase().capitalize()
    }
    var bedDestroyed: Boolean = false
    val color: ChatColor
        get() {
        return when(id) {
            1 -> ChatColor.RED
            2 -> ChatColor.BLUE
            3 -> ChatColor.GREEN
            4 -> ChatColor.YELLOW
            5 -> ChatColor.AQUA
            6 -> ChatColor.WHITE
            7 -> ChatColor.LIGHT_PURPLE
            8 -> ChatColor.GRAY
            else -> ChatColor.BLACK
        }
    }
    val spawnPoint: Location? = (CgsGameArenaHandler.arena as BedwarsArena).getSpawnPoint(id)

    fun broadcastElimination() {
        CgsGameEngine.INSTANCE.sendMessage("")
        CgsGameEngine.INSTANCE.sendMessage("${CC.B_WHITE}Team Elimination")
        CgsGameEngine.INSTANCE.sendMessage("")
        CgsGameEngine.INSTANCE.sendMessage("$name ${CC.RED}has been eliminated!")
        CgsGameEngine.INSTANCE.sendMessage("")
    }
}