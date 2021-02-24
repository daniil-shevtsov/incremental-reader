package shevtsov.daniil.incrementalreader.core

import android.app.Application
import shevtsov.daniil.incrementalreader.core.di.DaggerAppComponent

class IncrementalReaderApplication : Application() {

    val appComponent by lazy {
        DaggerAppComponent.factory().create(appContext = applicationContext)
    }

    override fun onCreate() {
        super.onCreate()
    }

}
