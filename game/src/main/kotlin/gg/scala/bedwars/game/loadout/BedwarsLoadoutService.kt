package gg.scala.bedwars.game.loadout

import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

/**
 * @author GrowlyX
 * @since 5/27/2022
 */
object BedwarsLoadoutService
{
    fun applyLoadout(
        player: Player
    )
    {
        player.inventory.addItem(
            ItemStack(Material.WOOD_SWORD)
        )
    }
}
