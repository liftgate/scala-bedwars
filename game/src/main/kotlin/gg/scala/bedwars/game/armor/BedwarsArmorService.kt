package gg.scala.bedwars.game.armor

import com.cryptomorin.xseries.XMaterial
import gg.scala.bedwars.game.shop.categories.BedwarsShopBlockCategory.team
import net.evilblock.cubed.util.bukkit.ColorUtil
import net.evilblock.cubed.util.bukkit.ItemBuilder
import org.bukkit.entity.Player
import java.util.UUID

/**
 * @author GrowlyX
 * @since 5/27/2022
 */
object BedwarsArmorService
{
    private val armor =
        mutableMapOf<UUID, BedwarsArmorType>()

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
                        .data(
                            ColorUtil
                                .toDyeData(team.color).
                                toShort()
                        )
                )

                add(
                    ItemBuilder(XMaterial.LEATHER_HELMET)
                        .data(
                            ColorUtil
                                .toDyeData(team.color).
                                toShort()
                        )
                )
            }
            .map {
                // TODO: 5/27/2022 add enchants bla bla bla
                it.build()
            }
            .toTypedArray()

        player.inventory.armorContents = itemStacks
    }
}
