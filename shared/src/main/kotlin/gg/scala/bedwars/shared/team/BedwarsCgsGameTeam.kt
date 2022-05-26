package gg.scala.bedwars.shared.team

import gg.scala.bedwars.shared.arena.BedwarsArena
import gg.scala.cgs.common.CgsGameEngine
import gg.scala.cgs.common.information.arena.CgsGameArenaHandler
import gg.scala.cgs.common.teams.CgsGameTeam
import net.evilblock.cubed.util.CC
import net.evilblock.cubed.util.bukkit.ColorUtil
import net.evilblock.cubed.util.bukkit.Constants
import org.bukkit.ChatColor
import org.bukkit.DyeColor
import org.bukkit.Location

open class BedwarsCgsGameTeam(id: Int) : CgsGameTeam(id)
{
    val color = when (id)
    {
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

    val name = "$color${
        BedwarsCgsGameTeamColors.map(this.color)
    }"

    var bedDestroyed: Boolean = false
    val spawnPoint = (CgsGameArenaHandler.arena as BedwarsArena).getSpawnPoint(id)

    fun broadcastElimination()
    {
        CgsGameEngine.INSTANCE.sendMessage("")
        CgsGameEngine.INSTANCE.sendMessage("${CC.B_WHITE}Team Elimination ${CC.GRAY}${Constants.DOUBLE_ARROW_RIGHT} $name ${CC.RED}has been eliminated!")
        CgsGameEngine.INSTANCE.sendMessage("")
    }
}
