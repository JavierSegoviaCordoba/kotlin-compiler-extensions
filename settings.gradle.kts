pluginManagement {
    val hubdleVersion: String =
        file("$rootDir/gradle/libs.versions.toml")
            .readLines()
            .first { it.contains("hubdle") }
            .split("\"")[1]

    repositories {
        gradlePluginPortal()
        mavenCentral()
        google()
        maven("https://oss.sonatype.org/content/repositories/snapshots") { //
            content { //
                includeGroup("com.javiersc.hubdle")
            }
        }
        mavenLocal { //
            content { //
                includeGroup("com.javiersc.hubdle")
            }
        }
    }

    plugins {
        id("com.javiersc.hubdle") version hubdleVersion
    }
}

plugins {
    id("com.javiersc.hubdle")
}
