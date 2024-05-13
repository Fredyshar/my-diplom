package ru.iteco.fmhandroid.ui.common;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static ru.iteco.fmhandroid.ui.common.EspressoUtils.waitDisplayed;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.pages.AuthorizationPage;
import ru.iteco.fmhandroid.ui.pages.MainPage;

public class BaseSteps {
    private final AuthorizationPage authPage = new AuthorizationPage();
    private final MainPage mainPage = new MainPage();
    private final ViewInteraction logoApp = onView(withId(R.id.trademark_image_view));
    private final TestData testData = new TestData();

    public void logout() {
        mainPage.logOut();
        authPage.checkHeaderAuthPage();
    }

    public void expectSplashScreen() {
        ViewInteraction imageSplashScreen = onView(withId(R.id.splashscreen_image_view));
        imageSplashScreen.check(matches(isDisplayed()));

        ViewInteraction progressBar = onView(withId(R.id.splash_screen_circular_progress_indicator));
        progressBar.check(matches(isDisplayed()));

        ViewInteraction textSplashScreen = onView(withId(R.id.splashscreen_text_view));
        textSplashScreen.check(matches(isDisplayed()));
        onView(isRoot()).perform(waitDisplayed(R.id.login_text_input_layout, 4000));
        authPage.checkHeaderAuthPage();
    }

    public void showToastMessage(String textMessage) {
        onView(withText(textMessage))
                .inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));
    }

    public void checkLogoApp() {
        logoApp.check(matches(isDisplayed()));
    }

    public void navigationTo(String page) {
        switch (page) {
            case "AboutPage":
                mainPage.goToAboutPage();
                break;
            case "NewsPage":
                mainPage.goToNewsPage();
                break;
            case "MainPage":
                mainPage.goToMainPage();
                break;
            case "QuotesPage":
                mainPage.goToQuotesPage();
                break;
            default:
                System.out.println("Invalid page name: " + page);
                break;
        }
    }

    public void ensureAuthenticated() {
        try {
            onView(isRoot()).perform(waitDisplayed(R.id.trademark_image_view, 5000));
        } catch (Exception e) {
            authPage.logIn(testData.getValidLogin(), testData.getValidPassword());
        }
    }

    public void checkInitStateAppWhenUserAuth() {
        mainPage.checkInitStatePage();
    }

}