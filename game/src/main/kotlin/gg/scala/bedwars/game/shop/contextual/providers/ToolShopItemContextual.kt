package gg.scala.bedwars.game.shop.contextual.providers

import gg.scala.bedwars.game.armor.BedwarsArmorService
import gg.scala.bedwars.game.armor.BedwarsArmorType
import gg.scala.bedwars.game.shop.BedwarsShopItem
import gg.scala.bedwars.game.shop.contextual.BedwarsShopItemContextualProvider
import gg.scala.bedwars.game.tool.BedwarsAxeType
import gg.scala.bedwars.game.tool.BedwarsPickaxeType
import gg.scala.bedwars.game.tool.BedwarsToolService
import net.evilblock.cubed.util.CC
import org.bukkit.Material
import org.bukkit.entity.Player

/**
 * @author GrowlyX
 * @since 5/27/2022
 */
object ToolShopItemContextual : BedwarsShopItemContextualProvider
{
    private val mappings = mapOf(
        "Wooden Pickaxe" to BedwarsPickaxeType.WOOD,
        "Iron Pickaxe" to BedwarsPickaxeType.IRON,
        "Gold Pickaxe" to BedwarsPickaxeType.GOLD,
        "Diamond Pickaxe" to BedwarsPickaxeType.DIAMOND,

        "Wooden Axe" to BedwarsAxeType.WOOD,
        "Stone Axe" to BedwarsAxeType.STONE,
        "Iron Axe" to BedwarsAxeType.IRON,
        "Diamond Axe" to BedwarsAxeType.DIAMOND,

        "Shears" to null
    )

    override fun provide(
        item: BedwarsShopItem, player: Player
    )
    {
        val mapping = mappings[item.name]

        BedwarsToolService.updateType(
            player.uniqueId, mapping
        )

        BedwarsToolService.applyTools(player)
    }

    override fun allowed(
        player: Player, item: BedwarsShopItem
    ): Boolean
    {
        return when (val tool = mappings[item.name]) {
            is BedwarsPickaxeType -> {
                val pickaxe = BedwarsToolService.pickaxes[player.uniqueId] ?: return true
                return pickaxe.ordinal < tool.ordinal && pickaxe.next() == tool
            }

            is BedwarsAxeType -> {
                val axe = BedwarsToolService.axes[player.uniqueId] ?: return true
                return axe.ordinal < tool.ordinal && axe.next() == tool
            }

            else -> return player.uniqueId !in BedwarsToolService.shears
        }
    }
}
