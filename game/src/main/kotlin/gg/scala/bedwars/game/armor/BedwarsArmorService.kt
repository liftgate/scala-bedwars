package gg.scala.bedwars.game.armor

import com.cryptomorin.xseries.XMaterial
import gg.scala.bedwars.game.shop.categories.BedwarsShopBlockCategory.team
import net.evilblock.cubed.util.bukkit.ColorUtil
import net.evilblock.cubed.util.bukkit.ItemBuilder
import org.bukkit.ChatColor
import org.bukkit.Color
import org.bukkit.entity.Player
import org.bukkit.inventory.meta.LeatherArmorMeta
import java.util.UUID

/**
 * @author GrowlyX
 * @since 5/27/2022
 */
object BedwarsArmorService
{
    val armor =
        mutableMapOf<UUID, BedwarsArmorType>()

    private val mappings = mapOf(
        ChatColor.RED to Color.RED,
        ChatColor.BLUE to Color.BLUE,
        ChatColor.GREEN to Color.LIME,
        ChatColor.YELLOW to Color.YELLOW,
        ChatColor.AQUA to Color.AQUA,
        ChatColor.WHITE to Color.WHITE,
        ChatColor.LIGHT_PURPLE to Color
            .fromRGB(255, 105, 180),
        ChatColor.GRAY to Color.GRAY
    )

    fun updateType(
        uuid: UUID,
        type: BedwarsArmorType
    )
    {
        this.armor[uuid] = type
    }

    fun applyArmor(
        player: Player
    )
    {
        val armor = this
            .armor[player.uniqueId]
            ?: BedwarsArmorType.LEATHER

        val team = player.team()
            ?: return

        val itemStacks = armor
            .contents.toMutableList()
            .apply {
                add(
                    ItemBuilder(XMaterial.LEATHER_CHESTPLATE)
                )

                add(
                    ItemBuilder(XMaterial.LEATHER_HELMET)
                )
            }
            .map {
                // TODO: 5/27/2022 add enchants bla bla bla
                it
                    .setUnbreakable(true)
                    .build()
                    .apply {
                        if (itemMeta is LeatherArmorMeta)
                        {
                            (itemMeta as LeatherArmorMeta).color =
                                mappings[team.color]
                        }
                    }
            }
            .toTypedArray()

        player.inventory.armorContents = itemStacks
    }
}
