package gg.scala.bedwars.game.upgrades

import gg.scala.bedwars.shared.team.BedwarsCgsGameTeam
import gg.scala.cgs.common.CgsGameEngine
import gg.scala.cgs.common.teams.CgsGameTeamService
import net.evilblock.cubed.menu.Button
import net.evilblock.cubed.menu.Menu
import net.evilblock.cubed.menu.pagination.PaginatedMenu
import net.evilblock.cubed.util.CC
import net.evilblock.cubed.util.bukkit.Constants
import net.evilblock.cubed.util.bukkit.ItemBuilder
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemFlag

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

    val mode = CgsGameEngine.INSTANCE.gameMode
    val gameMode = if (isDuosOrSolos()) "eight" else "four"

    private fun isDuosOrSolos(): Boolean
    {
        return this.mode.isSoloGame() || this.mode.getTeamSize() == 2
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

        listOf(13, 13 + 9).forEach {
            buttons[it] = ItemBuilder
                .copyOf(
                    PaginatedMenu.PLACEHOLDER
                        .getButtonItem(player)
                )
                .name(
                    "${CC.D_GRAY}${
                        Constants.DOUBLE_ARROW_LEFT
                    } ${CC.GRAY}Upgrades"
                )
                .addToLore(
                    "${CC.GRAY}Traps ${CC.D_GRAY}${
                        Constants.DOUBLE_ARROW_RIGHT
                    }"
                )
                .toButton()
        }

        for (upgrade in BedwarsTeamUpgrades.values())
        {
            val description = mutableListOf<String>()
            description += "${CC.GRAY}Viewing tiers:"

            for (entry in upgrade.names)
            {
                val price = upgrade
                    .costs[this.gameMode]!![entry.key]

                description += " ${CC.WHITE}- ${entry.value}: ${CC.AQUA}$price Diamond${
                    if (price == 1) "" else "s"
                }"
            }

            buttons[upgrade.position] = ItemBuilder
                .of(upgrade.item)
                .name("${CC.YELLOW}${upgrade.display}")
                .addFlags(
                    ItemFlag.HIDE_ATTRIBUTES,
                    ItemFlag.HIDE_ENCHANTS,
                    ItemFlag.HIDE_POTION_EFFECTS,
                    ItemFlag.HIDE_UNBREAKABLE,
                    ItemFlag.HIDE_PLACED_ON
                )
                .toButton()
        }

        return buttons
    }
}
