package gg.scala.bedwars.game.shop.categories

import gg.scala.bedwars.game.shop.BedwarsShopCategory
import gg.scala.bedwars.game.shop.BedwarsShopCurrency
import gg.scala.bedwars.game.shop.BedwarsShopItem
import gg.scala.bedwars.game.shop.contextual.providers.ArmorShopItemContextual
import gg.scala.bedwars.game.shop.contextual.providers.MeleeShopItemContextual
import gg.scala.cgs.common.CgsGameEngine
import net.evilblock.cubed.util.bukkit.ItemBuilder
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment

/**
 * @author GrowlyX
 * @since 5/27/2022
 */
object BedwarsShopMeleeCategory
{
    private val mode = CgsGameEngine
        .INSTANCE.gameMode

    val category = BedwarsShopCategory(
        "Melee", 1,
        ItemBuilder
            .of(Material.DIAMOND_SWORD)
            .build(),
        listOf(
            BedwarsShopItem(
                name = "Stone Sword",
                price = BedwarsShopCurrency.IRON to 10,
                description = listOf("lmao"),
                itemCreator = {
                    ItemBuilder
                        .of(Material.STONE_SWORD)
                        .build()
                },
                contextualProvider = MeleeShopItemContextual
            ),
            BedwarsShopItem(
                name = "Iron Sword",
                price = BedwarsShopCurrency.GOLD to 7,
                description = listOf("lmao"),
                itemCreator = {
                    ItemBuilder
                        .of(Material.IRON_SWORD)
                        .build()
                },
                contextualProvider = MeleeShopItemContextual
            ),
            BedwarsShopItem(
                name = "Diamond Sword",
                price = BedwarsShopCurrency.EMERALD to (if (isDuosOrSolos()) 4 else if (isTriosOrQuads()) 3 else 3),
                description = listOf("lmao"),
                itemCreator = {
                    ItemBuilder
                        .of(Material.DIAMOND_SWORD)
                        .build()
                },
                contextualProvider = MeleeShopItemContextual
            ),
            BedwarsShopItem(
                name = "Knockback Fish",
                price = BedwarsShopCurrency.EMERALD to 5,
                description = listOf("lmao"),
                itemCreator = {
                    ItemBuilder
                        .of(Material.RAW_FISH)
                        .enchant(
                            Enchantment.KNOCKBACK, 1
                        )
                        .build()
                }
            )
        )
    )

    private fun isDuosOrSolos(): Boolean
    {
        return this.mode.isSoloGame() || this.mode.getTeamSize() == 2
    }

    private fun isTriosOrQuads(): Boolean
    {
        return this.mode.getTeamSize() == 4 || this.mode.getTeamSize() == 3
    }
}
