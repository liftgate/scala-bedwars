package gg.scala.bedwars.game.upgrades

import gg.scala.bedwars.shared.team.BedwarsCgsGameTeam

/**
 * @author GrowlyX
 * @since 5/28/2022
 */
object BedwarsTeamUpgradesTrackerService
{
    val trackers =
        mutableMapOf<Int, BedwarsTeamUpgradesTracker>()
}

object Tracker
{
    fun of(team: BedwarsCgsGameTeam) =
        BedwarsTeamUpgradesTrackerService.trackers[team.id]!!
}
