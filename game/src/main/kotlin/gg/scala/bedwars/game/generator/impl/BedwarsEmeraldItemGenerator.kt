package gg.scala.bedwars.game.generator.impl

import com.cryptomorin.xseries.XMaterial
import gg.scala.bedwars.game.generator.BedwarsProminentItemGenerator
import net.evilblock.cubed.util.CC
import net.evilblock.cubed.util.bukkit.ItemBuilder
import org.bukkit.Location
import org.bukkit.inventory.ItemStack

class BedwarsEmeraldItemGenerator(
    location: Location,
    override val itemMaterial: ItemStack =
        ItemBuilder.of(XMaterial.EMERALD).build(),
    override val name: String = "${CC.D_GREEN}Emerald"
) : BedwarsProminentItemGenerator(mapOf(
    0 to 90,
    1 to 75,
    2 to 60,
    3 to 45
), location,
    ItemBuilder.of(XMaterial.EMERALD_BLOCK).build()
)
