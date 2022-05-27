package gg.scala.bedwars.game.shop

import org.bukkit.inventory.ItemStack

/**
 * @author GrowlyX
 * @since 5/27/2022
 */
data class BedwarsShopCategory(
    val name: String,
    val position: Int,
    val icon: ItemStack,
    val items: List<BedwarsShopItem>
)
