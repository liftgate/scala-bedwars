package gg.scala.bedwars.shared.arena.eight

import gg.scala.bedwars.shared.arena.BedwarsArena
import org.bukkit.Location
import org.bukkit.Material

object TestEightArena : BedwarsArena() {

    override val diamondGenerators: List<Location>
        get() = mutableListOf(
            buildSpawnPoint(5.0, 100.0, 0.0),
            buildSpawnPoint(-5.0, 100.0, 0.0),
            buildSpawnPoint(0.0, 100.0, 5.0),
            buildSpawnPoint(0.0, 100.0, -5.0),
        )
    override val emeraldGenerators: List<Location>
        get() = mutableListOf(buildSpawnPoint(0.0, 100.0, 0.0))
    override val teamSpawnPoints: MutableMap<Int, Location>
        get() = mutableMapOf(
            1 to buildSpawnPoint(-8.0, 100.0, 15.0),
            2 to buildSpawnPoint(8.0, 100.0, 15.0),

            3 to buildSpawnPoint(8.0, 100.0, -15.0),
            4 to buildSpawnPoint(-8.0, 100.0, -15.0),


            5 to buildSpawnPoint(15.0, 100.0, 8.0),
            6 to buildSpawnPoint(15.0, 100.0, -8.0),

            7 to buildSpawnPoint(-15.0, 100.0, 8.0),
            8 to buildSpawnPoint(-15.0, 100.0, -8.0)
        )

    override fun getDescription(): String {
        return "TEST - Bedwars Arena (EIGHT)"
    }

    override fun getId(): String {
        return "bw-test-eight"
    }

    override fun getMaterial(): Pair<Material, Int> {
        return Material.GRASS to 0
    }

    override fun getName(): String {
        return "TEST - 8"
    }
}