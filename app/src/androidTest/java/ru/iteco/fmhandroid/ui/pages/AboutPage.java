package ru.iteco.fmhandroid.ui.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.init;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.Intents.release;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasData;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.CoreMatchers.allOf;

import android.content.Intent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.UiDevice;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.common.TestData;

public class AboutPage {
    private final ViewInteraction titleVersion = onView(withId(R.id.about_version_title_text_view));
    private final ViewInteraction valueVersion = onView(withId(R.id.about_version_value_text_view));
    private final ViewInteraction companyInfo = onView(withId(R.id.about_company_info_label_text_view));
    private final ViewInteraction privacyPolicyTitle = onView(withId(R.id.about_privacy_policy_label_text_view));
    private final ViewInteraction privacyPolicyValue = onView(withId(R.id.about_privacy_policy_value_text_view));
    private final ViewInteraction termsOfUseTitle = onView(withId(R.id.about_terms_of_use_label_text_view));
    private final ViewInteraction termsOfUseValue = onView(withId(R.id.about_terms_of_use_value_text_view));
    private final ViewInteraction backButton = onView(withId(R.id.about_back_image_button));
    private final TestData testData = new TestData();

    public void checkDisplayedVersionAndCompanyInfo() {
        titleVersion.check(matches(isDisplayed()));
        titleVersion.check(matches(withText("Version:")));
        valueVersion.check(matches(isDisplayed()));
        valueVersion.check(matches(withText("1.0.0")));

        companyInfo.check(matches(isDisplayed()));
        companyInfo.check(matches(withText("© I-Teco, 2022")));
    }

    public void checkDisplayedTitlesAndLink() {

        privacyPolicyTitle.check(matches(isDisplayed()));
        privacyPolicyTitle.check(matches(withText("Privacy Policy:")));

        privacyPolicyValue.check(matches(isDisplayed()));
        privacyPolicyValue.check(matches(withText("https://vhospice.org/#/privacy-policy/")));

        termsOfUseTitle.check(matches(isDisplayed()));
        termsOfUseTitle.check(matches(withText("Terms of use:")));

        termsOfUseValue.check(matches(isDisplayed()));
        termsOfUseValue.check(matches(withText("https://vhospice.org/#/terms-of-use")));
    }
    public void clickBackButton() {
        backButton.check(matches(isDisplayed()));
        backButton.perform(click());
    }

    public void checkOpenOfPageByLink(String url) {
        init();
        if (url.equals(testData.getLinkContainingSubstring("privacy"))) {
            privacyPolicyValue.perform(click());
        } else if (url.equals(testData.getLinkContainingSubstring("terms"))) {
            termsOfUseValue.perform(click());
        }
        intended(allOf(
                hasData(url),
                hasAction(Intent.ACTION_VIEW)
        ));
        release();
        UiDevice uiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        uiDevice.pressBack();
    }

}
