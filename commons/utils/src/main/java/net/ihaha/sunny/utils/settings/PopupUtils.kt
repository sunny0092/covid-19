package net.ihaha.sunny.utils.settings

import net.ihaha.sunny.utils.prefs.SharePrefsApi
import net.ihaha.sunny.utils.prefs.SharedPrefKeys


class PopupUtils(private val sharePrefsApi: SharePrefsApi){

    fun getPopup() : Boolean?{
        return sharePrefsApi.get(SharedPrefKeys.SLIDE_SCREEN_KEY, Boolean::class.java)
    }

    fun putPopup(isChange: Boolean){
        sharePrefsApi.put(SharedPrefKeys.SLIDE_SCREEN_KEY, isChange)
    }
}