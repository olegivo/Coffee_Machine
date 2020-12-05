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
        coffeeMachine.run(command)
    } while (command != "exit")
}

