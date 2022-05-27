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
        "${CC.D_AQUA}Item Shop",
        "${CC.GREEN}CLICK TO OPEN!"
    ),
    location
)
{
    override fun onRightClick(player: Player)
    {
        BedwarsShopMenu().openMenu(player)
    }
}
