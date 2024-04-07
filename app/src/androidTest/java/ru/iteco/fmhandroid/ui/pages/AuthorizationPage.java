package ru.iteco.fmhandroid.ui.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class AuthorizationPage {
    public static ViewInteraction headerAuthPage = onView(withText("Authorization"));
    public static ViewInteraction loginField = onView(withId(R.id.login_text_input_layout));
    public static ViewInteraction passField = onView(withId(R.id.password_text_input_layout));
    public static ViewInteraction SignInButton = onView(withId(R.id.enter_button));


}
