package ru.iteco.fmhandroid.ui.tests;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.pages.AuthorizationPage;
import ru.iteco.fmhandroid.ui.common.BaseSteps;
import ru.iteco.fmhandroid.ui.common.TestData;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class AuthorizationTests {
    BaseSteps baseSteps = new BaseSteps();
    AuthorizationPage authPage = new AuthorizationPage();
    TestData testData = new TestData();

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void setUp() {
    }

    @Test
    @DisplayName("Авторизация с валидным логином и паролем")
    public void test_success_auth_and_logout() {
        baseSteps.expectSplashScreen();
        authPage.fillLoginAndPasswordField(testData.getValidLogin(), testData.getValidPassword());
        authPage.clickSignIn();

        ViewInteraction logoApp = onView(withId(R.id.trademark_image_view));
        logoApp.check(matches(isDisplayed()));

        baseSteps.logout();

    }

    @Test
    @DisplayName("Авторизация с невалидным логином и паролем")
    public void test_auth_with_wrong_login_and_pass() {
        baseSteps.expectSplashScreen();
        authPage.fillLoginAndPasswordField(testData.getNoValidLogin(), testData.getNoValidLogin());
        authPage.signInButton.perform(click());

        baseSteps.showToastMessage("Something went wrong. Try again later.");
    }

    @Test
    @DisplayName("Авторизация с пустым логином и паролем")
    public void test_auth_with_empty_login_and_password() {
        baseSteps.expectSplashScreen();
        authPage.signInButton.perform(click());

        baseSteps.showToastMessage("Login and password cannot be empty");
    }

    @Test
    @DisplayName("Авторизация с пустым логином")
    public void test_auth_with_empty_login() {
        baseSteps.expectSplashScreen();
        authPage.fillLoginAndPasswordField("", testData.getValidPassword());
        authPage.signInButton.perform(click());

        baseSteps.showToastMessage("Login and password cannot be empty");
    }
    @Test
    @DisplayName("Авторизация с пустым паролем")
    public void test_auth_with_empty_pass() {
        baseSteps.expectSplashScreen();
        authPage.fillLoginAndPasswordField(testData.getValidLogin(), "");
        authPage.signInButton.perform(click());

        baseSteps.showToastMessage("Login and password cannot be empty");
    }
}
