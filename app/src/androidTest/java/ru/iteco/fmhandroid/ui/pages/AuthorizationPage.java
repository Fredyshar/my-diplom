package ru.iteco.fmhandroid.ui.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static ru.iteco.fmhandroid.ui.steps.BaseSteps.childAtPosition;
import androidx.test.espresso.ViewInteraction;
import ru.iteco.fmhandroid.R;

public class AuthorizationPage {
    public static ViewInteraction headerAuthPage = onView(withText("Authorization"));
    public static ViewInteraction loginField = onView(withId(R.id.login_text_input_layout));
    public static ViewInteraction passField = onView(withId(R.id.password_text_input_layout));
    public static ViewInteraction signInButton = onView(withId(R.id.enter_button));

    public static void fillLogin(String login) {
       loginField.perform(click());
        onView(allOf(childAtPosition(
                        childAtPosition(
                                withId(R.id.login_text_input_layout),
                                0),
                        0),
                isDisplayed())).perform(replaceText(login), closeSoftKeyboard());
    }

    public static void fillPassword(String password) {
        passField.perform(click());
        onView(allOf(childAtPosition(
                        childAtPosition(
                                withId(R.id.password_text_input_layout),
                                0),
                        0),
                isDisplayed())).perform(replaceText(password), closeSoftKeyboard());
    }

    public static void fillLoginAndPasswordField(String login, String password) {
        fillLogin(login);
        fillPassword(password);
    }

    public static void clickSignIn() throws InterruptedException {
        signInButton.check(matches(isDisplayed()));
        signInButton.perform(click());
        Thread.sleep(2000);
    }
}
