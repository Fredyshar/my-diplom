package ru.iteco.fmhandroid.ui.tests;

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
import ru.iteco.fmhandroid.ui.common.TestData;
import ru.iteco.fmhandroid.ui.pages.AboutPage;
import ru.iteco.fmhandroid.ui.pages.MainPage;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class AboutPageTests {
    private final BaseSteps baseSteps = new BaseSteps();
    private final TestData testData = new TestData();
    private final AboutPage aboutPage = new AboutPage();

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void setUp() {
        baseSteps.ensureAuthenticated();
        baseSteps.navigationTo("AboutPage");
    }

    @Test
    @DisplayName("Отображение элементов на странице 'О приложении', и клик по кнопке назад")
    public void test_displayed_main_elements_on_the_page_and_click_back() {
        aboutPage.checkDisplayedVersionAndCompanyInfo();
        aboutPage.checkDisplayedTitlesAndLink();

        aboutPage.clickBackButton();
        baseSteps.checkInitStateAppWhenUserAuth();
    }

    @Test
    @DisplayName("Перехода по первой ссылке - privacy_policy")
    public void test_open_page_with_privacy_policy() {
        aboutPage.checkOpenOfPageByLink(testData.getLinkContainingSubstring("privacy"));
        aboutPage.clickBackButton();
    }

    @Test
    @DisplayName("Перехода по второй ссылке - terms_of_use")
    public void test_open_page_with_terms_of_use() {
        aboutPage.checkOpenOfPageByLink(testData.getLinkContainingSubstring("terms"));
        aboutPage.clickBackButton();
    }
}
