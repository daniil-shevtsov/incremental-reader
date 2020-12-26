package shevtsov.daniil.incrementalreader.core.di

import dagger.Component
import shevtsov.daniil.incrementalreader.creation.CreationFragment
import shevtsov.daniil.incrementalreader.creation.di.CreationModule
import shevtsov.daniil.incrementalreader.main.MainFragment

@AppScope
@Component(
    modules = [AppModule::class, CreationModule::class]
)
interface AppComponent {

    fun inject(mainFragment: MainFragment)

    fun inject(creationFragment: CreationFragment)

}
