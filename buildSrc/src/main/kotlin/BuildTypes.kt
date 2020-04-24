import com.android.build.gradle.ProguardFiles
import com.android.build.gradle.ProguardFiles.getDefaultProguardFile
import com.android.build.gradle.internal.dsl.BuildType
import com.android.build.gradle.internal.dsl.SigningConfig
import extensions.buildConfigBooleanField
import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.Project
import utils.hasAndroidLibraryPlugin

interface BuildTypeCustom {

    companion object {
        const val DEBUG = "debug"
        const val RELEASE = "release"
    }

    val isMinifyEnabled: Boolean
    val isCrashlyticsEnabled: Boolean
    val isTestCoverageEnabled: Boolean
    val isDebuggable: Boolean
}

object BuildTypeDebug : BuildTypeCustom {
    override val isMinifyEnabled = false
    override val isCrashlyticsEnabled = false
    override val isTestCoverageEnabled = true
    override val isDebuggable: Boolean get() = true

    const val applicationIdSuffix = ".debug"
    const val versionNameSuffix = "-DEBUG"
}

object BuildTypeRelease : BuildTypeCustom {
    override val isMinifyEnabled = true
    override val isCrashlyticsEnabled = true
    override val isTestCoverageEnabled = false
    override val isDebuggable: Boolean get() = false
}


interface BuildTypeCreator {
    val name: String

    fun create(
        namedDomainObjectContainer: NamedDomainObjectContainer<BuildType>,
        project: Project, signingConfigUser: NamedDomainObjectContainer<SigningConfig>?= null
    ): BuildType
}

object Debug : BuildTypeCreator {
    override val name = BuildTypeCustom.DEBUG

    override fun create(
        namedDomainObjectContainer: NamedDomainObjectContainer<BuildType>,
        project: Project, signingConfigUser: NamedDomainObjectContainer<SigningConfig>?
    ): BuildType {
        return namedDomainObjectContainer.maybeCreate(name).apply {

            signingConfig = signingConfigUser?.getByName(BuildTypeCustom.DEBUG)

            if(!project.hasAndroidLibraryPlugin) {
                buildConfigBooleanField("ENABLE_CRASHLYTICS", BuildTypeDebug.isCrashlyticsEnabled)
                applicationIdSuffix = BuildTypeDebug.applicationIdSuffix
            }

            versionNameSuffix = BuildTypeDebug.versionNameSuffix

            isMinifyEnabled = BuildTypeDebug.isMinifyEnabled
            isDebuggable = BuildTypeDebug.isDebuggable

            isTestCoverageEnabled = BuildTypeDebug.isTestCoverageEnabled
        }
    }
}

object Release : BuildTypeCreator {
    override val name = BuildTypeCustom.RELEASE

    override fun create(
        namedDomainObjectContainer: NamedDomainObjectContainer<BuildType>,
        project: Project, signingConfigUser: NamedDomainObjectContainer<SigningConfig>?
    ): BuildType {
        return namedDomainObjectContainer.maybeCreate(name).apply {

            signingConfig = signingConfigUser?.getByName(BuildTypeCustom.RELEASE)

            buildConfigBooleanField("ENABLE_CRASHLYTICS", BuildTypeRelease.isCrashlyticsEnabled)

            isMinifyEnabled = BuildTypeRelease.isMinifyEnabled
            isTestCoverageEnabled = BuildTypeRelease.isTestCoverageEnabled

            proguardFiles(
                getDefaultProguardFile(ProguardFiles.ProguardFile.OPTIMIZE.fileName, project.layout.buildDirectory),
                "proguard-rules.pro"
            )
        }
    }
}