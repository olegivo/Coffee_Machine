package machine

fun main() {

    val coffeeMachine = CoffeeMachine(
        waterAmount = 400,
        milkAmount = 540,
        beansAmount = 120,
        disposableCupsAmount = 9,
        moneyAmount = 550
    )

    var command: String?
    do {
        println("Write action (buy, fill, take):")
        command = readLine()
        when (command) {
            "buy" -> {
                println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:")
                when (readLine()) {
                    "1" -> coffeeMachine.espresso()
                    "2" -> coffeeMachine.latte()
                    "3" -> coffeeMachine.cappuccino()
                }
            }
            "fill" -> {
                val water = readInt("Write how many ml of water the coffee do you want to add:")
                val milk = readInt("Write how many ml of milk the coffee do you want to add:")
                val beans = readInt("Write how many grams of coffee beans the coffee do you want to add:")
                val cups = readInt("Write how many disposable cups of coffee do you want to add:")
                coffeeMachine.fill(water, milk, beans, cups)
            }
            "take" -> {
                coffeeMachine.take()
            }
            "remaining" -> {
                coffeeMachine.status()
            }
        }
    } while (command != "exit")
}

private fun readInt(prompt: String): Int {
    println(prompt)
    return readLine()!!.toInt()
}
