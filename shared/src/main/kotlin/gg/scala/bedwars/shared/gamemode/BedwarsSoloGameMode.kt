package gg.scala.bedwars.shared.gamemode

import gg.scala.cgs.common.information.mode.CgsGameMode
import gg.scala.bedwars.shared.arena.BedwarsRandomArena
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

    override fun getMaterial() = Pair(Material.BED, 0)

    override fun getDescription() = "${CC.GRAY}A solo game of Bedwars!"

    override fun getArenas() = listOf(
        BedwarsRandomArena
    )

    override fun getTeamSize() = 1 // team size
    override fun getMaxTeams() = 8 // max teams
}
