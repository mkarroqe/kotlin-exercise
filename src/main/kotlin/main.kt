import menu.APPLE
import menu.ORANGE

//  Initialize Items ("constants" at runtime)
object menu {
    val APPLE = Item("Apple", 0.60)
    val ORANGE: Item = Item("Orange", 0.25)
    val MENU = listOf(APPLE, ORANGE)
}

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
    order[menu.APPLE] = 0
    order[menu.ORANGE] = 0

//  split at comma
    for (item in orderInput.split(", ")) {
        if (item in validApple) {
            order[menu.APPLE] = order[menu.APPLE]?.plus(1)
        }
        else if (item in validOrange) {
            order[menu.ORANGE] = order[menu.ORANGE]?.plus(1)
        }
        else if (item == "") { continue }
        else {
            error("Invalid order item $item.")
        }
    }

    return order
}

fun getXforYPrice(item: Item, quantity: Int, numItems: Int, numCharged: Int): Double {
    val currPrice = item.price * quantity
    val discount:Double = numItems / (numCharged).toDouble()

    if (quantity % numItems == 0) {
        return (currPrice / discount)
    }
    else {
        val remainder = quantity % numItems
        return ((item.price * (quantity - remainder)) / discount) + (item.price * remainder)
    }
}

fun applyOffers(item: Item, quantity: Int): Double {
//  buy one get one free on Apples (AKA, 2 for 1)
    if ((item == APPLE) && (quantity > 1)) {
        return getXforYPrice(item, quantity, 2, 1)
    }
//  3 for the price of 2 on Oranges
    else if ((item == ORANGE) && (quantity > 2)) {
        return getXforYPrice(item, quantity, 3, 2)
    }
    else {
        return item.price * quantity
    }
}

//  Calculate Total Cost
fun checkout(orderInput: String): Double {
    val order: MutableMap<Item, Int?> = processOrder(orderInput)
    var cost: Double = 0.00

    for ((item, quantity) in order) {
        if (item !in menu.MENU) {
            error("Invalid Item Name: $item.name")
        }
        else {
            var price = quantity?.let { applyOffers(item, it) }
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
