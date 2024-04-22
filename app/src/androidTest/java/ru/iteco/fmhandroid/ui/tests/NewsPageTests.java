package ru.iteco.fmhandroid.ui.tests;

import static androidx.test.espresso.Espresso.pressBack;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.common.BaseSteps;
import ru.iteco.fmhandroid.ui.common.TestData;
import ru.iteco.fmhandroid.ui.pages.AboutPage;
import ru.iteco.fmhandroid.ui.pages.AuthorizationPage;
import ru.iteco.fmhandroid.ui.pages.FilterNewsPage;
import ru.iteco.fmhandroid.ui.pages.MainPage;
import ru.iteco.fmhandroid.ui.pages.NewsPage;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class NewsPageTests {
    private final BaseSteps baseSteps = new BaseSteps();
    private final AuthorizationPage authPage = new AuthorizationPage();
    private final MainPage mainPage = new MainPage();
    private final TestData testData = new TestData();
    private final FilterNewsPage filterNewsPage = new FilterNewsPage();
    private final NewsPage newsPage = new NewsPage();

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void logIn() {
        authPage.logIn(testData.getValidLogin(), testData.getValidPassword());
    }

    @After
    public void logOut() {
        baseSteps.logout();
    }

    @Test
    @DisplayName("Видимость основынх элементов на странице")
    public void test_displayed_main_elements_on_the_page() {
        mainPage.goToNewsPage();

        newsPage.checkHeaderPage();
        newsPage.checkDisplayedMainElements();
    }

    @Test
    @DisplayName("Переход на страницу фильтрации")
    public void test_open_filter_page() {
        mainPage.goToNewsPage();
        newsPage.clickFilterButton();
        filterNewsPage.checkHeaderPage();
        pressBack();
    }

    @Test
    @DisplayName("Переход на страницу редактирования новостей")
    public void test_open_edit_news_page() {
        mainPage.goToNewsPage();
        newsPage.clickEditNewsButton();
    }

}
