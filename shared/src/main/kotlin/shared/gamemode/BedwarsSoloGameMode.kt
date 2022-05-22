package shared.gamemode

import gg.scala.cgs.common.information.mode.CgsGameMode
import shared.arena.BedwarsRandomArena
import net.evilblock.cubed.util.CC
import org.bukkit.Material

/**
 * @author GrowlyX
 * @since 12/3/2021
 */
object BedwarsSoloGameMode : CgsGameMode
{
    override fun getId() = "solo"
    override fun getName() = "Solo"

    override fun getMaterial() = Pair(Material.GOLDEN_APPLE, 0)

    override fun getDescription() = "${CC.GRAY}A solo game of UHC Meetup!"

    override fun getArenas() = listOf(
        BedwarsRandomArena
    )

    override fun getTeamSize() = 1 // team size
    override fun getMaxTeams() = 32 // max teams
}
