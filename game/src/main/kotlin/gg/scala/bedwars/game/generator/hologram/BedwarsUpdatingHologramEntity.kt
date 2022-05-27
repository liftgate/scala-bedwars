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
    @Transient
    private val generator: BedwarsProminentItemGenerator
) : UpdatingHologramEntity("GeneratorX", location)
{
    override fun getNewLines() =
        listOf(
            "${generator.name}${
                if (numeral() == null)
                {
                    ""
                } else
                {
                    " ${numeral()}"
                }
            }",
            "${CC.SEC}Spawns in ${CC.GREEN}${generator.cooldown}${CC.SEC}..."
        )

    private fun numeral() = BedwarsItemGeneratorTierIncrementer
        .numerals[generator.tier - 1]

    override fun getTickInterval() = 900L
}
