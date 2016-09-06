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

import tobedevelopers.project_fury.task_info.implementation.TaskInfoView;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isFocusable;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;

/**
 * Created by a on 5/09/2016.
 */
@RunWith( AndroidJUnit4.class )
public class FURY011TaskInfoTest{

	@Rule
	public ActivityTestRule< TaskInfoView > taskInfoViewActivityTestRule = new ActivityTestRule<>( TaskInfoView.class );

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
	public void testTaskInfoTextWording(){
		onView( withText( "Task Information" ) ).check( matches( isDisplayed() ) );
		onView( withText( "Below is information about this task:" ) ).check( matches( isDisplayed() ) );
		onView( withId( R.id.taskInfoActivity_taskNameEditText ) ).check( matches( isDisplayed() ) );
		onView( withId( R.id.taskInfoActivity_taskDescriptionEditText ) ).check( matches( isDisplayed() ) );
		onView( withText( "Assignee" ) ).check( matches( isDisplayed() ) );
		onView( withText( "Priority" ) ).check( matches( isDisplayed() ) );
		onView( withText( "Remove Task" ) ).check( matches( isDisplayed() ) );
		onView( withText( "Edit Task" ) ).check( matches( isDisplayed() ) );
	}

	@Test
	public void testEditTextValidInput(){
		onView( withId( R.id.taskInfoActivity_updateTaskButton ) ).perform( click() );

		onView( withId( R.id.taskInfoActivity_taskNameEditText ) ).perform( click(), replaceText( "Create XML Code" ) );
		onView( withId( R.id.taskInfoActivity_taskNameEditText ) ).check( matches( withText( "Create XML Code" ) ) );

		onView( withId( R.id.taskInfoActivity_taskDescriptionEditText ) ).perform( click(), replaceText( "This is just a test" ) );
		onView( withId( R.id.taskInfoActivity_taskDescriptionEditText ) ).check( matches( withText( "This is just a test" ) ) );

		onView( withId( R.id.taskInfoActivity_assigneeSpinner ) ).check( matches( isDisplayed() ) );

		onView( withId( R.id.taskInfoActivity_prioritySpinner ) ).perform( click() );
		onData( allOf( is( instanceOf( String.class ) ), is( "Unassigned" ) ) ).perform( click() );
		onView( withId( R.id.taskInfoActivity_prioritySpinner ) ).check( matches( withSpinnerText( containsString( "Unassigned" ) ) ) );

		onView( withId( R.id.taskInfoActivity_prioritySpinner ) ).perform( click() );
		onData( allOf( is( instanceOf( String.class ) ), is( "Low" ) ) ).perform( click() );
		onView( withId( R.id.taskInfoActivity_prioritySpinner ) ).check( matches( withSpinnerText( containsString( "Low" ) ) ) );

		onView( withId( R.id.taskInfoActivity_prioritySpinner ) ).perform( click() );
		onData( allOf( is( instanceOf( String.class ) ), is( "Normal" ) ) ).perform( click() );
		onView( withId( R.id.taskInfoActivity_prioritySpinner ) ).check( matches( withSpinnerText( containsString( "Normal" ) ) ) );

		onView( withId( R.id.taskInfoActivity_prioritySpinner ) ).perform( click() );
		onData( allOf( is( instanceOf( String.class ) ), is( "High" ) ) ).perform( click() );
		onView( withId( R.id.taskInfoActivity_prioritySpinner ) ).check( matches( withSpinnerText( containsString( "High" ) ) ) );

		onView( withId( R.id.taskInfoActivity_taskDescriptionEditText ) ).perform( ViewActions.closeSoftKeyboard() );

		onView( withId( R.id.taskInfoActivity_saveTaskButton ) ).check( matches( isDisplayed() ) );
		onView( withId( R.id.taskInfoActivity_saveTaskButton ) ).check( matches( isClickable() ) );
	}

	@Test
	public void testEditTextNotEditable(){
		onView( withId( R.id.taskInfoActivity_taskNameEditText ) ).check( matches( isDisplayed() ) );
		onView( withId( R.id.taskInfoActivity_taskNameEditText ) ).check( matches( not( isClickable() ) ) );
		onView( withId( R.id.taskInfoActivity_taskNameEditText ) ).check( matches( not( isFocusable() ) ) );

		onView( withId( R.id.taskInfoActivity_taskDescriptionEditText ) ).check( matches( isDisplayed() ) );
		onView( withId( R.id.taskInfoActivity_taskDescriptionEditText ) ).check( matches( not( isClickable() ) ) );
		onView( withId( R.id.taskInfoActivity_taskDescriptionEditText ) ).check( matches( not( isFocusable() ) ) );

		onView( withId( R.id.taskInfoActivity_updateTaskButton ) ).perform( click() );
	}

	@Test
	public void testInvalidTooShortErrorMessage(){
		onView( withId( R.id.taskInfoActivity_updateTaskButton ) ).perform( click() );

		onView( withId( R.id.taskInfoActivity_taskNameEditText ) ).perform( click(), replaceText( "Up" ) );
		onView( withId( R.id.taskInfoActivity_taskNameEditText ) ).check( matches( withText( "Up" ) ) );
		onView( withId( R.id.taskInfoActivity_taskNameEditText ) ).check( matches( withError( "Minimum of 3 characters" ) ) );
	}

	@Test
	public void testInvalidTooLongErrorMessage(){
		onView( withId( R.id.taskInfoActivity_updateTaskButton ) ).perform( click() );

		onView( withId( R.id.taskInfoActivity_taskNameEditText ) ).perform( click(), replaceText( "12345678901234567890" ) );
		onView( withId( R.id.taskInfoActivity_taskNameEditText ) ).check( matches( withText( "12345678901234567890" ) ) );
		onView( withId( R.id.taskInfoActivity_taskNameEditText ) ).check( matches( withError( "Maximum of 20 characters" ) ) );

		onView( withId( R.id.taskInfoActivity_taskDescriptionEditText ) ).perform( click(), replaceText( "Lorem ipsum dolor1 sit amet consectetur adipiscing elit Morbi a lectus sit amet elit egestas hendrerit ut a metus1 Nulla eu sed1" ) );
		onView( withId( R.id.taskInfoActivity_taskDescriptionEditText ) ).check( matches( withText( "Lorem ipsum dolor1 sit amet consectetur adipiscing elit Morbi a lectus sit amet elit egestas hendrerit ut a metus1 Nulla eu sed1" ) ) );
		onView( withId( R.id.taskInfoActivity_taskDescriptionEditText ) ).check( matches( withError( "Maximum of 128 characters" ) ) );
	}

	@Test
	public void testInvalidCharacterInput(){
		onView( withId( R.id.taskInfoActivity_updateTaskButton ) ).perform( click() );

		onView( withId( R.id.taskInfoActivity_taskNameEditText ) ).perform( typeText( "':|][?" ) );
		onView( withId( R.id.taskInfoActivity_taskNameEditText ) ).perform( click() );
		onView( withId( R.id.taskInfoActivity_taskNameEditText ) ).check( matches( withText( "" ) ) );

		onView( withId( R.id.taskInfoActivity_taskDescriptionEditText ) ).perform( typeText( "':|][?" ), closeSoftKeyboard() );
		onView( withId( R.id.taskInfoActivity_taskDescriptionEditText ) ).perform( click() );
		onView( withId( R.id.taskInfoActivity_taskDescriptionEditText ) ).check( matches( withText( "" ) ) );
	}

	//FURY#015 - Delete a Task
	@Test
	public void testDeletetask(){
		onView( withId( R.id.taskInfoActivity_deleteTaskButton ) ).perform( click() );

		onView( withText( "Delete Task?" ) ).check( matches( isDisplayed() ) );
		onView( withText( "This will permanently delete this task and all of the attached sub-tasks" ) ).check( matches( isDisplayed() ) );

		onView( withText( "Delete" ) ).check( matches( isDisplayed() ) );
		onView( withText( "Delete" ) ).check( matches( isClickable() ) );

		onView( withText( "Cancel" ) ).check( matches( isDisplayed() ) );
		onView( withText( "Cancel" ) ).check( matches( isClickable() ) );
	}
}
