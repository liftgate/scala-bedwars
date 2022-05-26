package gg.scala.bedwars.game.generator

import gg.scala.flavor.service.Service

@Service
object BedwarsItemGeneratorService
{
    val generators: MutableList<BedwarsItemGenerator> = mutableListOf()
}