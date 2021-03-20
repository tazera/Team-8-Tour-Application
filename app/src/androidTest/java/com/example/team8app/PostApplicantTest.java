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
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

/* @Purpose: Instrumented Test class to test appropriate UI elements are shown on the 'Post Applicant Day' screen
 * @Created  by Nathan Fish
 * @Since   20/04/2020
 */
@LargeTest
@RunWith(AndroidJUnit4.class)
public class PostApplicantTest {

    @Rule
    public ActivityTestRule<NavigationBar> mActivityTestRule = new ActivityTestRule<>(NavigationBar.class);

    @Test
    public void postApplicantTest() {

        // Added a sleep statement to match the app's execution delay.
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Click on "post applicant day" button
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.postAppVisit), withText("Post Application Visit Day"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.fragment_container),
                                        0),
                                2),
                        isDisplayed()));
        appCompatButton.perform(click());
        // Added a sleep statement to match the app's execution delay.
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Check navigated to correct page by checking title text
        ViewInteraction textView = onView(
                allOf(withId(R.id.titleText), withText("Post Applicant Days"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class),
                                        0),
                                0),
                        isDisplayed()));
        textView.check(matches(withText("Post Applicant Days")));

        // Check main text is correctly displayed
        ViewInteraction textView2 = onView(
                allOf(withId(R.id.postAppText1), withText("This Offer Holder Day will provide you with the opportunity to put questions to current staff and students, visit teaching and research labs and see examples of student work. Questions can range from the course, to University life, feel free to ask anything! \n\nAs a prospective student you will get another look at the University and the environment you will be working in. Additionally it will give the opportunity to revisit the USB. This is where you will be spending most of your time when working at Newcastle for this course."),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class),
                                        0),
                                1),
                        isDisplayed()));
        textView2.check(matches(isDisplayed()));

        // Check sub-title is loaded
        ViewInteraction textView3 = onView(
                allOf(withId(R.id.header1), withText("What will happen on the day?"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class),
                                        0),
                                2),
                        isDisplayed()));
        textView3.check(matches(isDisplayed()));

        // Check text is correct
        ViewInteraction textView4 = onView(
                allOf(withId(R.id.header2), withText("The day will take place from 10:00 to 15:30 and include:"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class),
                                        0),
                                3),
                        isDisplayed()));
        textView4.check(matches(isDisplayed()));

        // Check list is correctly displayed
        ViewInteraction textView5 = onView(
                allOf(withId(R.id.postAppText2), withText("- Registration and refreshments in the USB \n - Welcome from the Head of School\n - Icebreaker session for applicants\n - Buffet lunch for all\n - Student led talks on life as a student in Newcastle\n - Demonstrations and talks by our research groups\n - Alumni talk\n - Informal chat with an academic\n - Chats with existing students and academic staff over coffee and cake\n - Tours of the School’s facilities"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class),
                                        0),
                                4),
                        isDisplayed()));
        textView5.check(matches(isDisplayed()));

        // Go back to home
        pressBack();
        // Added a sleep statement to match the app's execution delay.
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Ensure navigation back to home screen succeeded
        ViewInteraction textView6 = onView(
                allOf(withId(R.id.title), withText("Welcome!"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.fragment_container),
                                        0),
                                1),
                        isDisplayed()));
        textView6.check(matches(withText("Welcome!")));
    }

    // Auto generated helper methods
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
