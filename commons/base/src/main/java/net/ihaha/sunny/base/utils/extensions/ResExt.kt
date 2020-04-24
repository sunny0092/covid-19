package net.ihaha.sunny.base.exception

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.os.Build
import androidx.annotation.ArrayRes
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.core.graphics.drawable.DrawableCompat
import java.util.*

// 각종 Resource 값을 가져올때 버전 이슈를 해결하기 위한 익스텐션

fun Context.getDrawableById(@DrawableRes res: Int): Drawable = (if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) getDrawable(res) else resources.getDrawable(res))!!

fun Context.getColorById(@ColorRes res: Int): Int = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) getColor(res) else resources.getColor(res)

@SuppressLint("ResourceType")
fun Context.getStringArrayById(@ArrayRes res: Int): Array<String> = resources.getStringArray(res)

fun Context.getDimenById(@DimenRes res: Int): Int = resources.getDimensionPixelSize(res)

fun Context.getColorStateListById(@ColorRes res: Int): ColorStateList =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        getColorStateList(res)
    } else {
        resources.getColorStateList(res)
    }

fun Drawable.setColorTint(color: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        setTint(color)
    } else {
        DrawableCompat.wrap(this)
        mutate()
        DrawableCompat.setTint(this, color)
    }
}

fun forceLocale(context: Context, localeCode: String) {
    val localeCodeLowerCase = localeCode.toLowerCase(Locale.US)

    val resources = context.applicationContext.resources
    val overrideConfiguration = resources.configuration
    val overrideLocale = Locale(localeCodeLowerCase)

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
        overrideConfiguration.setLocale(overrideLocale)
    } else {
        overrideConfiguration.locale = overrideLocale
    }

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        context.applicationContext.createConfigurationContext(overrideConfiguration)
    } else {
        resources.updateConfiguration(overrideConfiguration, null)
    }
}