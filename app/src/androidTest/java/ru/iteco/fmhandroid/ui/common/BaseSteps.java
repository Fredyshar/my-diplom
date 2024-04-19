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
    AuthorizationPage authPage = new AuthorizationPage();
    MainPage mainPage = new MainPage();
    private final ViewInteraction logoApp = onView(withId(R.id.trademark_image_view));
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

    public void hamburgerMenuButtonClick() {
        mainPage.hamburgerMenuButtonClick();
    }
}
