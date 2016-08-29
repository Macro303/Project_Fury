package tobedevelopers.project_fury;

import android.support.design.widget.TextInputEditText;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import tobedevelopers.project_fury.login.implementation.LoginView;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

/**
 * Created by Macro303 on 25/08/2016.
 */
@RunWith( AndroidJUnit4.class )
public class FURY002LoginTests{

	@Rule
	public ActivityTestRule< LoginView > loginViewActivityTestRule = new ActivityTestRule<>( LoginView.class );

	private static Matcher< View > withError( final String expected ){
		return new TypeSafeMatcher< View >(){

			@Override
			public void describeTo( Description description ){

			}

			@Override
			protected boolean matchesSafely( View view ){
				if( !( view instanceof TextInputEditText ) ){
					return false;
				}
				TextInputEditText editText = ( TextInputEditText ) view;
				return editText.getError().toString().equals( expected );
			}
		};
	}

	@Test
	public void testLoginTextWording(){
		onView( withText( "Project Fury" ) ).check( matches( isDisplayed() ) );
		onView( withText( "Don't Get Angry, Get Organised" ) ).check( matches( isDisplayed() ) );
		onView( withId( R.id.loginActivity_usernameEditText ) ).check( matches( isDisplayed() ) );
		onView( withId( R.id.loginActivity_passwordEditText ) ).check( matches( isDisplayed() ) );
		onView( withText( "Login" ) ).check( matches( isDisplayed() ) );
		onView( withText( "Not a User?" ) ).check( matches( isDisplayed() ) );
		onView( withText( "Register Here" ) ).check( matches( isDisplayed() ) );
	}

	@Test
	public void testEditTextValidInput(){
		//Username
		onView( withId( R.id.loginActivity_usernameEditText ) ).perform( click(), replaceText( "Username303" ) );
		onView( withId( R.id.loginActivity_usernameEditText ) ).check( matches( withText( "Username303" ) ) );

		//Password
		onView( withId( R.id.loginActivity_passwordEditText ) ).perform( clearText(), replaceText( "Password123" ) );
		onView( withId( R.id.loginActivity_passwordEditText ) ).check( matches( withText( "Password123" ) ) );

		//Login button enabled
		onView( withId( R.id.loginActivity_loginButton ) ).check( matches( isEnabled() ) );

		//Register button enabled
		onView( withId( R.id.loginActivity_registerButton ) ).check( matches( isEnabled() ) );
	}

	@Test
	public void testInvalidInputTooShortErrorMessage(){
		//Username too short
		onView( withId( R.id.loginActivity_usernameEditText ) ).perform( click(), replaceText( "Use" ) );
		onView( withId( R.id.loginActivity_usernameEditText ) ).check( matches( withText( "Use" ) ) );
		onView( withId( R.id.loginActivity_usernameEditText ) ).check( matches( withError( "Minimum of 6 characters" ) ) );

		//Password too short
		onView( withId( R.id.loginActivity_passwordEditText ) ).perform( clearText(), replaceText( "Pas" ) );
		onView( withId( R.id.loginActivity_passwordEditText ) ).check( matches( withText( "Pas" ) ) );
		onView( withId( R.id.loginActivity_passwordEditText ) ).check( matches( withError( "Minimum of 6 characters" ) ) );

		//Login button disabled
		onView( withId( R.id.loginActivity_loginButton ) ).check( matches( not( isEnabled() ) ) );

		//Register button enabled
		onView( withId( R.id.loginActivity_registerButton ) ).check( matches( isEnabled() ) );
	}

	@Test
	public void testInvalidInputTooLongMessage(){
		//Username too long
		onView( withId( R.id.loginActivity_usernameEditText ) ).perform( click(), replaceText( "12345678901234567890" ) );
		onView( withId( R.id.loginActivity_usernameEditText ) ).check( matches( withText( "12345678901234567890" ) ) );
		onView( withId( R.id.loginActivity_usernameEditText ) ).check( matches( withError( "Maximum of 20 characters" ) ) );

		//Password too long
		onView( withId( R.id.loginActivity_passwordEditText ) ).perform( clearText(), replaceText( "12345678901234567890" ) );
		onView( withId( R.id.loginActivity_passwordEditText ) ).check( matches( withText( "12345678901234567890" ) ) );
		onView( withId( R.id.loginActivity_passwordEditText ) ).check( matches( withError( "Maximum of 20 characters" ) ) );

		//Login button disabled
		onView( withId( R.id.loginActivity_loginButton ) ).check( matches( isEnabled() ) );

		//Register button enabled
		onView( withId( R.id.loginActivity_registerButton ) ).check( matches( isEnabled() ) );
	}

	@Test
	public void testInvalidUsernameCharacterInput(){
		onView( withId( R.id.loginActivity_usernameEditText ) ).perform( click(), typeText( "':|][?" ) );
		onView( withId( R.id.loginActivity_usernameEditText ) ).check( matches( withText( "" ) ) );
	}

	@Test
	public void testInvalidPasswordCharacterInput(){
		onView( withId( R.id.loginActivity_passwordEditText ) ).perform( click(), typeText( "':|][?" ) );
		onView( withId( R.id.loginActivity_passwordEditText ) ).check( matches( withText( "" ) ) );
	}
}
