package gg.scala.bedwars.game.team

import gg.scala.bedwars.shared.team.BedwarsCgsGameTeam
import gg.scala.lemon.channel.ChatChannelComposite
import gg.scala.lemon.channel.channels.DefaultChatChannel
import gg.scala.lemon.player.rank.Rank
import net.evilblock.cubed.util.CC
import net.kyori.adventure.text.TextComponent
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer
import org.bukkit.entity.Player
import java.util.*

/**
 * @author GrowlyX
 * @since 5/28/2022
 */
class BedwarsTeamChatChannelComposite(
    private val team: BedwarsCgsGameTeam
) : ChatChannelComposite
{
    override fun format(
        sender: UUID, receiver: Player,
        message: String, server: String, rank: Rank
    ): TextComponent
    {
        val default = DefaultChatChannel.format(
            sender, receiver, message, server, rank
        )

        return LegacyComponentSerializer.legacySection()
            .deserialize("${CC.GOLD}[TEAM] ${CC.GRAY}[1✫] ")
            .append(default)
    }

    override fun identifier() = "team-${team.id}"
}
