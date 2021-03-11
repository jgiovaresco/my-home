plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
}


dependencies {
    val spotlessVersion = "5.10.1"

    implementation("com.diffplug.spotless:spotless-plugin-gradle:$spotlessVersion")
}

kotlinDslPluginOptions {
    experimentalWarning.set(false)
}