package gg.scala.bedwars.game

import gg.scala.cgs.common.CgsGameEngine
import gg.scala.cgs.common.information.arena.CgsGameArenaHandler
import gg.scala.flavor.service.Configure
import gg.scala.flavor.service.Service
import shared.gamemode.BedwarsSoloGameMode

/**
 * @author GrowlyX
 * @since 5/21/2022
 */
@Service
object ScalaBedwarsGameOrchestrator
{
    @Configure
    fun configure()
    {
        CgsGameArenaHandler
            .configure(
                BedwarsSoloGameMode
            )

        ScalaBedwarsGameEngine
            .initialResourceLoad()

        ScalaBedwarsGameEngine
            .initialLoad()

        CgsGameEngine.INSTANCE =
            ScalaBedwarsGameEngine
    }
}
