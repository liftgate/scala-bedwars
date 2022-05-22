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
object BedwarsRandomArena : CgsGameArena
{
    override fun getId() = "uhc_meetup"
    override fun getName() = "Random"

    override fun getMaterial() = Pair(Material.ENDER_PEARL, 0)
    override fun getDescription() = "Bedwars games have randomly generated arenas!"

    override fun getDirectory(): Path? = null

    override fun getBukkitWorldName() = "meetup"

    override fun getPreLobbyLocation() = Location(
        Bukkit.getWorld("bedwars"),
        0.5, 150.0, 0.5
    )

    override fun getSpectatorLocation() = Location(
        Bukkit.getWorld("bedwars"), 0.5, Bukkit.getWorld("bedwars").getHighestBlockYAt(0, 0) + 15.0, 0.5
    )
}
