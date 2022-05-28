package gg.scala.bedwars.game.shop.categories

import gg.scala.bedwars.game.loadout.BedwarsLoadoutService
import gg.scala.bedwars.game.shop.BedwarsShopCategory
import gg.scala.bedwars.game.shop.BedwarsShopCurrency
import gg.scala.bedwars.game.shop.BedwarsShopItem
import gg.scala.bedwars.game.shop.contextual.providers.ArmorShopItemContextual
import gg.scala.bedwars.game.shop.contextual.providers.MeleeShopItemContextual
import gg.scala.bedwars.game.shop.contextual.providers.ToolShopItemContextual
import gg.scala.cgs.common.CgsGameEngine
import net.evilblock.cubed.util.bukkit.ItemBuilder
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment

/**
 * @author GrowlyX
 * @since 5/27/2022
 */
object BedwarsShopToolsCategory
{
    val category = BedwarsShopCategory(
        "Tools", 7,
        ItemBuilder
            .of(Material.DIAMOND_PICKAXE)
            .build(),
        listOf(
            BedwarsShopItem(
                name = "Wooden Pickaxe",
                price = BedwarsShopCurrency.IRON to 10,
                description = listOf("lmao"),
                itemCreator = {
                    ItemBuilder
                        .of(Material.WOOD_PICKAXE)
                        .setUnbreakable(true)
                        .build()
                },
                contextualProvider = ToolShopItemContextual
            ),
            BedwarsShopItem(
                name = "Iron Pickaxe",
                price = BedwarsShopCurrency.IRON to 10,
                description = listOf("lmao"),
                itemCreator = {
                    ItemBuilder
                        .of(Material.IRON_PICKAXE)
                        .setUnbreakable(true)
                        .build()
                },
                contextualProvider = ToolShopItemContextual
            ),
            BedwarsShopItem(
                name = "Gold Pickaxe",
                price = BedwarsShopCurrency.GOLD to 3,
                description = listOf("lmao"),
                itemCreator = {
                    ItemBuilder
                        .of(Material.GOLD_PICKAXE)
                        .setUnbreakable(true)
                        .build()
                },
                contextualProvider = ToolShopItemContextual
            ),
            BedwarsShopItem(
                name = "Diamond Pickaxe",
                price = BedwarsShopCurrency.GOLD to 6,
                description = listOf("lmao"),
                itemCreator = {
                    ItemBuilder
                        .of(Material.DIAMOND_PICKAXE)
                        .setUnbreakable(true)
                        .build()
                },
                contextualProvider = ToolShopItemContextual
            ),
            BedwarsShopItem(
                name = "Wooden Axe",
                price = BedwarsShopCurrency.IRON to 10,
                description = listOf("lmao"),
                itemCreator = {
                    ItemBuilder
                        .of(Material.WOOD_AXE)
                        .setUnbreakable(true)
                        .build()
                },
                contextualProvider = ToolShopItemContextual
            ),
            BedwarsShopItem(
                name = "Stone Axe",
                price = BedwarsShopCurrency.IRON to 10,
                description = listOf("lmao"),
                itemCreator = {
                    ItemBuilder
                        .of(Material.STONE_AXE)
                        .setUnbreakable(true)
                        .build()
                },
                contextualProvider = ToolShopItemContextual
            ),
            BedwarsShopItem(
                name = "Iron Axe",
                price = BedwarsShopCurrency.GOLD to 3,
                description = listOf("lmao"),
                itemCreator = {
                    ItemBuilder
                        .of(Material.IRON_AXE)
                        .setUnbreakable(true)
                        .build()
                },
                contextualProvider = ToolShopItemContextual
            ),
            BedwarsShopItem(
                name = "Diamond Axe",
                price = BedwarsShopCurrency.GOLD to 6,
                description = listOf("lmao"),
                itemCreator = {
                    ItemBuilder
                        .of(Material.DIAMOND_AXE)
                        .setUnbreakable(true)
                        .build()
                },
                contextualProvider = ToolShopItemContextual
            ),
            BedwarsShopItem(
                name = "Shears",
                price = BedwarsShopCurrency.IRON to 20,
                description = listOf("lmao"),
                itemCreator = {
                    ItemBuilder
                        .of(Material.SHEARS)
                        .setUnbreakable(true)
                        .build()
                },
                contextualProvider = ToolShopItemContextual
            ),
        )
    )
}
