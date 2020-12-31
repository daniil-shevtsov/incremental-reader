package shevtsov.daniil.incrementalreader.core.di

import dagger.Component
import shevtsov.daniil.incrementalreader.creation.CreationFragment
import shevtsov.daniil.incrementalreader.creation.di.CreationModule
import shevtsov.daniil.incrementalreader.main.MainFragment
import shevtsov.daniil.incrementalreader.structure.StructureFragment

@AppScope
@Component(
    modules = [AppModule::class, CreationModule::class]
)
interface AppComponent {

    fun inject(mainFragment: MainFragment)

    fun inject(creationFragment: CreationFragment)

    fun inject(structureFragment: StructureFragment)

}
