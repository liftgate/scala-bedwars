package gg.scala.bedwars.game.tool

import com.cryptomorin.xseries.XMaterial
import net.evilblock.cubed.util.bukkit.ItemBuilder
import org.bukkit.Material

/**
 * @author GrowlyX
 * @since 5/27/2022
 */
enum class BedwarsPickaxeType(
    val tool: ItemBuilder
)
{
    WOOD(
        ItemBuilder.of(XMaterial.WOODEN_PICKAXE)
    ),
    IRON(
        ItemBuilder.of(XMaterial.IRON_PICKAXE)
    ),
    GOLD(
        ItemBuilder.of(XMaterial.GOLDEN_PICKAXE)
    ),
    DIAMOND(
        ItemBuilder.of(XMaterial.DIAMOND_PICKAXE)
    );

    fun previous() : BedwarsPickaxeType? {
        if (ordinal == WOOD.ordinal) return null
        return values()[this.ordinal - 1]
    }

    fun next() : BedwarsPickaxeType? {
        if (ordinal == DIAMOND.ordinal) return null
        return values()[this.ordinal + 1]
    }
}
