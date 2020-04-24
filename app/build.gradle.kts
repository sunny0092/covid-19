import dependencies.AnnotationProcessorsDependencies
import dependencies.DebugDependencies
import dependencies.Dependencies
import extensions.addTestsDependencies
import extensions.implementation
import extensions.kapt
import utils.SIGNING_DEBUG_PROPERTIES_FILE_NAME
import utils.SIGNING_RELEASE_PROPERTIES_FILE_NAME
import utils.manageSigningConfig

plugins {
    id(BuildPlugins.ANDROID_APPLICATION)
    id(BuildPlugins.CRASHLYTICS)
    kotlin(BuildPlugins.KOTLIN_PRE_ANDROID)
    kotlin(BuildPlugins.KOTLIN_PRE_ANDROID_EXTENSIONS)
    kotlin(BuildPlugins.KOTLIN_PRE_KAPT)
    id(BuildPlugins.KOTLIN_ALLOPEN)
    id(BuildPlugins.NAVIGATION_SAFE_ARGS)
    id(BuildPlugins.JACOCO)
    id(BuildPlugins.GRAPH_GENERATOR)
}


android {
    compileSdkVersion(BuildAndroidConfig.COMPILE_SDK_VERSION)

    defaultConfig {
        applicationId = BuildAndroidConfig.APPLICATION_ID

        minSdkVersion(BuildAndroidConfig.MIN_SDK_VERSION)
        targetSdkVersion(BuildAndroidConfig.TARGET_SDK_VERSION)

        versionCode = BuildAndroidConfig.VERSION_CODE
        versionName = BuildAndroidConfig.VERSION_NAME

        multiDexEnabled = true
        vectorDrawables.useSupportLibrary = BuildAndroidConfig.SUPPORT_LIBRARY_VECTOR_DRAWABLES

        testInstrumentationRunner = BuildAndroidConfig.TEST_INSTRUMENTATION_RUNNER
        testInstrumentationRunnerArguments =
            BuildAndroidConfig.TEST_INSTRUMENTATION_RUNNER_ARGUMENTS

        resConfigs(AndroidSdk.locales)
    }

    signingConfigs {
        getByName(BuildTypeCustom.DEBUG) {
//            manageSigningConfig(this, SIGNING_DEBUG_PROPERTIES_FILE_NAME)
        }
        create(BuildTypeCustom.RELEASE) {
//            manageSigningConfig(this, SIGNING_RELEASE_PROPERTIES_FILE_NAME)
        }
    }

    buildTypes {
        Debug.create(this, project, signingConfigs)
        Release.create(this, project, signingConfigs)
    }

    flavorDimensions(BuildProductDimensions.ENVIRONMENT)

    productFlavors {
        ProductFlavorDevelop.appCreate(this)
        ProductFlavorProduction.appCreate(this)
    }

    sourceSets {
        getByName("main") {
            java.srcDir("src/main/kotlin")
        }
        getByName("test") {
            java.srcDir("src/test/kotlin")
        }

        getByName("androidTest").java.srcDirs("src/androidTest/kotlin/")
    }

    dynamicFeatures = mutableSetOf(
        BuildModules.FEATURES.HOME,
        BuildModules.FEATURES.NEWS,
        BuildModules.FEATURES.PREVENTION,
        BuildModules.FEATURES.SETTINGS,
        BuildModules.FEATURES.TUTORIAL
    )

    buildFeatures.viewBinding = true
    buildFeatures.dataBinding = true

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    lintOptions {
        lintConfig = rootProject.file(".lint/config.xml")
        isCheckAllWarnings = true
        isWarningsAsErrors = true
    }

    testOptions {
        unitTests.isIncludeAndroidResources = true
        unitTests.isReturnDefaultValues = true
        animationsDisabled = true
    }

    packagingOptions.exclude("META-INF/main.kotlin_module")
}


androidExtensions {
    isExperimental = true
}

dependencies {
    //CORE
    implementation(Dependencies.CORE_KTX)
    implementation(Dependencies.MULTIDEX)

    //LIFECYCLE
    implementation(Dependencies.LIFECYCLE_EXTENSIONS)
    implementation(Dependencies.LIFECYCLE_VIEWMODEL)
    implementation(Dependencies.LIFECYCLE_RUNTIME)
    implementation(Dependencies.LIFECYCLE_LIVEDATA)

    //LAYOUT
    implementation(Dependencies.CONSTRAIN_LAYOUT)
    implementation(Dependencies.RECYCLE_VIEW)
    implementation(Dependencies.FRAGMENT_KTX)
    implementation(Dependencies.MATERIAL)
    implementation(Dependencies.VIEWPAGER2)
    implementation(Dependencies.COIL)
    implementation(Dependencies.PRO_PROGRESS_VIEWS)

    //PAGER
    implementation(Dependencies.NAVIGATION_FRAGMENT)
    implementation(Dependencies.NAVIGATION_UI)
    implementation(Dependencies.PAGING)

    //NETWORK
    implementation(Dependencies.OKHTTP3_LOGGING_INTERCEPTOR)
    implementation(Dependencies.RETROFIT)
    implementation(Dependencies.RETROFIT_RX_ADAPTER)
    implementation(Dependencies.RETROFIT_CONVERTER)

    //DI
    implementation(Dependencies.KOIN)
    implementation(Dependencies.KOIN_VIEW_MODEL)
    implementation(Dependencies.KOIN_EXT)

    //COROUTINE
    implementation(Dependencies.COROUTINES)
    implementation(Dependencies.COROUTINES_ANDROID)
    implementation(Dependencies.COROUTINES_MANAGER)

    //DATA
    implementation(Dependencies.ROOM)
    implementation(Dependencies.ROOM_KTX)
    implementation(Dependencies.LEGACY)
    kapt(Dependencies.ROOM_COMPILER)

    //IMAGE
    implementation(Dependencies.GLIDE)
    kapt(Dependencies.GLIDE_COMPILER)

    //LOG
    implementation(Dependencies.TIMBER)

    //ADAPTER
    implementation("com.mikepenz:fastadapter-extensions-diff:5.0.0") // diff util helpers
    implementation("com.mikepenz:fastadapter-extensions-paged:5.0.0") // paging support
    implementation("com.mikepenz:fastadapter-extensions-scroll:5.0.0") // scroll helpers
    implementation("com.mikepenz:fastadapter-extensions-swipe:5.0.0") // swipe support
    implementation("com.mikepenz:fastadapter-extensions-ui:5.0.0") // pre-defined ui components

    //PROJECT
    implementation(project(BuildModules.COMMONS.BASE))
    implementation(project(BuildModules.COMMONS.UI))
    implementation(project(BuildModules.CORE))
    implementation(project(BuildModules.UTILS))

    kapt(AnnotationProcessorsDependencies.DATABINDING)
}

//apply(mapOf("plugin" to "com.google.gms.google-services"))