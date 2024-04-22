package ru.iteco.fmhandroid.ui.tests;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;

import static ru.iteco.fmhandroid.ui.common.EspressoUtils.waitDisplayed;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.common.BaseSteps;
import ru.iteco.fmhandroid.ui.common.TestData;
import ru.iteco.fmhandroid.ui.pages.AboutPage;
import ru.iteco.fmhandroid.ui.pages.AuthorizationPage;
import ru.iteco.fmhandroid.ui.pages.FilterNewsPage;
import ru.iteco.fmhandroid.ui.pages.MainPage;
import ru.iteco.fmhandroid.ui.pages.NewsPage;
import ru.iteco.fmhandroid.ui.pages.QuotesPage;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class NewsPageTests {
    private final BaseSteps baseSteps = new BaseSteps();
    private final AuthorizationPage authPage = new AuthorizationPage();
    private final MainPage mainPage = new MainPage();
    private final TestData testData = new TestData();
    private final FilterNewsPage filterNewsPage = new FilterNewsPage();
    private final NewsPage newsPage = new NewsPage();
    private final QuotesPage quotesPage = new QuotesPage();
    private final AboutPage aboutPage = new AboutPage();

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void logIn() {
        try {
            authPage.logIn(testData.getValidLogin(), testData.getValidPassword());
            mainPage.goToNewsPage();
        } catch (Exception e) {
            baseSteps.logout();
            authPage.logIn(testData.getValidLogin(), testData.getValidPassword());
            mainPage.goToNewsPage();
        }
    }

    @After
    public void logOut() {
        baseSteps.logout();
    }

    @Test
    @DisplayName("Видимость основынх элементов на странице")
    public void test_displayed_main_elements_on_the_page() {
        newsPage.checkHeaderPage();
        newsPage.checkDisplayedMainElements();
    }

    @Test
    @DisplayName("Переход на страницу фильтрации")
    public void test_open_filter_page() {
        newsPage.clickFilterButton();
        filterNewsPage.checkHeaderPage();
        pressBack();
    }

    @Test
    @DisplayName("Переход на страницу редактирования новостей")
    public void test_open_edit_news_page() {
        newsPage.clickEditNewsButton();
    }

    @Test
    @DisplayName("Переход с страницы новостей на страницы прилложения")
    public void navigatingPagesOfMainMenuFromNewsPage() {
        mainPage.goToQuotesPage();
        quotesPage.checkHeaderQuotesPage();
        pressBack();

        mainPage.goToMainPage();
        mainPage.checkClickableAllNewsButton();
        pressBack();

        mainPage.goToAboutPage();
        aboutPage.checkDisplayedVersionAndCompanyInfo();
        aboutPage.clickBackButton();

        baseSteps.logout();
    }
}
