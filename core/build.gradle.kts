plugins {
    id("myhome.java-library-conventions")
}

dependencies {
    val jakartaValidation = "2.0.2"

    implementation(project(":common"))

    implementation("jakarta.validation:jakarta.validation-api:$jakartaValidation")
}
