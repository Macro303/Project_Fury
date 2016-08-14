package tobedevelopers.project_fury;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import tobedevelopers.project_fury.login.implementation.LoginView;
import tobedevelopers.project_fury.register.implementation.RegisterView;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by A on 8/14/2016.
 */
@RunWith( AndroidJUnit4.class )
public class TestEspresso2{

	@Rule
	public ActivityTestRule< LoginView > loginViewActivityTestRule = new ActivityTestRule<>( LoginView.class );
	@Rule
	public ActivityTestRule< RegisterView > registerViewActivityTestRule = new ActivityTestRule<>( RegisterView.class );

	@Test
	public void clickRegister(){
		onView( withId( R.id.loginActivity_registerButton ) ).perform( click() );
	}

	@Test
	public void clickCreateAccount(){
		onView( withId( R.id.registerActivity_createAccountButton ) ).perform( click() );
	}
}
