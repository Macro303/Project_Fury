package tobedevelopers.project_fury;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import tobedevelopers.project_fury.project_info.implementation.ProjectInfoView;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by A on 8/29/2016.
 */
@RunWith( AndroidJUnit4.class )
public class FURY010ProjectInfoTest{

	@Rule
	public ActivityTestRule< ProjectInfoView > projectInfoViewActivityTestRule = new ActivityTestRule<>( ProjectInfoView.class );

	@Test
	public void testProjectInfoTextWording(){
		onView( withText( "Project Information" ) ).check( matches( isDisplayed() ) );
		onView( withText( "Below is information about this project:" ) ).check( matches( isDisplayed() ) );
		onView( withId( R.id.projectInfoActivity_projectNameEditText ) ).check( matches( isDisplayed() ) );
		onView( withId( R.id.projectInfoActivity_projectDescriptionEditText ) ).check( matches( isDisplayed() ) );
		onView( withText( "Edit Project" ) ).check( matches( isDisplayed() ) );
	}
}
