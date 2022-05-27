package gg.scala.bedwars.game.shop

import net.evilblock.cubed.util.bukkit.ItemBuilder
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player

/**
 * @author GrowlyX
 * @since 5/27/2022
 */
enum class BedwarsShopCurrency(
    val displaySingular: String,
    val displayPlural: String,
    val color: ChatColor,
    val removeFrom: (Player, Int) -> Unit
)
{
    EMERALD(
        displaySingular = "Emerald",
        displayPlural = "Emeralds",
        color = ChatColor.DARK_GREEN,
        removeFrom = { player, amount ->
            player.inventory.removeItem(
                ItemBuilder.of(Material.EMERALD)
                    .amount(amount)
                    .build()
            )
        }
    ),

    DIAMOND(
        displaySingular = "Diamond",
        displayPlural = "Diamonds",
        color = ChatColor.AQUA,
        removeFrom = { player, amount ->
            player.inventory.removeItem(
                ItemBuilder.of(Material.DIAMOND)
                    .amount(amount)
                    .build()
            )
        }
    ),

    IRON(
        displaySingular = "Iron",
        displayPlural = "Iron",
        color = ChatColor.WHITE,
        removeFrom = { player, amount ->
            player.inventory.removeItem(
                ItemBuilder.of(Material.IRON_INGOT)
                    .amount(amount)
                    .build()
            )
        }
    ),

    GOLD(
        displaySingular = "Gold",
        displayPlural = "Gold",
        color = ChatColor.GOLD,
        removeFrom = { player, amount ->
            player.inventory.removeItem(
                ItemBuilder.of(Material.GOLD_INGOT)
                    .amount(amount)
                    .build()
            )
        }
    )
}
