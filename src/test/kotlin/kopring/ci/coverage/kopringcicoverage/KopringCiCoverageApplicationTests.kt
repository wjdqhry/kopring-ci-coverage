package kopring.ci.coverage.kopringcicoverage

import kopring.ci.coverage.kopringcicoverage.api.HelloService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class KopringCiCoverageApplicationTests(
    @Autowired private val helloService: HelloService
) {

    @Test
    fun testHello() {
        val helloResult = helloService.getHello()

        assertEquals(helloResult, "Hello")
    }

    @Test
    fun testHelloDTO() {
        val helloDtoResult = helloService.getHelloDTO()

        assertEquals(helloDtoResult.message, "Hello")
    }

    @Test
    fun testNamedHello() {
        val name = "보쿄"

        val helloResult = helloService.getHello(name = name)

        assertEquals(helloResult, "Hello $name!")
    }
}
