package ru.iteco.fmhandroid.ui.tests;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.steps.AuthSteps;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AuthorizationTests {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    String validLogin = "login2";
    String validPassword = "password2";

    @Test
    @DisplayName("Авторизация с валидным логином и паролем")
    public void test_success_auth_and_logout() throws InterruptedException {
        AuthSteps.expectSplashScreen();
        AuthSteps.logIn(validLogin, validPassword);

        ViewInteraction logoApp = onView(withId(R.id.trademark_image_view));
        logoApp.check(matches(isDisplayed()));

        AuthSteps.logout();
    }

}
