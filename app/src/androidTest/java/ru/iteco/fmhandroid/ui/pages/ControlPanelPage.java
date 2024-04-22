package ru.iteco.fmhandroid.ui.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItem;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withParentIndex;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import static ru.iteco.fmhandroid.ui.common.ToastMatcher.withIndex;

import androidx.test.espresso.ViewInteraction;

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
        onView(withIndex(
                allOf(withId(R.id.news_item_published_text_view),
                        withParent(withParent(withId(R.id.news_item_material_card_view))),
                        isDisplayed()), index))
                .check(matches(withText(status)));

        onView(withIndex(
                allOf(withId(R.id.delete_news_item_image_view),
                        withParent(withParent(withId(R.id.news_item_material_card_view))),
                        isDisplayed()), index))
                .check(matches(isDisplayed()));

        onView(withIndex(
                allOf(withId(R.id.edit_news_item_image_view),
                        withParent(withParent(withId(R.id.news_item_material_card_view))),
                        isDisplayed()), index))
                .check(matches(isDisplayed()));

        onView(withIndex(
                allOf(withId(R.id.view_news_item_image_view), withContentDescription("Expand news card button"),
                        withParent(withParent(withId(R.id.news_item_material_card_view))),
                        isDisplayed()), index))
                .check(matches(isDisplayed()));

        onView(withIndex(
                allOf(withId(R.id.news_item_title_text_view),
                        withParent(withParent(withId(R.id.news_item_material_card_view))),
                        isDisplayed()), index))
                .check(matches(isDisplayed()));

        onView(withIndex(
                allOf(withId(R.id.news_item_publication_text_view),
                        withParent(withParent(withId(R.id.news_item_material_card_view))),
                        withText("Publication date"),
                        isDisplayed()), index))
                .check(matches(isDisplayed()));

        onView(withIndex(
                allOf(withId(R.id.news_item_creation_text_view),
                        withParent(withParent(withId(R.id.news_item_material_card_view))),
                        withText("Creation date"),
                        isDisplayed()), index))
                .check(matches(isDisplayed()));

        onView(withIndex(
                allOf(withId(R.id.news_item_author_text_view),
                        withParent(withParent(withId(R.id.news_item_material_card_view))),
                        withText("Author"),
                        isDisplayed()), index))
                .check(matches(isDisplayed()));

        onView(withIndex(
                allOf(withId(R.id.category_icon_image_view),
                        withParent(withParent(withId(R.id.news_item_material_card_view))),
                        isDisplayed()), index))
                .check(matches(isDisplayed()));
    }

    public void checkNewsByIndexAndTitle(int index, String status, String title) {
        onView(withIndex(
                allOf(withId(R.id.news_item_published_text_view),
                        withParent(withParent(withId(R.id.news_item_material_card_view))),
                        isDisplayed()), index))
                .check(matches(withText(status)));

        onView(withIndex(
                allOf(withId(R.id.delete_news_item_image_view),
                        withParent(withParent(withId(R.id.news_item_material_card_view))),
                        isDisplayed()), index))
                .check(matches(isDisplayed()));

        onView(withIndex(
                allOf(withId(R.id.edit_news_item_image_view),
                        withParent(withParent(withId(R.id.news_item_material_card_view))),
                        isDisplayed()), index))
                .check(matches(isDisplayed()));

        onView(withIndex(
                allOf(withId(R.id.view_news_item_image_view), withContentDescription("Expand news card button"),
                        withParent(withParent(withId(R.id.news_item_material_card_view))),
                        isDisplayed()), index))
                .check(matches(isDisplayed()));

        onView(withIndex(
                allOf(withId(R.id.news_item_title_text_view),
                        withParent(withParent(withId(R.id.news_item_material_card_view))),
                        withText(title),
                        isDisplayed()), index))
                .check(matches(isDisplayed()));

        onView(withIndex(
                allOf(withId(R.id.news_item_publication_text_view),
                        withParent(withParent(withId(R.id.news_item_material_card_view))),
                        withText("Publication date"),
                        isDisplayed()), index))
                .check(matches(isDisplayed()));

        onView(withIndex(
                allOf(withId(R.id.news_item_creation_text_view),
                        withParent(withParent(withId(R.id.news_item_material_card_view))),
                        withText("Creation date"),
                        isDisplayed()), index))
                .check(matches(isDisplayed()));

        onView(withIndex(
                allOf(withId(R.id.news_item_author_text_view),
                        withParent(withParent(withId(R.id.news_item_material_card_view))),
                        withText("Author"),
                        isDisplayed()), index))
                .check(matches(isDisplayed()));

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

}
