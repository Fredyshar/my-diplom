package ru.iteco.fmhandroid.ui.tests;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

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
import ru.iteco.fmhandroid.ui.pages.ControlPanelPage;
import ru.iteco.fmhandroid.ui.pages.CreateAndEditNewsPage;
import ru.iteco.fmhandroid.ui.pages.FilterNewsPage;
import ru.iteco.fmhandroid.ui.pages.NewsPage;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class CreateAndEditNewsTests {
    private final BaseSteps baseSteps = new BaseSteps();
    private final TestData testData = new TestData();
    private final FilterNewsPage filterNewsPage = new FilterNewsPage();
    private final NewsPage newsPage = new NewsPage();
    private final ControlPanelPage controlPanelPage = new ControlPanelPage();
    private final CreateAndEditNewsPage createAndEditNewsPage = new CreateAndEditNewsPage();

    int randomNumber = new Random().nextInt(100) + 1;
    String descriptionText = "test_" + randomNumber;


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
    @DisplayName("Проверка статуса новости, изменение статуса и возвращение в начальное состояние ")
    public void test_edit_status_news() {
        controlPanelPage.checkContainNewsByIndex(0, "ACTIVE");
        createAndEditNewsPage.editNewsByIndex(0);
        controlPanelPage.checkContainNewsByIndex(0, "NOT ACTIVE");
        createAndEditNewsPage.editNewsByIndex(0);
    }

    @Test
    @DisplayName("Создание активной новости")
    public void test_create_active_news() {
        createAndEditNewsPage.createNews(testData.getNameNews(), "14.05.2024", descriptionText);
    }

    @Test
    @DisplayName("Создание активной новости, проверка с сортировкой и удаление новости")
    public void test_create_active_news_check_with_sort_and_delete() {
        createAndEditNewsPage.createNews(testData.getNameNews(), "14.05.2024", descriptionText);
        filterNewsPage.useFilter("12.05.2024", "15.05.2024");
        createAndEditNewsPage.checkCreatedNews(0, "ACTIVE", testData.getNameNews());
        createAndEditNewsPage.deleteNews(0);
    }
}
