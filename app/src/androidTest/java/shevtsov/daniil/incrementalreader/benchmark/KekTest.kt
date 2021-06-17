package shevtsov.daniil.incrementalreader.benchmark

import androidx.benchmark.junit4.BenchmarkRule
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import shevtsov.daniil.incrementalreader.structure.view.StructureFragment

@RunWith(AndroidJUnit4::class)
class KekTest {

    @get:Rule
    val benchmarkRule = BenchmarkRule()

//    @get:Rule
//    val activityRule = ActivityScenario(MainActivity::class.java)


    @Test
    fun testKek() {
        val scenario = launchFragmentInContainer<StructureFragment>()
        scenario.moveToState(Lifecycle.State.RESUMED)

    }

}