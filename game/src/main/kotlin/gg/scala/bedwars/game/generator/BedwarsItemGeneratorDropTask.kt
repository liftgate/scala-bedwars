package gg.scala.bedwars.game.generator

import gg.scala.commons.annotations.runnables.Repeating

@Repeating(20L)
object BedwarsItemGeneratorDropTask : Runnable
{
    override fun run()
    {
        BedwarsItemGeneratorService.generators.forEach {
            it.cooldown--
            if (it.cooldown == 0)
            {
                it.cooldown = it.seconds
                it.drop()
            }
        }
    }
}