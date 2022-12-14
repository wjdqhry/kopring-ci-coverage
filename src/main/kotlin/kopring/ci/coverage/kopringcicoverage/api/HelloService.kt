package kopring.ci.coverage.kopringcicoverage.api

import org.springframework.stereotype.Service

@Service
class HelloService {
    fun getHello() = "Hello"

    fun getHelloDTO() = HelloDTO(message = "Hello")
}