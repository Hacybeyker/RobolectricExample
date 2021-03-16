package com.hacybeyker.robolectric

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.hamcrest.CoreMatchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(sdk = [24])
class MainActivityTest1 {

    @get:Rule
    val activity = ActivityTestRule(MainActivity::class.java)

    @Test
    fun textStartsWithHi() {
        onView(withId(R.id.textMessage)).check(matches(withText("Hi")))
    }

    @Test
    fun textChangeToClickedWhenButtonClickMeClicked() {
        onView(withId(R.id.buttonClickMe)).perform(click())
        onView(withId(R.id.textMessage)).check(matches(withText("Hello World!")))
    }

    @Test
    fun hideButtonWhenClickedToButtonHideClicked() {
        onView(withId(R.id.buttonHide)).perform(click())
        assertThat(withId(R.id.buttonHide), CoreMatchers.equalTo(View.GONE))
    }
}
