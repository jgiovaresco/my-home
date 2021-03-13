plugins {
    id("myhome.java-application-conventions")

    id("org.springframework.boot") version("2.4.3")
    id("io.spring.dependency-management") version("1.0.11.RELEASE")
}


dependencies {
    val springDocVersion = "1.5.4"
    val restAssuredVersion = "4.2.1"
    val reactorAddonsVersion = "3.4.2"
    val mockitoVersion = "3.8.0"

    implementation(project(":common"))
    implementation(project(":core"))

    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    implementation("io.projectreactor.addons:reactor-adapter:$reactorAddonsVersion")
    developmentOnly("org.springframework.boot:spring-boot-devtools")

    implementation("org.springframework.security:spring-security-config")
    implementation("org.springframework.security:spring-security-oauth2-jose")
    implementation("org.springframework.security:spring-security-oauth2-resource-server")

    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springdoc:springdoc-openapi-webflux-ui:$springDocVersion")

    testImplementation("org.mockito:mockito-core:$mockitoVersion")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude("org.junit.vintage:junit-vintage-engine")
    }
    testImplementation("org.springframework.security:spring-security-test")
    testImplementation("io.rest-assured:rest-assured:$restAssuredVersion")
    testImplementation("io.rest-assured:json-path:$restAssuredVersion")
    testImplementation("io.rest-assured:xml-path:$restAssuredVersion")
}
