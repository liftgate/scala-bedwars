package gg.scala.bedwars.shared.gamemode

import gg.scala.cgs.common.information.mode.CgsGameMode
import gg.scala.bedwars.shared.arena.BedwarsArena
import gg.scala.bedwars.shared.arena.four.TestFourArena
import net.evilblock.cubed.util.CC
import org.bukkit.Material

/**
 * @author GrowlyX
 * @since 12/3/2021
 */
object BedwarsTriosGameMode : CgsGameMode
{
    override fun getId() = "four_three"
    override fun getName() = "Trios"

    override fun getMaterial() = Pair(Material.BED, 0)

    override fun getDescription() = "${CC.GRAY}A trios game of Bedwars!"

    override fun getArenas() = listOf(
        TestFourArena
    )

    override fun getTeamSize() = 3
    override fun getMaxTeams() = 4
}
