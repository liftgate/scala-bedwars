package gg.scala.bedwars.shared

import gg.scala.cgs.common.information.CgsGameGeneralInfo
import gg.scala.bedwars.shared.gamemode.BedwarsDuosGameMode
import gg.scala.bedwars.shared.gamemode.BedwarsSoloGameMode
import gg.scala.bedwars.shared.gamemode.BedwarsTriosGameMode
import gg.scala.bedwars.shared.gamemode.BedwarsFoursGameMode

/**
 * @author GrowlyX
 * @since 12/17/2021
 */
object BedwarsCgsInfo : CgsGameGeneralInfo(
    "BedWars", 0.02F, 2,
    61, BedwarsCgsAwards, false,
    false, false, true,
    true, listOf(
        BedwarsSoloGameMode,
        BedwarsDuosGameMode,
        BedwarsTriosGameMode,
        BedwarsFoursGameMode
    )
)
