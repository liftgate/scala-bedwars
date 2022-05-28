package gg.scala.bedwars.shared

import gg.scala.bedwars.shared.quickbuy.BedwarsQuickBuyData
import gg.scala.cgs.common.player.statistic.GameSpecificStatistics
import gg.scala.cgs.common.player.statistic.value.CgsGameStatistic
import java.lang.reflect.Type

/**
 * @author GrowlyX
 * @since 12/17/2021
 */
class BedwarsCgsStatistics
@JvmOverloads
constructor(
    override var gameKills: CgsGameStatistic = CgsGameStatistic(),
    override var kills: CgsGameStatistic = CgsGameStatistic(),
    var finalKills: CgsGameStatistic = CgsGameStatistic(),
    var bedsBroken: CgsGameStatistic = CgsGameStatistic(),
    var gameBedsBroken: CgsGameStatistic = CgsGameStatistic(),
    var gameFinalKills: CgsGameStatistic = CgsGameStatistic(),
    override var deaths: CgsGameStatistic = CgsGameStatistic(),
    override var played: CgsGameStatistic = CgsGameStatistic(),
    override var wins: CgsGameStatistic = CgsGameStatistic(),
    override var losses: CgsGameStatistic = CgsGameStatistic(),
    val quickBuyData: BedwarsQuickBuyData = BedwarsQuickBuyData()
) : GameSpecificStatistics()
{
    override fun getAbstractType(): Type
    {
        return BedwarsCgsStatistics::class.java
    }
}
