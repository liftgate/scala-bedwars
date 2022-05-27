package gg.scala.bedwars.game.shop.categories

import gg.scala.bedwars.game.shop.BedwarsShopCategory
import gg.scala.bedwars.game.shop.BedwarsShopCurrency
import gg.scala.bedwars.game.shop.BedwarsShopItem
import gg.scala.bedwars.shared.team.BedwarsCgsGameTeam
import gg.scala.cgs.common.teams.CgsGameTeamService
import net.evilblock.cubed.util.bukkit.ColorUtil
import net.evilblock.cubed.util.bukkit.ItemBuilder
import org.bukkit.Material
import org.bukkit.entity.Player

/**
 * @author GrowlyX
 * @since 5/27/2022
 */
object BedwarsShopBlockCategory
{
    val category = BedwarsShopCategory(
        "Blocks", 2,
        ItemBuilder
            .of(Material.BRICK)
            .build(),
        listOf(
            BedwarsShopItem(
                name = "Wool x16",
                price = BedwarsShopCurrency.IRON to 4,
                description = listOf("lmao"),
                itemCreator = {
                    val team = it.team()!!

                    ItemBuilder.of(Material.WOOL)
                        .data(
                            ColorUtil
                                .toWoolData(team.color)
                                .toShort()
                        )
                        .amount(16)
                        .build()
                }
            ),
            BedwarsShopItem(
                name = "Stained Clay x16",
                price = BedwarsShopCurrency.IRON to 12,
                description = listOf("lmao"),
                itemCreator = {
                    val team = it.team()!!

                    ItemBuilder.of(Material.STAINED_CLAY)
                        .data(
                            ColorUtil
                                .toWoolData(team.color)
                                .toShort()
                        )
                        .amount(16)
                        .build()
                }
            ),
            BedwarsShopItem(
                name = "Wood x16",
                price = BedwarsShopCurrency.GOLD to 4,
                description = listOf("lmao"),
                itemCreator = {
                    ItemBuilder.of(Material.WOOD)
                        .amount(16)
                        .build()
                }
            ),
            BedwarsShopItem(
                name = "Endstone x12",
                price = BedwarsShopCurrency.IRON to 24,
                description = listOf("lmao"),
                itemCreator = {
                    ItemBuilder.of(Material.ENDER_STONE)
                        .amount(12)
                        .build()
                }
            ),
            BedwarsShopItem(
                name = "Glass x4",
                price = BedwarsShopCurrency.IRON to 12,
                description = listOf("lmao"),
                itemCreator = {
                    val team = it.team()!!

                    ItemBuilder.of(Material.GLASS)
                        .amount(4)
                        .data(
                            ColorUtil
                                .toWoolData(team.color)
                                .toShort()
                        )
                        .build()
                }
            ),
            BedwarsShopItem(
                name = "Glass x4",
                price = BedwarsShopCurrency.IRON to 12,
                description = listOf("lmao"),
                itemCreator = {
                    val team = it.team()!!

                    ItemBuilder.of(Material.GLASS)
                        .amount(4)
                        .data(
                            ColorUtil
                                .toWoolData(team.color)
                                .toShort()
                        )
                        .build()
                }
            ),
            BedwarsShopItem(
                name = "Ladder x16",
                price = BedwarsShopCurrency.IRON to 12,
                description = listOf("lmao"),
                itemCreator = {
                    ItemBuilder.of(Material.LADDER)
                        .amount(16)
                        .build()
                }
            ),
            BedwarsShopItem(
                name = "Obsdian x4",
                price = BedwarsShopCurrency.EMERALD to 4,
                description = listOf("lmao"),
                itemCreator = {
                    ItemBuilder.of(Material.OBSIDIAN)
                        .amount(4)
                        .build()
                }
            )
        )
    )

    fun Player.team(): BedwarsCgsGameTeam?
    {
        return (CgsGameTeamService.getTeamOf(player) as BedwarsCgsGameTeam?)
    }
}
