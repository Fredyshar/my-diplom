package ru.iteco.fmhandroid.ui.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static ru.iteco.fmhandroid.ui.common.ToastMatcher.childAtPosition;
import static ru.iteco.fmhandroid.ui.common.EspressoUtils.waitDisplayed;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.common.TestData;

public class AuthorizationPage {
    private final ViewInteraction headerAuthPage = onView(withText("Authorization"));
    private final ViewInteraction loginField = onView(withId(R.id.login_text_input_layout));
    private final ViewInteraction passField = onView(withId(R.id.password_text_input_layout));
    private final ViewInteraction signInButton = onView(withId(R.id.enter_button));

    public void fillLogin(String login) {
        loginField.perform(click());
        onView(allOf(childAtPosition(
                        childAtPosition(
                                withId(R.id.login_text_input_layout),
                                0),
                        0),
                isDisplayed())).perform(replaceText(login), closeSoftKeyboard());
    }

    public void fillPassword(String password) {
        passField.perform(click());
        onView(allOf(childAtPosition(
                        childAtPosition(
                                withId(R.id.password_text_input_layout),
                                0),
                        0),
                isDisplayed())).perform(replaceText(password), closeSoftKeyboard());
    }

    public void fillLoginAndPasswordField(String login, String password) {
        fillLogin(login);
        fillPassword(password);
    }

    public void clickSignIn() {
        signInButton.check(matches(isDisplayed()));
        signInButton.perform(click());
    }

    public void logIn(String login, String password) {
        onView(isRoot()).perform(waitDisplayed(R.id.login_text_input_layout, 4000));
        fillLoginAndPasswordField(login, password);
        clickSignIn();
        onView(isRoot()).perform(waitDisplayed(R.id.authorization_image_button, 2000));
    }

    public void checkHeaderAuthPage() {
        headerAuthPage.check(matches(isDisplayed()));
    }
}
