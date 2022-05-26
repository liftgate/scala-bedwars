package gg.scala.bedwars.game.generator

import gg.scala.bedwars.game.generator.impl.BedwarsDiamondItemGenerator
import gg.scala.bedwars.game.generator.impl.BedwarsEmeraldItemGenerator
import gg.scala.flavor.service.Close
import gg.scala.flavor.service.Service

@Service
object BedwarsItemGeneratorService
{
    val generators: MutableList<BedwarsItemGenerator> = mutableListOf()

    @Close
    fun close()
    {
        generators.filterIsInstance<BedwarsProminentItemGenerator>().forEach {
            it.stand.remove()
        }
    }
}
