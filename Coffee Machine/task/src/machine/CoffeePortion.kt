package machine

sealed class CoffeePortion(
    val water: Int,
    val milk: Int,
    val beans: Int,
    val cost: Int
) {
    object Espresso : CoffeePortion(250, 0, 16, 4)
    object Latte : CoffeePortion(350, 75, 20, 7)
    object Cappuccino : CoffeePortion(200, 100, 12, 6)
}