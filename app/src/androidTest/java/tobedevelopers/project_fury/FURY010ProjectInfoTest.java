package tobedevelopers.project_fury;

import android.support.design.widget.TextInputEditText;
import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import tobedevelopers.project_fury.project_info.implementation.ProjectInfoView;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.isFocusable;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsNot.not;

/**
 * Created by A on 8/29/2016.
 */
@RunWith( AndroidJUnit4.class )
public class FURY010ProjectInfoTest{

	@Rule
	public ActivityTestRule< ProjectInfoView > projectInfoViewActivityTestRule = new ActivityTestRule<>( ProjectInfoView.class );

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
	public void testProjectInfoTextWording(){
		onView( withText( "Project Information" ) ).check( matches( isDisplayed() ) );
		onView( withText( "Below is information about this project:" ) ).check( matches( isDisplayed() ) );
		onView( withId( R.id.projectInfoActivity_projectNameEditText ) ).check( matches( isDisplayed() ) );
		onView( withId( R.id.projectInfoActivity_projectDescriptionEditText ) ).check( matches( isDisplayed() ) );
		onView( withText( "Edit Project" ) ).check( matches( isDisplayed() ) );
	}

	@Test
	public void testEditTextValidInput(){
		onView( withId( R.id.projectInfoActivity_editProjectButton ) ).perform( click() );

		onView( withId( R.id.projectInfoActivity_projectNameEditText ) ).perform( click(), replaceText( "Update a Project" ) );
		onView( withId( R.id.projectInfoActivity_projectNameEditText ) ).check( matches( withText( "Update a Project" ) ) );

		onView( withId( R.id.projectInfoActivity_projectDescriptionEditText ) ).perform( click(), replaceText( "This is just for testing" ) );
		onView( withId( R.id.projectInfoActivity_projectDescriptionEditText ) ).check( matches( withText( "This is just for testing" ) ) );

		onView( withId( R.id.projectInfoActivity_projectDescriptionEditText ) ).perform( ViewActions.closeSoftKeyboard() );

		onView( withId( R.id.projectInfoActivity_saveProjectButton ) ).check( matches( isDisplayed() ) );
		onView( withId( R.id.projectInfoActivity_saveProjectButton ) ).check( matches( isClickable() ) );
	}

	@Test
	public void testEditTextNotEditable(){
		onView( withId( R.id.projectInfoActivity_projectNameEditText ) ).check( matches( isDisplayed() ) );
		onView( withId( R.id.projectInfoActivity_projectNameEditText ) ).check( matches( not( isClickable() ) ) );
		onView( withId( R.id.projectInfoActivity_projectNameEditText ) ).check( matches( not( isFocusable() ) ) );

		onView( withId( R.id.projectInfoActivity_projectDescriptionEditText ) ).check( matches( isDisplayed() ) );
		onView( withId( R.id.projectInfoActivity_projectDescriptionEditText ) ).check( matches( not( isClickable() ) ) );
		onView( withId( R.id.projectInfoActivity_projectDescriptionEditText ) ).check( matches( not( isFocusable() ) ) );

		onView( withId( R.id.projectInfoActivity_editProjectButton ) ).check( matches( isClickable() ) );
	}

	@Test
	public void testInvalidTooShortErrorMessage(){
		onView( withId( R.id.projectInfoActivity_editProjectButton ) ).perform( click() );

		onView( withId( R.id.projectInfoActivity_projectNameEditText ) ).perform( click(), replaceText( "Up" ) );
		onView( withId( R.id.projectInfoActivity_projectNameEditText ) ).check( matches( withText( "Up" ) ) );
		onView( withId( R.id.projectInfoActivity_projectNameEditText ) ).check( matches( withError( "Minimum of 3 characters" ) ) );
	}

	@Test
	public void testInvalidTooLongErrorMessage(){
		onView( withId( R.id.projectInfoActivity_editProjectButton ) ).perform( click() );

		onView( withId( R.id.projectInfoActivity_projectNameEditText ) ).perform( click(), replaceText( "12345678901234567890" ) );
		onView( withId( R.id.projectInfoActivity_projectNameEditText ) ).check( matches( withText( "12345678901234567890" ) ) );
		onView( withId( R.id.projectInfoActivity_projectNameEditText ) ).check( matches( withError( "Maximum of 20 characters" ) ) );

		onView( withId( R.id.projectInfoActivity_projectDescriptionEditText ) ).perform( click(), replaceText( "Lorem ipsum dolor1 sit amet consectetur adipiscing elit Morbi a lectus sit amet elit egestas hendrerit ut a metus1 Nulla eu sed1" ) );
		onView( withId( R.id.projectInfoActivity_projectDescriptionEditText ) ).check( matches( withText( "Lorem ipsum dolor1 sit amet consectetur adipiscing elit Morbi a lectus sit amet elit egestas hendrerit ut a metus1 Nulla eu sed1" ) ) );
		onView( withId( R.id.projectInfoActivity_projectDescriptionEditText ) ).check( matches( withError( "Maximum of 128 characters" ) ) );
	}

	@Test
	public void testInvalidCharacterInput(){
		onView( withId( R.id.projectInfoActivity_editProjectButton ) ).perform( click() );

		onView( withId( R.id.projectInfoActivity_projectNameEditText ) ).perform( typeText( "':|][?" ) );
		onView( withId( R.id.projectInfoActivity_projectNameEditText ) ).perform( click() );
		onView( withId( R.id.projectInfoActivity_projectNameEditText ) ).check( matches( withText( "" ) ) );

		onView( withId( R.id.projectInfoActivity_projectDescriptionEditText ) ).perform( typeText( "':|][?" ), closeSoftKeyboard() );
		onView( withId( R.id.projectInfoActivity_projectDescriptionEditText ) ).perform( click() );
		onView( withId( R.id.projectInfoActivity_projectDescriptionEditText ) ).check( matches( withText( "" ) ) );
	}

	//FURY014 - Delete a Project
	@Test
	public void testDeleteProject(){
		onView( withId( R.id.projectInfoActivity_deleteProjectButton ) ).perform( click() );

		onView( withText( "Delete Project?" ) ).check( matches( isDisplayed() ) );
		onView( withText( "This will permanently delete this project and all of the attached tasks" ) ).check( matches( isDisplayed() ) );

		onView( withText( "Delete" ) ).check( matches( isDisplayed() ) );
		onView( withText( "Delete" ) ).check( matches( isClickable() ) );

		onView( withText( "Cancel" ) ).check( matches( isDisplayed() ) );
		onView( withText( "Cancel" ) ).check( matches( isClickable() ) );
	}

	//FURY009 - Add a Column
	@Test
	public void testAddColumnProject(){
		onView( withText( "Column Names:" ) ).check( matches( isDisplayed() ) );

		onView( withId( R.id.projectInfoActivity_addColumnButton ) ).check( matches( not( isEnabled() ) ) );
		onView( withId( R.id.projectInfoActivity_editProjectButton ) ).perform( click() );

		onView( withId( R.id.projectInfoActivity_addColumnButton ) ).perform( click() );

		onView( withText( "New Column" ) ).check( matches( isDisplayed() ) );
		onView( withHint( "Column Name" ) ).check( matches( isDisplayed() ) );

		onView( withText( "Create" ) ).check( matches( isDisplayed() ) );
		onView( withText( "Create" ) ).check( matches( isClickable() ) );

		onView( withText( "Cancel" ) ).check( matches( isDisplayed() ) );
		onView( withText( "Cancel" ) ).check( matches( isClickable() ) );
	}
}
