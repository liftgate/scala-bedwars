package gg.scala.bedwars.game.listener

import gg.scala.commons.annotations.Listeners
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.CraftItemEvent

@Listeners
object BedwarsProtectionListener : Listener
{

    @EventHandler
    fun onCraft(e: CraftItemEvent) {
        e.isCancelled = true
    }
}