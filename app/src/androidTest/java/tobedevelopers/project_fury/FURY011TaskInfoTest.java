package tobedevelopers.project_fury;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import tobedevelopers.project_fury.task_info.implementation.TaskInfoView;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by a on 5/09/2016.
 */
@RunWith( AndroidJUnit4.class )
public class FURY011TaskInfoTest{

	@Rule
	public ActivityTestRule< TaskInfoView > taskInfoViewActivityTestRule = new ActivityTestRule<>( TaskInfoView.class );

	@Test
	public void testTaskInfoTextWording(){
		onView( withText( "Task Information" ) ).check( matches( isDisplayed() ) );
		onView( withText( "Below is information about this task:" ) ).check( matches( isDisplayed() ) );
//		onView( withText( R.id.taskInfoActivity_taskNameEditText ) ).check( matches( isDisplayed() ) );
//		onView( withText( R.id.taskInfoActivity_taskDescriptionEditText ) ).check( matches( isDisplayed() ) );
		onView( withText( "Assignee" ) ).check( matches( isDisplayed() ) );
		onView( withText( "Priority" ) ).check( matches( isDisplayed() ) );
		onView( withText( "Remove Task" ) ).check( matches( isDisplayed() ) );
		onView( withText( "Edit Task" ) ).check( matches( isDisplayed() ) );
	}
}
