package shevtsov.daniil.incrementalreader.core.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import shevtsov.daniil.incrementalreader.creation.view.CreationFragment
import shevtsov.daniil.incrementalreader.learning.view.LearningFragment
import shevtsov.daniil.incrementalreader.main.view.MainFragment
import shevtsov.daniil.incrementalreader.structure.view.StructureFragment

@AppScope
@Component(
    modules = [
        AppModule::class,
    ]
)
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance appContext: Context
        ): AppComponent
    }


    fun inject(mainFragment: MainFragment)

    fun inject(creationFragment: CreationFragment)

    fun inject(structureFragment: StructureFragment)

    fun inject(learningFragment: LearningFragment)

}
