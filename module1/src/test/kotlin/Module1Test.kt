import org.junit.jupiter.api.Test
import kotlin.test.assertSame

class Module1Test {

    @Test
    fun `module 1 passing test`() {
        assertSame(2, 1 + 1)
    }
}