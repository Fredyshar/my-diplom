package ru.iteco.fmhandroid.ui.tests;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static ru.iteco.fmhandroid.ui.common.EspressoUtils.waitDisplayed;

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
public class AuthorizationPageTests {
    private final BaseSteps baseSteps = new BaseSteps();
    private final AuthorizationPage authPage = new AuthorizationPage();
    private final TestData testData = new TestData();

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void setUp() {
        try {
            onView(isRoot()).perform(waitDisplayed(R.id.login_text_input_layout, 5000));
        } catch (Exception e) {
            baseSteps.logout();
        }
    }

    @Test
    @DisplayName("Авторизация с валидным логином и паролем")
    public void test_success_auth_and_logout() {
        authPage.logIn(testData.getValidLogin(), testData.getValidPassword());
        baseSteps.checkLogoApp();

        baseSteps.logout();
    }

    @Test
    @DisplayName("Авторизация с невалидным логином и паролем")
    public void test_auth_with_wrong_login_and_pass() {
        authPage.fillLoginAndPasswordField(testData.getNoValidLogin(), testData.getNoValidLogin());
        authPage.clickSignIn();

        baseSteps.showToastMessage("Something went wrong. Try again later.");
    }

    @Test
    @DisplayName("Авторизация с пустым логином и паролем")
    public void test_auth_with_empty_login_and_password() {
        authPage.clickSignIn();

        baseSteps.showToastMessage("Login and password cannot be empty");
    }

    @Test
    @DisplayName("Авторизация с пустым логином")
    public void test_auth_with_empty_login() {
        authPage.fillLoginAndPasswordField("", testData.getValidPassword());
        authPage.clickSignIn();

        baseSteps.showToastMessage("Login and password cannot be empty");
    }

    @Test
    @DisplayName("Авторизация с пустым паролем")
    public void test_auth_with_empty_pass() {
        authPage.fillLoginAndPasswordField(testData.getValidLogin(), "");
        authPage.clickSignIn();

        baseSteps.showToastMessage("Login and password cannot be empty");
    }
}
