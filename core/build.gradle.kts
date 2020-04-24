import dependencies.AnnotationProcessorsDependencies
import dependencies.Dependencies
import dependencies.DebugDependencies
import extensions.*
import extensions.implementation
import org.gradle.kotlin.dsl.dependencies

plugins {
    id("commons.android-library")
    id(BuildPlugins.KOTLIN_KAPT)
}


//android {
//    buildTypes.forEach {
//        try {
//            it.buildConfigStringField("MARVEL_API_BASE_URL", "https://gateway.marvel.com/")
//            it.buildConfigStringField("MARVEL_DATABASE_NAME", "characters-db")
//            it.buildConfigIntField("MARVEL_DATABASE_VERSION", 1)
//        } catch (ignored: Exception) {
//            throw InvalidUserDataException("You should define 'marvel.key.public' and " +
//                    "'marvel.key.private' in local.properties. Visit 'https://developer.marvel.com' " +
//                    "to obtain them.")
//        }
//    }
//}

dependencies {
    implementation(Dependencies.CORE_KTX)

    //LIFECYCLE
    implementation(Dependencies.LIFECYCLE_EXTENSIONS)
    implementation(Dependencies.LIFECYCLE_VIEWMODEL)

    //NETWORK
    implementation(Dependencies.OKHTTP3_LOGGING_INTERCEPTOR)
    implementation(Dependencies.RETROFIT)
    implementation(Dependencies.RETROFIT_RX_ADAPTER)
    implementation(Dependencies.RETROFIT_CONVERTER)

    //DATA
    implementation(Dependencies.ROOM)
    implementation(Dependencies.ROOM_KTX)
    kapt(Dependencies.ROOM_COMPILER)

    //FIREBASE
    implementation(Dependencies.CRASHLYTICS)

    implementation(Dependencies.ROOM)
    testImplementation("junit:junit:4.12")
    androidTestImplementation("androidx.test.ext:junit:1.1.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.2.0")
    androidTestImplementation("androidx.annotation:annotation:1.1.0")

    addTestsDependencies()
}

