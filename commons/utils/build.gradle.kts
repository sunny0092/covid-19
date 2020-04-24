import dependencies.Dependencies
import dependencies.AnnotationProcessorsDependencies
import extensions.implementation
import extensions.kapt

plugins {
    id("commons.android-library")
    id(BuildPlugins.KOTLIN_KAPT)
}


dependencies {

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

    //PAGER
    implementation(Dependencies.NAVIGATION_FRAGMENT)
    implementation(Dependencies.NAVIGATION_UI)
    implementation(Dependencies.PAGING)

    //IMAGE
    implementation(Dependencies.GLIDE)
    kapt(Dependencies.GLIDE_COMPILER)

    //ADAPTER
    implementation("com.mikepenz:fastadapter-extensions-diff:5.0.0") // diff util helpers
    implementation("com.mikepenz:fastadapter-extensions-paged:5.0.0") // paging support
    implementation("com.mikepenz:fastadapter-extensions-scroll:5.0.0") // scroll helpers
    implementation("com.mikepenz:fastadapter-extensions-swipe:5.0.0") // swipe support
    implementation("com.mikepenz:fastadapter-extensions-ui:5.0.0") // pre-defined ui components

    kapt(AnnotationProcessorsDependencies.DATABINDING)
}