package gg.scala.bedwars.game.generator.hologram

import gg.scala.bedwars.game.generator.BedwarsProminentItemGenerator
import gg.scala.bedwars.game.generator.tier.BedwarsItemGeneratorTierIncrementer
import net.evilblock.cubed.entity.hologram.updating.UpdatingHologramEntity
import net.evilblock.cubed.util.CC
import org.bukkit.Location

/**
 * @author GrowlyX
 * @since 5/27/2022
 */
class BedwarsUpdatingHologramEntity(
    location: Location,
    private val generator: BedwarsProminentItemGenerator
) : UpdatingHologramEntity("GeneratorX", location)
{
    override fun getNewLines() =
        listOf(
            "${generator.name} ${
                BedwarsItemGeneratorTierIncrementer
                    .numerals[generator.tier]
            }",
            "${CC.SEC}Spawns in ${CC.GREEN}${generator.cooldown}${CC.SEC} seconds!"
        )

    override fun getTickInterval() = 900L
}
