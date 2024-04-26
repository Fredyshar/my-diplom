package ru.iteco.fmhandroid.ui.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItem;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withParentIndex;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import static ru.iteco.fmhandroid.ui.common.ToastMatcher.withIndex;

import android.view.View;
import android.widget.TextView;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.ViewInteraction;

import org.hamcrest.Matcher;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;

public class ControlPanelPage {
    private final ViewInteraction headerPage = onView(withText("Control panel"));
    private final ViewInteraction sortButton = onView(withId(R.id.sort_news_material_button));
    private final ViewInteraction filterButton = onView(withId(R.id.filter_news_material_button));
    private final ViewInteraction addNewsButton = onView(withId(R.id.add_news_image_view));
    private final ViewInteraction newsByIndex = onView(withParent(allOf(withId(R.id.news_item_material_card_view), withParentIndex(0))));


    public void checkHeaderPage() {
        headerPage.check(matches(isDisplayed()));
        headerPage.check(matches(withText("Control panel")));
    }

    public void checkInitStatePage() {
        sortButton.check(matches(isDisplayed()));
        filterButton.check(matches(isDisplayed()));
        addNewsButton.check(matches(isDisplayed()));
//        newsByIndex.check(matches(isDisplayed()));
    }

    public void checkContainNewsByIndex(int index, String status) {
        Allure.step("Проверка видимости текста названия публикации с индексом");
        onView(withIndex(
                allOf(withId(R.id.news_item_published_text_view),
                        withParent(withParent(withId(R.id.news_item_material_card_view))),
                        isDisplayed()), index))
                .check(matches(withText(status)));
        Allure.step("Проверка видимости кнопки удаления публикации с индексом");
        onView(withIndex(
                allOf(withId(R.id.delete_news_item_image_view),
                        withParent(withParent(withId(R.id.news_item_material_card_view))),
                        isDisplayed()), index))
                .check(matches(isDisplayed()));
        Allure.step("Проверка видимости кнопки редактирования публикации с индексом");
        onView(withIndex(
                allOf(withId(R.id.edit_news_item_image_view),
                        withParent(withParent(withId(R.id.news_item_material_card_view))),
                        isDisplayed()), index))
                .check(matches(isDisplayed()));
        Allure.step("Проверка видимости кнопки разворачивания публикации с индексом");
        onView(withIndex(
                allOf(withId(R.id.view_news_item_image_view), withContentDescription("Expand news card button"),
                        withParent(withParent(withId(R.id.news_item_material_card_view))),
                        isDisplayed()), index))
                .check(matches(isDisplayed()));
        Allure.step("Проверка видимости title публикации с индексом");
        onView(withIndex(
                allOf(withId(R.id.news_item_title_text_view),
                        withParent(withParent(withId(R.id.news_item_material_card_view))),
                        isDisplayed()), index))
                .check(matches(isDisplayed()));
        Allure.step("Проверка видимости текста публикации с индексом");
        onView(withIndex(
                allOf(withId(R.id.news_item_publication_text_view),
                        withParent(withParent(withId(R.id.news_item_material_card_view))),
                        withText("Publication date"),
                        isDisplayed()), index))
                .check(matches(isDisplayed()));
        Allure.step("Проверка видимости \"Creation date\" публикации с индексом");
        onView(withIndex(
                allOf(withId(R.id.news_item_creation_text_view),
                        withParent(withParent(withId(R.id.news_item_material_card_view))),
                        withText("Creation date"),
                        isDisplayed()), index))
                .check(matches(isDisplayed()));
        Allure.step("Проверка видимости \"Author\" публикации с индексом");
        onView(withIndex(
                allOf(withId(R.id.news_item_author_text_view),
                        withParent(withParent(withId(R.id.news_item_material_card_view))),
                        withText("Author"),
                        isDisplayed()), index))
                .check(matches(isDisplayed()));
        Allure.step("Проверка видимости иконки публикации с индексом");
        onView(withIndex(
                allOf(withId(R.id.category_icon_image_view),
                        withParent(withParent(withId(R.id.news_item_material_card_view))),
                        isDisplayed()), index))
                .check(matches(isDisplayed()));
    }

    public void checkNewsByIndexAndTitle(int index, String status, String title) {
        Allure.step("Проверка статуса публикации с индексом");
        onView(withIndex(
                allOf(withId(R.id.news_item_published_text_view),
                        withParent(withParent(withId(R.id.news_item_material_card_view))),
                        isDisplayed()), index))
                .check(matches(withText(status)));
        Allure.step("Проверка видимости кнопки удаления публикации с индексом");
        onView(withIndex(
                allOf(withId(R.id.delete_news_item_image_view),
                        withParent(withParent(withId(R.id.news_item_material_card_view))),
                        isDisplayed()), index))
                .check(matches(isDisplayed()));
        Allure.step("Проверка видимости иконки публикации с индексом");
        onView(withIndex(
                allOf(withId(R.id.edit_news_item_image_view),
                        withParent(withParent(withId(R.id.news_item_material_card_view))),
                        isDisplayed()), index))
                .check(matches(isDisplayed()));
        Allure.step("Проверка видимости кнопки разворачивания публикации с индексом");
        onView(withIndex(
                allOf(withId(R.id.view_news_item_image_view), withContentDescription("Expand news card button"),
                        withParent(withParent(withId(R.id.news_item_material_card_view))),
                        isDisplayed()), index))
                .check(matches(isDisplayed()));
        Allure.step("Проверка видимости текста 'title' публикации с индексом");
        onView(withIndex(
                allOf(withId(R.id.news_item_title_text_view),
                        withParent(withParent(withId(R.id.news_item_material_card_view))),
                        withText(title),
                        isDisplayed()), index))
                .check(matches(isDisplayed()));
        Allure.step("Проверка видимости текста \"Publication date\" публикации с индексом");
        onView(withIndex(
                allOf(withId(R.id.news_item_publication_text_view),
                        withParent(withParent(withId(R.id.news_item_material_card_view))),
                        withText("Publication date"),
                        isDisplayed()), index))
                .check(matches(isDisplayed()));
        Allure.step("Проверка видимости текста \"Creation date\" публикации с индексом");
        onView(withIndex(
                allOf(withId(R.id.news_item_creation_text_view),
                        withParent(withParent(withId(R.id.news_item_material_card_view))),
                        withText("Creation date"),
                        isDisplayed()), index))
                .check(matches(isDisplayed()));
        Allure.step("Проверка видимости текста \"Author\" публикации с индексом");
        onView(withIndex(
                allOf(withId(R.id.news_item_author_text_view),
                        withParent(withParent(withId(R.id.news_item_material_card_view))),
                        withText("Author"),
                        isDisplayed()), index))
                .check(matches(isDisplayed()));
        Allure.step("Проверка видимости иконки категории публикации с индексом");
        onView(withIndex(
                allOf(withId(R.id.category_icon_image_view),
                        withParent(withParent(withId(R.id.news_item_material_card_view))),
                        isDisplayed()), index))
                .check(matches(isDisplayed()));
    }

    public void clickSortButton() {
        sortButton.perform(click());
    }

    public void clickDeleteNewsButtonByIndex(int index) {
        onView(withIndex(
                allOf(withId(R.id.delete_news_item_image_view),
                        withParent(withParent(withId(R.id.news_item_material_card_view))),
                        isDisplayed()), index))
                .perform(click());
    }

    public void searchNewsByTitle(String title, String descriptionText) {
        onView(withId(R.id.news_list_recycler_view))
                .perform(actionOnItem(hasDescendant(withText(title)), scrollTo()), click());
        onView(withId(R.id.news_item_description_text_view))
                .check(matches(withText(descriptionText)));
    }

    public String getNewsStatus(int index) {
        ViewInteraction statusNewsByIndex = onView(withIndex(
                allOf(withId(R.id.news_item_published_text_view),
                        withParent(withParent(withId(R.id.news_item_material_card_view))),
                        isDisplayed()), index));

        return getTextFromView(statusNewsByIndex);
    }

    public String getNewsTitle(int index) {
        ViewInteraction titleNewsByIndex = onView(withIndex(
                allOf(withId(R.id.news_item_title_text_view),
                        withParent(withParent(withId(R.id.news_item_material_card_view))),
                        isDisplayed()), index));

        return getTextFromView(titleNewsByIndex);
    }
    private String getTextFromView(ViewInteraction viewInteraction) {
        final String[] text = {""};
        viewInteraction.perform(new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isAssignableFrom(TextView.class);
            }

            @Override
            public String getDescription() {
                return "getting text from TextView";
            }

            @Override
            public void perform(UiController uiController, View view) {
                TextView textView = (TextView) view;
                text[0] = textView.getText().toString();
            }
        });
        return text[0];
    }


}
