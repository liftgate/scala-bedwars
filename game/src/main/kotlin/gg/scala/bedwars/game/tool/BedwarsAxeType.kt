package gg.scala.bedwars.game.tool

import com.cryptomorin.xseries.XMaterial
import gg.scala.bedwars.shared.arena.BedwarsArena
import net.evilblock.cubed.util.bukkit.ItemBuilder
import org.bukkit.Material

/**
 * @author GrowlyX
 * @since 5/27/2022
 */
enum class BedwarsAxeType(
    val tool: ItemBuilder
)
{
    WOOD(
        ItemBuilder.of(Material.WOOD_PICKAXE)
    ),
    STONE(
        ItemBuilder.of(XMaterial.STONE_PICKAXE)
    ),
    IRON(
        ItemBuilder.of(XMaterial.IRON_PICKAXE)
    ),
    DIAMOND(
        ItemBuilder.of(XMaterial.DIAMOND_PICKAXE)
    );


    fun previous() : BedwarsAxeType {
        return BedwarsAxeType.values()[this.ordinal - 1]
    }

    fun next() : BedwarsAxeType {
        return BedwarsAxeType.values()[this.ordinal + 1]
    }
}