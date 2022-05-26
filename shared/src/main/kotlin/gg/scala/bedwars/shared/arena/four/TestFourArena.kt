package gg.scala.bedwars.shared.arena.four

import gg.scala.bedwars.shared.arena.BedwarsArena
import org.bukkit.Location
import org.bukkit.Material

object TestFourArena : BedwarsArena() {
    override val diamondGenerators: List<Location>
        get() = TODO("Not yet implemented")
    override val emeraldGenerators: List<Location>
        get() = TODO("Not yet implemented")
    override val teamSpawnPoints: MutableMap<Int, Location>
        get() = mutableMapOf(
            1 to buildSpawnPoint(0.0, 100.0, -10.0),
            2 to buildSpawnPoint(0.0, 100.0, 10.0),

            3 to buildSpawnPoint(10.0, 100.0, 0.0),
            4 to buildSpawnPoint(-10.0, 100.0, 0.0),
        )

    override fun getDescription(): String {
        return "TEST - Bedwars Arena (FOOR)"
    }

    override fun getId(): String {
        return "bw-test-four"
    }

    override fun getMaterial(): Pair<Material, Int> {
        return Material.GRASS to 0
    }

    override fun getName(): String {
        return "TEST - 4"
    }
}