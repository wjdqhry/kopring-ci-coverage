package kopring.ci.coverage.kopringcicoverage

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KopringCiCoverageApplication

fun main(args: Array<String>) {
    runApplication<KopringCiCoverageApplication>(*args)
}
