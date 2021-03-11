plugins {
    id("myhome.java-library-conventions")
}

dependencies {
    val hibernateValidatorVersion = "6.2.0.Final"
    val javaxElVersion = "3.0.0"

    implementation("org.hibernate.validator:hibernate-validator:$hibernateValidatorVersion")
    implementation("javax.el:javax.el-api:$javaxElVersion")
    implementation("org.glassfish:javax.el:$javaxElVersion")
}
