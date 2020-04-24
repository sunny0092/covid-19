/**
 * Configuration of build modules
 */
object BuildModules {
    const val APP = ":app"
    const val CORE = ":core"
    const val UTILS = ":commons:utils"

    object FEATURES {
        const val HOME = ":features:ui_home"
        const val TUTORIAL = ":features:ui_tutorial"
        const val NEWS = ":features:ui_news"
        const val PREVENTION = ":features:ui_prevention"
        const val SETTINGS = ":features:ui_settings"
    }

    object COMMONS {
        const val BASE = ":commons:base"
        const val UI = ":commons:ui"
    }

    object LIBRARY {
        const val TEST_UTILS = ":libraries:test_utils"
    }
}
