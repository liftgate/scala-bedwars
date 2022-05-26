package gg.scala.bedwars.game.generator

import org.bukkit.Location
import org.bukkit.entity.ArmorStand
import org.bukkit.entity.EntityType
import org.bukkit.inventory.ItemStack

abstract class BedwarsItemGenerator(
    val seconds: Int, var location: Location
)
{
    var cooldown = seconds

    abstract val itemMaterial: ItemStack

    open fun drop()
    {
        this.location.world.dropItem(
            this.location, this.itemMaterial
        )
    }

    init {
        this.location = this.location
            .add(0.5, 0.0, 0.5)
        BedwarsItemGeneratorService.generators.add(this)
    }
}
