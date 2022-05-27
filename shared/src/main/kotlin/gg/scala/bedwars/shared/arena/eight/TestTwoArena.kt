package gg.scala.bedwars.shared.arena.eight

import gg.scala.bedwars.shared.arena.BedwarsArena
import org.bukkit.Location
import org.bukkit.Material

object TestTwoArena : BedwarsArena() {

    override val diamondGenerators = mutableListOf(
        buildSpawnPoint(15.5, 87.0, 77.5),
        buildSpawnPoint(15.0, 87.0, 59.5),
    )

    override val emeraldGenerators = mutableListOf(
        buildSpawnPoint(15.0, 90.0, 67.0)
    )

    override val teamShopLocations = mutableMapOf(
        1 to buildSpawnPoint(13.0, 86.0, 33.0),
        2 to buildSpawnPoint(17.0, 86.0, 103.0, 180F, 0F)
    )

    override val teamGeneratorLocations = mutableMapOf(
        1 to buildSpawnPoint(15.0, 86.0, 31.0),
        2 to buildSpawnPoint(15.0, 86.0, 105.0),
    )

    override val teamItemUpgradeLocations = mutableMapOf(
        1 to buildSpawnPoint(17.0, 86.0, 33.0),
        2 to buildSpawnPoint(13.0, 86.0, 103.0, 180F, 0F)
    )

    override val teamBedLocations = mutableMapOf(
        1 to buildSpawnPoint(15.5, 86.0, 37.5),
        2 to buildSpawnPoint(15.5, 86.0, 98.5)
    )

    override val teamEnderChestLocations = mutableMapOf(
        1 to buildSpawnPoint(16.5, 86.0, 34.5),
        2 to buildSpawnPoint(16.5, 86.0, 102.5)
    )

    override val teamChestLocations = mutableMapOf(
        1 to buildSpawnPoint(14.5, 86.0, 34.5),
        2 to buildSpawnPoint(14.5, 86.0, 102.5)
    )

    override val teamSpawnPoints = mutableMapOf(
        1 to buildSpawnPoint(15.5, 86.0, 34.5),
        2 to buildSpawnPoint(15.5, 86.0, 102.5, 180F, 0F)
    )

    override fun getDescription(): String {
        return "TEST - Bedwars Arena (two)"
    }

    override fun getId(): String {
        return "bw-test-two"
    }

    override fun getMaterial(): Pair<Material, Int> {
        return Material.GRASS to 0
    }

    override fun getName(): String {
        return "TEST - 2"
    }
}
