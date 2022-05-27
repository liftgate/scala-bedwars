package gg.scala.bedwars.shared.team

import com.cryptomorin.xseries.XMaterial
import org.bukkit.entity.Player

enum class BedwarsCgsGameTeamUpgrade(
    val item: XMaterial,
    val names: Map<Int, String>,
    val costs: Map<String, Map<Int, Int>>,
    val context: (BedwarsCgsGameTeam) -> Unit = {}
)
{
    SHARPNESS(
        XMaterial.IRON_SWORD,
        mapOf(1 to "Sharpness I"),
        mapOf(
            "eight" to mapOf(1 to 4),
            "four" to mapOf(1 to 8)
        )
    ),
    PROTECTION(
        XMaterial.IRON_CHESTPLATE,
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
        )
    ),
    HASTE(
        XMaterial.DIAMOND_PICKAXE,
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
        mapOf(1 to "Heal Pool"),
        mapOf(
            "eight" to mapOf(1 to 1),
            "four" to mapOf(1 to 3)
        )
    ),
    BLINDNESS_TRAP(
        XMaterial.TRIPWIRE_HOOK,
        mapOf(1 to "It's a trap!"),
        emptyMap()
    ),
    COUNTER_TRAP(
        XMaterial.FEATHER,
        mapOf(1 to "Counter-Offensive Trap"),
        emptyMap()
    ),
    ALARM_TRAP(
        XMaterial.REDSTONE_TORCH,
        mapOf(1 to "Alarm Trap"),
        emptyMap()
    ),
    FATIGUE_TRAP(
        XMaterial.DIAMOND_PICKAXE,
        mapOf(1 to "Miner Fatigue Trap"),
        emptyMap()
    );

    val maxLevel: Int get() {
        return names.size
    }
}