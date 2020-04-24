include(
    ":app",
    ":commons:base",
    ":commons:ui",
    ":commons:utils",
    ":features:ui_tutorial",
        ":features:ui_prevention",
    ":features:ui_settings",
        ":features:ui_news",
    ":features:ui_home",
    ":core"
)

rootProject.name = "covid19"
rootProject.buildFileName = "build.gradle.kts"