plugins {
    id("java")
    id("application")
}

group = "io.acordi"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}


dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    implementation("org.openjdk.jmh:jmh-core:1.35")
    annotationProcessor("org.openjdk.jmh:jmh-generator-annprocess:1.35")
}

application {
    mainClass.set("io.acordi.Main")
}

tasks.test {
    useJUnitPlatform()
}

