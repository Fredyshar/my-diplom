package ru.iteco.fmhandroid.ui.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import static ru.iteco.fmhandroid.ui.common.ToastMatcher.withIndex;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.pages.ControlPanelPage;


public class CreateAndEditNewsPage {
    private final ControlPanelPage controlPanelPage = new ControlPanelPage();
    private final ViewInteraction switchActiveButton = onView(withId(R.id.switcher));
    private final ViewInteraction saveButton = onView(withId(R.id.save_button));
    private final ViewInteraction addNewsButton = onView(
            allOf(withId(R.id.add_news_image_view), withContentDescription("Add news button"),
                    isDisplayed()));
    private final ViewInteraction headerPage = onView(withId(R.id.custom_app_bar_title_text_view));
    private final ViewInteraction choiceCategory = onView(withId(R.id.news_item_category_text_auto_complete_text_view));
    private final ViewInteraction categoryNewsList = onView(withId(R.id.news_item_category_text_auto_complete_text_view));
    private final ViewInteraction titleField = onView((withId(R.id.news_item_title_text_input_edit_text)));
    private final ViewInteraction datePublishField = onView(withId(R.id.news_item_publish_date_text_input_edit_text));
    private final ViewInteraction okButton = onView(withId(android.R.id.button1));
    private final ViewInteraction timePublishField = onView(withId(R.id.news_item_publish_time_text_input_edit_text));
    private final ViewInteraction descriptionField = onView(withId(R.id.news_item_description_text_input_edit_text));

    public void checkHeaderPage() {
        headerPage.check(matches(withText("Creating")));
        headerPage.check(matches(withText("News")));
    }

    public void editNewsByIndex(int index) {
        onView(withIndex(
                allOf(withId(R.id.edit_news_item_image_view),
                        withParent(withParent(withId(R.id.news_item_material_card_view))),
                        isDisplayed()), index))
                .perform(click());

        switchActiveButton.check(matches(isDisplayed()));
        switchActiveButton.perform(scrollTo(), click());

        saveButton.check(matches(isDisplayed()));
        saveButton.perform(scrollTo(), click());
    }

    public void createNews(String nameNews, String datePublish, String description) {
        addNewsButton.check(matches(isDisplayed()));
        addNewsButton.perform(click());

        choiceCategory.perform(click());
        categoryNewsList.perform(replaceText("Объявление"), closeSoftKeyboard());
        titleField.check(matches(isDisplayed()));
        titleField.perform(replaceText(nameNews), closeSoftKeyboard());
        datePublishField.perform(replaceText(datePublish), closeSoftKeyboard());
        timePublishField.perform(replaceText("12:00"), closeSoftKeyboard());
        descriptionField.perform(replaceText(description), closeSoftKeyboard());
        saveButton.perform(scrollTo(), click());
    }

    public void checkCreatedNews(int index, String status, String title) {
        controlPanelPage.checkNewsByIndexAndTitle(index, status, title);
    }

    public void deleteNews(int index) {
        controlPanelPage.clickDeleteNewsButtonByIndex(index);
        okButton.perform(click());
    }

}
