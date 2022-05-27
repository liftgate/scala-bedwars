package gg.scala.bedwars.game.shop.contextual.providers

import gg.scala.bedwars.game.armor.BedwarsArmorService
import gg.scala.bedwars.game.armor.BedwarsArmorType
import gg.scala.bedwars.game.shop.BedwarsShopItem
import gg.scala.bedwars.game.shop.contextual.BedwarsShopItemContextualProvider
import org.bukkit.entity.Player

/**
 * @author GrowlyX
 * @since 5/27/2022
 */
object ArmorShopItemContextual : BedwarsShopItemContextualProvider
{
    private val mappings = mapOf(
        "Permanent Chainmail Armor" to BedwarsArmorType.CHAINMAIL,
        "Permanent Iron Armor" to BedwarsArmorType.IRON,
        "Permanent Diamond Armor" to BedwarsArmorType.DIAMOND
    )

    override fun provide(
        item: BedwarsShopItem, player: Player
    )
    {
        val mapping = mappings[item.name]!!

        BedwarsArmorService.updateType(
            player.uniqueId, mapping
        )

        BedwarsArmorService.applyArmor(player)
    }
}
