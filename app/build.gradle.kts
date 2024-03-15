plugins {
    id("checkstyle")
    application
    jacoco
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

    // https://mvnrepository.com/artifact/com.fasterxml.jackson.dataformat/jackson-dataformat-yaml
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.16.1")

}

tasks.test {
    useJUnitPlatform()
}

jacoco {
    toolVersion = "0.8.9"
    reportsDirectory = layout.buildDirectory.dir("reports/jacoco")
}

tasks.jacocoTestReport {
    reports {
        xml.required = true
//        csv.required = false
//        html.outputLocation = layout.buildDirectory.dir("jacocoHtml")
    }
}