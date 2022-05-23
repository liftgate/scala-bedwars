package gg.scala.bedwars.game

import com.avaje.ebeaninternal.server.util.InternalAssert.notNull
import gg.scala.bedwars.game.visibility.ScalaBedwarsVisibilityAdapter
import gg.scala.cgs.common.CgsGameEngine
import gg.scala.cgs.common.player.nametag.CgsGameNametagAdapter
import gg.scala.cgs.common.player.scoreboard.CgsGameScoreboardRenderer
import gg.scala.cgs.common.player.visibility.CgsGameVisibilityAdapter
import gg.scala.cgs.common.snapshot.CgsGameSnapshot
import org.bukkit.plugin.java.JavaPlugin
import gg.scala.bedwars.shared.BedwarsCgsInfo
import gg.scala.bedwars.shared.BedwarsCgsStatistics
import gg.scala.bedwars.shared.gamemode.BedwarsSoloGameMode
import gg.scala.bedwars.shared.team.BedwarsCgsGameTeam
import gg.scala.cgs.common.information.CgsGameGeneralInfo
import gg.scala.cgs.common.information.mode.CgsGameMode
import gg.scala.cgs.common.teams.CgsGameTeam
import gg.scala.commons.ExtendedScalaPlugin
import kotlin.properties.Delegates

/**
 * @author GrowlyX
 * @since 5/21/2022
 */
class ScalaBedwarsGameEngine(
    plugin: ExtendedScalaPlugin,
    gameInfo: CgsGameGeneralInfo,
    gameMode: CgsGameMode
) : CgsGameEngine<BedwarsCgsStatistics>(
    plugin,
    gameInfo, gameMode,
    BedwarsCgsStatistics::class
)
{
    companion object
    {
        @JvmStatic
        var INSTANCE by Delegates.notNull<ScalaBedwarsGameEngine>()
    }


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
        return ScalaBedwarsVisibilityAdapter
    }

    override fun createTeam(id: Int) : CgsGameTeam {
        return BedwarsCgsGameTeam(id)
    }
}
