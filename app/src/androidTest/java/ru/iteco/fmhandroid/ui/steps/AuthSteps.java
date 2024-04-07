package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.pages.AuthorizationPage;

public class AuthSteps {
    public static void logIn(String login, String password) throws InterruptedException {
        Thread.sleep(5000);

        AuthorizationPage.headerAuthPage.check(matches(isDisplayed()));

        AuthorizationPage.loginField.check(matches(isDisplayed()));
        AuthorizationPage.loginField.perform(click());

        onView(allOf(childAtPosition(
                        childAtPosition(
                                withId(R.id.login_text_input_layout),
                                0),
                        0),
                isDisplayed())).perform(replaceText(login), closeSoftKeyboard());

        AuthorizationPage.passField.check(matches(isDisplayed()));
        AuthorizationPage.passField.perform(click());
        onView(allOf(childAtPosition(
                        childAtPosition(
                                withId(R.id.password_text_input_layout),
                                0),
                        0),
                isDisplayed())).perform(replaceText(password), closeSoftKeyboard());

        AuthorizationPage.SignInButton.check(matches(isDisplayed()));
        AuthorizationPage.SignInButton.perform(click());

        Thread.sleep(3000);
    }

    public static void logout() {
        ViewInteraction appCompatImageButton = onView(withId(R.id.authorization_image_button));
        appCompatImageButton.perform(click());

        ViewInteraction materialTextView = onView(withId(android.R.id.title));
        materialTextView.perform(click());

        AuthorizationPage.headerAuthPage.check(matches(isDisplayed()));
    }

    public static void expectSplashScreen() {
        ViewInteraction imageSplashScreen = onView(withId(R.id.splashscreen_image_view));
        imageSplashScreen.check(matches(isDisplayed()));

        ViewInteraction progressBar = onView(withId(R.id.splash_screen_circular_progress_indicator));
        progressBar.check(matches(isDisplayed()));

        ViewInteraction textSplashScreen = onView(withId(R.id.splashscreen_text_view));
        textSplashScreen.check(matches(isDisplayed()));
    }
    static Matcher<View> childAtPosition(
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
