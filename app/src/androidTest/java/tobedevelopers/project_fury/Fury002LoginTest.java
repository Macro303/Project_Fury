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
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by Macro303 on 25/08/2016.
 */
@RunWith( AndroidJUnit4.class )
public class Fury002LoginTest{

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
		onView( withHint( "Username" ) ).check( matches( isDisplayed() ) );
		onView( withHint( "Password" ) ).check( matches( isDisplayed() ) );
		onView( withText( "Login" ) ).check( matches( isDisplayed() ) );
		onView( withText( "Not a User?" ) ).check( matches( isDisplayed() ) );
		onView( withText( "Register Here" ) ).check( matches( isDisplayed() ) );
	}

	/*@Test
	public void testEditTestValidInputDescriptionNull(){
		//Username
		onView( withId( R.id.loginActivity_usernameEditText ) ).perform( click(), replaceText( "Project Fury" ) );
		onView( withId( R.id.createProjectActivity_projectNameEditText ) ).check( matches( withText( "Project Fury" ) ) );

		//Project Description - Null
		onView( withId( R.id.createProjectActivity_projectDescriptionEditText ) ).perform( click(), replaceText( "" ) );
		onView( withId( R.id.createProjectActivity_projectDescriptionEditText ) ).check( matches( withText( "" ) ) );

		//Create Project enabled
		onView( withId( R.id.createProjectActivity_createProjectButton ) ).check( matches( isEnabled() ) );
	}*/
}
