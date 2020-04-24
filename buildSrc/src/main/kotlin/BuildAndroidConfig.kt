/**
 * Configuration of android build
 */
object BuildAndroidConfig {
    const val APPLICATION_ID = "net.ihaha.sunny.covid19"

    const val BUILD_TOOLS_VERSION = "29.0.2"
    const val COMPILE_SDK_VERSION = 29
    const val MIN_SDK_VERSION = 21
    const val TARGET_SDK_VERSION = 29

    const val VERSION_CODE = 1
    const val VERSION_NAME = "1.0"

    const val SUPPORT_LIBRARY_VECTOR_DRAWABLES = true
    const val MULTIDEX_ENABLED = true

    val LOCALE =  listOf("en", "hi")

    const val TEST_INSTRUMENTATION_RUNNER = "androidx.test.runner.AndroidJUnitRunner"

    val TEST_INSTRUMENTATION_RUNNER_ARGUMENTS = mutableMapOf<String, String>(
        "leakcanary.FailTestOnLeakRunListener" to "listener"
    )
}
