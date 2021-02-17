import Menu.APPLE_STOCK
import Menu.ORANGE_STOCK
import org.junit.Test
import java.lang.IllegalStateException
import kotlin.test.assertEquals
import kotlin.test.fail
import org.junit.runners.Suite

import org.junit.runner.RunWith
import org.junit.runners.Suite.SuiteClasses


val origNumApples = APPLE_STOCK
fun resetMenu() {
    APPLE_STOCK = origNumApples
    ORANGE_STOCK = origNumApples
}

class OrderServiceTest {
    @Test
    fun `Returns Correct Price of One Apple LowerCase`() {
        val order = Order("apple")
        val totalCost = order.totalCost
        assertEquals(0.60, totalCost)
        resetMenu()
    }

    @Test
    fun `Returns Correct Price of One Apple Capitalized`() {
        val order = Order("Apple")
        val totalCost = order.totalCost
        assertEquals(0.60, totalCost)
        resetMenu()
    }

    @Test
    fun `Returns Correct Price of One Orange Lowercase`() {
        val order = Order("orange")
        val totalCost = order.totalCost
        assertEquals(0.25, totalCost)
        resetMenu()
    }

    @Test
    fun `Returns Correct Price of One Orange Capitalized`() {
        val order = Order("Orange")
        val totalCost = order.totalCost
        assertEquals(0.25, totalCost)
        resetMenu()
    }

    @Test
    fun `Returns Correct Price of Multiple (4) Apples`() {
        val order = Order("apple, apple, apple, apple")
        val totalCost = order.totalCost
        assertEquals(1.20, totalCost)
        resetMenu()
    }

    @Test
    fun `Returns Correct Price of Multiple (4) Oranges`() {
        val order = Order("orange, orange, orange, orange")
        val totalCost = order.totalCost
        assertEquals(0.75, totalCost)
        resetMenu()
    }

    @Test
    fun `Returns Correct Price of Multiple (2) Oranges Diff Caps`() {
        val order = Order("orange, Orange")
        val totalCost = order.totalCost
        assertEquals(0.50, totalCost)
        resetMenu()
    }

    @Test
    fun `Returns Correct Price of Multiple (2) Apples Diff Caps`() {
        val order = Order("apple, Apple")
        val totalCost = order.totalCost
        assertEquals(0.60, totalCost)
        resetMenu()
    }

    @Test
    fun `Returns Correct Price of Mixed Order`() {
        val order = Order("apple, Orange")
        val totalCost = order.totalCost
        assertEquals(0.85, totalCost)
        resetMenu()
    }

    @Test
    fun `Returns Correct Price of Given Mixed Order`() {
        val order = Order("apple, apple, orange, apple")
        val totalCost = order.totalCost
        assertEquals(1.45, totalCost)
        resetMenu()
    }

    @Test
    fun `Returns Zero for Empty Order`() {
        val order = Order("")
        val totalCost = order.totalCost
        assertEquals(0.00, totalCost)
        resetMenu()
    }
}

class SimpleOffersTest {
    @Test
    fun `3 Oranges for Price of 2`() {
        val order = Order("orange, orange, orange")
        val totalCost = order.totalCost
        assertEquals(0.50, totalCost)
        resetMenu()
    }
    @Test
    fun `BOGO 2 Apples`() {
        val order = Order("apple, apple")
        val totalCost = order.totalCost
        assertEquals(0.60, totalCost)
        resetMenu()
    }
    @Test
    fun `3 oranges 2 apples`() {
        val order = Order("apple, apple, orange, orange, orange")
        val totalCost = order.totalCost
        assertEquals(1.10, totalCost)
        resetMenu()
    }
    @Test
    fun `6 oranges 4 apples`() {
        val order = Order("apple, apple, apple, apple, orange, orange, orange, orange, orange, orange")
        val totalCost = order.totalCost
        assertEquals(2.20, totalCost)
        resetMenu()
    }
}

class NotificationTest {
    @Test
    fun `Successful order notification`() {
        val order = Order("apple, orange")
        val status = order.completionStatus
        assertEquals(true, status)
        resetMenu()
    }

    fun `Failed order notification`() {
        val order = Order("apple, aksjdfh;ah")
        val status = order.completionStatus
        assertEquals(false, status)
        resetMenu()
    }
}

class StockTest {
    @Test
    fun `In stock order success`() {
        val order = Order("apple, orange, apple, orange, orange")
        val status = order.completionStatus
        assertEquals(true, status)
        resetMenu()
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

@RunWith(Suite::class)
@SuiteClasses(
    OrderServiceTest::class,
    SimpleOffersTest::class,
    NotificationTest::class,
    StockTest::class
)
class SuiteAllTests {} // run this to test all cases