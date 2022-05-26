package gg.scala.bedwars.game.scoreboard

import gg.scala.bedwars.game.ScalaBedwarsGameEngine
import gg.scala.bedwars.shared.team.BedwarsCgsGameTeam
import gg.scala.cgs.common.CgsGameEngine
import gg.scala.cgs.common.player.handler.CgsPlayerHandler
import gg.scala.cgs.common.player.scoreboard.CgsGameScoreboardRenderer
import gg.scala.cgs.common.runnable.state.StartingStateRunnable
import gg.scala.cgs.common.states.CgsGameState
import gg.scala.cgs.common.teams.CgsGameTeamService
import net.evilblock.cubed.util.CC
import net.evilblock.cubed.util.time.TimeUtil
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import java.util.*

/**
 * @author GrowlyX
 * @since 12/3/2021
 */
object ScalaBedwarsScoreboard : CgsGameScoreboardRenderer
{
    override fun getTitle() = "${CC.B_PRI}Bedwars"

    override fun render(lines: LinkedList<String>, player: Player, state: CgsGameState)
    {
        if (state == CgsGameState.WAITING || state == CgsGameState.STARTING)
        {
            lines.add(
                if (state == CgsGameState.WAITING) "Waiting for players..."
                else "Starting in ${CC.PRI}${TimeUtil.formatIntoAbbreviatedString(StartingStateRunnable.PRE_START_TIME)}"
            )

            lines.add("")
            lines.add("Players: ${CC.PRI}${
                Bukkit.getOnlinePlayers().size
            }/${
                Bukkit.getMaxPlayers()
            }")
            lines.add("")
            lines.add("Mode: ${CC.PRI}${
                CgsGameEngine.INSTANCE.gameMode.getName()
            }")
            lines.add("Version: ${CC.PRI}${
                CgsGameEngine.INSTANCE.gameInfo.gameVersion
            }")
        } else if (state.isAfter(CgsGameState.STARTED))
        {
            val cgsGamePlayer = CgsPlayerHandler.find(player)!!

            val statistics = ScalaBedwarsGameEngine.INSTANCE
                .getStatistics(cgsGamePlayer)

            lines.add("Next Event: " + CC.AQUA + "Diamond I")
            lines.add("")

            CgsGameTeamService.teams.map {
                it.value as BedwarsCgsGameTeam
            }.forEach {
                lines.add(" ${it.color}${it.color.name.substring(0, 1)} ${CC.WHITE}${ChatColor.stripColor(it.name)}: ${
                    if (it.bedDestroyed) {
                        if (it.alive.isEmpty()) CC.RED + "✘"
                        else CC.GREEN + it.alive.size
                    } else CC.GREEN + "✔"
                }")
            }

            if (ScalaBedwarsGameEngine.INSTANCE.gameMode.getMaxTeams() <= 4) {
                lines.add("")
                lines.add("Kills: " + CC.PRI + statistics.gameKills.value)
            }
        }

        lines.add("")
        lines.add("${CC.AQUA}www.glade.gg")
    }

    private fun getFormattedPing(ping: Int): String
    {
        return if (ping > 300)
        {
            CC.D_RED + ping + "ms"
        } else if (ping > 150)
        {
            CC.PRI + ping + "ms"
        } else if (ping > 80)
        {
            CC.YELLOW + ping + "ms"
        } else
        {
            CC.GREEN + ping + "ms"
        }
    }
}