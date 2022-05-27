package gg.scala.bedwars.game.shop

import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

/**
 * @author GrowlyX
 * @since 5/27/2022
 */
data class BedwarsShopItem(
    val name: String,
    val price: Pair<BedwarsShopCurrency, Int>,
    val description: List<String>,
    val itemCreator: (Player) -> ItemStack
)
