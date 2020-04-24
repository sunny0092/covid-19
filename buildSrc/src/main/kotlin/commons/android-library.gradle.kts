package commons

import BuildAndroidConfig
import BuildProductDimensions
import ProductFlavorDevelop
import ProductFlavorProduction
import dependencies.Dependencies
import dependencies.AnnotationProcessorsDependencies
import extensions.addTestsDependencies
import extensions.implementation
import extensions.testImplementation
import extensions.kapt

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
    id("kotlin-allopen")
    id("com.vanniktech.android.junit.jacoco")
    id("com.vanniktech.dependency.graph.generator")
}

android {
    compileSdkVersion(BuildAndroidConfig.COMPILE_SDK_VERSION)

    defaultConfig {
        minSdkVersion(BuildAndroidConfig.MIN_SDK_VERSION)
        targetSdkVersion(BuildAndroidConfig.TARGET_SDK_VERSION)

        testInstrumentationRunner = BuildAndroidConfig.TEST_INSTRUMENTATION_RUNNER
        testInstrumentationRunnerArguments = BuildAndroidConfig.TEST_INSTRUMENTATION_RUNNER_ARGUMENTS

        vectorDrawables.useSupportLibrary = true
        multiDexEnabled = true

        resConfigs(AndroidSdk.locales)
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    buildFeatures.viewBinding = true
    buildFeatures.dataBinding = true

    androidExtensions {
        isExperimental = true
    }

    buildTypes {
        Debug.create(this, project)
        Release.create(this, project)
    }

    flavorDimensions(BuildProductDimensions.ENVIRONMENT)

    productFlavors {
        ProductFlavorDevelop.libraryCreate(this)
        ProductFlavorProduction.libraryCreate(this)
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

junitJacoco {
    includeNoLocationClasses = true
}

dependencies {
    api(Dependencies.KOTLIN)
    api(Dependencies.COROUTINES)
    api(Dependencies.COROUTINES_ANDROID)

    api(Dependencies.TIMBER)

    api(Dependencies.JAVAX_INJECT)
    //DI
    implementation(Dependencies.KOIN)
    implementation(Dependencies.KOIN_VIEW_MODEL)
    implementation(Dependencies.KOIN_EXT)
//    api(Dependencies.DAGGER)
//    kapt(AnnotationProcessorsDependencies.DAGGER)

//    testImplementation(project(BuildModules.Libraries.TEST_UTILS))
    addTestsDependencies()
}