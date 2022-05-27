package gg.scala.bedwars.game.shop.upgrades

import gg.scala.bedwars.game.shop.BedwarsShopCurrency
import gg.scala.bedwars.game.shop.BedwarsShopItem
import gg.scala.bedwars.game.shop.categories.BedwarsShopBlockCategory.team
import gg.scala.bedwars.game.shop.contextual.BedwarsShopItemContextualProvider
import gg.scala.bedwars.shared.team.BedwarsCgsGameTeamUpgrade
import gg.scala.cgs.common.information.arena.CgsGameArenaHandler
import net.evilblock.cubed.menu.Button
import net.evilblock.cubed.menu.Menu
import net.evilblock.cubed.util.CC
import net.evilblock.cubed.util.bukkit.ItemBuilder
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemFlag

class BedwarsUpgradeShopMenu : Menu("Upgrade Shop")
{
    val arenaType = if (CgsGameArenaHandler.arena.getId().contains("eight")) "eight" else "four"

    override fun getButtons(player: Player): Map<Int, Button>
    {
        var index = 0
        val buttons = mutableMapOf<Int, Button>()

        val team = player.team() ?: return emptyMap()

        BedwarsCgsGameTeamUpgrade.values().forEach {
            val cost = it.costs[arenaType]!![team.getNextLevel(it)] ?: 999//if its 999 something ufcked up lMAO

            val item = BedwarsShopItem(
                it.names[team.getNextLevel(it)]!!,
                BedwarsShopCurrency.DIAMOND to cost,
                listOf("cock"),
                { player ->
                    ItemBuilder
                        .of(it.item)
                        .build()
                },
                object : BedwarsShopItemContextualProvider {
                    override fun provide(item: BedwarsShopItem, player: Player)
                    {
                        team.upgrades[it] = team.upgrades[it] ?: 1
                    }
                }
            )

            buttons[index++] = ItemBuilder
                .copyOf(item.itemCreator(player))
                .name("${CC.YELLOW}${item.name}")
                .addFlags(
                    ItemFlag.HIDE_ATTRIBUTES
                )
                .setLore(
                    mutableListOf<String>()
                        .apply {
                            add(
                                "${CC.GRAY}Price: ${
                                    item.price.first.color
                                }${
                                    item.price.second
                                } ${
                                    if (item.price.second == 1)
                                    {
                                        item.price.first.displaySingular
                                    } else
                                    {
                                        item.price.first.displayPlural
                                    }
                                }"
                            )
                            add("")
                            add("${CC.GREEN}Click to purchase.")
                        }
                )
                .toButton { player, _ ->
                    val amount = player!!
                        .inventory.contents
                        .filterNotNull()
                        .filter {
                            it.type == item.price.first.material
                        }
                        .sumOf {
                            it.amount
                        }

                    if (amount < item.price.second)
                    {
                        player.sendMessage(
                            "${CC.RED}You need ${CC.BOLD}${item.price.second - amount}${CC.RED} more ${
                                if (item.price.second - amount == 1)
                                {
                                    item.price.first.displaySingular.lowercase()
                                } else
                                {
                                    item.price.first.displayPlural.lowercase()
                                }
                            } to purchase this item!"
                        )
                        return@toButton
                    }

                    if (!item.contextualProvider.allowed(player, item))
                    {
                        return@toButton
                    }

                    player.sendMessage(
                        "${CC.GREEN}You purchased ${CC.SEC}${item.name}${CC.GREEN}!"
                    )

                    item.price.first.removeFrom
                        .invoke(player, item.price.second)

                    team.upgrades[it] = team.upgrades[it] ?: 1
                    item.contextualProvider.provide(item, player)
                    it.context.invoke(team)
                }
        }

        return buttons
    }
}