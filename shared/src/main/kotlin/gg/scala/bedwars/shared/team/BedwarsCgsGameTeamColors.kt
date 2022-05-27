package gg.scala.bedwars.shared.team

import net.kyori.adventure.platform.facet.Facet.Chat
import org.bukkit.ChatColor

/**
 * @author GrowlyX
 * @since 5/26/2022
 */
object BedwarsCgsGameTeamColors
{
    private val mappings = mapOf(
        ChatColor.LIGHT_PURPLE to "Pink"
    )

    fun fromId(id: Int) : ChatColor {
        return when (id)
        {
            1 -> ChatColor.RED
            2 -> ChatColor.BLUE
            3 -> ChatColor.GREEN
            4 -> ChatColor.YELLOW
            5 -> ChatColor.AQUA
            6 -> ChatColor.WHITE
            7 -> ChatColor.LIGHT_PURPLE
            8 -> ChatColor.GRAY
            else -> ChatColor.BLACK
        }
    }

    fun map(
        color: ChatColor
    ): String
    {
        return this.mappings[color] ?:
            color.name.lowercase().capitalize()
    }
}

