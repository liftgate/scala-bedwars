package gg.scala.bedwars.game.nametag

import gg.scala.bedwars.shared.team.BedwarsCgsGameTeam
import gg.scala.cgs.common.player.CgsGamePlayer
import gg.scala.cgs.common.player.handler.CgsSpectatorHandler
import gg.scala.cgs.common.player.nametag.CgsGameNametagAdapter
import gg.scala.cgs.common.teams.CgsGameTeamService
import net.evilblock.cubed.nametag.NametagInfo
import net.evilblock.cubed.nametag.NametagProvider
import net.evilblock.cubed.util.CC
import org.bukkit.Bukkit
import org.bukkit.ChatColor

/**
 * @author GrowlyX
 * @since 12/3/2021
 */
object ScalaBedwarsNametagAdapter : CgsGameNametagAdapter
{
    override fun computeNametag(
        viewer: CgsGamePlayer,
        target: CgsGamePlayer
    ): NametagInfo
    {
        val player = Bukkit.getPlayer(viewer.uniqueId)
        val targetPlayer = Bukkit.getPlayer(viewer.uniqueId)

        if (viewer == target && player.hasMetadata("spectator") && targetPlayer.hasMetadata("spectator"))
        {
            return NametagProvider.createNametag(ChatColor.GRAY.toString(), "")
        }

        return NametagProvider.createNametag((CgsGameTeamService.getTeamOf(targetPlayer) as BedwarsCgsGameTeam).color.toString(), "")
    }
}