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
        maven(url = "https://maven.pkg.jetbrains.space/kotlin/p/kotlin/bootstrap")
        maven(url = "https://maven.pkg.jetbrains.space/kotlin/p/kotlin/dev")
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
        maven(url = "https://maven.pkg.jetbrains.space/kotlin/p/kotlin/bootstrap")
        maven(url = "https://maven.pkg.jetbrains.space/kotlin/p/kotlin/dev")
        sonatypeSnapshot()
    }
}

val hubdleCatalogVersion: String =
    file("$rootDir/gradle/libs.versions.toml")
        .readLines()
        .first { it.contains("hubdleCatalog") }
        .split("\"")[1]

hubdleSettings {
    catalog {
        version(hubdleCatalogVersion)
    }
}
