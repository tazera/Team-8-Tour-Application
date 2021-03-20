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
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

/* @Purpose: Instrumented Test class to test appropriate UI elements are shown on the 'Open Day' screen
 * @Created  by Nathan Fish
 * @Since   20/04/2020
 */

@LargeTest
@RunWith(AndroidJUnit4.class)
public class OpenDayTest {

    @Rule
    public ActivityTestRule<NavigationBar> mActivityTestRule = new ActivityTestRule<>(NavigationBar.class);

    @Test
    public void openDayTest() {

        // Added a sleep statement to match the app's execution delay.
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Click button to navigate to open day screen
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.openDay), withText("Open Day"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.fragment_container),
                                        0),
                                3),
                        isDisplayed()));
        appCompatButton.perform(click());
        // Added a sleep statement to match the app's execution delay.
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Check "open day" title text is loaded
        ViewInteraction textView = onView(
                allOf(withId(R.id.titleText), withText("Open Day"),
                        isDisplayed()));
        textView.check(matches(withText("Open Day")));

        // Check sub title is loaded correctly
        ViewInteraction textView2 = onView(
                allOf(withId(R.id.infoTitle), withText("Information"),
                        isDisplayed()));
        textView2.check(matches(withText("Information")));

        // Check text is loaded correctly
        ViewInteraction textView3 = onView(
                allOf(withId(R.id.infoText), withText("The open days here at Newcastle are a great way to explore and experience the Newcastle university campus directly. During Summer and Autumn annually the university hosts the open days for students interested in seeking a higher education after school.\n\n On the day you’ll be provided with a number of activities and aimed to help you learn more about the USB, the university in general, and get a feel for life in the city. You’ll also be given the chance to get face to face with a number of teachers, staff, and students currently studying at the university.\n"),
                        isDisplayed()));
        textView3.check(matches(withText("The open days here at Newcastle are a great way to explore and experience the Newcastle university campus directly. During Summer and Autumn annually the university hosts the open days for students interested in seeking a higher education after school.\n\n On the day you’ll be provided with a number of activities and aimed to help you learn more about the USB, the university in general, and get a feel for life in the city. You’ll also be given the chance to get face to face with a number of teachers, staff, and students currently studying at the university.\n")));

        // Go back to home
        pressBack();
        // Added a sleep statement to match the app's execution delay.
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Check we are back on home page by checking "welcome" title
        ViewInteraction textView5 = onView(
                allOf(withId(R.id.title), withText("Welcome!"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.fragment_container),
                                        0),
                                1),
                        isDisplayed()));
        textView5.check(matches(withText("Welcome!")));
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
