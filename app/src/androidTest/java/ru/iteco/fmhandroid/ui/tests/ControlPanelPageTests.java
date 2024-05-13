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
import ru.iteco.fmhandroid.ui.pages.ControlPanelPage;
import ru.iteco.fmhandroid.ui.pages.NewsPage;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class ControlPanelPageTests {
    private final BaseSteps baseSteps = new BaseSteps();
    private final NewsPage newsPage = new NewsPage();
    private final ControlPanelPage controlPanelPage = new ControlPanelPage();

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void setUp() {
        baseSteps.ensureAuthenticated();
        baseSteps.navigationTo("NewsPage");
        newsPage.clickEditNewsButton();
    }


    @Test
    @DisplayName("Видимость основных элементов на странице")
    public void test_displayed_main_elements_on_the_page() {
        controlPanelPage.checkHeaderPage();
        controlPanelPage.checkInitStatePage();
    }

    @Test
    @DisplayName("Первая карточка в списке. Основные элементы")
    public void test_displayed_first_news_on_the_page() {
        String statusFirstNews = controlPanelPage.getNewsStatus(0);
        String titleFirstNews = controlPanelPage.getNewsTitle(0);
        controlPanelPage.checkNewsByIndexAndTitle(0, statusFirstNews, titleFirstNews);
    }

}
