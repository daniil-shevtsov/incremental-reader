package shevtsov.daniil.incrementalreader.core

import android.app.Application
import shevtsov.daniil.incrementalreader.core.di.AppComponent
import shevtsov.daniil.incrementalreader.core.di.DaggerAppComponent

class IncrementalReaderApplication : Application() {

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.create()
    }

    fun getAppComponent() = appComponent

}
