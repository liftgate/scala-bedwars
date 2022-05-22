package gg.scala.bedwars.game

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
        PluginDependency("Lemon")
    ]
)
class ScalaBedwarsGame : ExtendedScalaPlugin()
