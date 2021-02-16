//  Initialize Items (constants)
val APPLE = Item("Apple", 0.60)
val ORANGE = Item("Orange", 0.25)
val MENU = listOf(APPLE, ORANGE)

class Item(var name: String, var price: Double) {
//  for testing
    override fun toString(): String {
        return name
    }
}

fun processOrder(orderInput: String): MutableMap<Item, Int?> {
//  defining acceptable item inputs
    val validApple = listOf("apple", "Apple")
    val validOrange = listOf("orange", "Orange")

    //  create hash map of item : quantity
    var order = mutableMapOf<Item, Int?>()
    order[APPLE] = 0
    order[ORANGE] = 0

//  split at comma
    for (item in orderInput.split(", ")) {
        if (item in validApple) {
            order[APPLE] = order[APPLE]?.plus(1)
        }
        else if (item in validOrange) {
            order[ORANGE] = order[ORANGE]?.plus(1)
        }
        else if (item == "") { continue }
        else {
            error("Invalid order item $item.")
        }
    }

    return order
}

fun applyOffers(item: Item, quantity: Int?): Double {
    var currPrice = item.price * quantity!!

//  buy one get one free on Apples
    if ((item == APPLE) && (quantity > 1)) {
        if (quantity % 2 == 0) {
            return currPrice / 2
        }
        else {
            return ((item.price * (quantity - 1)) / 2) + item.price
        }
    }
//  3 for the price of 2 on Oranges
    else if ((item == ORANGE) && (quantity > 2)) {
        if (quantity % 3 == 0) {
            return (currPrice / 1.5)
        }
        else {
            val remainder = quantity % 3
            return ((item.price * (quantity - remainder)) / 1.5) + (item.price * remainder)
        }
    }
    else {
        return currPrice
    }
}

//  Calculate Total Cost
fun checkout(orderInput: String): Double {
    val order: MutableMap<Item, Int?> = processOrder(orderInput)
    var cost: Double = 0.00

    for ((item, quantity) in order) {
        if (item !in MENU) {
            error("Invalid Item Name: $item.name")
        }
        else {
            var price = applyOffers(item, quantity)
            if (price != null) {
                cost += price
            }
        }
    }
    return cost
}

fun main(args: Array<String>) {
    println("Please enter your order, separating items with a comma and a space:")
    val orderInput = readLine()
    val totalCost = orderInput?.let { checkout(it) }
    println("The total cost is $totalCost")
}
