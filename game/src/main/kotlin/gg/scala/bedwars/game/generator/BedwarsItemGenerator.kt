package gg.scala.bedwars.game.generator

import org.bukkit.Location

abstract class BedwarsItemGenerator(
    val seconds: Int, var location: Location
)
{
    var cooldown = seconds

    abstract fun drop()
    open fun tick() {}

    init {
        location = location.add(0.5, 0.0, 0.5)
    }
}
