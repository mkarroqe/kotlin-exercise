//  Initialize Items (constants)
val APPLE = Item("Apple", 0.60)
val ORANGE = Item("Orange", 0.25)
val MENU = listOf(APPLE, ORANGE)

class Item(var name: String, var price: Double) {}

fun processOrder(orderInput: String): List<Item> {
    var order = mutableListOf<Item>()
    val validApple = listOf("apple", "Apple")
    val validOrange = listOf("orange", "Orange")

//  splice at comma
    for (item in orderInput.split(", ")) {
        if (item in validApple) { order.add(APPLE) }
        else if (item in validOrange) { order.add(ORANGE) }
        else if (item == "") { continue }
        else {
            error("Invalid order item $item.")
        }
    }

    return order
}

//  Calculate Total Cost
fun checkout(orderInput: String): Double {
    val order: List<Item> = processOrder(orderInput)
    var cost: Double = 0.0
    for (item in order) {
//      Error Check
        if (item !in MENU) {
            error("Invalid Item Name: $item.name")
        }
        else { cost += item.price }
    }
    return cost
}

fun main(args: Array<String>) {
    println("Please enter your order, separating items with a comma and a space:")
    val orderInput = readLine()
    val totalCost = orderInput?.let { checkout(it) }
    println("The total cost is $totalCost")
}