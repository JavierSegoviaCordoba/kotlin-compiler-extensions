import com.javiersc.gradle.version.GradleVersion
import com.javiersc.gradle.version.isSnapshot
import org.jetbrains.kotlin.gradle.plugin.getKotlinPluginVersion

hubdle {
    config {
        analysis()
        coverage()
        documentation { //
            api()
        }
        explicitApi()
        languageSettings { //
            optIn("org.jetbrains.kotlin.compiler.plugin.ExperimentalCompilerApi")
        }
        publishing()
        versioning {
            semver {
                mapVersion { gradleVersion ->
                    gradleVersion.mapIfKotlinVersionIsProvided(getKotlinPluginVersion())
                }
            }
        }
    }
    kotlin {
        jvm {
            features { //
                contextReceivers()
            }

            main {
                dependencies {
                    compileOnly(hubdle.jetbrains.kotlin.compiler)
                    compileOnly(hubdle.jetbrains.kotlin.compiler.internal.test.framework)
                    implementation(hubdle.junit.jupiter.junit.jupiter.api)
                }
            }
        }
    }
}

fun GradleVersion.mapIfKotlinVersionIsProvided(kotlinVersion: String): String {
    val major: Int = major
    val minor: Int = minor
    val patch: Int = patch
    check(kotlinVersion.isKotlinDevVersion()) {
        """ |Kotlin version: $kotlinVersion
            |Requirements to use a specific Kotlin version:  
            | - It must be a dev version, for example: `1.9.20-dev-5788`
            |Check the Kotlin dev versions on the bootstrap repository:
            |  - https://maven.pkg.jetbrains.space/kotlin/p/kotlin/bootstrap/org/jetbrains/kotlin/ 
        """
            .trimMargin()
    }
    check(isSnapshot) {
        """ |Current version: ${this@mapIfKotlinVersionIsProvided}
            |Kotlin version: $kotlinVersion
            |Requirements to use a specific Kotlin version:  
            | - Use a SNAPSHOT version with `-P semver.stage=snapshot`
            | - Clean repo or use `-P semver.checkClean=false`
        """
            .trimMargin()
    }
    return "$major.$minor.$patch+$kotlinVersion-SNAPSHOT"
}

fun String.isKotlinDevVersion(): Boolean =
    matches(Regex("""(0|[1-9]\d*)\.(0|[1-9]\d*)\.(0|[1-9]\d*)-dev-(0|[1-9]\d*)"""))
