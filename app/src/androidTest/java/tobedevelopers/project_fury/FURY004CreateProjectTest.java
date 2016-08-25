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

import tobedevelopers.project_fury.create_project.implementation.CreateProjectView;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsNot.not;

/**
 * Created by A on 8/23/2016.
 */
@RunWith( AndroidJUnit4.class )
public class FURY004CreateProjectTest{

	@Rule
	public ActivityTestRule< CreateProjectView > createProjectViewActivityTestRule = new ActivityTestRule<>( CreateProjectView.class );

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
	public void testCreateProjectTextWording(){
		onView( withText( "Create a Project" ) ).check( matches( isDisplayed() ) );
	}

	@Test
	public void testEditTestValidInputDescriptionNull(){
		//Project Name
		onView( withId( R.id.createProjectActivity_projectNameEditText ) ).perform( click(), replaceText( "Project Fury" ) );
		onView( withId( R.id.createProjectActivity_projectNameEditText ) ).check( matches( withText( "Project Fury" ) ) );

		//Project Description - Null
		onView( withId( R.id.createProjectActivity_projectDescriptionEditText ) ).perform( click(), replaceText( "" ) );
		onView( withId( R.id.createProjectActivity_projectDescriptionEditText ) ).check( matches( withText( "" ) ) );

		//Create Project enabled
		onView( withId( R.id.createProjectActivity_createProjectButton ) ).check( matches( isEnabled() ) );
	}

	@Test
	public void testEditTextValidInput(){
		//Project Name
		onView( withId( R.id.createProjectActivity_projectNameEditText ) ).perform( click(), replaceText( "Project Fury" ) );
		onView( withId( R.id.createProjectActivity_projectNameEditText ) ).check( matches( withText( "Project Fury" ) ) );

		//Project Description
		onView( withId( R.id.createProjectActivity_projectDescriptionEditText ) ).perform( click(), replaceText( "This project is about the awesomeness of Android" ) );
		onView( withId( R.id.createProjectActivity_projectDescriptionEditText ) ).check( matches( withText( "This project is about the awesomeness of Android" ) ) );

		//Create Project enabled
		onView( withId( R.id.createProjectActivity_createProjectButton ) ).check( matches( isEnabled() ) );
	}

	@Test
	public void testInvalidInputTooShortErrorMessage(){
		//Project Name
		onView( withId( R.id.createProjectActivity_projectNameEditText ) ).perform( click(), replaceText( "B" ) );
		onView( withId( R.id.createProjectActivity_projectNameEditText ) ).check( matches( withText( "B" ) ) );
		onView( withId( R.id.createProjectActivity_projectNameEditText ) ).check( matches( withError( "Minimum of 3 characters" ) ) );

		//Project Description - Null
		onView( withId( R.id.createProjectActivity_projectDescriptionEditText ) ).perform( click(), replaceText( "" ) );
		onView( withId( R.id.createProjectActivity_projectDescriptionEditText ) ).check( matches( withText( "" ) ) );

		//Create Project disabled
		onView( withId( R.id.createProjectActivity_createProjectButton ) ).check( matches( not( isEnabled() ) ) );
	}

	@Test
	public void testInvalidInputTooLongErrorMessages(){
		//Project Name
		onView( withId( R.id.createProjectActivity_projectNameEditText ) ).perform( click(), replaceText( "12345678901234567890" ) );
		onView( withId( R.id.createProjectActivity_projectNameEditText ) ).check( matches( withText( "12345678901234567890" ) ) );
		onView( withId( R.id.createProjectActivity_projectNameEditText ) ).check( matches( withError( "Maximum of 20 characters" ) ) );

		//Project Description
		onView( withId( R.id.createProjectActivity_projectDescriptionEditText ) ).perform( click(), replaceText( "Lorem ipsum dolor1 sit amet consectetur adipiscing elit Morbi a lectus sit amet elit egestas hendrerit ut a metus1 Nulla eu sed1" ) );
		onView( withId( R.id.createProjectActivity_projectDescriptionEditText ) ).check( matches( withText( "Lorem ipsum dolor1 sit amet consectetur adipiscing elit Morbi a lectus sit amet elit egestas hendrerit ut a metus1 Nulla eu sed1" ) ) );
		onView( withId( R.id.createProjectActivity_projectDescriptionEditText ) ).check( matches( withError( "Maximum of 128 characters" ) ) );

		//Create Project disabled
		onView( withId( R.id.createProjectActivity_createProjectButton ) ).check( matches( not( isEnabled() ) ) );
	}

	@Test
	public void testInvalidCharacterInput(){
		onView( withId( R.id.createProjectActivity_projectNameEditText ) ).perform( typeText( "':|][?" ), closeSoftKeyboard() );
		onView( withId( R.id.createProjectActivity_projectNameEditText ) ).perform( click() );
		onView( withId( R.id.createProjectActivity_projectNameEditText ) ).check( matches( withText( "" ) ) );

		onView( withId( R.id.createProjectActivity_projectDescriptionEditText ) ).perform( typeText( "':|][?" ), closeSoftKeyboard() );
		onView( withId( R.id.createProjectActivity_projectDescriptionEditText ) ).perform( click() );
		onView( withId( R.id.createProjectActivity_projectDescriptionEditText ) ).check( matches( withText( "" ) ) );
	}
}
