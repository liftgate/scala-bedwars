package gg.scala.bedwars.shared.gamemode

import gg.scala.cgs.common.information.mode.CgsGameMode
import gg.scala.bedwars.shared.arena.BedwarsArena
import net.evilblock.cubed.util.CC
import org.bukkit.Material

/**
 * @author GrowlyX
 * @since 12/3/2021
 */
object BedwarsFoursGameMode : CgsGameMode
{
    override fun getId() = "four_four"
    override fun getName() = "Fours"

    override fun getMaterial() = Pair(Material.BED, 0)

    override fun getDescription() = "${CC.GRAY}A fours game of Bedwars!"

    override fun getArenas() = listOf(
        BedwarsArena
    )

    override fun getTeamSize() = 4
    override fun getMaxTeams() = 4
}
