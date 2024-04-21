package ru.iteco.fmhandroid.ui.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class FilterNewsPage {
    private final NewsPage newsPage = new NewsPage();
    private final ViewInteraction headerPage = onView(withId(R.id.filter_news_title_text_view));
    private final ViewInteraction editText = onView(withId(R.id.news_item_category_text_auto_complete_text_view));
    private final ViewInteraction dateFrom = onView(withId(R.id.news_item_publish_date_start_text_input_edit_text));
    private final ViewInteraction dateTo = onView(withId(R.id.news_item_publish_date_end_text_input_edit_text));
    private final ViewInteraction filterButton = onView(withId(R.id.filter_button));
    private final ViewInteraction cancelButton = onView(withId(R.id.cancel_button));

    public void checkHeaderPage() {
        headerPage.check(matches(isDisplayed()));
        headerPage.check(matches(withText("Filter news")));
    }

    public void checkInitStatePage() {
        editText.check(matches(isDisplayed()));
        dateFrom.check(matches(isDisplayed()));
        dateTo.check(matches(isDisplayed()));
        filterButton.check(matches(isDisplayed()));
        filterButton.check(matches(withText("FILTER")));
        cancelButton.check(matches(isDisplayed()));
        cancelButton.check(matches(withText("CANCEL")));
    }

    public void clickFilterButton() {
        filterButton.check(matches(isDisplayed()));
        filterButton.perform(click());
    }

    public void clickCancelButton() {
        cancelButton.check(matches(isDisplayed()));
        cancelButton.perform(click());
    }

    public void useFilter(String date1, String date2) {
        newsPage.clickFilterButton();
        dateFrom.perform(replaceText(date1), closeSoftKeyboard());
        dateTo.perform(replaceText(date2), closeSoftKeyboard());
        filterButton.perform(click());
    }
}
