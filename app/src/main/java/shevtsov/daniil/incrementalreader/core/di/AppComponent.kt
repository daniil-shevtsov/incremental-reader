package shevtsov.daniil.incrementalreader.core.di

import dagger.Component
import shevtsov.daniil.incrementalreader.fragmenta.FragmentA
import shevtsov.daniil.incrementalreader.fragmentb.FragmentB

@AppScope
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(fragmentA: FragmentA)

    fun inject(fragmentB: FragmentB)

}
