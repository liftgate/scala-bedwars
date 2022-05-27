package gg.scala.bedwars.game.shop.categories

import gg.scala.bedwars.game.shop.BedwarsShopCategory
import gg.scala.bedwars.game.shop.BedwarsShopCurrency
import gg.scala.bedwars.game.shop.BedwarsShopItem
import gg.scala.bedwars.game.shop.categories.BedwarsShopBlockCategory.team
import net.evilblock.cubed.util.bukkit.ColorUtil
import net.evilblock.cubed.util.bukkit.ItemBuilder
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment

/**
 * @author GrowlyX
 * @since 5/27/2022
 */
object BedwarsShopRangedCategory
{
    val category = BedwarsShopCategory(
        "Ranged", 4,
        ItemBuilder
            .of(Material.BOW)
            .build(),
        listOf(
            BedwarsShopItem(
                name = "Arrow x8",
                price = BedwarsShopCurrency.GOLD to 2,
                description = listOf("lmao"),
                itemCreator = {
                    ItemBuilder.of(Material.ARROW)
                        .amount(8)
                        .build()
                }
            ),
            BedwarsShopItem(
                name = "Bow",
                price = BedwarsShopCurrency.GOLD to 12,
                description = listOf("lmao"),
                itemCreator = {
                    ItemBuilder
                        .of(Material.BOW)
                        .build()
                }
            ),
            BedwarsShopItem(
                name = "Bow (Power I)",
                price = BedwarsShopCurrency.GOLD to 24,
                description = listOf("lmao"),
                itemCreator = {
                    ItemBuilder
                        .of(Material.BOW)
                        .enchant(
                            Enchantment.ARROW_DAMAGE, 1
                        )
                        .build()
                }
            ),
            BedwarsShopItem(
                name = "Bow (Power I, Punch I)",
                price = BedwarsShopCurrency.EMERALD to 6,
                description = listOf("lmao"),
                itemCreator = {
                    ItemBuilder
                        .of(Material.BOW)
                        .enchant(
                            Enchantment.ARROW_DAMAGE, 1
                        )
                        .enchant(
                            Enchantment.ARROW_KNOCKBACK, 1
                        )
                        .build()
                }
            )
        )
    )
}
