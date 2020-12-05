package machine

fun main() {
    val water = readInt("Write how many ml of water the coffee machine has:")
    val milk = readInt("Write how many ml of milk the coffee machine has:")
    val beans = readInt("Write how many grams of coffee beans the coffee machine has:")
    val cups = readInt("Write how many cups of coffee you will need:")

    CoffeeMachine(water, milk, beans)
        .run(cups)
}

private fun readInt(prompt: String): Int {
    println(prompt)
    return readLine()!!.toInt()
}

sealed class Resource(private val amount: Int, private val perOneCup: Int) {
    val availableCups get() = amount / perOneCup

    class Water(amount: Int) : Resource(amount, 200)
    class Milk(amount: Int) : Resource(amount, 50)
    class Beans(amount: Int) : Resource(amount, 15)
}

class CoffeeMachine(
    waterAmount: Int,
    milkAmount: Int,
    beansAmount: Int
) {
    private val water = Resource.Water(waterAmount)
    private val milk = Resource.Milk(milkAmount)
    private val beans = Resource.Beans(beansAmount)
    private val resources = listOf(water, milk, beans)

    fun run(cupsCount: Int) {
        val minReminder = resources
            .map {
                it.availableCups - cupsCount
            }
            .minOrNull()!!
        if (minReminder >= 0) {
            print("Yes, I can make that amount of coffee")
            println(if (minReminder > 0) " (and even $minReminder more than that)" else "")
        } else {
            println("No, I can make only ${cupsCount + minReminder} cup(s) of coffee")
        }
    }
}

