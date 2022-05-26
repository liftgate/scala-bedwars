package gg.scala.bedwars.game

import gg.scala.bedwars.game.generator.tier.BedwarsItemGeneratorTierIncrementer
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

        CgsGameEngine.INSTANCE = engine
        engine.initialLoad()

        BedwarsItemGeneratorTierIncrementer
            .runTaskTimerAsynchronously(
                this, 0L, 20L
            )
    }
}
