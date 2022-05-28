package gg.scala.bedwars.game.armor

import com.cryptomorin.xseries.XMaterial
import gg.scala.bedwars.game.shop.categories.BedwarsShopBlockCategory.team
import gg.scala.bedwars.game.upgrades.BedwarsTeamUpgrades
import gg.scala.bedwars.game.upgrades.Tracker
import net.evilblock.cubed.util.bukkit.ColorUtil
import net.evilblock.cubed.util.bukkit.ItemBuilder
import org.bukkit.ChatColor
import org.bukkit.Color
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemFlag
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
        ChatColor.LIGHT_PURPLE to Color.FUCHSIA,
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
                it
                    .setUnbreakable(true)
                    .apply {
                        val tracker = Tracker.of(team)

                        val protection = tracker
                            .upgrades[BedwarsTeamUpgrades.PROTECTION]

                        if (protection != null)
                        {
                            enchant(
                                Enchantment.PROTECTION_ENVIRONMENTAL,
                                protection
                            )
                        }
                    }
                    .addFlags(
                        ItemFlag.HIDE_UNBREAKABLE
                    )
                    .build()
                    .apply {
                        if (itemMeta is LeatherArmorMeta)
                        {
                            val meta = (itemMeta as LeatherArmorMeta)
                            meta.color = mappings[team.color]

                            itemMeta = meta
                        }
                    }
            }
            .toTypedArray()

        player.inventory.armorContents = itemStacks
    }
}
