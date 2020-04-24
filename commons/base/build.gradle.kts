import dependencies.Dependencies
import dependencies.AnnotationProcessorsDependencies
import extensions.implementation
import extensions.kapt

plugins {
    id("commons.android-library")
    id(BuildPlugins.KOTLIN_KAPT)
}

junitJacoco {
    excludes = listOf("**/extensions/NavigationExtensions*.*")
}

dependencies {
    //SUPPORT
    implementation(Dependencies.LEAK_CANARY)
    implementation(Dependencies.MULTIDEX)
    //CORE
    implementation(Dependencies.CORE_KTX)

    //LIFECYCLE
    implementation(Dependencies.LIFECYCLE_EXTENSIONS)
    implementation(Dependencies.LIFECYCLE_VIEWMODEL)
    implementation(Dependencies.LIFECYCLE_LIVEDATA)
    implementation(Dependencies.LIFECYCLE_RUNTIME)

    //LAYOUT
    implementation(Dependencies.CONSTRAIN_LAYOUT)
    implementation(Dependencies.RECYCLE_VIEW)
    implementation(Dependencies.FRAGMENT_KTX)
    implementation(Dependencies.MATERIAL)
    implementation(Dependencies.VIEWPAGER2)
    implementation(Dependencies.COIL)
    implementation(Dependencies.PRO_PROGRESS_VIEWS)
    implementation(Dependencies.SWIPE_REFRESH_LAYOUT)

    //PAGER
    implementation(Dependencies.NAVIGATION_FRAGMENT)
    implementation(Dependencies.NAVIGATION_UI)
    implementation(Dependencies.PAGING)

    //NETWORK
    implementation(Dependencies.OKHTTP3_LOGGING_INTERCEPTOR)
    implementation(Dependencies.RETROFIT)
    implementation(Dependencies.RETROFIT_RX_ADAPTER)
    implementation(Dependencies.RETROFIT_CONVERTER)

    //COROUTINE
    implementation(Dependencies.COROUTINES)
    implementation(Dependencies.COROUTINES_ANDROID)
    implementation(Dependencies.COROUTINES_MANAGER)

    //DATA
    implementation(Dependencies.ROOM)
    implementation(Dependencies.ROOM_KTX)
    kapt(Dependencies.ROOM_COMPILER)

    //IMAGE
    implementation(Dependencies.GLIDE)
    kapt(Dependencies.GLIDE_COMPILER)

    //FACEBOOK
    implementation(Dependencies.FACEBOOK_OKHTTP)
    implementation(Dependencies.FACEBOOK_STETHO)
    implementation(Dependencies.FACEBOOK_STETHO_JS)

    //LOG
    implementation(Dependencies.TIMBER)

    //ADAPTER
    implementation("com.mikepenz:fastadapter-extensions-diff:5.0.0") // diff util helpers
    implementation("com.mikepenz:fastadapter-extensions-paged:5.0.0") // paging support
    implementation("com.mikepenz:fastadapter-extensions-scroll:5.0.0") // scroll helpers
    implementation("com.mikepenz:fastadapter-extensions-swipe:5.0.0") // swipe support
    implementation("com.mikepenz:fastadapter-extensions-ui:5.0.0") // pre-defined ui components

    implementation("com.jakewharton.rxrelay2:rxrelay:2.1.1")
    implementation("com.jakewharton.threetenabp:threetenabp:1.2.3")
    implementation("com.jakewharton.rxbinding3:rxbinding:3.1.0")
    implementation("com.jakewharton.rxbinding3:rxbinding-core:3.1.0")
    implementation("com.jakewharton.rxbinding3:rxbinding-material:3.1.0")
    implementation("com.jakewharton.rxbinding3:rxbinding-swiperefreshlayout:3.1.0")
    implementation("com.jakewharton.rxbinding3:rxbinding-recyclerview:3.1.0")

    // Rx
    implementation("io.reactivex.rxjava2:rxkotlin:2.4.0")
    implementation("io.reactivex.rxjava2:rxandroid:2.1.1")

    //PROJECT
    implementation(project(BuildModules.COMMONS.UI))
    implementation(project(BuildModules.UTILS))



    kapt(AnnotationProcessorsDependencies.DATABINDING)
}