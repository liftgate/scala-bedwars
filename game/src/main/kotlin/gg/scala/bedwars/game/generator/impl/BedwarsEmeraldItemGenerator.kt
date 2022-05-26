package gg.scala.bedwars.game.generator.impl

import com.cryptomorin.xseries.XMaterial
import gg.scala.bedwars.game.generator.BedwarsItemGenerator
import net.evilblock.cubed.util.bukkit.ItemBuilder
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.entity.ArmorStand
import org.bukkit.entity.EntityType

class BedwarsEmeraldItemGenerator(location: Location) : BedwarsItemGenerator(60, location)
{
    var stand: ArmorStand

    override fun drop()
    {
        location.world.dropItem(location, ItemBuilder(XMaterial.EMERALD).build())
    }

    override fun tick()
    {
        val loc = stand.location
        if (loc.yaw == 360f) loc.yaw = 0f
        else loc.yaw += 2f
        stand.teleport(loc)
        stand.customName = "Emerald in $cooldown"
    }

    init {
        if (!location.world.isChunkLoaded(location.chunk)) location.world.loadChunk(location.chunk)
        stand = location.world.spawnEntity(location, EntityType.ARMOR_STAND) as ArmorStand

        stand.setGravity(false)
        stand.isCustomNameVisible = true
        stand.isVisible = false
        //stand.addPotionEffect(PotionEffect(PotionEffectType.INVISIBILITY, Int.MAX_VALUE, 1))
        stand.helmet = ItemBuilder(Material.EMERALD_BLOCK).build()
    }
}