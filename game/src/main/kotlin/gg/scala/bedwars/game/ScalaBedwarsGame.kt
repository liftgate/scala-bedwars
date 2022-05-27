package gg.scala.bedwars.game

import gg.scala.bedwars.game.generator.hologram.BedwarsUpdatingHologramEntity
import gg.scala.bedwars.game.shop.npc.BedwarsShopNpcEntity
import gg.scala.bedwars.game.shop.npc.BedwarsTeamUpgradesNpcEntity
import gg.scala.bedwars.game.generator.tier.BedwarsItemGeneratorTierIncrementer
import gg.scala.bedwars.game.shop.categories.BedwarsShopArmorCategory
import gg.scala.bedwars.game.shop.categories.BedwarsShopBlockCategory
import gg.scala.bedwars.game.shop.categories.BedwarsShopCategories
import gg.scala.bedwars.game.shop.categories.BedwarsShopRangedCategory
import gg.scala.bedwars.shared.BedwarsCgsInfo
import gg.scala.bedwars.shared.arena.BedwarsArena
import gg.scala.cgs.common.CgsGameEngine
import gg.scala.cgs.common.information.arena.CgsGameArenaHandler
import gg.scala.cgs.common.information.mode.CgsGameMode
import gg.scala.commons.ExtendedScalaPlugin
import gg.scala.commons.annotations.container.ContainerEnable
import gg.scala.commons.config.annotations.ContainerConfig
import me.lucko.helper.Schedulers
import me.lucko.helper.plugin.ap.Plugin
import me.lucko.helper.plugin.ap.PluginDependency
import net.evilblock.cubed.entity.EntityHandler
import org.bukkit.entity.Player

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
                BedwarsShopBlockCategory.category,
                BedwarsShopRangedCategory.category,
                BedwarsShopArmorCategory.category
            )
        BedwarsItemGeneratorTierIncrementer
            .runTaskTimerAsynchronously(
                this, 0L, 20L
            )
    }
}
