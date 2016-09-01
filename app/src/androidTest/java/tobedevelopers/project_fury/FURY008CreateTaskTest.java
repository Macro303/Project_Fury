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

import tobedevelopers.project_fury.create_task.implementation.CreateTaskView;

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
 * Created by A on 8/29/2016.
 */
@RunWith( AndroidJUnit4.class )
public class FURY008CreateTaskTest{

	@Rule
	public ActivityTestRule< CreateTaskView > createTaskViewActivityTestRule = new ActivityTestRule<>( CreateTaskView.class );

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
	public void testCreateTaskTextWording(){
		onView( withText( "Create a Task" ) ).check( matches( isDisplayed() ) );
	}

	@Test
	public void testEditTextValidInputDescriptionNull(){
		//Task Name
		onView( withId( R.id.createTaskActivity_taskNameEditText ) ).perform( click(), replaceText( "Update a Project" ) );
		onView( withId( R.id.createTaskActivity_taskNameEditText ) ).check( matches( withText( "Update a Project" ) ) );

		//Task Description - Null
		onView( withId( R.id.createTaskActivity_taskDescriptionEditText ) ).perform( click(), replaceText( "" ) );
		onView( withId( R.id.createTaskActivity_taskDescriptionEditText ) ).check( matches( withText( "" ) ) );

		//Create Task enabled
		onView( withId( R.id.createTaskActivity_createTaskButton ) ).check( matches( isEnabled() ) );
	}

	@Test
	public void testEditTextValidInput(){
		//Task Name
		onView( withId( R.id.createTaskActivity_taskNameEditText ) ).perform( click(), replaceText( "Update a Project" ) );
		onView( withId( R.id.createTaskActivity_taskNameEditText ) ).check( matches( withText( "Update a Project" ) ) );

		//Task Description - Null
		onView( withId( R.id.createTaskActivity_taskDescriptionEditText ) ).perform( click(), replaceText( "This project is about the awesomeness of Android" ) );
		onView( withId( R.id.createTaskActivity_taskDescriptionEditText ) ).check( matches( withText( "This project is about the awesomeness of Android" ) ) );

		//Create Task enabled
		onView( withId( R.id.createTaskActivity_createTaskButton ) ).check( matches( isEnabled() ) );
	}

	@Test
	public void testInvalidInputTooShortErrorMessage(){
		//Task Name
		onView( withId( R.id.createTaskActivity_taskNameEditText ) ).perform( click(), replaceText( "Up" ) );
		onView( withId( R.id.createTaskActivity_taskNameEditText ) ).check( matches( withText( "Up" ) ) );
		onView( withId( R.id.createTaskActivity_taskNameEditText ) ).check( matches( withError( "Minimum of 3 characters" ) ) );

		//Task Description - Null
		onView( withId( R.id.createTaskActivity_taskDescriptionEditText ) ).perform( click(), replaceText( "" ) );
		onView( withId( R.id.createTaskActivity_taskDescriptionEditText ) ).check( matches( withText( "" ) ) );

		//Create Task enabled
		onView( withId( R.id.createTaskActivity_createTaskButton ) ).check( matches( not( isEnabled() ) ) );
	}

	@Test
	public void testInvalidInputTooLongErrorMessage(){
		//Task Name
		onView( withId( R.id.createTaskActivity_taskNameEditText ) ).perform( click(), replaceText( "12345678901234567890" ) );
		onView( withId( R.id.createTaskActivity_taskNameEditText ) ).check( matches( withText( "12345678901234567890" ) ) );
		onView( withId( R.id.createTaskActivity_taskNameEditText ) ).check( matches( withError( "Maximum of 20 characters" ) ) );

		//Task Description - Null
		onView( withId( R.id.createTaskActivity_taskDescriptionEditText ) ).perform( click(), replaceText( "Lorem ipsum dolor1 sit amet consectetur adipiscing elit Morbi a lectus sit amet elit egestas hendrerit ut a metus1 Nulla eu sed1" ) );
		onView( withId( R.id.createTaskActivity_taskDescriptionEditText ) ).check( matches( withText( "Lorem ipsum dolor1 sit amet consectetur adipiscing elit Morbi a lectus sit amet elit egestas hendrerit ut a metus1 Nulla eu sed1" ) ) );
		onView( withId( R.id.createTaskActivity_taskDescriptionEditText ) ).check( matches( withError( "Maximum of 128 characters" ) ) );

		//Create Task enabled
		onView( withId( R.id.createTaskActivity_createTaskButton ) ).check( matches( isEnabled() ) );
	}

	@Test
	public void testInvalidCharacterInput(){
		onView( withId( R.id.createTaskActivity_taskNameEditText ) ).perform( typeText( "':|][?" ), closeSoftKeyboard() );
		onView( withId( R.id.createTaskActivity_taskNameEditText ) ).perform( click() );
		onView( withId( R.id.createTaskActivity_taskNameEditText ) ).check( matches( withText( "" ) ) );

		onView( withId( R.id.createTaskActivity_taskDescriptionEditText ) ).perform( typeText( "':|][?" ), closeSoftKeyboard() );
		onView( withId( R.id.createTaskActivity_taskDescriptionEditText ) ).perform( click() );
		onView( withId( R.id.createTaskActivity_taskDescriptionEditText ) ).check( matches( withText( "" ) ) );
	}
}
