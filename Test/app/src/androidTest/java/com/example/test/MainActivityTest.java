package com.example.test;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.rule.ActivityTestRule;
import android.view.View;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

public class MainActivityTest {

    MainActivity mainActivity  = null;

    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(SecondActivity.class.getName(),null,false);

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() throws Exception {
        mainActivity = activityTestRule.getActivity();

    }

    @Test
    public void testLaunch(){
        View view  = mainActivity.findViewById(R.id.tvMessage);

        assertNotNull(view);
    }
    @Test
    public void testLaunchSecondActivityOnButtonClick(){
        assertNotNull(mainActivity.findViewById(R.id.btnGoto));

        onView(withId( R.id.btnGoto)).perform(click());
        Activity secondActivity = getInstrumentation().waitForMonitorWithTimeout(monitor,500);
        assertNotNull(secondActivity);
        secondActivity.finish();
    }

    @After
    public void tearDown() throws Exception {
        mainActivity = null;
    }
}