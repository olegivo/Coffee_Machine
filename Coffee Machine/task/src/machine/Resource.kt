package machine

sealed class Resource(amount: Int, private val name: String) {

    var amount = amount
        private set

    fun availableStatus() = "$amount of $name"

    fun remove(quantity: Int) {
        amount -= quantity
    }

    fun add(quantity: Int) {
        amount += quantity
    }

    abstract class ProductionResource(amount: Int, name: String, private val perOneCup: Int) : Resource(amount, name) {
        val availableCups get() = amount / perOneCup
    }

    class Water(amount: Int) : ProductionResource(amount = amount, name = "water", perOneCup = 200)
    class Milk(amount: Int) : ProductionResource(amount = amount, name = "milk", perOneCup = 50)
    class Beans(amount: Int) : ProductionResource(amount = amount, name = "coffee beans", perOneCup = 15)
    class DisposableCups(amount: Int) : ProductionResource(amount = amount, name = "disposable cups", perOneCup = 1)
    class Money(amount: Int) : Resource(amount, "money")
}