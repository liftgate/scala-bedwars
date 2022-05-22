package shared.gamemode

import gg.scala.cgs.common.information.mode.CgsGameMode
import shared.arena.BedwarsRandomArena
import net.evilblock.cubed.util.CC
import org.bukkit.Material

/**
 * @author GrowlyX
 * @since 12/3/2021
 */
object BedwarsTriosGameMode : CgsGameMode
{
    override fun getId() = "trios"
    override fun getName() = "Trios"

    override fun getMaterial() = Pair(Material.BED, 0)

    override fun getDescription() = "${CC.GRAY}A trios game of Bedwars!"

    override fun getArenas() = listOf(
        BedwarsRandomArena
    )

    override fun getTeamSize() = 3
    override fun getMaxTeams() = 8
}
