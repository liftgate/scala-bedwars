package gg.scala.bedwars.game.generator.impl

import com.cryptomorin.xseries.XMaterial
import gg.scala.bedwars.game.generator.BedwarsItemGenerator
import net.evilblock.cubed.util.bukkit.ItemBuilder
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.entity.ArmorStand
import org.bukkit.entity.EntityType

class BedwarsDiamondItemGenerator(location: Location) : BedwarsItemGenerator(45, location)
{
    var stand: ArmorStand

    override fun drop()
    {
        location.world.dropItem(location, ItemBuilder(XMaterial.DIAMOND).build())
    }

    override fun tick()
    {
        val loc = stand.location
        if (loc.yaw == 360f) loc.yaw = 0f
        else loc.yaw += 2f
        stand.teleport(loc)
        stand.customName = "Diamond in $cooldown"
    }

    init {
        if (!location.world.isChunkLoaded(location.chunk)) location.world.loadChunk(location.chunk)
        stand = location.world.spawnEntity(location, EntityType.ARMOR_STAND) as ArmorStand

        stand.location.world.getBlockAt(stand.location.subtract(0.0, 1.0, 0.0)).type = Material.DIAMOND_BLOCK

        stand.setGravity(false)
        stand.isCustomNameVisible = true
        stand.isVisible = false
        //stand.addPotionEffect(PotionEffect(PotionEffectType.INVISIBILITY, Int.MAX_VALUE, 1))
        stand.helmet = ItemBuilder(Material.DIAMOND_BLOCK).build()
    }
}