//  Initialize Items (constants)
val APPLE = Item("Apple", 0.60)
val ORANGE = Item("Orange", 0.25)
val MENU = listOf(APPLE, ORANGE)

class Item(var name: String, var price: Double) {}

//  Calculate Total Cost
fun checkout(order: List<Item>): Double {
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

fun processOrder(orderInput: String): List<Item> {
    var order = mutableListOf<Item>()
    val validApple = listOf("apple", "Apple")
    val validOrange = listOf("orange", "Orange")

//  splice at comma
    for (item in orderInput.split(", ")) {
        if (item in validApple) { order.add(APPLE) }
        else if (item in validOrange) {order.add(ORANGE) }
        else {
            error("Invalid order item $item.")
        }
    }

    return order
}

fun main(args: Array<String>) {
    println("Please enter your order:")
    val orderInput = readLine()
    val order = orderInput?.let { processOrder(it) }
    val totalCost = order?.let { checkout(it) }
    println("The total cost is $totalCost")
}