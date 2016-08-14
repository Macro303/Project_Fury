package tobedevelopers.project_fury;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import tobedevelopers.project_fury.login.implementation.LoginView;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by A on 8/14/2016.
 */
@RunWith( AndroidJUnit4.class )
@LargeTest
public class TestingEspresso{

	@Rule
	public ActivityTestRule< LoginView > loginRule = new ActivityTestRule( LoginView.class );

	@Test
	public void testTextOnLoginActivity(){
		onView( withText( "Login" ) ).check( matches( isDisplayed() ) );
		onView( withId( R.id.loginActivity_registerButton ) ).check( matches( isDisplayed() ) );
	}
}

