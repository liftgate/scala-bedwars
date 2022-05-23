package gg.scala.bedwars.shared.gamemode

import gg.scala.cgs.common.information.mode.CgsGameMode
import gg.scala.bedwars.shared.arena.BedwarsArena
import net.evilblock.cubed.util.CC
import org.bukkit.Material

/**
 * @author GrowlyX
 * @since 12/3/2021
 */
object BedwarsSoloGameMode : CgsGameMode
{
    override fun getId() = "eight_one"
    override fun getName() = "Solo"

    override fun getMaterial() = Pair(Material.BED, 0)

    override fun getDescription() = "${CC.GRAY}A solo game of Bedwars!"

    override fun getArenas() = listOf(
        BedwarsArena
    )

    override fun getTeamSize() = 1 // team size
    override fun getMaxTeams() = 8 // max teams
}
