package com.platformstest

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.platformstest.ui.BaseActivity
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @get:Rule
    var mActivityRule = ActivityTestRule(BaseActivity::class.java)

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.platformstest", appContext.packageName)
        Thread.sleep(6000)
        assertNotNull(onView(withId(R.id.postDataRecyclerView)))
        onView(withId(R.id.postDataRecyclerView)).check(
            ViewAssertions.matches(
                hasDescendant(
                    withText("Flag")
                )
            )
        )
    }

    @Test
    fun checkRefreshButton() {
        Thread.sleep(3000)
        assertNotNull(onView(withId(R.id.fab)))
        onView(withId(R.id.postDataRecyclerView)).perform(click())
        Thread.sleep(3000)
    }
}
