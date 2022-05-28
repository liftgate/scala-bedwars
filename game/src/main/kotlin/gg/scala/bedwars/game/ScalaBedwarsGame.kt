package gg.scala.bedwars.game

import gg.scala.bedwars.game.generator.hologram.BedwarsUpdatingHologramEntity
import gg.scala.bedwars.game.generator.tier.BedwarsItemGeneratorTierIncrementer
import gg.scala.bedwars.game.shop.categories.*
import gg.scala.bedwars.game.shop.categories.BedwarsShopBlockCategory.team
import gg.scala.bedwars.game.shop.npc.BedwarsShopNpcEntity
import gg.scala.bedwars.game.shop.npc.BedwarsTeamUpgradesNpcEntity
import gg.scala.bedwars.game.upgrades.BedwarsTeamUpgradesTicker
import gg.scala.bedwars.game.upgrades.BedwarsTeamUpgradesTracker
import gg.scala.bedwars.game.upgrades.BedwarsTeamUpgradesTrackerService
import gg.scala.bedwars.shared.BedwarsCgsInfo
import gg.scala.bedwars.shared.team.BedwarsCgsGameTeam
import gg.scala.cgs.common.CgsGameEngine
import gg.scala.cgs.common.information.arena.CgsGameArenaHandler
import gg.scala.cgs.common.information.mode.CgsGameMode
import gg.scala.cgs.common.teams.CgsGameTeamService
import gg.scala.commons.ExtendedScalaPlugin
import gg.scala.commons.annotations.container.ContainerEnable
import gg.scala.commons.config.annotations.ContainerConfig
import gg.scala.lemon.channel.channels.DefaultChatChannel
import me.lucko.helper.plugin.ap.Plugin
import me.lucko.helper.plugin.ap.PluginDependency
import net.evilblock.cubed.entity.EntityHandler
import net.evilblock.cubed.util.CC
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer
import org.bukkit.entity.Player
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

/**
 * @author GrowlyX
 * @since 5/21/2022
 */
@Plugin(
    name = "bedwars",
    depends = [
        PluginDependency("scala-commons"),
        PluginDependency("CGS-Engine"),
        PluginDependency("Lemon")
    ]
)
@ContainerConfig(
    value = "orchestration",
    model = ScalaBedwarsOrchestration::class
)
class ScalaBedwarsGame : ExtendedScalaPlugin()
{
    @ContainerEnable
    fun containerEnable()
    {
        val modeClass = Class.forName(
            this.config<ScalaBedwarsOrchestration>().mode
        )

        val modeClassObject =
            modeClass.kotlin.objectInstance!! as CgsGameMode

        CgsGameArenaHandler.configure(modeClassObject)

        val engine = ScalaBedwarsGameEngine(
            this, BedwarsCgsInfo,
            modeClassObject
        )

        this.server.worlds.forEach {
            for (entity in it.entities)
            {
                if (entity is Player)
                    return

                entity.remove()
            }
        }

        EntityHandler
            .getEntitiesByType(
                BedwarsUpdatingHologramEntity::class.java
            )
            .forEach {
                EntityHandler.forgetEntity(it)
            }

        EntityHandler
            .getEntitiesByType(
                BedwarsShopNpcEntity::class.java
            )
            .forEach {
                EntityHandler.forgetEntity(it)
            }

        EntityHandler
            .getEntitiesByType(
                BedwarsTeamUpgradesNpcEntity::class.java
            )
            .forEach {
                EntityHandler.forgetEntity(it)
            }

        EntityHandler.close()

        CgsGameEngine.INSTANCE = engine
        engine.initialLoad()

        BedwarsShopCategories
            .register(
                BedwarsShopMeleeCategory.category,
                BedwarsShopBlockCategory.category,
                BedwarsShopRangedCategory.category,
                BedwarsShopArmorCategory.category,
                BedwarsShopPotionCategory.category,
                BedwarsShopUtilityCategory.category
            )

        DefaultChatChannel
            .provideAdditionalPrefix {
                val team = it.team()
                    ?: return@provideAdditionalPrefix Component.empty()

                LegacyComponentSerializer.legacySection()
                    .deserialize(
                        "${team.color}[${
                            team.name
                        }] ${CC.WHITE}"
                    )
            }

        CgsGameTeamService.teams
            .forEach { (_, u) ->
                val team = u as BedwarsCgsGameTeam

                BedwarsTeamUpgradesTrackerService.trackers[team.id] =
                    BedwarsTeamUpgradesTracker(team)
            }

        val executor = Executors
            .newSingleThreadScheduledExecutor()

        executor.scheduleAtFixedRate(
            BedwarsTeamUpgradesTicker,
            0L, 1L, TimeUnit.SECONDS
        )

        BedwarsItemGeneratorTierIncrementer
            .runTaskTimerAsynchronously(
                this, 0L, 20L
            )
    }
}
