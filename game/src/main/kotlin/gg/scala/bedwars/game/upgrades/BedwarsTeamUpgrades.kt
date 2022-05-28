package gg.scala.bedwars.game.upgrades

import com.cryptomorin.xseries.XMaterial
import gg.scala.bedwars.game.armor.BedwarsArmorService
import gg.scala.bedwars.game.loadout.BedwarsLoadoutService
import gg.scala.bedwars.shared.team.BedwarsCgsGameTeam
import org.bukkit.entity.Player

enum class BedwarsTeamUpgrades(
    val item: XMaterial,
    val display: String,
    val position: Int,
    val names: Map<Int, String>,
    val costs: Map<String, Map<Int, Int>>,
    val context: (Player) -> Unit = {}
)
{
    SHARPNESS(
        XMaterial.IRON_SWORD,
        "Sharpness", 10,
        mapOf(1 to "Sharpness I"),
        mapOf(
            "eight" to mapOf(1 to 4),
            "four" to mapOf(1 to 8)
        ),
        context = {
            val swords = it.inventory.contents
                .filter { stack ->
                    stack != null && stack.type.name.contains("SWORD")
                }

            swords.forEach { stack ->
                BedwarsLoadoutService
                    .affectItem(it, stack)
            }
        }
    ),
    PROTECTION(
        XMaterial.IRON_CHESTPLATE,
        "Protection", 11,
        mapOf(
            1 to "Protection I",
            2 to "Protection II",
            3 to "Protection III",
            4 to "Protection IV"
        ),
        mapOf(
            "eight" to mapOf(
                1 to 2,
                2 to 4,
                3 to 8,
                4 to 16
            ),
            "four" to mapOf(
                1 to 5,
                2 to 10,
                3 to 20,
                4 to 30
            )
        ),
        context = {
            BedwarsArmorService.applyArmor(it)
        }
    ),
    HASTE(
        XMaterial.GOLDEN_PICKAXE,
        "Maniac Miner", 12,
        mapOf(
            1 to "Maniac Miner I",
            2 to "Maniac Miner II",
        ),
        mapOf(
            "eight" to mapOf(
                1 to 2,
                2 to 4
            ),
            "four" to mapOf(
                1 to 4,
                2 to 6
            )
        )
    ),
    FORGE(
        XMaterial.FURNACE,
        "Generator Upgrades", 19,
        mapOf(
            1 to "Iron Forge",
            2 to "Gold Forge",
            3 to "Spawn Emerald",
            4 to "Molten Forge"
        ),
        mapOf(
            "eight" to mapOf(
                1 to 2,
                2 to 4,
                3 to 8,
                4 to 16
            ),
            "four" to mapOf(
                1 to 4,
                2 to 8,
                3 to 12,
                4 to 16
            )
        )
    ),
    HEAL_POOL(
        XMaterial.BEACON,
        "Heal Pool", 20,
        mapOf(1 to "Heal Pool"),
        mapOf(
            "eight" to mapOf(1 to 1),
            "four" to mapOf(1 to 3)
        )
    ),
    DRAGON_BUFF(
        XMaterial.DRAGON_EGG, "Dragon Buff", 21,
        mapOf(1 to "Dragon Buff"),
        mapOf(
            "eight" to mapOf(1 to 1),
            "four" to mapOf(1 to 3)
        )
    ),

    BLINDNESS_TRAP(
        XMaterial.TRIPWIRE_HOOK,
        "Blindness Trap", 14,
        mapOf(1 to "It's a trap!"),
        mapOf(
            "eight" to mapOf(1 to 1),
            "four" to mapOf(1 to 3)
        )
    ),
    COUNTER_TRAP(
        XMaterial.FEATHER, "Counter-Offensive Trap", 15,
        mapOf(1 to "Counter-Offensive Trap"),
        mapOf(
            "eight" to mapOf(1 to 1),
            "four" to mapOf(1 to 3)
        )
    ),
    ALARM_TRAP(
        XMaterial.REDSTONE_TORCH,
        "Alarm Trap", 16,
        mapOf(1 to "Alarm Trap"),
        mapOf(
            "eight" to mapOf(1 to 1),
            "four" to mapOf(1 to 3)
        )
    ),
    FATIGUE_TRAP(
        XMaterial.IRON_PICKAXE,
        "Miner Fatigue", 24,
        mapOf(1 to "Miner Fatigue Trap"),
        mapOf(
            "eight" to mapOf(1 to 1),
            "four" to mapOf(1 to 3)
        )
    );

    val maxLevel = names.size
}
