package gg.scala.bedwars.game.tool

import com.cryptomorin.xseries.XMaterial
import gg.scala.bedwars.game.shop.categories.BedwarsShopBlockCategory.team
import gg.scala.bedwars.game.upgrades.BedwarsTeamUpgrades
import gg.scala.bedwars.game.upgrades.Tracker
import net.evilblock.cubed.util.bukkit.ItemBuilder
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.meta.LeatherArmorMeta
import java.util.UUID

/**
 * @author GrowlyX
 * @since 5/27/2022
 */
object BedwarsToolService
{
    val pickaxes = mutableMapOf<UUID, BedwarsPickaxeType>()
    val axes = mutableMapOf<UUID, BedwarsAxeType>()
    val shears = mutableListOf<UUID>()

    fun updateType(
        uuid: UUID,
        enum: Enum<*>?
    ) {
        if (enum is BedwarsAxeType) updateAxe(uuid, enum)
        if (enum is BedwarsPickaxeType) updatePickaxe(uuid, enum)
        if (enum == null) updateShears(uuid)
    }

    fun updateType(
        uuid: UUID,
        type: BedwarsAxeType
    ) {
        updateAxe(uuid, type)
    }


    fun updateType(
        uuid: UUID,
        type: BedwarsPickaxeType
    ) {
        updatePickaxe(uuid, type)
    }

    fun updatePickaxe(
        uuid: UUID,
        type: BedwarsPickaxeType
    )
    {
        this.pickaxes[uuid] = type
    }

    fun updateAxe(
        uuid: UUID,
        type: BedwarsAxeType
    )
    {
        this.axes[uuid] = type
    }

    fun updateShears(
        uuid: UUID
    ) {
        this.shears.add(uuid)
    }

    fun decreaseType(uuid: UUID) {
        if (uuid in pickaxes && pickaxes[uuid] != BedwarsPickaxeType.WOOD) pickaxes[uuid] = pickaxes[uuid]!!.previous()
        if (uuid in axes && axes[uuid] != BedwarsAxeType.WOOD) axes[uuid] = axes[uuid]!!.previous()
    }

    fun applyTools(
        player: Player
    )
    {
        val uniqueId = player.uniqueId
        if (uniqueId in pickaxes) {
            val tool = pickaxes[uniqueId]!!
            player.inventory.remove(tool.previous().tool.build())

            if (!player.inventory.contains(tool.tool.build())) {
                player.inventory.addItem(tool.tool.build())
            }
        }
        if (uniqueId in axes) {
            val tool = axes[uniqueId]!!
            player.inventory.remove(tool.previous().tool.build())
            if (!player.inventory.contains(tool.tool.build())) {
                player.inventory.addItem(tool.tool.build())
            }
        }
        if (uniqueId in shears) {
            try
            {
                player.inventory.remove(Material.SHEARS)
            } catch (_: Exception) {
                // stfu
            }
            player.inventory.addItem(ItemBuilder.of(XMaterial.SHEARS).build())
        }
    }
}
