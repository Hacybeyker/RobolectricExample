package com.hacybeyker.robolectric

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config


@RunWith(AndroidJUnit4::class)
@Config(sdk = [24])
class MainActivityTest3 {

    private var context: Context? = null
    private lateinit var intent: Intent
    private lateinit var scenario: ActivityScenario<*>

    @Before
    fun setUp() {
        //give
        context = ApplicationProvider.getApplicationContext<MainActivity>()
        intent = Intent(context, MainActivity::class.java)
        scenario = ActivityScenario.launch<Activity>(intent)
    }

    @Test
    fun testScenario() {
        // when
        scenario.moveToState(Lifecycle.State.CREATED)
        // then
        scenario.onActivity { activity -> Assert.assertEquals(activity.intent, intent) }
    }
}