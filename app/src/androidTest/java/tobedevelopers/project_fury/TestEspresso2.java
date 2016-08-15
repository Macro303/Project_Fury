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
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by A on 8/14/2016.
 */
@RunWith( AndroidJUnit4.class )
public class TestEspresso2{

	@Rule
	public ActivityTestRule< LoginView > loginViewActivityTestRule = new ActivityTestRule<>( LoginView.class );
	TestEspresso3 test = new TestEspresso3();

	@Test
	public void loginView(){
		onView( withId( R.id.loginActivity_loginButton ) ).check( matches( isDisplayed() ) );
		onView( withId( R.id.loginActivity_registerButton ) ).check( matches( isDisplayed() ) );
	}

	@Test
	public void clickRegister(){
		onView( withId( R.id.loginActivity_registerButton ) ).perform( click() );
		test.clickCreateAccount();
	}
}

class TestEspresso3{

	@Rule
	public ActivityTestRule< RegisterView > registerViewActivityTestRule = new ActivityTestRule<>( RegisterView.class );

	@Test
	public void clickCreateAccount(){
		onView( withId( R.id.registerActivity_createAccountButton ) ).perform( click() );
	}
}
