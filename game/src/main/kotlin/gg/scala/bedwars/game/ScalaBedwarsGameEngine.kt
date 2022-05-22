package gg.scala.bedwars.game

import gg.scala.cgs.common.CgsGameEngine
import gg.scala.cgs.common.player.nametag.CgsGameNametagAdapter
import gg.scala.cgs.common.player.scoreboard.CgsGameScoreboardRenderer
import gg.scala.cgs.common.player.visibility.CgsGameVisibilityAdapter
import gg.scala.cgs.common.snapshot.CgsGameSnapshot
import org.bukkit.plugin.java.JavaPlugin
import gg.scala.bedwars.shared.BedwarsCgsInfo
import gg.scala.bedwars.shared.BedwarsCgsStatistics
import gg.scala.bedwars.shared.gamemode.BedwarsSoloGameMode

/**
 * @author GrowlyX
 * @since 5/21/2022
 */
object ScalaBedwarsGameEngine : CgsGameEngine<BedwarsCgsStatistics>(
    JavaPlugin.getPlugin(ScalaBedwarsGame::class.java),
    BedwarsCgsInfo, BedwarsSoloGameMode,
    BedwarsCgsStatistics::class
)
{
    override fun getGameSnapshot(): CgsGameSnapshot
    {
        TODO("Not yet implemented")
    }

    override fun getNametagAdapter(): CgsGameNametagAdapter
    {
        TODO("Not yet implemented")
    }

    override fun getScoreboardRenderer(): CgsGameScoreboardRenderer
    {
        TODO("Not yet implemented")
    }

    override fun getVisibilityAdapter(): CgsGameVisibilityAdapter
    {
        TODO("Not yet implemented")
    }
}
