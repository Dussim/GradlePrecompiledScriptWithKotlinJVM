import org.junit.jupiter.api.Test
import kotlin.test.assertSame

class Module2Test {

    @Test
    fun `module 2 failing test`() {
        assertSame(3, 1 + 1)
    }
}