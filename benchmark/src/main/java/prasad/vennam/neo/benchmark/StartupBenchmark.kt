package prasad.vennam.neo.benchmark

import androidx.benchmark.macro.CompilationMode
import androidx.benchmark.macro.StartupMode
import androidx.benchmark.macro.StartupTimingMetric
import androidx.benchmark.macro.junit4.MacrobenchmarkRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class StartupBenchmark {
    @get:Rule
    val benchmarkRule = MacrobenchmarkRule()

    @Test
    fun startupWithoutBaselineProfile() = startup(CompilationMode.None())

    @Test
    fun startupWithBaselineProfile() = startup(CompilationMode.Partial())

    private fun startup(compilationMode: CompilationMode) =
        benchmarkRule.measureRepeated(
            packageName = "prasad.vennam.neo.sample",
            metrics = listOf(StartupTimingMetric()),
            compilationMode = compilationMode,
            startupMode = StartupMode.COLD,
            iterations = 5,
        ) {
            pressHome()
            try {
                startActivityAndWait()
            } catch (e: Throwable) {
                val instrumentation = InstrumentationRegistry.getInstrumentation()
                instrumentation.uiAutomation.executeShellCommand(
                    "am start -n prasad.vennam.neo.sample/prasad.vennam.neo.sample.MainActivity",
                )
                Thread.sleep(5000)
            }
        }
}
