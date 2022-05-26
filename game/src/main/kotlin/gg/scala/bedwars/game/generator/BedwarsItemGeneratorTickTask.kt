package gg.scala.bedwars.game.generator

import gg.scala.commons.annotations.runnables.Repeating

@Repeating(1L)
object BedwarsItemGeneratorTickTask : Runnable
{
    override fun run()
    {
        BedwarsItemGeneratorService
            .generators.forEach {
                it.tick()
            }
    }
}
