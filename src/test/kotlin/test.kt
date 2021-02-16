import org.junit.Test
import java.lang.IllegalStateException
import kotlin.test.assertEquals
import kotlin.test.fail

class OrderServiceTest {
//    @Test(expected=IllegalStateException::class)
//    fun `Rejects Invalid Input`() {
//        val order = Order("appleapple orange")
//    }

    @Test
    fun `Returns Correct Price of One Apple LowerCase`() {
        val order = Order("apple")
        val totalCost = order.totalCost
        assertEquals(0.60, totalCost)
    }

    @Test
    fun `Returns Correct Price of One Apple Capitalized`() {
        val order = Order("Apple")
        val totalCost = order.totalCost
        assertEquals(0.60, totalCost)
    }

    @Test
    fun `Returns Correct Price of One Orange Lowercase`() {
        val order = Order("orange")
        val totalCost = order.totalCost
        assertEquals(0.25, totalCost)
    }

    @Test
    fun `Returns Correct Price of One Orange Capitalized`() {
        val order = Order("Orange")
        val totalCost = order.totalCost
        assertEquals(0.25, totalCost)
    }

    @Test
    fun `Returns Correct Price of Multiple (4) Apples`() {
        val order = Order("apple, apple, apple, apple")
        val totalCost = order.totalCost
        assertEquals(1.20, totalCost)
    }

    @Test
    fun `Returns Correct Price of Multiple (4) Oranges`() {
        val order = Order("orange, orange, orange, orange")
        val totalCost = order.totalCost
        assertEquals(0.75, totalCost)
    }

    @Test
    fun `Returns Correct Price of Multiple (2) Oranges Diff Caps`() {
        val order = Order("orange, Orange")
        val totalCost = order.totalCost
        assertEquals(0.50, totalCost)
    }

    @Test
    fun `Returns Correct Price of Multiple (2) Apples Diff Caps`() {
        val order = Order("apple, Apple")
        val totalCost = order.totalCost
        assertEquals(0.60, totalCost)
    }

    @Test
    fun `Returns Correct Price of Mixed Order`() {
        val order = Order("apple, Orange")
        val totalCost = order.totalCost
        assertEquals(0.85, totalCost)
    }

    @Test
    fun `Returns Correct Price of Given Mixed Order`() {
        val order = Order("apple, apple, orange, apple")
        val totalCost = order.totalCost
        assertEquals(1.45, totalCost)
    }

    @Test
    fun `Returns Zero for Empty Order`() {
        val order = Order("")
        val totalCost = order.totalCost
        assertEquals(0.00, totalCost)
    }
}

class SimpleOffersTest {
    @Test
    fun `3 Oranges for Price of 2`() {
        val order = Order("orange, orange, orange")
        val totalCost = order.totalCost
        assertEquals(0.50, totalCost)
    }
    @Test
    fun `BOGO 2 Apples`() {
        val order = Order("apple, apple")
        val totalCost = order.totalCost
        assertEquals(0.60, totalCost)
    }
    @Test
    fun `3 oranges 2 apples`() {
        val order = Order("apple, apple, orange, orange, orange")
        val totalCost = order.totalCost
        assertEquals(1.10, totalCost)
    }
    @Test
    fun `6 oranges 4 apples`() {
        val order = Order("apple, apple, apple, apple, orange, orange, orange, orange, orange, orange")
        val totalCost = order.totalCost
        assertEquals(2.20, totalCost)
    }
}