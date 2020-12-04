package machine

fun main() {
    println("Write how many cups of coffee you will need:")
    val cupsCount = readLine()!!.toInt()
    println("For $cupsCount cups of coffee you will need:")
    println("${cupsCount * 200} ml of water")
    println("${cupsCount * 50} ml of milk")
    println("${cupsCount * 15} g of coffee beans")
}
