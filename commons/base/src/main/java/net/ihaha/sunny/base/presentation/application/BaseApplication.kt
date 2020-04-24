package net.ihaha.sunny.base.presentation.application

import androidx.multidex.MultiDexApplication
import com.bumptech.glide.Glide
import com.squareup.leakcanary.LeakCanary

class BaseApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        instance = this

        initLeakCanary()
    }

    override fun onTerminate() {
        super.onTerminate()
        instance = null
    }

    override fun onLowMemory() {
        super.onLowMemory()
        Glide.get(this).clearMemory()
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        Glide.get(this).trimMemory(level)
    }

    private fun initLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return
        }
        LeakCanary.install(this)
    }

    companion object {
        @get:Synchronized
        private var instance: BaseApplication? = null

        fun getInstance(): BaseApplication {
            if (instance == null) {
                instance = BaseApplication()
            }
            return instance as BaseApplication
        }

        var DEBUG: Boolean = false
        val TAG = BaseApplication::class.java.simpleName
    }
}