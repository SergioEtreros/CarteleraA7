package com.senkou.carteleraa7


import androidx.test.InstrumentationRegistry
import org.junit.Assert.assertEquals

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleInstrumentedTest {
    @org.junit.jupiter.api.Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.senkou.carteleraa7", appContext.packageName)
    }
}
