package tobedevelopers.project_fury;

import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.EditText;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import tobedevelopers.project_fury.register.implementation.RegisterView;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsNot.not;

/**
 * Created by a on 19/08/2016.
 */
@RunWith(AndroidJUnit4.class)
public class FURY001RegisterTest {

    @Rule
    public ActivityTestRule<RegisterView> registerViewActivityTestRule = new ActivityTestRule<>(RegisterView.class);

    private static Matcher<View> withError(final String expected) {
        return new TypeSafeMatcher<View>() {

            @Override
            public void describeTo(Description description) {

            }

            @Override
            protected boolean matchesSafely(View view) {
                if (!(view instanceof EditText)) {
                    return false;
                }
                EditText editText = (EditText) view;
                return editText.getError().toString().equals(expected);
            }
        };
    }

    @Test
    public void testRegistrationTextWording() {
        onView(withText("Register New Account")).check(matches(isDisplayed()));
        onView(withText("Please fill in all the below fields:")).check(matches(isDisplayed()));
        onView(withText("Create Account")).check(matches(isDisplayed()));
        onView(withText("Already a User?")).check(matches(isDisplayed()));
        onView(withText("Login")).check(matches(isDisplayed()));
    }

    @Test
    public void testEditTextValidInput() {
        //Username
        onView(withId(R.id.registerActivity_usernameEditText)).perform(click(), replaceText("Andrea123"));
        onView(withId(R.id.registerActivity_usernameEditText)).check(matches(withText("Andrea123")));

        //Email
        onView(withId(R.id.registerActivity_emailEditText)).perform(click(), replaceText("awolff@live.ca"));
        onView(withId(R.id.registerActivity_emailEditText)).check(matches(withText("awolff@live.ca")));

        //Password
        onView(withId(R.id.registerActivity_passwordEditText)).perform(click(), replaceText("Password123"));
        onView(withId(R.id.registerActivity_passwordEditText)).check(matches(withText("Password123")));

        //Confirm Password
        onView(withId(R.id.registerActivity_confirmPasswordEditText)).perform(click(), replaceText("Password123"));
        onView(withId(R.id.registerActivity_confirmPasswordEditText)).check(matches(withText("Password123")));
        onView(withId(R.id.registerActivity_confirmPasswordEditText)).perform(ViewActions.closeSoftKeyboard());

        //Create Account Button enabled
        onView(withId(R.id.registerActivity_createAccountButton)).check(matches(isEnabled()));

        //Login button enabled
        onView(withId(R.id.registerActivity_returnToLoginButton)).check(matches(isEnabled()));
    }

    @Test
    public void testInvalidInputTooShortErrorMessages() {
        //Username too short
        onView(withId(R.id.registerActivity_usernameEditText)).perform(click(), replaceText("And"));
        onView(withId(R.id.registerActivity_usernameEditText)).check(matches(withText("And")));
        onView(withId(R.id.registerActivity_usernameEditText)).check(matches(withError("Minimum of 6 Characters")));

        //Username too long
        onView(withId(R.id.registerActivity_usernameEditText)).perform(click(), replaceText("12345678901234567890"));
        onView(withId(R.id.registerActivity_usernameEditText)).check(matches(withText("12345678901234567890")));
        onView(withId(R.id.registerActivity_usernameEditText)).check(matches(withError("Maximum of 20 Characters")));

        //Email invalid
        onView(withId(R.id.registerActivity_emailEditText)).perform(click(), replaceText("awolff.com"));
        onView(withId(R.id.registerActivity_emailEditText)).check(matches(withText("awolff.com")));
        onView(withId(R.id.registerActivity_emailEditText)).check(matches(withError("Please enter a valid email")));

        //Password too short
        onView(withId(R.id.registerActivity_passwordEditText)).perform(click(), replaceText("Pass"));
        onView(withId(R.id.registerActivity_passwordEditText)).check(matches(withText("Pass")));
        onView(withId(R.id.registerActivity_passwordEditText)).check(matches(withError("Minimum of 6 Characters")));

        //Password too long
        onView(withId(R.id.registerActivity_passwordEditText)).perform(click(), replaceText("12345678901234567890"));
        onView(withId(R.id.registerActivity_passwordEditText)).check(matches(withText("12345678901234567890")));
        onView(withId(R.id.registerActivity_passwordEditText)).check(matches(withError("Maximum of 20 Characters")));

        //Confirm Password does not match Password
        onView(withId(R.id.registerActivity_confirmPasswordEditText)).perform(click(), replaceText("123456"));
        onView(withId(R.id.registerActivity_confirmPasswordEditText)).check(matches(withText("123456")));
        onView(withId(R.id.registerActivity_confirmPasswordEditText)).check(matches(withError("Password does not currently match")));

        //Create Account button disabled
        onView(withId(R.id.registerActivity_createAccountButton)).check(matches(not(isEnabled())));

        //Login button enabled
        onView(withId(R.id.registerActivity_returnToLoginButton)).check(matches(isEnabled()));
    }
}

