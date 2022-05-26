package gg.scala.bedwars.game.generator.impl

import com.cryptomorin.xseries.XMaterial
import gg.scala.bedwars.game.generator.BedwarsItemGenerator
import net.evilblock.cubed.util.bukkit.ItemBuilder
import org.bukkit.Location

class BedwarsTeamItemGenerator(location: Location) : BedwarsItemGenerator(1, location)
{
    var goldCooldown = 4

    override fun drop()
    {
        goldCooldown--
        location.world.dropItem(location, ItemBuilder.of(XMaterial.IRON_INGOT).build())
        if (goldCooldown == 0)
        {
            location.world.dropItem(location, ItemBuilder.of(XMaterial.GOLD_INGOT).build())
            goldCooldown = 4
        }
    }
}