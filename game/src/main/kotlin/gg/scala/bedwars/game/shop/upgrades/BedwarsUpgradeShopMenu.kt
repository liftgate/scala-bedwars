package gg.scala.bedwars.game.shop.upgrades

import net.evilblock.cubed.menu.Button
import net.evilblock.cubed.menu.Menu
import org.bukkit.entity.Player

class BedwarsUpgradeShopMenu : Menu("Upgrade Shop")
{
    override fun getButtons(player: Player): Map<Int, Button>
    {
        val buttons = mutableMapOf<Int, Button>()

        return buttons
    }
}