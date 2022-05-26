package gg.scala.bedwars.game.generator.impl

import com.cryptomorin.xseries.XMaterial
import gg.scala.bedwars.game.generator.BedwarsItemGenerator
import net.evilblock.cubed.util.bukkit.ItemBuilder
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.entity.ArmorStand
import org.bukkit.entity.EntityType
import org.bukkit.inventory.ItemStack

class BedwarsEmeraldItemGenerator(
    location: Location,
    override val itemMaterial: ItemStack =
        ItemBuilder.of(XMaterial.EMERALD).build(),
    override val itemBlock: ItemStack =
        ItemBuilder.of(XMaterial.EMERALD_BLOCK).build()
) : BedwarsItemGenerator(60, location)
