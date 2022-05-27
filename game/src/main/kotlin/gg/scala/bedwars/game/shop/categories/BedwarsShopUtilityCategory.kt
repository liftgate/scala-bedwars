package gg.scala.bedwars.game.shop.categories

import gg.scala.bedwars.game.shop.BedwarsShopCategory
import gg.scala.bedwars.game.shop.BedwarsShopCurrency
import gg.scala.bedwars.game.shop.BedwarsShopItem
import gg.scala.bedwars.game.shop.contextual.providers.ArmorShopItemContextual
import gg.scala.cgs.common.CgsGameEngine
import net.evilblock.cubed.util.bukkit.ItemBuilder
import org.bukkit.Material

/**
 * @author GrowlyX
 * @since 5/27/2022
 */
object BedwarsShopUtilityCategory
{
    private val mode = CgsGameEngine
        .INSTANCE.gameMode

    val category = BedwarsShopCategory(
        "Utility", 5,
        ItemBuilder
            .of(Material.TNT)
            .build(),
        listOf(
            BedwarsShopItem(
                name = "Golden Apple",
                price = BedwarsShopCurrency.GOLD to 3,
                description = listOf("lmao"),
                itemCreator = {
                    ItemBuilder
                        .of(Material.GOLDEN_APPLE)
                        .build()
                }
            ),
            BedwarsShopItem(
                name = "Bedbug",
                price = BedwarsShopCurrency.IRON to 40,
                description = listOf("lmao"),
                itemCreator = {
                    ItemBuilder
                        .of(Material.MOB_SPAWNER)
                        .build()
                }
            ),
            BedwarsShopItem(
                name = "Dream Defender",
                price = BedwarsShopCurrency.IRON to 120,
                description = listOf("lmao"),
                itemCreator = {
                    ItemBuilder
                        .of(Material.MONSTER_EGG)
                        .build()
                }
            ),
            BedwarsShopItem(
                name = "Fireball",
                price = BedwarsShopCurrency.IRON to 40,
                description = listOf("lmao"),
                itemCreator = {
                    ItemBuilder
                        .of(Material.FIREBALL)
                        .build()
                }
            ),
            BedwarsShopItem(
                name = "TNT",
                price = BedwarsShopCurrency.GOLD to (if (isDuosOrSolos()) 4 else if (isTriosOrQuads()) 8 else 4),
                description = listOf("lmao"),
                itemCreator = {
                    ItemBuilder
                        .of(Material.MONSTER_EGG)
                        .build()
                }
            ),
            BedwarsShopItem(
                name = "Ender Pearl",
                price = BedwarsShopCurrency.EMERALD to 4,
                description = listOf("lmao"),
                itemCreator = {
                    ItemBuilder
                        .of(Material.ENDER_PEARL)
                        .build()
                }
            ),
            BedwarsShopItem(
                name = "Water Bucket",
                price = BedwarsShopCurrency.GOLD to (if (isDuosOrSolos()) 3 else if (isTriosOrQuads()) 6 else 3),
                description = listOf("lmao"),
                itemCreator = {
                    ItemBuilder
                        .of(Material.WATER_BUCKET)
                        .build()
                }
            ),
            BedwarsShopItem(
                name = "Bridge Eggs",
                price = BedwarsShopCurrency.EMERALD to (if (isDuosOrSolos()) 2 else if (isTriosOrQuads()) 1 else 2),
                description = listOf("lmao"),
                itemCreator = {
                    ItemBuilder
                        .of(Material.EGG)
                        .build()
                }
            ),
            BedwarsShopItem(
                name = "Sponge x4",
                price = BedwarsShopCurrency.GOLD to (if (isDuosOrSolos()) 4 else if (isTriosOrQuads()) 6 else 4),
                description = listOf("lmao"),
                itemCreator = {
                    ItemBuilder
                        .of(Material.SPONGE)
                        .amount(4)
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
