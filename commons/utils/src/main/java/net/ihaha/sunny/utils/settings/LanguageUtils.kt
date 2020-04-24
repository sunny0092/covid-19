package net.ihaha.sunny.utils.settings

import android.app.Application
import android.content.Context
import android.os.Build
import android.os.LocaleList
import net.ihaha.sunny.utils.prefs.SharePrefsApi
import net.ihaha.sunny.utils.prefs.SharePrefsImpl
import net.ihaha.sunny.utils.prefs.SharedPrefKeys
import java.util.*


/**
 * Created by Sunny on 10/18/2019.
 * Version 1.0
 */

class LanguageUtils(private val application: Application, private val sharePrefsApi: SharePrefsApi) {

    fun applyLanguage(isChange: Boolean) {
        persistLanguagePreference(isChange)
        ContextWrapper.wrap(application)
    }

    fun getLanguageLocal() : Boolean?{
        return sharePrefsApi.get(SharedPrefKeys.LANGUAGE_KEY, Boolean::class.java)
    }

    private fun persistLanguagePreference(isChange: Boolean) {
        sharePrefsApi.put(SharedPrefKeys.LANGUAGE_KEY, isChange)
    }

}

@Suppress("DEPRECATION")
open class ContextWrapper(base: Context) : android.content.ContextWrapper(base) {
    companion object {
        fun wrap(context: Context): ContextWrapper {
            val sharePrefsApi = SharePrefsImpl(context)
            val local : Locale
            local = if (sharePrefsApi.get(SharedPrefKeys.LANGUAGE_KEY, Boolean::class.java) == false) {
                Locale(SharedPrefKeys.LANGUAGE_DEFAULT)
            } else {
                Locale(SharedPrefKeys.LANGUAGE_ENGLISH)
            }
            var mContext = context

            val res = mContext.resources
            val configuration = res.configuration

            when {
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.N -> {
                    configuration.setLocale(local)
                    val localeList = LocaleList(local)
                    LocaleList.setDefault(localeList)
//                    configuration.locales = localeList
                    mContext = context.createConfigurationContext(configuration)
                }
                else -> {
                    configuration.locale = local
                    res.updateConfiguration(configuration, res.displayMetrics)
                }
            }
            return ContextWrapper(mContext)
        }
    }
}





