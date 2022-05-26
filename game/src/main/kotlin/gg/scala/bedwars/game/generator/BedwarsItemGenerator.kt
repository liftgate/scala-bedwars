package gg.scala.bedwars.game.generator

import org.bukkit.Location

abstract class BedwarsItemGenerator(val seconds: Int, val location: Location)
{
    var cooldown = seconds

    abstract fun drop()

    init {
        BedwarsItemGeneratorService.generators.add(this)
    }
}