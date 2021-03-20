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

/* @Purpose: Instrumented Test class to test buttons on the FAQ screen and database connection
 * @Created  by Lyubomir Tsvetkov
 * @Since   20/04/2020
 */

@LargeTest
@RunWith(AndroidJUnit4.class)
public class FaqTest {

    @Rule
    public ActivityTestRule<NavigationBar> mActivityTestRule = new ActivityTestRule<>(NavigationBar.class);

    @Test
    public void faqTest() {

        // Added a sleep statement to match the app's execution delay.
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ViewInteraction appCompatImageButton2 = onView(
                allOf(withContentDescription("Open navigation drawer"),
                        childAtPosition(
                                allOf(withId(R.id.toolbar),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatImageButton2.perform(click());
        // Open navbar and navigate to FAQ screen
        ViewInteraction navigationMenuItemView = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.design_navigation_view),
                                childAtPosition(
                                        withId(R.id.nav_view),
                                        0)),
                        4),
                        isDisplayed()));
        navigationMenuItemView.perform(click());

        // Check navigated to FAQ screen by checking title text
        ViewInteraction textView = onView(
                allOf(withId(R.id.titleText), withText("Frequently Asked Questions"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class),
                                        0),
                                0),
                        isDisplayed()));
        textView.check(matches(withText("Frequently Asked Questions")));

        // Check button for first question is displayed
        ViewInteraction button = onView(
                allOf(withId(R.id.question1),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class),
                                        0),
                                1),
                        isDisplayed()));
        button.check(matches(isDisplayed()));

        // Click on first question button
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.question1), withText("Is there disabled access to the building?"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                1)));
        appCompatButton.perform(scrollTo(), click());
        // Added a sleep statement to match the app's execution delay.
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Check answer text corresponds to first questions answer
        ViewInteraction textView2 = onView(
                allOf(withId(R.id.text_view_faq), withText("Yes there is disabled access to the building."),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class),
                                        0),
                                6),
                        isDisplayed()));
        textView2.check(matches(withText("Yes there is disabled access to the building.")));

        // Check second question button is loaded
        ViewInteraction button2 = onView(
                allOf(withId(R.id.question2),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class),
                                        0),
                                2),
                        isDisplayed()));
        button2.check(matches(isDisplayed()));

        // Click second question button
        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.question2), withText("Where are the medical centers located near the USB?"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                2)));
        appCompatButton2.perform(scrollTo(), click());
        // Added a sleep statement to match the app's execution delay.
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Check answer text has changed to the correct answer for the second question
        ViewInteraction textView3 = onView(
                allOf(withId(R.id.text_view_faq), withText("The nearest medical center is Royal Victoria Infirmary."),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class),
                                        0),
                                6),
                        isDisplayed()));
        textView3.check(matches(withText("The nearest medical center is Royal Victoria Infirmary.")));

        // Check third question button is loaded
        ViewInteraction button3 = onView(
                allOf(withId(R.id.question3),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class),
                                        0),
                                3),
                        isDisplayed()));
        button3.check(matches(isDisplayed()));

        // Click third question button
        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.question3), withText("Is food available within the USB?"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        appCompatButton3.perform(scrollTo(), click());
        // Added a sleep statement to match the app's execution delay.
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Check answer text has changed to the correct answer for the third question
        ViewInteraction textView4 = onView(
                allOf(withId(R.id.text_view_faq), withText("Yes there is a coffee shop within the building."),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class),
                                        0),
                                6),
                        isDisplayed()));
        textView4.check(matches(withText("Yes there is a coffee shop within the building.")));

        // Check fourth question button is loaded
        ViewInteraction button4 = onView(
                allOf(withId(R.id.question4),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class),
                                        0),
                                4),
                        isDisplayed()));
        button4.check(matches(isDisplayed()));

        // Click fourth question button
        ViewInteraction appCompatButton4 = onView(
                allOf(withId(R.id.question4), withText("What nearby transport links are available?"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                4)));
        appCompatButton4.perform(scrollTo(), click());
        // Added a sleep statement to match the app's execution delay.
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Check answer text has changed to the correct answer for the fourth question
        ViewInteraction textView5 = onView(
                allOf(withId(R.id.text_view_faq), withText("The USB is not linked to any public transport stops directly, but there are are metro stations and bus stops only short walks away. For more information, please check the Directions to USB page."),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class),
                                        0),
                                6),
                        isDisplayed()));
        textView5.check(matches(withText("The USB is not linked to any public transport stops directly, but there are are metro stations and bus stops only short walks away. For more information, please check the Directions to USB page.")));

        // Check fifth question button is loaded
        ViewInteraction button5 = onView(
                allOf(withId(R.id.question5),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class),
                                        0),
                                5),
                        isDisplayed()));
        button5.check(matches(isDisplayed()));

        // Click fifth question button
        ViewInteraction appCompatButton5 = onView(
                allOf(withId(R.id.question5), withText("Where are toilets located?"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                5)));
        appCompatButton5.perform(scrollTo(), click());
        // Added a sleep statement to match the app's execution delay.
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Check answer text has changed to the correct answer for the fifth question
        ViewInteraction textView6 = onView(
                allOf(withId(R.id.text_view_faq), withText("There are restroom facilities on every floor. For more information open the USB Self Guided Tour section where different rooms including toilets are shown."),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class),
                                        0),
                                6),
                        isDisplayed()));

        textView6.check(matches(withText("There are restroom facilities on every floor. For more information open the USB Self Guided Tour section where different rooms including toilets are shown.")));
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
