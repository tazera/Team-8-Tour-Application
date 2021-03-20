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
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;


/* @Purpose: Instrumented Test class to test appropriate UI elements are shown on the Floor plan screens
 * @Created  by Matthew Pearson
 * @Since   20/04/2020
 */

@LargeTest
@RunWith(AndroidJUnit4.class)
public class FloorPlanTest {

    @Rule
    public ActivityTestRule<NavigationBar> mActivityTestRule = new ActivityTestRule<>(NavigationBar.class);

    @Test
    public void floorPlanTest() {

        // Added a sleep statement to match the app's execution delay.
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Open nav drawer
        ViewInteraction appCompatImageButton = onView(
                allOf(withContentDescription("Open navigation drawer"),
                        childAtPosition(
                                allOf(withId(R.id.toolbar),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatImageButton.perform(click());
        // Added a sleep statement to match the app's execution delay.
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Open nav bar and click self tour button
        ViewInteraction navigationMenuItemView = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.design_navigation_view),
                                childAtPosition(
                                        withId(R.id.nav_view),
                                        0)),
                        3),
                        isDisplayed()));
        navigationMenuItemView.perform(click());
        // Added a sleep statement to match the app's execution delay.
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // CLick 'own pace' button
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.ownPaceButton), withText("Discover at your own pace"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                2)));
        appCompatButton.perform(scrollTo(), click());
        // Added a sleep statement to match the app's execution delay.
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Ensure navigated to correct screen by checking title
        ViewInteraction textView = onView(
                allOf(withId(R.id.titleText), withText("Self Discover"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class),
                                        0),
                                0),
                        isDisplayed()));
        textView.check(matches(withText("Self Discover")));

        // Check button for first floor is loaded
        ViewInteraction button = onView(
                allOf(withId(R.id.firstFloor),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class),
                                        0),
                                1),
                        isDisplayed()));
        button.check(matches(isDisplayed()));

        // CLick first floor button
        ViewInteraction appCompatButton1 = onView(
                allOf(withId(R.id.firstFloor), withText("1st Floor"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                1)));
        appCompatButton1.perform(scrollTo(), click());
        // Added a sleep statement to match the app's execution delay.
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Check title has changed to first floor title
        ViewInteraction textView2 = onView(
                allOf(withId(R.id.titleText), withText("1st Floor"),
                        isDisplayed()));
        textView2.check(matches(withText("1st Floor")));

        // Check appropriate floor plan is loaded
        ViewInteraction imageView = onView(
                allOf(withId(R.id.firstFloor),
                        isDisplayed()));
        imageView.check(matches(isDisplayed()));

        // Click map marker
        ViewInteraction imageView3 = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.floorPlan),
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0)),
                        9),
                        isDisplayed()));
        imageView3.perform(click());

        // Check appropriate message dialog about clicked rooms is opened
        ViewInteraction textViews2 = onView(
                allOf(IsInstanceOf.<View>instanceOf(android.widget.TextView.class), withText("Room number 1.043"),
                        isDisplayed()));
        textViews2.check(matches(isDisplayed()));

        // Exit dialog
        pressBack();
        // Go back to floor selection screen
        pressBack();
        // Added a sleep statement to match the app's execution delay.
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /* Repeat above test for all floors*/

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.secondFloor), withText("2nd Floor"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                2)));
        appCompatButton2.perform(scrollTo(), click());
        // Added a sleep statement to match the app's execution delay.
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.titleText), withText("2nd Floor"),
                        isDisplayed()));
        textView3.check(matches(withText("2nd Floor")));

        ViewInteraction imageView4 = onView(
                allOf(withId(R.id.secondFloor),
                        isDisplayed()));
        imageView4.check(matches(isDisplayed()));

        ViewInteraction imageView5 = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.floorPlan),
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0)),
                        15),
                        isDisplayed()));
        imageView5.perform(click());

        ViewInteraction textView4 = onView(
                allOf(IsInstanceOf.<View>instanceOf(android.widget.TextView.class), withText("Room number 2.006"),
                        isDisplayed()));
        textView4.check(matches(withText("Room number 2.006")));

        pressBack();
        pressBack();
        // Added a sleep statement to match the app's execution delay.
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.thirdFloor), withText("3rd Floor"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        appCompatButton3.perform(scrollTo(), click());
        // Added a sleep statement to match the app's execution delay.
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction textView5 = onView(
                allOf(withId(R.id.titleText), withText("3rd Floor"),
                        isDisplayed()));
        textView5.check(matches(withText("3rd Floor")));

        ViewInteraction imageView6 = onView(
                allOf(withId(R.id.thirdFloor),
                        isDisplayed()));
        imageView6.check(matches(isDisplayed()));

        ViewInteraction imageView7 = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.floorPlan),
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0)),
                        15),
                        isDisplayed()));
        imageView7.perform(click());

        ViewInteraction textView6 = onView(
                allOf(IsInstanceOf.<View>instanceOf(android.widget.TextView.class), withText("Room number 3.005"),
                        isDisplayed()));
        textView6.check(matches(withText("Room number 3.005")));

        pressBack();
        pressBack();
        // Added a sleep statement to match the app's execution delay.
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatButton4 = onView(
                allOf(withId(R.id.fourthFloor), withText("4th Floor"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                4)));
        appCompatButton4.perform(scrollTo(), click());
        // Added a sleep statement to match the app's execution delay.
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction textView7 = onView(
                allOf(withId(R.id.titleText), withText("4th Floor"),
                        isDisplayed()));
        textView7.check(matches(withText("4th Floor")));

        ViewInteraction imageView8 = onView(
                allOf(withId(R.id.fourthFloor),
                        isDisplayed()));
        imageView8.check(matches(isDisplayed()));

        ViewInteraction imageView9 = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.floorPlan),
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0)),
                        14),
                        isDisplayed()));
        imageView9.perform(click());

        ViewInteraction textView8 = onView(
                allOf(IsInstanceOf.<View>instanceOf(android.widget.TextView.class), withText("Room number 4.022"),
                        isDisplayed()));
        textView8.check(matches(withText("Room number 4.022")));

        pressBack();
        pressBack();
        // Added a sleep statement to match the app's execution delay.
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatButton5 = onView(
                allOf(withId(R.id.fifthFloor), withText("5th Floor"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                5)));
        appCompatButton5.perform(scrollTo(), click());
        // Added a sleep statement to match the app's execution delay.
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction textView9 = onView(
                allOf(withId(R.id.titleText), withText("5th Floor"),
                        isDisplayed()));
        textView9.check(matches(withText("5th Floor")));

        ViewInteraction imageView10 = onView(
                allOf(withId(R.id.fifthFloor),
                        isDisplayed()));
        imageView10.check(matches(isDisplayed()));

        ViewInteraction imageView11 = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.floorPlan),
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0)),
                        15),
                        isDisplayed()));
        imageView11.perform(click());

        ViewInteraction textView10 = onView(
                allOf(IsInstanceOf.<View>instanceOf(android.widget.TextView.class), withText("Room number 5.018"),
                        isDisplayed()));
        textView10.check(matches(withText("Room number 5.018")));

        pressBack();
        pressBack();
        // Added a sleep statement to match the app's execution delay.
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatButton6 = onView(
                allOf(withId(R.id.sixthFloor), withText("6th Floor"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                6)));
        appCompatButton6.perform(scrollTo(), click());
        // Added a sleep statement to match the app's execution delay.
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction textView11 = onView(
                allOf(withId(R.id.titleText), withText("6th Floor"),
                        isDisplayed()));
        textView11.check(matches(withText("6th Floor")));

        ViewInteraction imageView12 = onView(
                allOf(withId(R.id.sixthFloor),
                        isDisplayed()));
        imageView12.check(matches(isDisplayed()));

        ViewInteraction imageView13 = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.floorPlan),
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0)),
                        9),
                        isDisplayed()));
        imageView13.perform(click());

        ViewInteraction textView12 = onView(
                allOf(IsInstanceOf.<View>instanceOf(android.widget.TextView.class), withText("Room number 6.013/6.014"),
                        isDisplayed()));
        textView12.check(matches(withText("Room number 6.013/6.014")));

    }

    // Auto generated methods
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
