package gg.scala.bedwars.game.generator.impl

import com.cryptomorin.xseries.XMaterial
import gg.scala.bedwars.game.generator.BedwarsItemGenerator
import gg.scala.bedwars.shared.team.BedwarsCgsGameTeam
import net.evilblock.cubed.util.bukkit.ItemBuilder
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

class BedwarsTeamItemGenerator(
    location: Location,
    val team: BedwarsCgsGameTeam,
    override val itemMaterial: ItemStack = ItemBuilder.of(Material.IRON_INGOT).build()
) : BedwarsItemGenerator(mapOf(
    1 to 2,
    2 to 1,
    3 to 1,
    4 to 1
), location)
{
    private var goldCooldown = 4
    private var emeraldCooldown = 120

    override fun drop()
    {
        goldCooldown--
        emeraldCooldown--

        super.drop()
        if (goldCooldown == 0)
        {
            location.world.dropItem(
                location, ItemBuilder.of(XMaterial.GOLD_INGOT).build()
            )

            goldCooldown = when (tier) {
                1 -> 4
                2 -> 3
                3 -> 2
                else -> 1
            }
        }

        if (emeraldCooldown == 0)
        {
            if (tier >= 3)
            {
                location.world.dropItem(
                    location, ItemBuilder.of(XMaterial.GOLD_INGOT).build()
                )
            }

            emeraldCooldown = when (tier) {
                1 -> 120
                2 -> 120
                3 -> 120
                else -> 60
            }
        }
    }
}
