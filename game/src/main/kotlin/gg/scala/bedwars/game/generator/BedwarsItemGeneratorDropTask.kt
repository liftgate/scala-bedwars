package gg.scala.bedwars.game.generator

import gg.scala.commons.annotations.runnables.Repeating

@Repeating(20L)
object BedwarsItemGeneratorDropTask : Runnable
{
    override fun run()
    {
        BedwarsItemGeneratorService.generators.forEach {
            it.`cool down`--

            if (it.`cool down` == 0)
            {
                it.`cool down` = it.upgrades[it.tier] ?: it.upgrades[1]!!
                it.drop()
            }
        }
    }
}
