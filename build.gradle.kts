plugins {
    java
    application
}

group = "com.hackathon"
version = "1.0-SNAPSHOT"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

application {
    mainClass.set("com.hackathon.agenda.Main")
}
