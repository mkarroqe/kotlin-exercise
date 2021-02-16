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

fun main(args: Array<String>) {
//  Get Order
    println("Please enter your order:")
    val order = listOf<Item>(APPLE, APPLE, ORANGE, APPLE)
    val totalCost = checkout(order)
    println("The total cost is $totalCost")
}