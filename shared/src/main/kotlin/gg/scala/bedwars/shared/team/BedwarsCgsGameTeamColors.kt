package gg.scala.bedwars.shared.team

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

    fun map(
        color: ChatColor
    ): String
    {
        return this.mappings[color] ?:
            color.name.lowercase().capitalize()
    }
}

