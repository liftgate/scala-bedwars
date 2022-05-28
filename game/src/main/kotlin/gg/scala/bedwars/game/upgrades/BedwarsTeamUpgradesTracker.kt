package gg.scala.bedwars.game.upgrades

import gg.scala.bedwars.shared.team.BedwarsCgsGameTeam

/**
 * @author GrowlyX
 * @since 5/28/2022
 */
class BedwarsTeamUpgradesTracker(
    private val team: BedwarsCgsGameTeam
)
{
    val upgrades =
        mutableMapOf<BedwarsTeamUpgrades, Int>()
}
