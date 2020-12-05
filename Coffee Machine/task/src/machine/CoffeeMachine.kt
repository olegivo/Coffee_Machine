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

    fun run(cupsCount: Int) {
        val minReminder = productionResources
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
}