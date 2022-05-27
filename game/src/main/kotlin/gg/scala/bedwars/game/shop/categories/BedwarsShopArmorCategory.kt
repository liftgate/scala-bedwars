package gg.scala.bedwars.game.shop.categories

import gg.scala.bedwars.game.shop.BedwarsShopCategory
import gg.scala.bedwars.game.shop.BedwarsShopCurrency
import gg.scala.bedwars.game.shop.BedwarsShopItem
import gg.scala.bedwars.game.shop.contextual.providers.ArmorShopItemContextual
import net.evilblock.cubed.util.bukkit.ItemBuilder
import org.bukkit.Material

/**
 * @author GrowlyX
 * @since 5/27/2022
 */
object BedwarsShopArmorCategory
{
    val category = BedwarsShopCategory(
        "Armor", 2,
        ItemBuilder
            .of(Material.GOLD_CHESTPLATE)
            .build(),
        listOf(
            BedwarsShopItem(
                name = "Permanent Chainmail Armor",
                price = BedwarsShopCurrency.IRON to 40,
                description = listOf("lmao"),
                itemCreator = {
                    ItemBuilder.of(Material.CHAINMAIL_CHESTPLATE).build()
                },
                contextualProvider = ArmorShopItemContextual
            ),
            BedwarsShopItem(
                name = "Permanent Iron Armor",
                price = BedwarsShopCurrency.GOLD to 12,
                description = listOf("lmao"),
                itemCreator = {
                    ItemBuilder.of(Material.IRON_CHESTPLATE).build()
                },
                contextualProvider = ArmorShopItemContextual
            ),
            BedwarsShopItem(
                name = "Permanent Diamond Armor",
                price = BedwarsShopCurrency.EMERALD to 6,
                description = listOf("lmao"),
                itemCreator = {
                    ItemBuilder.of(Material.DIAMOND_CHESTPLATE).build()
                },
                contextualProvider = ArmorShopItemContextual
            )
        )
    )
}
