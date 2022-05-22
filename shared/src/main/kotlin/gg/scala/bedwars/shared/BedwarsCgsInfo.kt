package gg.scala.bedwars.shared

import gg.scala.cgs.common.information.CgsGameGeneralInfo
import gg.scala.bedwars.shared.gamemode.BedwarsDuosGameMode
import gg.scala.bedwars.shared.gamemode.BedwarsSoloGameMode
import gg.scala.bedwars.shared.gamemode.BedwarsTriosGameMode

/**
 * @author GrowlyX
 * @since 12/17/2021
 */
object BedwarsCgsInfo : CgsGameGeneralInfo(
    "BedWars", 0.01F, 2,
    61, BedwarsCgsAwards, true,
    true, false, true,
    false, listOf(
        BedwarsSoloGameMode,
        BedwarsDuosGameMode,
        BedwarsTriosGameMode
    )
)
