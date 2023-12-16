plugins {
    id("java")
    id("checkstyle")
    application
    checkstyle
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

application {
    mainClass.set("hexlet.code.App")
}

tasks.compileJava {
    options.release.set(20)

    options.compilerArgs.plusAssign(("-Aproject=${project.group}/${project.name}"))
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.2"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.2")

    implementation("info.picocli:picocli:4.7.5")
    annotationProcessor ("info.picocli:picocli-codegen:4.7.5")

    // https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind
    implementation("com.fasterxml.jackson.core:jackson-databind:2.16.0")

    // https://mvnrepository.com/artifact/org.projectlombok/lombok
    compileOnly("org.projectlombok:lombok:1.18.30")


}

tasks.test {
    useJUnitPlatform()
}