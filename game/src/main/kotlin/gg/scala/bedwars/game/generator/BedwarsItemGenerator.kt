package gg.scala.bedwars.game.generator

import org.bukkit.Location
import org.bukkit.inventory.ItemStack

abstract class BedwarsItemGenerator(
    val upgrades: Map<Int, Int>, var location: Location
)
{
    var tier = 1
    var cooldown = upgrades[tier]!!

    abstract val itemMaterial: ItemStack

    open fun tier() = tier

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
