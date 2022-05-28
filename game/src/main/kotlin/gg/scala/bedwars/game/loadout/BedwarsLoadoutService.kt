package gg.scala.bedwars.game.loadout

import net.evilblock.cubed.util.bukkit.ItemBuilder
import org.bukkit.Material
import org.bukkit.entity.Player

/**
 * @author GrowlyX
 * @since 5/27/2022
 */
object BedwarsLoadoutService
{
    val defaultLoadout = ItemBuilder
        .of(Material.WOOD_SWORD)
        .setUnbreakable(true)
        .build()

    fun applyLoadout(
        player: Player
    )
    {
        player.inventory.addItem(
            this.defaultLoadout
        )
    }
}
