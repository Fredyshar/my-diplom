package ru.iteco.fmhandroid.ui.tests;


import static androidx.test.espresso.Espresso.pressBack;


import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.common.BaseSteps;
import ru.iteco.fmhandroid.ui.pages.AboutPage;
import ru.iteco.fmhandroid.ui.pages.MainPage;
import ru.iteco.fmhandroid.ui.pages.NewsPage;
import ru.iteco.fmhandroid.ui.pages.QuotesPage;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class MainPageTests {
    private final BaseSteps baseSteps = new BaseSteps();
    private final MainPage mainPage = new MainPage();
    private final NewsPage newsPage = new NewsPage();
    private final QuotesPage quotesPage = new QuotesPage();
    private final AboutPage aboutPage = new AboutPage();

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void setUp() {
        baseSteps.ensureAuthenticated();
    }


    @Test
    @DisplayName("Отображение элементов на главной странице и изначального состояния блока новостей")
    public void test_displayed_main_elements_on_the_page() {
        mainPage.checkInitStatePage();
        mainPage.checkInitStateNewsBlock();
    }

    @Test
    @DisplayName("Сворачивание и разворачивание главного меню")
    public void test_drop_dawn_hamburger_menu() {
        mainPage.hamburgerMenuButtonClick();
        mainPage.checkDisplayedItemsHamburger();
        pressBack();
        mainPage.checkIsNotDisplayedItemsHamburger();
    }

    @Test
    @DisplayName("Кликабельность кнопки перехода ко всем новостям")
    public void test_clickable_all_news_button() {
        mainPage.checkClickableAllNewsButton();
    }

    @Test
    @DisplayName("Сворачивание и разворачивание новостного блока")
    public void test_drop_dawn_news_block() {
        mainPage.checkInitStateNewsBlock();
        mainPage.clickExpandButton();
        mainPage.checkHiddenStateNewsBlock();
        mainPage.clickExpandButton();
        mainPage.checkInitStateNewsBlock();
    }

    @Test
    @DisplayName("Переход с главной страницы на страницы прилложения")
    public void navigatingPagesOfMainMenuAndAllNewsButton() {
        mainPage.goToNewsPage();
        newsPage.checkHeaderPage();
        pressBack();

        mainPage.clickAllNewsButton();
        newsPage.checkHeaderPage();
        pressBack();

        mainPage.goToQuotesPage();
        quotesPage.checkHeaderQuotesPage();
        pressBack();

        mainPage.goToAboutPage();
        aboutPage.checkDisplayedVersionAndCompanyInfo();
        aboutPage.clickBackButton();
    }
}
