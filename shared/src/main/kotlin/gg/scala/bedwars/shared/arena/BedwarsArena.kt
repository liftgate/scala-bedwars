package gg.scala.bedwars.shared.arena

import gg.scala.cgs.common.information.arena.CgsGameArena
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.Material
import java.nio.file.Path

/**
 * @author GrowlyX
 * @since 12/3/2021
 */
abstract class BedwarsArena : CgsGameArena
{
    override fun getDirectory(): Path? = null
    override fun getBukkitWorldName() = getId()

    override fun getPreLobbyLocation() = Location(
        Bukkit.getWorld(getBukkitWorldName()),
        0.5, 150.0, 0.5
    )

    override fun getSpectatorLocation() = Location(
        Bukkit.getWorld(getBukkitWorldName()), 0.5, Bukkit.getWorld(getBukkitWorldName()).getHighestBlockYAt(0, 0) + 15.0, 0.5
    )

    fun buildSpawnPoint(x: Double, y: Double, z: Double) : Location {
        return Location(Bukkit.getWorld(getBukkitWorldName()), x, y, z)
    }

    fun getSpawnPoint(id: Int): Location? {
        return teamSpawnPoints[id]
    }

    abstract val teamSpawnPoints: MutableMap<Int, Location>
}
