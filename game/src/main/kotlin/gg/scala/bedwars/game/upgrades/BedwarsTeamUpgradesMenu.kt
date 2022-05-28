package gg.scala.bedwars.game.upgrades

import gg.scala.bedwars.shared.team.BedwarsCgsGameTeam
import gg.scala.cgs.common.teams.CgsGameTeamService
import net.evilblock.cubed.menu.Button
import net.evilblock.cubed.menu.Menu
import net.evilblock.cubed.util.CC
import net.evilblock.cubed.util.bukkit.ItemBuilder
import org.bukkit.entity.Player

/**
 * @author GrowlyX
 * @since 5/28/2022
 */
class BedwarsTeamUpgradesMenu : Menu(
    "Team Upgrades & Traps"
)
{
    init
    {
        updateAfterClick = true
        placeholdBorders = true
    }

    override fun size(
        buttons: Map<Int, Button>
    ) = 36

    override fun getButtons(
        player: Player
    ): Map<Int, Button>
    {
        val buttons =
            mutableMapOf<Int, Button>()

        val team = CgsGameTeamService
            .getTeamOf(player)!! as BedwarsCgsGameTeam

        val tracker = Tracker.of(team)

        for (upgrade in BedwarsTeamUpgrades.values())
        {
            buttons[upgrade.position] = ItemBuilder
                .of(upgrade.item)
                .name("${CC.YELLOW}${upgrade.display}")
                .toButton()
        }

        return buttons
    }
}
