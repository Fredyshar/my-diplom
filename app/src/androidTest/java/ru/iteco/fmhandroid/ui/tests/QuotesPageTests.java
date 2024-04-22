package ru.iteco.fmhandroid.ui.tests;

import static androidx.test.espresso.Espresso.pressBack;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Random;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.common.BaseSteps;
import ru.iteco.fmhandroid.ui.common.TestData;
import ru.iteco.fmhandroid.ui.pages.AboutPage;
import ru.iteco.fmhandroid.ui.pages.AuthorizationPage;
import ru.iteco.fmhandroid.ui.pages.MainPage;
import ru.iteco.fmhandroid.ui.pages.NewsPage;
import ru.iteco.fmhandroid.ui.pages.QuotesPage;


@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class QuotesPageTests {
    private final BaseSteps baseSteps = new BaseSteps();
    private final AuthorizationPage authPage = new AuthorizationPage();
    private final MainPage mainPage = new MainPage();
    private final TestData testData = new TestData();
    private final QuotesPage quotesPage = new QuotesPage();
    private final AboutPage aboutPage = new AboutPage();
    private final NewsPage newsPage = new NewsPage();
    private int indexQuote = 0;

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void logIn() {
        authPage.logIn(testData.getValidLogin(), testData.getValidPassword());
        mainPage.goToQuotesPage();
    }

    @After
    public void logOut() {
        baseSteps.logout();
    }

    @Test
    @DisplayName("Переход на страницу с цитатами и видимость основных элементов")
    public void test_quotes_page_navigation_and_visibility() {
        quotesPage.checkHeaderQuotesPage();
    }

    @Test
    @DisplayName("Разворачивание рандомной цитаты, проверка отображения и содержания текста")
    public void test_drop_down_quote() {
        int indexQuote = new Random().nextInt(testData.lengthQuotes());
        quotesPage.checkAvailQuote(indexQuote);
        quotesPage.dropDownQuote(indexQuote);
        quotesPage.checkAvailQuoteDescription(indexQuote);
    }

    @Test
    @DisplayName("Разворачивание первой цитаты и сворачивание")
    public void test_drop_down_first_quote() {
        quotesPage.checkNotDisplayedAvailQuoteDescription(indexQuote);
        quotesPage.dropDownQuote(indexQuote);
        quotesPage.checkAvailQuoteDescription(indexQuote);
        quotesPage.dropDownQuote(indexQuote);
        quotesPage.checkNotDisplayedAvailQuoteDescription(indexQuote);
    }

    @Test
    @DisplayName("Переход с страницы цитат на страницы прилложения")
    public void navigatingPagesOfMainMenuFromQuotesPage() {
        quotesPage.checkHeaderQuotesPage();

        mainPage.goToNewsPage();
        newsPage.checkHeaderPage();
        pressBack();

        mainPage.goToMainPage();
        mainPage.checkClickableAllNewsButton();
        pressBack();

        mainPage.goToAboutPage();
        aboutPage.checkDisplayedVersionAndCompanyInfo();
        aboutPage.clickBackButton();
    }
}
