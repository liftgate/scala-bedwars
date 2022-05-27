package gg.scala.bedwars.game.generator

import gg.scala.bedwars.game.generator.hologram.BedwarsUpdatingHologramEntity
import net.evilblock.cubed.entity.EntityHandler
import org.bukkit.Location
import org.bukkit.entity.ArmorStand
import org.bukkit.entity.EntityType
import org.bukkit.inventory.ItemStack

abstract class BedwarsProminentItemGenerator(
    upgrades: Map<Int, Int>, location: Location, itemBlock: ItemStack
) : BedwarsItemGenerator(upgrades, location)
{
    val stand: ArmorStand

    abstract val name: String

    open fun tick()
    {
        val location =
            this.stand.location

        if (location.yaw == 360f)
            location.yaw = 0f
        else location.yaw += 2f

        this.stand.teleport(location)
    }

    init
    {
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

        BedwarsUpdatingHologramEntity(
            location.clone().add(0.0, 0.900, 0.0), this
        ).apply {
            initializeData()

            EntityHandler
                .trackEntity(this)
        }

        this.stand.setGravity(false)
        this.stand.isCustomNameVisible = false
        this.stand.isVisible = false
        this.stand.helmet = itemBlock
    }
}
