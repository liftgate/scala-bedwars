package gg.scala.bedwars.game.command

import gg.scala.bedwars.game.shop.BedwarsShopMenu
import gg.scala.commons.acf.annotation.CommandAlias
import gg.scala.commons.acf.annotation.CommandPermission
import gg.scala.commons.annotations.commands.AutoRegister
import gg.scala.commons.command.ScalaCommand
import org.bukkit.entity.Player

/**
 * @author GrowlyX
 * @since 5/27/2022
 */
@AutoRegister
object ShopCommand : ScalaCommand()
{
    @CommandAlias("shop")
    @CommandPermission("op")
    fun onShop(
        player: Player
    )
    {
        BedwarsShopMenu().openMenu(player)
    }
}
