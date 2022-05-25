package gg.scala.bedwars.game

import gg.scala.bedwars.shared.BedwarsCgsInfo
import gg.scala.bedwars.shared.arena.BedwarsArena
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
        PluginDependency("CGS-Engine")
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

        val modeClassObject = modeClass
            .kotlin.objectInstance!!
        CgsGameArenaHandler.configure(modeClassObject as CgsGameMode)

        val engine = ScalaBedwarsGameEngine(
            this, BedwarsCgsInfo,
            modeClassObject
        )

        engine.initialLoad()
    }
}
