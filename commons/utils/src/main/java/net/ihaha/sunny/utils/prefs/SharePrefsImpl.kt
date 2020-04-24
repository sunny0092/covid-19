package net.ihaha.sunny.utils.prefs

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

/**
 * Created by Sunny on 9/29/2019.
 * Version 1.0
 */

class SharePrefsImpl(context: Context) : SharePrefsApi {
    private var sharePreferences: SharedPreferences? = null

    init {
        sharePreferences = PreferenceManager.getDefaultSharedPreferences(context)
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T> get(key: String, clazz: Class<T>): T? {
        when (clazz) {
            String::class.java -> return sharePreferences?.getString(key, "") as T
            Boolean::class.java -> return sharePreferences?.getBoolean(key, false) as T
            Float::class.java -> return sharePreferences?.getFloat(key, 0.0F) as T
            Int::class.java -> return sharePreferences?.getInt(key, 0) as T
            Long::class.java -> return sharePreferences?.getLong(key, 0L) as T
        }
        return null
    }

    override fun <T> put(key: String, data: T) {
        val editor = sharePreferences?.edit()
        when (data) {
            is String -> editor?.putString(key, data)
            is Boolean -> editor?.putBoolean(key, data)
            is Float -> editor?.putFloat(key, data)
            is Int -> editor?.putInt(key, data)
            is Long -> editor?.putLong(key, data)
        }
        editor?.apply()
    }

    override fun delete(key: String) {
        val editor = sharePreferences?.edit()
        editor?.remove(key)
        editor?.apply()
    }

    override fun clear() {
        sharePreferences?.edit()?.clear()?.apply()
    }
}
