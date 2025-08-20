plugins {
    java
    `maven-publish`
    id("com.gradleup.shadow") version "8.3.5"
}

group = "dev.snowz.velogui"
version = "1.0.2-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://mvn.exceptionflug.de/repository/exceptionflug-public/")
}

dependencies {
    compileOnly("com.velocitypowered:velocity-api:3.4.0-SNAPSHOT")
    annotationProcessor("com.velocitypowered:velocity-api:3.4.0-SNAPSHOT")

    compileOnly("dev.simplix:protocolize-api:2.4.2")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks {
    shadowJar {
        archiveClassifier.set("")
    }

    test {
        useJUnitPlatform()
    }
}

artifacts {
    archives(tasks.shadowJar)
}

publishing {
    publications {
        create<MavenPublication>("shadow") {
            project.shadow.component(this)
        }
    }
}
