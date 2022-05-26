package gg.scala.bedwars.game.event

import gg.scala.bedwars.shared.team.BedwarsCgsGameTeam
import gg.scala.cgs.common.CgsGameEngine
import org.bukkit.entity.Player

class BedwarsBedDestroyEvent(
    val team: BedwarsCgsGameTeam,
    val destroyer: Player?
) : CgsGameEngine.CgsGameEvent()
