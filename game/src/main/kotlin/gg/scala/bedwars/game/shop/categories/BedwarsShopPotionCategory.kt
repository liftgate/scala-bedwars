package gg.scala.bedwars.game.shop.categories

import gg.scala.bedwars.game.shop.BedwarsShopCategory
import gg.scala.bedwars.game.shop.BedwarsShopCurrency
import gg.scala.bedwars.game.shop.BedwarsShopItem
import net.evilblock.cubed.util.bukkit.ItemBuilder
import org.bukkit.Material

/**
 * @author GrowlyX
 * @since 5/27/2022
 */
object BedwarsShopPotionCategory
{
    val category = BedwarsShopCategory(
        "Potions", 4,
        ItemBuilder
            .of(Material.POTION)
            .data(16421)
            .build(),
        listOf(
            BedwarsShopItem(
                name = "Speed Potion",
                price = BedwarsShopCurrency.EMERALD to 1,
                description = listOf("lmao"),
                itemCreator = {
                    ItemBuilder
                        .of(Material.POTION)
                        .build()
                }
            )
        )
    )
}
