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
    val color = BedwarsCgsGameTeamColors.fromId(id)

    val name = "$color${
        BedwarsCgsGameTeamColors.map(this.color)
    }"

    var bedDestroyed: Boolean = false
    val spawnPoint = (CgsGameArenaHandler.arena as BedwarsArena).getSpawnPoint(id)
    val upgrades = mutableMapOf<BedwarsCgsGameTeamUpgrade ,Int>()

    fun getNextLevel(upgrade: BedwarsCgsGameTeamUpgrade) : Int {
        return upgrades[upgrade] ?: 1
    }

    fun broadcastElimination()
    {
        CgsGameEngine.INSTANCE.sendMessage("")
        CgsGameEngine.INSTANCE.sendMessage("${CC.B_WHITE}Team Elimination ${CC.GRAY}${Constants.DOUBLE_ARROW_RIGHT} $name ${CC.RED}has been eliminated!")
        CgsGameEngine.INSTANCE.sendMessage("")
    }
}
