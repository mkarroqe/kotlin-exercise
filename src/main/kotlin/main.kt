import Menu.APPLE
import Menu.MENU
import Menu.ORANGE
import Menu.validApple
import Menu.validOrange
import java.lang.IllegalStateException

//  ("constants" at runtime)
object Menu {
    val APPLE = Item("Apple", 0.60)
    val validApple = listOf("apple", "Apple")

    val ORANGE: Item = Item("Orange", 0.25)
    val validOrange = listOf("orange", "Orange")

    val MENU = listOf(APPLE, ORANGE)
}

class Item(var name: String, var price: Double) {
//  for testing
    override fun toString(): String {
        return name
    }
}

class Order(orderInput: String) {
    var completionStatus: Boolean = false
    var totalCost: Double = checkout(orderInput)

    private fun processOrder(orderInput: String): MutableMap<Item, Int?> {
        completionStatus = true;

        //  create hash map of item : quantity
        val order = mutableMapOf<Item, Int?>()
        order[APPLE] = 0
        order[ORANGE] = 0

//      split at comma
        for (item in orderInput.split(", ")) {
            if (item in validApple) {
                order[APPLE] = order[APPLE]?.plus(1)
            } else if (item in validOrange) {
                order[ORANGE] = order[ORANGE]?.plus(1)
            } else if (item == "") {
                continue
            }
            else {
                println("Invalid order item $item.")
                completionStatus = false
                break
            }
        }

        return order
    }

    private fun getXforYPrice(item: Item, quantity: Int, numItems: Int, numCharged: Int): Double {
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

    private fun applyOffers(item: Item, quantity: Int): Double {
//  buy one get one free on Apples (AKA, 2 for 1)
        if ((item == APPLE) && (quantity > 1)) {
            return getXforYPrice(item, quantity, 2, 1)
        }

//  3 for the price of 2 on Oranges
        else if ((item == ORANGE) && (quantity > 2)) {
            return getXforYPrice(item, quantity, 3, 2)
        }

        return item.price * quantity
    }

    //  Calculate Total Cost
    private fun checkout(orderInput: String): Double {
        val order: MutableMap<Item, Int?> = processOrder(orderInput)
        var cost: Double = 0.00

        for ((item, quantity) in order) {
            if (item !in MENU) {
                error("Invalid Item Name: $item.name")
            }
            else {
                val price = quantity?.let { applyOffers(item, it) }
                if (price != null) {
                    cost += price
                }
            }
        }
        return cost
    }
}

// s/o stack overflow https://stackoverflow.com/a/23088000
fun Double.format(digits: Int) = "%.${digits}f".format(this)

fun main() {
    println("Please enter your order, separating items with a comma and a space:")
    val orderInput = readLine()
    val order = orderInput?.let { Order(it) }

    val orderStatus = order?.completionStatus
    print("Your order status is:")
    if (orderStatus == true) {
        print(" COMPLETED.\n")
        val totalCost = order?.totalCost
        println("Total cost: $${totalCost.format(2)}")
    }
    else { print(" CANCELLED.") }
}
