import org.junit.Test
import java.lang.IllegalStateException
import kotlin.test.assertEquals
import kotlin.test.fail

class OrderServiceTest {
    @Test(expected=IllegalStateException::class)
    fun `Rejects Invalid Input`() {
        val orderInput = "appleapple orange"
        val totalCost = checkout(orderInput)
    }

    @Test
    fun `Returns Correct Price of One Apple`() {
        val orderInput = "apple"
        val totalCost = checkout(orderInput)
        assertEquals(0.60, totalCost)
    }

    @Test
    fun `Returns Correct Price of One Orange`() {
        val orderInput = "orange"
        val totalCost = checkout(orderInput)
        assertEquals(0.25, totalCost)
    }

    @Test
    fun `Returns Correct Price of Multiple Same Items`() {
        val orderInput = "apple, apple"
        val totalCost = checkout(orderInput)
        assertEquals(0.60, totalCost)
    }

    @Test
    fun `Returns Correct Price of Multiple Same Diff Caps`() {
        val orderInput = "orange, Orange"
        val totalCost = checkout(orderInput)
        assertEquals(0.50, totalCost)
    }

    @Test
    fun `Returns Correct Price of Mixed Order`() {
        val orderInput = "apple, Orange"
        val totalCost = checkout(orderInput)
        assertEquals(0.85, totalCost)
    }

    @Test
    fun `Returns Correct Price of Given Mixed Order`() {
        val orderInput = "apple, apple, orange, apple"
        val totalCost = checkout(orderInput)
        assertEquals(1.45, totalCost)
    }

    @Test
    fun `Returns Zero for Empty Order`() {
        val orderInput = ""
        val totalCost = checkout(orderInput)
        assertEquals(0.00, totalCost)
    }
}

class SimpleOffersTest {
    @Test
    fun `3 Oranges for Price of 2`() {
        val orderInput = "orange, orange, orange"
        val totalCost = checkout(orderInput)
        assertEquals(0.50, totalCost)
    }
    @Test
    fun `BOGO 2 Apples`() {
        val orderInput = "apple, apple"
        val totalCost = checkout(orderInput)
        assertEquals(0.60, totalCost)
    }
    @Test
    fun `3 oranges 2 apples`() {
        val orderInput = "apple, apple, orange, orange, orange"
        val totalCost = checkout(orderInput)
        assertEquals(1.10, totalCost)
    }
    @Test
    fun `6 oranges 4 apples`() {
        val orderInput = "apple, apple, apple, apple, orange, orange, orange, orange, orange, orange"
        val totalCost = checkout(orderInput)
        assertEquals(2.20, totalCost)
    }
}