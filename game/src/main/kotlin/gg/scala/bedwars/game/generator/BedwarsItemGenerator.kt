package gg.scala.bedwars.game.generator

import org.bukkit.Location
import org.bukkit.entity.ArmorStand
import org.bukkit.entity.EntityType
import org.bukkit.inventory.ItemStack

abstract class BedwarsItemGenerator(
    val seconds: Int, var location: Location
)
{
    var stand: ArmorStand
    var cooldown = seconds

    abstract val itemMaterial: ItemStack
    abstract val itemBlock: ItemStack

    open fun drop()
    {
        this.location.world.dropItem(
            this.location, this.itemMaterial
        )
    }

    open fun tick() {
        val location =
            this.stand.location

        if (location.yaw == 360f)
            location.yaw = 0f
        else location.yaw += 2f

        this.stand.teleport(location)
    }

    init {
        this.location = this.location
            .add(0.5, 0.0, 0.5)

        if (
            !this.location.world
                .isChunkLoaded(this.location.chunk)
        )
        {
            this.location.world
                .loadChunk(this.location.chunk)
        }

        this.stand = this.location.world
            .spawnEntity(
                location, EntityType.ARMOR_STAND
            ) as ArmorStand

        // TODO: 5/26/2022 add custom hologram through Cubed

        this.stand.setGravity(false)
        this.stand.isCustomNameVisible = false
        this.stand.isVisible = false
        this.stand.helmet = this.itemBlock

        BedwarsItemGeneratorService.generators.add(this)
    }
}
