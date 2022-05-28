package gg.scala.bedwars.game.loadout

import gg.scala.bedwars.game.shop.categories.BedwarsShopBlockCategory.team
import gg.scala.bedwars.game.upgrades.BedwarsTeamUpgrades
import gg.scala.bedwars.game.upgrades.Tracker
import net.evilblock.cubed.util.bukkit.ItemBuilder
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

/**
 * @author GrowlyX
 * @since 5/27/2022
 */
object BedwarsLoadoutService
{
    private val defaultLoadout = ItemBuilder
        .of(Material.WOOD_SWORD)
        .setUnbreakable(true)

    fun applyLoadout(
        player: Player
    )
    {
        player.inventory.addItem(
            this.affectItem(player, this.defaultLoadout.build())
        )
    }

    fun affectItem(
        player: Player,
        stack: ItemStack
    ): ItemStack
    {
        val team = player.team()!!
        val tracker = Tracker.of(team)

        if (stack.type.name.contains("SWORD"))
        {
            tracker
                .upgrades[BedwarsTeamUpgrades.SHARPNESS]
                ?: return stack

            stack.addEnchantment(
                Enchantment.DAMAGE_ALL, 1
            )

            return stack
        }

        return stack
    }
}
