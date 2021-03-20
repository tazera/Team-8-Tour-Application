package com.example.team8app;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

/* @Purpose: Instrumented Test class to test appropriate UI elements are shown on the 'Home' screen
 * @Created  by Nathan Fish
 * @Since   20/04/2020
 */

@LargeTest
@RunWith(AndroidJUnit4.class)
public class HomeScreenTest {

    @Rule
    public ActivityTestRule<NavigationBar> mActivityTestRule = new ActivityTestRule<>(NavigationBar.class);

    @Test
    public void homeScreenTest() {

        // Added a sleep statement to match the app's execution delay.
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Check University logo is loaded
        ViewInteraction imageView = onView(
                allOf(withId(R.id.newcLogo),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.fragment_container),
                                        0),
                                0),
                        isDisplayed()));
        imageView.check(matches(isDisplayed()));

        // Check welcome text is loaded
        ViewInteraction textView = onView(
                allOf(withId(R.id.title), withText("Welcome!"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.fragment_container),
                                        0),
                                1),
                        isDisplayed()));
        textView.check(matches(withText("Welcome!")));

        // Check button to navigate to open day screen is loaded
        ViewInteraction button = onView(
                allOf(withId(R.id.openDay),
                        isDisplayed()));
        button.check(matches(isDisplayed()));

        // Check button to navigate to post applicant day screen is loaded
        ViewInteraction button2 = onView(
                allOf(withId(R.id.postAppVisit),
                        isDisplayed()));
        button2.check(matches(isDisplayed()));
    }


    // Auto Generated helper methods for UI tests
    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
