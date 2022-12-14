package kopring.ci.coverage.kopringcicoverage.api

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/hello")
class HelloController(private val helloService: HelloService) {

    @GetMapping
    fun getHello(): String = helloService.getHello()

    @GetMapping("/dto")
    fun getHelloDTO(): HelloDTO = helloService.getHelloDTO()
}