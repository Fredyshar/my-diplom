package ru.iteco.fmhandroid.ui.tests;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Random;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Allure;
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
        int index = 0;
        Allure.step("Проверка статус публикации с индексом:" + index);
        controlPanelPage.checkContainNewsByIndex(index, "ACTIVE");
        Allure.step("Изменение статуса публикации с индексом:" + index);
        createAndEditNewsPage.editNewsByIndex(index);
        Allure.step("Проверка изменения статуса публикации с индексом:" + index);
        controlPanelPage.checkContainNewsByIndex(0, "NOT ACTIVE");
        Allure.step("Изменение статуса в первоначальное состояние публикации с индексом:" + index);
        createAndEditNewsPage.editNewsByIndex(index);
    }

    @Test
    @DisplayName("Создание активной новости")
    public void test_create_active_news() {
        Allure.step("Создание активной новости с текстом:" + descriptionText);
        createAndEditNewsPage.createNews(testData.getNameNews(), testData.getDateCreateNews(0), descriptionText);
    }

    @Test
    @DisplayName("Создание активной новости, проверка с сортировкой и удаление новости")
    public void test_create_active_news_check_with_sort_and_delete() {
        Allure.step("Создание активной новости с текстом:" + descriptionText);
        createAndEditNewsPage.createNews(testData.getNameNews(), testData.getDateCreateNews(0), descriptionText);
        Allure.step("Фильтрация новостей с датой начала:" + testData.getDateFilterNews("fromFilter") + "и датой окончания:" + testData.getDateFilterNews("toFilter"));
        filterNewsPage.useFilter(testData.getDateFilterNews("fromFilter"), testData.getDateFilterNews("toFilter"));
        Allure.step("Проверка создания новости с активным статусом и именем:" + testData.getNameNews());
        createAndEditNewsPage.checkCreatedNews(0, "ACTIVE", testData.getNameNews());
        Allure.step("Удаление нрвости с индексом 0");
        createAndEditNewsPage.deleteNews(0);
    }
}
