package gg.scala.bedwars.shared.gamemode

import gg.scala.cgs.common.information.mode.CgsGameMode
import gg.scala.bedwars.shared.arena.BedwarsArena
import gg.scala.bedwars.shared.arena.eight.TestEightArena
import net.evilblock.cubed.util.CC
import org.bukkit.Material

/**
 * @author GrowlyX
 * @since 12/3/2021
 */
object BedwarsDuosGameMode : CgsGameMode
{
    override fun getId() = "eight_two"
    override fun getName() = "Duos"

    override fun getMaterial() = Pair(Material.BED, 0)

    override fun getDescription() = "${CC.GRAY}A duos game of Bedwars!"

    override fun getArenas() = listOf(
        TestEightArena
    )

    override fun getTeamSize() = 2
    override fun getMaxTeams() = 8
}
