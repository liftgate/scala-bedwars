package gg.scala.bedwars.game.death

import gg.scala.bedwars.shared.team.BedwarsCgsGameTeam
import gg.scala.cgs.common.CgsGameEngine
import gg.scala.cgs.common.player.handler.CgsSpectatorHandler
import gg.scala.cgs.common.teams.CgsGameTeamService
import net.evilblock.cubed.util.CC
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer
import net.kyori.adventure.title.Title
import net.kyori.adventure.title.TitlePart
import org.bukkit.entity.Player
import org.bukkit.scheduler.BukkitRunnable
import java.time.Duration

/**
 * @author GrowlyX
 * @since 5/26/2022
 */
class BedwarsRespawnRunnable(
    private val player: Player
) : BukkitRunnable()
{
    private var tick = 6

    override fun run()
    {
        if (!this.player.isOnline)
        {
            this.cancel()
            return
        }

        val audience = CgsGameEngine.INSTANCE
            .audience.player(this.player)

        when (this.tick)
        {
            6 ->
            {
                CgsSpectatorHandler.setSpectator(this.player)

                this.player.teleport(
                    CgsGameEngine.INSTANCE.gameArena.getPreLobbyLocation()
                )
            }
            5, 4, 3, 2, 1 ->
            {
                audience.showTitle(
                    Title.title(
                        LegacyComponentSerializer.legacySection()
                            .deserialize(
                                "${CC.RED}YOU DIED"
                            ),
                        LegacyComponentSerializer.legacySection()
                            .deserialize(
                                "${CC.WHITE}You will respawn in ${CC.GREEN}$tick${CC.WHITE} seconds!"
                            )
                    )
                )

                this.player.sendMessage(
                    "${CC.SEC}You will respawn in ${CC.GREEN}$tick${CC.SEC} seconds."
                )
            }
            0 ->
            {
                val team = CgsGameTeamService
                    .getTeamOf(this.player)
                    ?: return this.cancel()

                val bedwarsTeam =
                    team as BedwarsCgsGameTeam

                CgsSpectatorHandler
                    .removeSpectator(this.player)

                bedwarsTeam.spawnPoint?.apply {
                    player.teleport(this)
                }

                audience.showTitle(
                    Title.title(
                        LegacyComponentSerializer.legacySection()
                            .deserialize(
                                "${CC.GREEN}RESPAWNED"
                            ),
                        LegacyComponentSerializer.legacySection()
                            .deserialize(
                                "${CC.WHITE}You have respawned!"
                            ),
                        Title.Times.times(
                            Duration.ofMillis(250L),
                            Duration.ofMillis(500L),
                            Duration.ofMillis(250L)
                        )
                    )
                )

                this.player.sendMessage("${CC.GREEN}You have respawned!")

                this.cancel()
            }
        }

        this.tick--
    }
}
