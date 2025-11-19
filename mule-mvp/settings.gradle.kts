pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "mule-mvp"

include(
    ":services:sb-core",
    ":services:idp-core",
    ":services:orco-core",
    ":services:mm-core",
    ":services:main-core",
    ":services:idb-core",
    ":services:ledger-core",
    ":services:capmule-core",
    ":services:af-core",
    ":services:wi-core"
)