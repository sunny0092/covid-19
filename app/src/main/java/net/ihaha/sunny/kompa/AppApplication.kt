package net.ihaha.sunny.kompa

import android.app.Application
import androidx.multidex.MultiDex
import net.ihaha.sunny.kompa.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


/**
 * Created by Sunny on 3/13/2020.
 * Version 1.0
 */

class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        MultiDex.install(this)

        startKoin {
            androidContext(this@AppApplication)
            modules(appModules)
        }
    }

}