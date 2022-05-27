package gg.scala.bedwars.game.armor

import net.evilblock.cubed.util.bukkit.ItemBuilder
import org.bukkit.Material

/**
 * @author GrowlyX
 * @since 5/27/2022
 */
enum class BedwarsArmorType(
    val contents: Array<ItemBuilder>
)
{
    LEATHER(
        contents = arrayOf(
            ItemBuilder
                .of(Material.LEATHER_BOOTS),
            ItemBuilder
                .of(Material.LEATHER_LEGGINGS)
        )
    ),
    CHAINMAIL(
        contents = arrayOf(
            ItemBuilder
                .of(Material.CHAINMAIL_BOOTS),
            ItemBuilder
                .of(Material.CHAINMAIL_LEGGINGS)
        )
    ),
    IRON(
        contents = arrayOf(
            ItemBuilder
                .of(Material.IRON_BOOTS),
            ItemBuilder
                .of(Material.IRON_LEGGINGS)
        )
    ),
    DIAMOND(
        contents = arrayOf(
            ItemBuilder
                .of(Material.DIAMOND_BOOTS),
            ItemBuilder
                .of(Material.DIAMOND_LEGGINGS)
        )
    )
}
