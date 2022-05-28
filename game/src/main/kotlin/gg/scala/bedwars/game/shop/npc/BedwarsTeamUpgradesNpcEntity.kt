package gg.scala.bedwars.game.shop.npc

import gg.scala.bedwars.game.upgrades.BedwarsTeamUpgradesMenu
import net.evilblock.cubed.entity.npc.NpcEntity
import net.evilblock.cubed.util.CC
import org.bukkit.Location
import org.bukkit.entity.Player

/**
 * @author GrowlyX
 * @since 5/27/2022
 */
class BedwarsTeamUpgradesNpcEntity(
    location: Location
) : NpcEntity(
    listOf(
        "${CC.B_AQUA}Team Upgrades",
        "${CC.YELLOW}Click to open!"
    ),
    location.add(0.5, 0.0, 0.5)
)
{
    override fun onRightClick(player: Player)
    {
        if (player.hasMetadata("spectator")) return
        BedwarsTeamUpgradesMenu().openMenu(player)
    }
}
