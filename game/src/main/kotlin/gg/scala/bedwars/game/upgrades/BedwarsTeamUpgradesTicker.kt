package gg.scala.bedwars.game.upgrades

import gg.scala.bedwars.game.shop.categories.BedwarsShopBlockCategory.team
import org.bukkit.Bukkit
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import org.bukkit.scheduler.BukkitRunnable

/**
 * @author GrowlyX
 * @since 5/28/2022
 */
object BedwarsTeamUpgradesTicker : BukkitRunnable()
{
    override fun run()
    {
        Bukkit.getOnlinePlayers()
            .filter {
                !it.hasMetadata("spectator")
            }
            .forEach {
                val team = it.team()
                    ?: return@forEach

                val tracker =
                    Tracker.of(team)

                val haste = tracker
                    .upgrades[BedwarsTeamUpgrades.HASTE]

                if (haste != null)
                {
                    if (
                        it.hasPotionEffect(
                            PotionEffectType.FAST_DIGGING
                        )
                    )
                    {
                        return@forEach
                    }

                    it.addPotionEffect(
                        PotionEffect(
                            PotionEffectType.FAST_DIGGING,
                            10000, haste
                        )
                    )
                }
            }
    }
}
