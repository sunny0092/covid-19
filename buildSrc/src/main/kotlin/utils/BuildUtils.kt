package utils

import org.gradle.api.Project
import java.util.Properties

/**
 * Util to obtain property declared on `$projectRoot/local.properties` file.
 *
 * @param propertyName the name of declared property
 * @param project the project reference
 *
 * @return the value of property name, otherwise throw [Exception]
 */
fun getPropertyName(fileName: String, propertyName: String, project: Project): String {
    val localProperties = Properties().apply {
        val localPropertiesFile = project.rootProject.file(fileName)
        if (localPropertiesFile.exists()) {
            load(localPropertiesFile.inputStream())
        }
    }

    return localProperties.getProperty(propertyName)?.let {
        it
    } ?: run {
        throw NoSuchFieldException("Not defined property: $propertyName")
    }
}
