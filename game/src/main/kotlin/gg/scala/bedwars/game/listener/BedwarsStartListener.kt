package gg.scala.bedwars.game.listener

import gg.scala.bedwars.game.ScalaBedwarsGame
import gg.scala.bedwars.game.generator.impl.BedwarsDiamondItemGenerator
import gg.scala.bedwars.game.generator.impl.BedwarsEmeraldItemGenerator
import gg.scala.bedwars.game.generator.impl.BedwarsTeamItemGenerator
import gg.scala.bedwars.shared.arena.BedwarsArena
import gg.scala.bedwars.shared.team.BedwarsCgsGameTeam
import gg.scala.cgs.common.CgsGameEngine
import gg.scala.cgs.common.information.arena.CgsGameArenaHandler
import gg.scala.cgs.common.teams.CgsGameTeamService
import gg.scala.commons.annotations.Listeners
import gg.scala.flavor.inject.Inject
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.block.BlockFace
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.metadata.FixedMetadataValue


@Listeners
object BedwarsStartListener : Listener
{
    @Inject
    lateinit var plugin: ScalaBedwarsGame

    @EventHandler
    fun onStart(event: CgsGameEngine.CgsGameStartEvent)
    {
        val arena = CgsGameArenaHandler.arena as BedwarsArena

        arena.diamondGenerators.forEach {
            BedwarsDiamondItemGenerator(it)
        }

        arena.emeraldGenerators.forEach {
            BedwarsEmeraldItemGenerator(it)
        }

        CgsGameTeamService.teams.values
            .map { it as BedwarsCgsGameTeam }
            .forEach {
                if (it.participants.size > 0)
                {
                    BedwarsTeamItemGenerator(it.spawnPoint!!)
                }

                if (it.participants.size <= 0)
                {
                    it.bedDestroyed = true
                } else
                {
                    val block = it.spawnPoint!!.block
                    it.spawnPoint!!.block.type = Material.BED_BLOCK

                    val bedFoot = block
                        .getRelative(
                            block.getFace(block)
                        )
                        .state

                    val bedHead = bedFoot.block
                        .getRelative(BlockFace.SOUTH)
                        .state

                    bedFoot.type = Material.BED_BLOCK
                    bedHead.type = Material.BED_BLOCK

                    bedFoot.setRawData(0x0.toByte())
                    bedHead.setRawData(0x8.toByte())

                    bedFoot.update(true, false)
                    bedHead.update(true, true)

                    listOf(bedFoot, bedHead)
                        .forEach { state ->
                            state.block.setMetadata(
                                "team", FixedMetadataValue(plugin, it.id)
                            )
                        }

                    it.participants.forEach { id ->
                        val player = Bukkit.getPlayer(id)
                        player.displayName = it.color.toString() + player.displayName
                        player.teleport(it.spawnPoint)
                    }
                }
            }
    }
}