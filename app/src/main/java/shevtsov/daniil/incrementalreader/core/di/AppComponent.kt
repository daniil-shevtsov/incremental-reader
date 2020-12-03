package shevtsov.daniil.incrementalreader.core.di

import dagger.Component
import shevtsov.daniil.incrementalreader.main.MainFragment
import shevtsov.daniil.incrementalreader.creation.CreationFragment

@AppScope
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(mainFragment: MainFragment)

    fun inject(creationFragment: CreationFragment)

}
