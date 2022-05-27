package gg.scala.bedwars.game.shop.npc

import gg.scala.bedwars.game.shop.BedwarsShopMenu
import net.evilblock.cubed.entity.npc.NpcEntity
import net.evilblock.cubed.util.CC
import org.bukkit.Location
import org.bukkit.entity.Player

/**
 * @author GrowlyX
 * @since 5/27/2022
 */
class BedwarsShopNpcEntity(
    location: Location
) : NpcEntity(
    listOf(
        "${CC.B_AQUA}Item Shop",
        "${CC.YELLOW}Click to open!"
    ),
    location.add(0.5, 0.0, 0.5)
)
{
    override fun onRightClick(player: Player)
    {
        BedwarsShopMenu().openMenu(player)
    }
}
