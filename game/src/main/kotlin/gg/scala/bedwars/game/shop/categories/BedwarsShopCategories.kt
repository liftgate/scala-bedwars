package gg.scala.bedwars.game.shop.categories

import gg.scala.bedwars.game.shop.BedwarsShopCategory

/**
 * @author GrowlyX
 * @since 5/27/2022
 */
object BedwarsShopCategories
{
    val categories =
        mutableListOf<BedwarsShopCategory>()

    fun register(
        vararg categories: BedwarsShopCategory
    )
    {
        categories.forEach {
            this.categories.add(it)
        }
    }
}
