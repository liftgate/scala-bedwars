package gg.scala.bedwars.game.shop

import gg.scala.bedwars.game.shop.categories.BedwarsShopCategories
import net.evilblock.cubed.menu.Button
import net.evilblock.cubed.menu.pagination.PaginatedMenu
import net.evilblock.cubed.util.CC
import net.evilblock.cubed.util.bukkit.ItemBuilder
import org.bukkit.entity.Player

/**
 * @author GrowlyX
 * @since 5/27/2022
 */
class BedwarsShopMenu : PaginatedMenu()
{
    private var current =
        BedwarsShopCategories.categories.first()

    init
    {
        updateAfterClick = true
        placeholdBorders = true
    }

    override fun size(buttons: Map<Int, Button>) = 36

    override fun getAllPagesButtonSlots() =
        listOf(
            10, 11, 12, 13, 14, 15, 16,
            19, 20, 21, 22, 23, 24, 25
        )

    override fun getAllPagesButtons(player: Player) =
        mutableMapOf<Int, Button>()
            .apply {
                for (item in current.items)
                {
                    val invoked = item.itemCreator
                        .invoke(player)

                    this[size] = ItemBuilder
                        .copyOf(invoked)
                        .name("${CC.D_AQUA}${item.name}")
                        .setLore(
                            mutableListOf<String>()
                                .apply {
                                    addAll(item.description)
                                    add("")
                                    add("${CC.GRAY}Price: ${
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
                                    }")
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
                                player.sendMessage("${CC.RED}You need ${CC.BOLD}${item.price.second - amount}${CC.RED} more ${
                                    if (item.price.second - amount == 1)
                                    {
                                        item.price.first.displaySingular.lowercase()
                                    } else
                                    {
                                        item.price.first.displayPlural.lowercase()
                                    }
                                } to purchase this item!")
                                return@toButton
                            }

                            player.inventory.addItem(invoked)
                            player.sendMessage("${CC.GREEN}You purchased ${CC.SEC}${item.name}${CC.GREEN}!")

                            item.price.first.removeFrom
                                .invoke(player, item.price.second)
                        }
                }
            }

    override fun getGlobalButtons(player: Player) =
        mutableMapOf<Int, Button>()
            .apply {
                for (category in BedwarsShopCategories.categories)
                {
                    this[category.position] = ItemBuilder
                        .copyOf(category.icon)
                        .name("${CC.D_AQUA}${category.name}")
                        .addToLore(
                            "${CC.WHITE}View all somethings.",
                            "",
                            "${CC.GREEN}Click to view."
                        )
                        .toButton { _, _ ->
                            current = category
                        }
                }
            }

    override fun getPrePaginatedTitle(player: Player) = "Item Shop"
}
