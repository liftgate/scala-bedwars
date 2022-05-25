package gg.scala.bedwars.game

import gg.scala.bedwars.shared.BedwarsCgsInfo
import gg.scala.bedwars.shared.arena.BedwarsArena
import gg.scala.cgs.common.CgsGameEngine
import gg.scala.cgs.common.information.arena.CgsGameArenaHandler
import gg.scala.cgs.common.information.mode.CgsGameMode
import gg.scala.commons.ExtendedScalaPlugin
import me.lucko.helper.plugin.ap.Plugin
import me.lucko.helper.plugin.ap.PluginDependency

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
class ScalaBedwarsGame : ExtendedScalaPlugin() {

    override fun enable()
    {
        val orchestration =
            loadConfig("orchestration.yml")

        val mode = orchestration.getString("mode")
            ?: "gg.scala.bedwars.shared.gamemode.BedwarsSoloGameMode"

        val modeClass = Class.forName(mode)

        val modeClassObject = modeClass.kotlin.objectInstance!! as CgsGameMode
        CgsGameArenaHandler.configure(modeClassObject)

        val engine = ScalaBedwarsGameEngine(
            this, BedwarsCgsInfo,
            modeClassObject
        )

        CgsGameEngine.INSTANCE = engine
        engine.initialLoad()
    }
}
