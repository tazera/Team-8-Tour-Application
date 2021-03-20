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

/* @Purpose: Instrumented Test class to test appropriate UI elements are loaded on all self tour screens
 * @Created  by Matthew Pearson
 * @Since   20/04/2020
 */

@LargeTest
@RunWith(AndroidJUnit4.class)
public class SelfTourTest {

    @Rule
    public ActivityTestRule<NavigationBar> mActivityTestRule = new ActivityTestRule<>(NavigationBar.class);

    @Test
    public void selfTourTest() {
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
        // Click self tour button
        ViewInteraction navigationMenuItemView = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.design_navigation_view),
                                childAtPosition(
                                        withId(R.id.nav_view),
                                        0)),
                        3),
                        isDisplayed()));
        navigationMenuItemView.perform(click());

        // Click pre-set tour option
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.presetButton), withText("Follow a pre-set walking tour"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                1)));
        appCompatButton.perform(scrollTo(), click());

        // Ensure navigated to correct screen by checking title text
        ViewInteraction textView = onView(
                allOf(withId(R.id.titleText), withText("Take a tour!"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class),
                                        0),
                                0),
                        isDisplayed()));
        textView.check(matches(withText("Take a tour!")));

        // Navigate to first screen
        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.welcome_btn), withText("Welcome!"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                1)));
        appCompatButton2.perform(scrollTo(), click());

        // Check first paragraph matches to ensure page has loaded
        ViewInteraction textView3 = onView(
                allOf(withId(R.id.textView2), withText("Welcome to the award winning Urban Sciences Building (also known as USB) – home of the School of Computing based on the Newcastle Helix site.\n\n\n This is part of a £350 million flagship city centre regeneration project bringing together academia, the public sector, communities, business and industry.\n\n\n The 24 acre site is being developed with Newcastle City Council and Legal and General. \n\n\n It is planned to be a mixture of residential and academic units and commercial businesses on the site. \n\n\n The site was originally the home to Scottish and Newcastle breweries who brewed Newcastle Brown Ale which is now brewed in the Netherlands ."),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.fragment_container),
                                        0),
                                1),
                        isDisplayed()));
        textView3.check(matches(isDisplayed()));

        // Go back
        pressBack();

        /* Click on next screen, and assert that all elements are correctly loaded.
            This looping testing repeats until all pages of the tour have been tested.
         */
        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.buildings_btn), withText("Surrounding Buildings"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                2)));
        appCompatButton3.perform(scrollTo(), click());

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.titleText), withText("Surrounding Buildings"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class),
                                        0),
                                0),
                        isDisplayed()));
        textView4.check(matches(withText("Surrounding Buildings")));

        ViewInteraction textView5 = onView(
                allOf(withId(R.id.textView2), withText("You may think we are working and studying on a building site, but the Helix is an exciting new development with buildings either side of us providing lecture space and big data opportunities for our students."),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class),
                                        0),
                                1),
                        isDisplayed()));
        textView5.check(matches(isDisplayed()));

        ViewInteraction textView6 = onView(
                allOf(withId(R.id.frederick_title), withText("The Frederick Douglass Centre"),
                        isDisplayed()));
        textView6.check(matches(withText("The Frederick Douglass Centre")));

        ViewInteraction imageView = onView(
                allOf(withId(R.id.imageView),
                        isDisplayed()));
        imageView.check(matches(isDisplayed()));

        ViewInteraction textView7 = onView(
                allOf(withId(R.id.textView3), withText("Designed to offer multi-use space for outreach and teaching events, it houses an auditorium that can hold 750 people, and can hold 200 more in a collaborative lecture theatre."),
                        isDisplayed()));
        textView7.check(matches(isDisplayed()));

        ViewInteraction imageView2 = onView(
                allOf(withId(R.id.imageView2),
                        isDisplayed()));
        imageView2.check(matches(isDisplayed()));

        ViewInteraction imageView3 = onView(
                allOf(withId(R.id.imageView),
                        isDisplayed()));
        imageView3.check(matches(isDisplayed()));

        pressBack();

        ViewInteraction appCompatButton4 = onView(
                allOf(withId(R.id.design_btn), withText("Design of the USB"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        appCompatButton4.perform(scrollTo(), click());

        ViewInteraction textView8 = onView(
                allOf(withId(R.id.titleText), withText("Design of the USB"),
                        isDisplayed()));
        textView8.check(matches(withText("Design of the USB")));

        ViewInteraction textView9 = onView(
                allOf(withId(R.id.textView2), withText("The design of the building has had a lot of input from academics which has lead to some good design features that would have otherwise not been included."),
                        isDisplayed()));
        textView9.check(matches(isDisplayed()));

        ViewInteraction textView10 = onView(
                allOf(withId(R.id.windows_title), withText("The USB's Windows"),
                        isDisplayed()));
        textView10.check(matches(withText("The USB's Windows")));

        ViewInteraction imageView4 = onView(
                allOf(withId(R.id.imageView),
                        isDisplayed()));
        imageView4.check(matches(isDisplayed()));

        pressBack();

        ViewInteraction appCompatButton5 = onView(
                allOf(withId(R.id.history_btn), withText("History of the School"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                4)));
        appCompatButton5.perform(scrollTo(), click());

        ViewInteraction textView11 = onView(
                allOf(withId(R.id.titleText), withText("History of the School"),
                        isDisplayed()));
        textView11.check(matches(withText("History of the School")));

        ViewInteraction imageView5 = onView(
                allOf(withId(R.id.imageView),
                        isDisplayed()));
        imageView5.check(matches(isDisplayed()));

        ViewInteraction textView12 = onView(
                allOf(withId(R.id.windows_title), withText("The Building Reception"),
                        isDisplayed()));
        textView12.check(matches(withText("The Building Reception")));

        ViewInteraction textView13 = onView(
                allOf(withId(R.id.textView2), withText("Although housed in a new state of the art building, the school offers a long history having recently celebrated its 60th birthday and is one of the oldest schools of computer science in the UK and was one of the first to award both undergraduate and postgraduate degrees. In 2019 we celebrate 50 years since the first graduation from an undergraduate programme.\n\n The display next to reception shows some of our oldest computers and those we have used throughout our 60 year history."),
                        isDisplayed()));
        textView13.check(matches(isDisplayed()));

        pressBack();

        ViewInteraction appCompatButton6 = onView(
                allOf(withId(R.id.atrium_btn), withText("The Atrium"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                5)));
        appCompatButton6.perform(scrollTo(), click());

        ViewInteraction textView14 = onView(
                allOf(withId(R.id.titleText), withText("The Atrium"),
                        isDisplayed()));
        textView14.check(matches(withText("The Atrium")));

        ViewInteraction textView15 = onView(
                allOf(withId(R.id.ground_floor_sub_title), withText("Ground Floor Facilities"),
                        isDisplayed()));
        textView15.check(matches(withText("Ground Floor Facilities")));

        ViewInteraction textView16 = onView(
                allOf(withId(R.id.textView2), withText("There is the Cyber Physical Systems Laboratory, This lab focuses on systems that combine hardware, software, networking and control. It is developing integrated systems for transport, smart grids, building infrastructure etc.\n\n There is the Decision Theatre, this is an iInteractive 3D facility linked to the Urban Observatory that provides companies, policy makers and researchers with powerful data visualisation for collaborative decision making.\n\n Finally there is the Urban Observatory, this is the largest urban sensing network in the UK. It records millions of observations daily and provides insights into how Newcastle works over multiple time frames and sectors."),
                        isDisplayed()));
        textView16.check(matches(isDisplayed()));

        ViewInteraction imageView6 = onView(
                allOf(withId(R.id.imageView),
                        isDisplayed()));
        imageView6.check(matches(isDisplayed()));

        pressBack();

        ViewInteraction appCompatButton7 = onView(
                allOf(withId(R.id.openlab_btn), withText("Open Lab / Lectures"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                6)));
        appCompatButton7.perform(scrollTo(), click());

        ViewInteraction textView17 = onView(
                allOf(withId(R.id.titleText), withText("Open Lab / Lectures"),
                        isDisplayed()));
        textView17.check(matches(withText("Open Lab / Lectures")));

        ViewInteraction textView18 = onView(
                allOf(withId(R.id.textView2), withText("Open lab is focused on human interaction with computers and human based design of computer systems.\n\n One of the examples of projects in the Open Lab is the ambient kitchen. This has utensils that contain instruments that communicate with the network around them allowing the kitchen to help you work out if you are stirring, cutting, how long you have been doing it for etc. This technology might allow someone with mild dementia to get help in the kitchen and enable them to get computer aided assistance."),
                        isDisplayed()));
        textView18.check(matches(isDisplayed()));

        ViewInteraction imageView7 = onView(
                allOf(withId(R.id.imageView),
                        isDisplayed()));
        imageView7.check(matches(isDisplayed()));

        ViewInteraction textView19 = onView(
                allOf(withId(R.id.textView3), withText("The lecture theatre has a capacity of 303 students and has the ability for the lecturer to customise what is shown on each of the projected screen. This means the lecture can still display the lecture notes whilst they are demonstrating a piece of code or practical element of the lecture.\n\n Each seat has lumbar support and access to a power socket with USB ports."),
                        isDisplayed()));
        textView19.check(matches(isDisplayed()));

        pressBack();

        ViewInteraction appCompatButton8 = onView(
                allOf(withId(R.id.staff_btn), withText("TIG / Staff"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                7)));
        appCompatButton8.perform(scrollTo(), click());

        ViewInteraction textView20 = onView(
                allOf(withId(R.id.titleText), withText("TIG / Staff"),
                        isDisplayed()));
        textView20.check(matches(withText("TIG / Staff")));

        ViewInteraction textView21 = onView(
                allOf(withId(R.id.textView2), withText("On the second floor you can find the exit to the lecture theatre, the School reception and our teaching innovation group.\n\n The School Office contains all the Professional Services staff and the NUIT Computing Support team.\n\n This is the area where you should come and ask for help and support when required."),
                        isDisplayed()));
        textView21.check(matches(isDisplayed()));

        ViewInteraction imageView8 = onView(
                allOf(withId(R.id.imageView),
                        isDisplayed()));
        imageView8.check(matches(isDisplayed()));

        ViewInteraction textView22 = onView(
                allOf(withId(R.id.textView3), withText("The Teaching Innovation Group aim to encourage, foster and pursue innovation in teaching. TIG run our Outreach programme with the Computing at School initiative.\n\n This programme runs teacher training, outreach events and visits to schools.\n\n TIG are also responsible for reviewing existing degree programmes, developing employability skills and maintaining links with industry."),
                        isDisplayed()));
        textView22.check(matches(isDisplayed()));

        pressBack();

        ViewInteraction appCompatButton9 = onView(
                allOf(withId(R.id.flatfloor_btn), withText("Flat Floor Teaching"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                8)));
        appCompatButton9.perform(scrollTo(), click());

        ViewInteraction textView23 = onView(
                allOf(withId(R.id.titleText), withText("Flat Floor Teaching"),
                        isDisplayed()));
        textView23.check(matches(withText("Flat Floor Teaching")));

        ViewInteraction textView24 = onView(
                allOf(withId(R.id.titleText), withText("Flat Floor Teaching"),
                        isDisplayed()));
        textView24.check(matches(withText("Flat Floor Teaching")));

        ViewInteraction textView25 = onView(
                allOf(withId(R.id.textView2), withText("The flat floor teaching spaces have 312 machines, with all the software that students will need for their degree.\n\n The machines run NetSupport software that allows the lecturer to send and receive files from the class. The lecturer is also able to share their screen to the students and give a lecture."),
                        isDisplayed()));
        textView25.check(matches(isDisplayed()));

        ViewInteraction imageView9 = onView(
                allOf(withId(R.id.imageView),
                        isDisplayed()));
        imageView9.check(matches(isDisplayed()));

        ViewInteraction textView26 = onView(
                allOf(withId(R.id.textView3), withText("The desks allow students to work on their own laptop or use pen and paper for the modules that do not require a computer.\n\n The undergraduate and the MSc Computer Science students use this area."),
                        isDisplayed()));
        textView26.check(matches(isDisplayed()));

        pressBack();

        ViewInteraction appCompatButton10 = onView(
                allOf(withId(R.id.terrace_btn), withText("The Terrace"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                9)));
        appCompatButton10.perform(scrollTo(), click());

        ViewInteraction textView27 = onView(
                allOf(withId(R.id.titleText), withText("The Terrace"),
                        isDisplayed()));
        textView27.check(matches(withText("The Terrace")));

        ViewInteraction textView28 = onView(
                allOf(withId(R.id.textView2), withText("On the fourth floor, there is more flat floor teaching spaces and seminar rooms but these are usually used by 3rd year students and above."),
                        isDisplayed()));
        textView28.check(matches(isDisplayed()));

        ViewInteraction imageView10 = onView(
                allOf(withId(R.id.imageView),
                        isDisplayed()));
        imageView10.check(matches(isDisplayed()));

        ViewInteraction textView29 = onView(
                allOf(withId(R.id.textView3), withText("The building has 2 roof terraces on the 4th and 5th floors, with the one on the 4th floor accessible to students.\n\n This roof terrace has a wild flower meadow which is great for bees and the views over Newcastle."),
                        isDisplayed()));
        textView29.check(matches(isDisplayed()));

        pressBack();

        ViewInteraction appCompatButton11 = onView(
                allOf(withId(R.id.research_btn), withText("Research Groups"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                10)));
        appCompatButton11.perform(scrollTo(), click());

        ViewInteraction textView30 = onView(
                allOf(withId(R.id.titleText), withText("Research Groups"),
                        isDisplayed()));
        textView30.check(matches(withText("Research Groups")));

        ViewInteraction textView31 = onView(
                allOf(withId(R.id.textView2), withText("The 5th and 6th floors of the building are home to 2 research groups.\n\n Interdisciplinary Computing and Complex BioSystems have expertise in machine intelligence, complex systems and computational biology. The problems they focus on are in natural complex systems and synthetic ones. This ranges from biology, chemistry and physics, to biological engineering, health care, and software engineering."),
                        isDisplayed()));
        textView31.check(matches(isDisplayed()));

        ViewInteraction imageView11 = onView(
                allOf(withId(R.id.imageView),
                        isDisplayed()));
        imageView11.check(matches(isDisplayed()));

        ViewInteraction textView32 = onView(
                allOf(withId(R.id.textView3), withText("Secure and Resilient Systems, Their research directly contributes to creating modern information systems, networks and infrastructures.\n\n They are dependable and secure in all aspects. The Secure and Resilient Systems group investigates fundamental concepts, development techniques, models, architectures and mechanisms that directly contribute to creating modern information systems, networks and infrastructures that are dependable and secure in all aspects."),
                        isDisplayed()));
        textView32.check(matches(isDisplayed()));

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
