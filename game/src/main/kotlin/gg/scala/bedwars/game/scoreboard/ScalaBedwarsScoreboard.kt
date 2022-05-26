package gg.scala.bedwars.game.scoreboard

import gg.scala.bedwars.game.ScalaBedwarsGameEngine
import gg.scala.bedwars.game.generator.tier.BedwarsItemGeneratorTierIncrementer
import gg.scala.bedwars.shared.team.BedwarsCgsGameTeam
import gg.scala.cgs.common.CgsGameEngine
import gg.scala.cgs.common.player.handler.CgsPlayerHandler
import gg.scala.cgs.common.player.scoreboard.CgsGameScoreboardRenderer
import gg.scala.cgs.common.runnable.state.StartingStateRunnable
import gg.scala.cgs.common.states.CgsGameState
import gg.scala.cgs.common.teams.CgsGameTeamService
import gg.scala.lemon.Lemon
import net.evilblock.cubed.util.CC
import net.evilblock.cubed.util.time.TimeUtil
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import java.text.SimpleDateFormat
import java.util.*

/**
 * @author GrowlyX
 * @since 12/3/2021
 */
object ScalaBedwarsScoreboard : CgsGameScoreboardRenderer
{
    private val dateFormat =
        SimpleDateFormat("MM/dd/yyyy")
            .format(
                Date()
            )
            .replace(
                "20", ""
            )
            .replace(
                "0", ""
            )

    private val serverId = Lemon.instance.settings
        .id.replace("bedwars", "mg")

    override fun getTitle() = "${CC.B_PRI}Bedwars"

    override fun render(lines: LinkedList<String>, player: Player, state: CgsGameState)
    {
        lines.add("${CC.GRAY}$dateFormat ${CC.D_GRAY}$serverId")
        lines.add("")

        if (state == CgsGameState.WAITING || state == CgsGameState.STARTING)
        {
            lines.add(
                "Map: ${CC.D_AQUA}${
                    CgsGameEngine.INSTANCE.gameArena.getName()
                }"
            )
            lines.add(
                "Players: ${CC.D_AQUA}${
                    Bukkit.getOnlinePlayers().size
                }/${
                    Bukkit.getMaxPlayers()
                }"
            )

            lines.add("")

            lines.add(
                if (state == CgsGameState.WAITING)
                {
                    "Waiting..."
                } else
                {
                    "Starting in ${CC.GREEN}${StartingStateRunnable.PRE_START_TIME}s"
                }
            )

            lines.add("")
            lines.add(
                "Mode: ${CC.D_AQUA}${
                    CgsGameEngine.INSTANCE.gameMode.getName()
                }"
            )
            lines.add(
                "Version: ${CC.GRAY}${
                    CgsGameEngine.INSTANCE.gameInfo.gameVersion
                }"
            )
        } else if (state.isAfter(CgsGameState.STARTED))
        {
            val cgsGamePlayer = CgsPlayerHandler.find(player)!!

            val statistics = ScalaBedwarsGameEngine.INSTANCE
                .getStatistics(cgsGamePlayer)

            lines.add("${
                BedwarsItemGeneratorTierIncrementer.formatted()
            } in ${CC.GREEN}${
                TimeUtil.formatIntoMMSS(
                    BedwarsItemGeneratorTierIncrementer.countdown
                )
            }${CC.WHITE}")

            lines.add("")

            CgsGameTeamService.teams
                .map {
                    it.value as BedwarsCgsGameTeam
                }
                .forEach {
                    lines.add(
                        " ${CC.BOLD}${it.color}${it.color.name.substring(0, 1)} ${CC.WHITE}${ChatColor.stripColor(it.name)}: ${
                            if (it.bedDestroyed)
                            {
                                if (it.alive.isEmpty()) CC.RED + "✘"
                                else CC.GREEN + it.alive.size
                            } else CC.GREEN + "✔"
                        }"
                    )
                }

            if (ScalaBedwarsGameEngine.INSTANCE.gameMode.getMaxTeams() <= 4)
            {
                lines.add("")
                lines.add("Kills: " + CC.D_AQUA + statistics.gameKills.value)
            }
        }

        lines.add("")
        lines.add("${CC.GRAY}www.glade.gg    ${CC.GRAY}")
    }
}
