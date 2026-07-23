package prasad.vennam.neo.benchmark

import androidx.benchmark.macro.junit4.BaselineProfileRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BaselineProfileGenerator {

    @get:Rule
    val baselineRule = BaselineProfileRule()

    @Test
    fun generate() {
        baselineRule.collect(
            packageName = "prasad.vennam.neo.sample",
            maxIterations = 10
        ) {
            pressHome()
            try {
                startActivityAndWait()
            } catch (e: Throwable) {
                val instrumentation = InstrumentationRegistry.getInstrumentation()
                instrumentation.uiAutomation.executeShellCommand(
                    "am start -n prasad.vennam.neo.sample/prasad.vennam.neo.sample.MainActivity"
                )
                Thread.sleep(5000)
            }
        }
    }
}
