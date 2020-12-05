package machine

class CoffeeMachine(
    waterAmount: Int,
    milkAmount: Int,
    beansAmount: Int,
    disposableCupsAmount: Int,
    moneyAmount: Int
) {
    private val water = Resource.Water(waterAmount)
    private val milk = Resource.Milk(milkAmount)
    private val beans = Resource.Beans(beansAmount)
    private val disposableCups = Resource.DisposableCups(disposableCupsAmount)
    private var money = Resource.Money(moneyAmount)
    private val productionResources = listOf(water, milk, beans, disposableCups)
    private val resources = productionResources + money

    fun run(command: String?) {
        when (command) {
            "buy" -> {
                println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:")
                when (readLine()) {
                    "1" -> this.espresso()
                    "2" -> this.latte()
                    "3" -> this.cappuccino()
                }
            }
            "fill" -> {
                val water = readInt("Write how many ml of water the coffee do you want to add:")
                val milk = readInt("Write how many ml of milk the coffee do you want to add:")
                val beans = readInt("Write how many grams of coffee beans the coffee do you want to add:")
                val cups = readInt("Write how many disposable cups of coffee do you want to add:")
                this.fill(water, milk, beans, cups)
            }
            "take" -> {
                this.take()
            }
            "remaining" -> {
                this.status()
            }
        }
    }

    fun status() {
        println("The coffee machine has:")
        resources.forEach { println(it.availableStatus()) }
    }

    fun espresso() {
        make(CoffeePortion.Espresso)
    }

    fun latte() {
        make(CoffeePortion.Latte)
    }

    fun cappuccino() {
        make(CoffeePortion.Cappuccino)
    }

    private fun make(portion: CoffeePortion) {
        val notEnough = productionResources.filter { it.availableCups <= 0 }
        if (notEnough.isEmpty()) {
            println("I have enough resources, making you a coffee!")
            water.remove(portion.water)
            milk.remove(portion.milk)
            beans.remove(portion.beans)
            disposableCups.remove(1)
            money.add(portion.cost)
        } else {
            notEnough.forEach { println("Sorry, not enough ${it.name}!") }
        }
    }

    fun fill(waterAmount: Int, milkAmount: Int, beansAmount: Int, cupsAmount: Int) {
        water.add(waterAmount)
        milk.add(milkAmount)
        beans.add(beansAmount)
        disposableCups.add(cupsAmount)
    }

    fun take() {
        val allMoney = money.amount
        println("I gave you \$$allMoney")
        money.remove(allMoney)
    }

    private fun readInt(prompt: String): Int {
        println(prompt)
        return readLine()!!.toInt()
    }
}