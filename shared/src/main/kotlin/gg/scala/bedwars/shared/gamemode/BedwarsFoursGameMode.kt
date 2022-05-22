package gg.scala.bedwars.shared.gamemode

import gg.scala.cgs.common.information.mode.CgsGameMode
import gg.scala.bedwars.shared.arena.BedwarsRandomArena
import net.evilblock.cubed.util.CC
import org.bukkit.Material

/**
 * @author GrowlyX
 * @since 12/3/2021
 */
object BedwarsFoursGameMode : CgsGameMode
{
    override fun getId() = "fours"
    override fun getName() = "fours"

    override fun getMaterial() = Pair(Material.BED, 0)

    override fun getDescription() = "${CC.GRAY}A fours game of Bedwars!"

    override fun getArenas() = listOf(
        BedwarsRandomArena
    )

    override fun getTeamSize() = 4
    override fun getMaxTeams() = 4
}
