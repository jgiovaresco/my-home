plugins {
    java
    idea

    id("com.diffplug.spotless")
}

repositories {
    mavenCentral()
}

dependencies {
    val reactorVersion = "2020.0.4"
    val logbackVersion = "1.2.3"
    val lombokVersion = "1.18.16"

    val fakerVersion = "1.0.2"
    val junitVersion = "5.7.1"
    val assertjVersion = "3.18.1"
    val mockitoVersion = "3.7.7"

    implementation("ch.qos.logback:logback-classic:$logbackVersion")
    implementation(platform("io.projectreactor:reactor-bom:$reactorVersion"))
    implementation("io.projectreactor:reactor-core")
    implementation("io.projectreactor:reactor-test")

    compileOnly("org.projectlombok:lombok:$lombokVersion")
    annotationProcessor("org.projectlombok:lombok:$lombokVersion")

    testImplementation(platform("org.junit:junit-bom:$junitVersion"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.assertj:assertj-core:$assertjVersion")
    testImplementation("org.mockito:mockito-core:$mockitoVersion")
    implementation("com.github.javafaker:javafaker:$fakerVersion")
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}

spotless {
    java {
        prettier(mapOf("prettier" to "2.2.1", "prettier-plugin-java" to "1.0.1"))
            .config(mapOf("parser" to "java", "printWidth" to 120, "tabWidth" to 4))
    }
}