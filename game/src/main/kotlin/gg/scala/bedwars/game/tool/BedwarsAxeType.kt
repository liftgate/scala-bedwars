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
        ItemBuilder.of(Material.WOOD_AXE)
    ),
    STONE(
        ItemBuilder.of(XMaterial.STONE_AXE)
    ),
    IRON(
        ItemBuilder.of(XMaterial.IRON_AXE)
    ),
    DIAMOND(
        ItemBuilder.of(XMaterial.DIAMOND_AXE)
    );


    fun previous() : BedwarsAxeType? {
        if (this == WOOD) return null
        return values()[this.ordinal - 1]
    }

    fun next() : BedwarsAxeType? {
        if (this == DIAMOND) return null
        return values()[this.ordinal + 1]
    }
}