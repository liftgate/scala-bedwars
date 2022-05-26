package gg.scala.bedwars.game.generator.impl

import com.cryptomorin.xseries.XMaterial
import gg.scala.bedwars.game.generator.BedwarsProminentItemGenerator
import net.evilblock.cubed.util.bukkit.ItemBuilder
import org.bukkit.Location
import org.bukkit.inventory.ItemStack

class BedwarsDiamondItemGenerator(
    location: Location,
    override val itemMaterial: ItemStack =
        ItemBuilder.of(XMaterial.DIAMOND).build(),
) : BedwarsProminentItemGenerator(mapOf(
    1 to 45,
    2 to 30,
    3 to 15
), location, ItemBuilder.of(XMaterial.DIAMOND_BLOCK).build())
