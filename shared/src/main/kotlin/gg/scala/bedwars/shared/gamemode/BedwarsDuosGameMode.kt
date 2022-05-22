package gg.scala.bedwars.shared.gamemode

import gg.scala.cgs.common.information.mode.CgsGameMode
import gg.scala.bedwars.shared.arena.BedwarsRandomArena
import net.evilblock.cubed.util.CC
import org.bukkit.Material

/**
 * @author GrowlyX
 * @since 12/3/2021
 */
object BedwarsDuosGameMode : CgsGameMode
{
    override fun getId() = "duos"
    override fun getName() = "Duos"

    override fun getMaterial() = Pair(Material.GOLDEN_APPLE, 0)

    override fun getDescription() = "${CC.GRAY}A duos game of UHC Meetup!"

    override fun getArenas() = listOf(
        BedwarsRandomArena
    )

    override fun getTeamSize() = 2
    override fun getMaxTeams() = 16
}
