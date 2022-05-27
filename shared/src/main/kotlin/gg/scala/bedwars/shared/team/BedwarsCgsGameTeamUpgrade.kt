package gg.scala.bedwars.shared.team

enum class BedwarsCgsGameTeamUpgrade(
    val names: Map<Int, String>
)
{
    SHARPNESS(
        mapOf(1 to "Sharpness I")
    ),
    PROTECTION(
        mapOf(
            1 to "Protection I",
            2 to "Protection II",
            3 to "Protection III",
            4 to "Protection IV"
        )
    ),
    HASTE(
        mapOf(
            1 to "Maniac Miner I",
            2 to "Maniac Miner II",
        )
    ),
    FORGE(
        mapOf(
            1 to "Iron Forge",
            2 to "Gold Forge",
            3 to "Spawn Emerald",
            4 to "Molten Forge"
        )
    ),
    HEAL_POOL(
        mapOf(1 to "Heal Pool")
    ),
    BLINDNESS_TRAP(
        mapOf(1 to "It's a trap!")
    ),
    COUNTER_TRAP(
        mapOf(1 to "Counter-Offensive Trap")
    ),
    ALARM_TRAP(
        mapOf(1 to "Alarm Trap")
    ),
    FATIGUE_TRAP(
        mapOf(1 to "Miner Fatigue Trap")
    );

    val maxLevel: Int get() {
        return names.size
    }
}