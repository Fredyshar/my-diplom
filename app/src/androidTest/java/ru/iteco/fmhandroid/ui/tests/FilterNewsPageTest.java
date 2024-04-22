package ru.iteco.fmhandroid.ui.tests;

import static androidx.test.espresso.Espresso.pressBack;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.common.BaseSteps;
import ru.iteco.fmhandroid.ui.common.TestData;
import ru.iteco.fmhandroid.ui.pages.AuthorizationPage;
import ru.iteco.fmhandroid.ui.pages.FilterNewsPage;
import ru.iteco.fmhandroid.ui.pages.MainPage;
import ru.iteco.fmhandroid.ui.pages.NewsPage;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class FilterNewsPageTest {
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
        mainPage.goToNewsPage();
        newsPage.clickFilterButton();
    }

    @After
    public void logOut() {
        baseSteps.logout();
    }

    @Test
    @DisplayName("Видимость основынх элементов на странице")
    public void test_displayed_main_elements_on_the_page() {
        filterNewsPage.checkHeaderPage();
        filterNewsPage.checkInitStatePage();
        pressBack();
    }

    @Test
    @DisplayName("Клик по кнопке отмены фильтрации возвращает на прошлую страницу")
    public void test_click_cancel_filter() {
        filterNewsPage.clickCancelButton();
        newsPage.checkHeaderPage();
    }

    @Test
    @DisplayName("Клик по фильтрации без ввода фильтров возвращает на страницу всех новостей")
    public void test_click_filter_without_choice_filters() {
        filterNewsPage.clickFilterButton();
        newsPage.checkHeaderPage();
    }
}
