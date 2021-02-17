import Menu.APPLE_STOCK
import Menu.ORANGE_STOCK
import org.junit.Test
import java.lang.IllegalStateException
import kotlin.test.assertEquals
import kotlin.test.fail

class OrderServiceTest {
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

class NotificationTest {
    @Test
    fun `Successful order notification`() {
        val order = Order("apple, orange")
        val status = order.completionStatus
        assertEquals(true, status)
    }

    fun `Failed order notification`() {
        val order = Order("apple, aksjdfh;ah")
        val status = order.completionStatus
        assertEquals(false, status)
    }
}

class StockTest {
    val origNumApples = APPLE_STOCK
    private fun resetMenu() {
        APPLE_STOCK = origNumApples
        ORANGE_STOCK = origNumApples
    }

    @Test
    fun `In stock order success`() {
        val order = Order("apple, orange, apple, orange, orange")
        val status = order.completionStatus
        assertEquals(true, status)
    }

    @Test
    fun `Oranges out of stock`() {
        val order = Order("orange, orange, orange, orange, orange, orange")
        val status = order.completionStatus
        assertEquals(false, status)
        resetMenu()
    }

    @Test
    fun `Apples out of stock`() {
        val order = Order("apple, apple, apple, apple, apple, apple, orange")
        val status = order.completionStatus
        assertEquals(false, status)
        resetMenu()
    }
}