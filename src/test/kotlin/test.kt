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
        assertEquals(1.20, totalCost)
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
        assertEquals(2.05, totalCost)
    }

    @Test
    fun `Returns Zero for Empty Order`() {
        val orderInput = ""
        val totalCost = checkout(orderInput)
        assertEquals(0.00, totalCost)
    }
}