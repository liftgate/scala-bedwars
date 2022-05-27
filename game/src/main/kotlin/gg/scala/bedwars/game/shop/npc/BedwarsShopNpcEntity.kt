package gg.scala.bedwars.game.shop.npc

import gg.scala.bedwars.game.shop.BedwarsShopMenu
import net.evilblock.cubed.entity.npc.NpcEntity
import net.evilblock.cubed.util.CC
import org.bukkit.Location
import org.bukkit.entity.Player

/**
 * @author GrowlyX
 * @since 5/27/2022
 */
class BedwarsShopNpcEntity(
    location: Location
) : NpcEntity(
    listOf(
        "${CC.B_AQUA}Item Shop",
        "${CC.YELLOW}Click to open!"
    ),
    location.add(0.5, 0.0, 0.5)
)
{
    init
    {
        updateTexture(
            "ewogICJ0aW1lc3RhbXAiIDogMTY1MzY4NzU0NzM0NSwKICAicHJvZmlsZUlkIiA6ICJmNjgwYWIxOTEyYjg0ZjQyYTY4NTZiMTM0NGQ0NzExNCIsCiAgInByb2ZpbGVOYW1lIiA6ICJOb29ieVR1YmUiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTc1N2Q1NGE0MDRhNjdkYTUzY2U5MThlMTE3N2UxZTBjNTJiMzdkYjA0MGRiMDYzNWRlYzExNzkwYzJhZGZiZSIKICAgIH0KICB9Cn0=",
            "b5qqL4TrR5iGbwbP1vHJD7Knnz/U8QDF0yZniFZ59CdyRRc+3Vd6JDUVhDWgy04AlT6IBa3DdwVPclkcHxJ3ay+OZ8c31HMjT1aGCFfYO1JIiK1wNEmmdlOluq53eLu3mPPFjvNi+5dG47Tp8aXbSU/b4SLB3iXELxPjf9UtTTM4xg1wp8jLwgUabBR3xl4Z0yD8dIdcZT2BMq047Qk4+gedFK0Cb09AWGi61J6mkmx1jQ2y8MVuI+3njORFbhdXjTzGOEtepeyWUxtCo9pXYl5bNe1r0mW0Np+BONAXdsMEkKnnYec29iPXB0I7VEZAP9eEYllbIr8BhLKwIK5Rnf6feeTsTzoYnoFEAJIegBZS9mxU/MQojmDp3vKd03KOoHZJ8UWiTqPpRnqnawkXo5OcxhwaB2EsqY0efI8K80G1e8i1AeG6HbccxrDzzfQAsE45k73jgXEPo9GbBU8zj3m+vzvqRHKjl1Mn0hYJFea2m4G/IJggL2kAIpYJNjxwTrmcrk9/HuuCXINDmk8OAki21r5BW6es6XwJ2Ld7yv7XZ14o2JzMvj0YnTyf6UK6vG5dgC8kwAfWz042uHLrYiRPto8aA9g01cSSmslGYUPBpPLfhkEocQ2PSY4XXB50SbBN5X13pf4QF3hY0jF2sbsnHYP4EaaJMURojRSU/7k="
        )
    }

    override fun onRightClick(player: Player)
    {
        BedwarsShopMenu().openMenu(player)
    }
}
