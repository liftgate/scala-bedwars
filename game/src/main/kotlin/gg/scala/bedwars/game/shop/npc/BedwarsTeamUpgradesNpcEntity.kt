package gg.scala.bedwars.game.shop.npc

import net.evilblock.cubed.entity.npc.NpcEntity
import net.evilblock.cubed.util.CC
import org.bukkit.Location
import org.bukkit.entity.Player

/**
 * @author GrowlyX
 * @since 5/27/2022
 */
class BedwarsTeamUpgradesNpcEntity(
    location: Location
) : NpcEntity(
    listOf(
        "${CC.D_AQUA}Team Upgrades",
        "${CC.GREEN}CLICK TO OPEN!"
    ),
    location
)
{
    override fun onRightClick(player: Player)
    {
        player.sendMessage("${CC.RED}test team upgrades")
    }
}
