package ru.iteco.fmhandroid.ui.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;

import static ru.iteco.fmhandroid.ui.common.EspressoUtils.isNotDisplayed;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.RecyclerViewActions;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.common.TestData;

public class QuotesPage {
    private final TestData testData = new TestData();
    private final ViewInteraction headerQuotesPage = onView(withId(R.id.our_mission_title_text_view));
    private final ViewInteraction recyclerViewItem = onView(withId(R.id.our_mission_item_list_recycler_view));


    public void checkInitStateQuotesPage() {
        headerQuotesPage.check(matches(isDisplayed()));
        headerQuotesPage.check(matches(withText("Love is all")));

    }

    public void checkAvailQuote(int index) {
        ViewInteraction quoteTitle = onView(
                allOf(withId(R.id.our_mission_item_title_text_view), withText(testData.getQuoteTitleByIndex(index)),
                        withParent(withParent(withId(R.id.our_mission_item_material_card_view))),
                        isDisplayed()));
        onView(withId(R.id.our_mission_item_list_recycler_view))
                .perform(RecyclerViewActions.scrollToPosition(index));
        quoteTitle.check(matches(isDisplayed()));
        quoteTitle.check(matches(withText(testData.getQuoteTitleByIndex(index))));
    }

    public  void checkAvailQuoteDescription(int index) {
        ViewInteraction quoteDescription = onView(
                allOf(withId(R.id.our_mission_item_description_text_view), withText(testData.getQuoteDescriptionByIndex(index)),
                        withParent(withParent(withId(R.id.our_mission_item_material_card_view))),
                        isDisplayed()));
        quoteDescription.check(matches(isDisplayed()));
        quoteDescription.check(matches(withText(testData.getQuoteDescriptionByIndex(index))));
    }

    public  void checkNotDisplayedAvailQuoteDescription(int index) {
        ViewInteraction quoteDescription = onView(
                allOf(withId(R.id.our_mission_item_description_text_view), withText(testData.getQuoteDescriptionByIndex(index)),
                        withParent(withParent(withId(R.id.our_mission_item_material_card_view))),
                        isDisplayed()));
        quoteDescription.check(isNotDisplayed());
    }

    public void dropDownQuote(int numberQuote) {
        recyclerViewItem.perform(actionOnItemAtPosition(numberQuote, click()));
    }

}
