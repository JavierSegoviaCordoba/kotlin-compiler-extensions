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
        mavenLocal { //
            content { //
                includeGroup("com.javiersc.hubdle")
            }
        }
        maven("https://oss.sonatype.org/content/repositories/snapshots")
    }

    plugins { //
        id("com.javiersc.hubdle") version hubdleVersion
    }
}

plugins { //
    id("com.javiersc.hubdle")
}

dependencyResolutionManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        google()
        mavenLocal { //
            content { //
                includeGroup("com.javiersc.hubdle")
            }
        }
        sonatypeSnapshot()
    }
}

hubdleSettings {
    catalog { //
        replaceStrictVersion("kotlin" to "1.9.255-SNAPSHOT")
    }
}
