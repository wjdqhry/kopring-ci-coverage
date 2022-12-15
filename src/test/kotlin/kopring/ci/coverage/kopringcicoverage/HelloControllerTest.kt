package kopring.ci.coverage.kopringcicoverage

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
class HelloControllerTest(
    @Autowired private val mockMvc: MockMvc
) {

    @Test
    fun testHello() {
        mockMvc
            .perform(get("/hello"))
            .andExpect(status().isOk)
            .andExpect(content().string("Hello"))
    }

    @Test
    fun testHelloDTO() {
        mockMvc
            .perform(get("/hello/dto"))
            .andExpect(status().isOk)
            .andExpect(content().json("""{ "message":  "Hello" }"""))
    }

    @Test
    fun testNamedHello() {
        val name = "보쿄"

        mockMvc
            .perform(get("/hello").param("name", name))
            .andExpect(status().isOk)
            .andExpect(content().string("Hello $name!"))
    }
}
