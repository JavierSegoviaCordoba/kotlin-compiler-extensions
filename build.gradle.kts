plugins {
    alias(libs.plugins.javiersc.hubdle)
}

hubdle {
    config {
        analysis()
        // TODO: Uncomment with Kotlin 2.0
        // binaryCompatibilityValidator()
        coverage()
        documentation {
            api()
            changelog()
            readme {
                badges()
            }
            site()
        }
        nexus()
    }
}
