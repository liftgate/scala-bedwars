package gg.scala.bedwars.game.shop.contextual.providers

import gg.scala.bedwars.game.shop.BedwarsShopItem
import gg.scala.bedwars.game.shop.contextual.BedwarsShopItemContextualProvider
import org.bukkit.entity.Player

/**
 * @author GrowlyX
 * @since 5/27/2022
 */
object DefaultShopItemContextual : BedwarsShopItemContextualProvider
{
    override fun provide(
        item: BedwarsShopItem, player: Player
    )
    {
        player.inventory.addItem(
            item.itemCreator.invoke(player)
        )
    }
}
