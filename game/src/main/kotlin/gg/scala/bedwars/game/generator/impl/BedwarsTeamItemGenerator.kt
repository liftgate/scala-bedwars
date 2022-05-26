package gg.scala.bedwars.game.generator.impl

import com.cryptomorin.xseries.XMaterial
import gg.scala.bedwars.game.generator.BedwarsItemGenerator
import net.evilblock.cubed.util.bukkit.ItemBuilder
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

class BedwarsTeamItemGenerator(
    location: Location,
    override val itemMaterial: ItemStack = ItemBuilder.of(Material.IRON_INGOT).build()
) : BedwarsItemGenerator(1, location)
{
    private var goldCooldown = 4

    override fun drop()
    {
        goldCooldown--

        super.drop()
        if (goldCooldown == 0)
        {
            location.world.dropItem(
                location, ItemBuilder.of(XMaterial.GOLD_INGOT).build()
            )

            goldCooldown = 4
        }
    }
}