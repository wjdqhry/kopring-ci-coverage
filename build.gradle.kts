import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.0.0"
    id("io.spring.dependency-management") version "1.1.0"
    kotlin("jvm") version "1.7.21"
    kotlin("plugin.spring") version "1.7.21"

    jacoco
}

group = "kopring.ci.coverage"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.springframework.boot:spring-boot-starter-web")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport)
}

val qDomains: MutableList<String> get() {
    val qDomains = mutableListOf<String>()
    for (qPattern in 'A' .. 'Z') {
        qDomains.add("*.Q${qPattern}*")
    }
    return qDomains
}
val qDirectories: MutableList<String> get() {
    val qDirectories = mutableListOf<String>()

    for (qPattern in 'A' .. 'Z') {
        qDomains.add("**/Q${qPattern}*")
    }

    return qDirectories
}

jacoco {
    toolVersion = "0.8.8"
    reportsDirectory.set(layout.buildDirectory.dir("reportsJaCoCo"))
}


tasks.jacocoTestReport {
    reports {
        html.required.set(true)
        xml.required.set(true)
        csv.required.set(true)
    }

    classDirectories.setFrom(
        files(
            classDirectories.files.map {
                fileTree(it) {
                    exclude(qDirectories + "**.*Application*")
                }
            }
        )
    )

    finalizedBy(tasks.jacocoTestCoverageVerification)
}


tasks.jacocoTestCoverageVerification {
    violationRules {
        rule {
            enabled = true
            element = "CLASS"

//            limit {
//                value = "COVEREDRATIO"
//                minimum = 0.7.toBigDecimal()
//            }

            excludes = qDomains + "**.*Application*"
        }
    }
}