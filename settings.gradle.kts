import com.javiersc.semver.project.gradle.plugin.SemverExtension
import org.jetbrains.kotlin.gradle.plugin.getKotlinPluginVersion

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
        // maven(url = "https://maven.pkg.jetbrains.space/kotlin/p/kotlin/bootstrap")
        // maven(url = "https://maven.pkg.jetbrains.space/kotlin/p/kotlin/dev")
        // maven("https://oss.sonatype.org/content/repositories/snapshots")
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
        // maven(url = "https://maven.pkg.jetbrains.space/kotlin/p/kotlin/bootstrap")
        // maven(url = "https://maven.pkg.jetbrains.space/kotlin/p/kotlin/dev")
        // maven("https://oss.sonatype.org/content/repositories/snapshots")
    }
}

buildscript {
    dependencies {
        val kotlinVersion: String =
            file("$rootDir/gradle/libs.versions.toml")
                .readLines()
                .first { it.contains("jetbrains-kotlin") }
                .split("\"")[1]

        val kotlinModule =
            file("$rootDir/gradle/libs.versions.toml")
                .readLines()
                .first { it.contains("jetbrains-kotlin-gradle-plugin") }
                .split("\"")[1]

        val kotlinDependency = "$kotlinModule:$kotlinVersion"
        classpath(kotlinDependency)
    }
}

val hubdleCatalogVersion: String =
    file("$rootDir/gradle/libs.versions.toml")
        .readLines()
        .first { it.contains("hubdleCatalog") }
        .split("\"")[1]

val kotlinVersion: String =
    file("$rootDir/gradle/libs.versions.toml")
        .readLines()
        .first { it.contains("jetbrains-kotlin") }
        .split("\"")[1]

hubdleSettings {
    catalog { //
        version(hubdleCatalogVersion)
        replaceVersion("jetbrains-kotlin" to kotlinVersion)
    }
}

settings.gradle.beforeProject {
    pluginManager.withPlugin("com.javiersc.semver") {
        val kotlinVersion = getKotlinPluginVersion(logger)
        project.configure<SemverExtension> {
            mapVersion { gradleVersion ->
                val metadata = gradleVersion.metadata?.let { "$kotlinVersion-$it" } ?: kotlinVersion
                "${gradleVersion.copy(metadata = metadata)}"
            }
        }
    }
}
