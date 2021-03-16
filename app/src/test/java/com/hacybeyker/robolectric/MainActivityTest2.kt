package com.hacybeyker.robolectric

import android.content.Intent
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import org.hamcrest.CoreMatchers
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [24])
class MainActivityTest2 {

    private lateinit var activity: MainActivity
    private lateinit var activity2: MainActivity2
    private lateinit var buttonHide: Button
    private lateinit var buttonClick: Button
    private lateinit var buttonNewIntent: Button
    private lateinit var textMessage: TextView

    @Before
    fun setUp() {
        activity = Robolectric.buildActivity(MainActivity::class.java).create().resume().get()
        activity2 = Robolectric.buildActivity(MainActivity2::class.java).create().recreate().get()
        /*activity = Robolectric.setupActivity(MainActivity::class.java)*/
        buttonHide = activity.findViewById(R.id.buttonHide)
        buttonClick = activity.findViewById(R.id.buttonClickMe)
        textMessage = activity.findViewById<TextView>(R.id.textMessage)
        buttonNewIntent = activity.findViewById(R.id.buttonNewIntent)
    }

    @Test
    fun shouldNotBeNull() {
        Assert.assertNotNull(activity)
    }

    @Test
    fun textStartsWithHi() {
        assertThat(textMessage.text.toString(), CoreMatchers.equalTo("Hi"))
    }

    @Test
    fun textChangeToClickedWhenButtonClickMeClicked() {
        buttonClick.performClick()
        assertThat(textMessage.text.toString(), CoreMatchers.equalTo("Hello World!"))
    }

    @Test
    fun hideButtonWhenClickedToButtonHideClicked() {
        buttonHide.performClick()
        assertThat(buttonHide.visibility, CoreMatchers.equalTo(View.GONE))
    }

    @Test
    fun startNewIntentWhenButtonNewIntentClicked() {
        val expectedIntent = Intent(activity, MainActivity2::class.java)
        buttonNewIntent.callOnClick()
        val shadowActivity = Shadows.shadowOf(activity)
        val actualIntent = shadowActivity.nextStartedActivity
        Assert.assertTrue(expectedIntent.filterEquals(actualIntent))
    }
}
