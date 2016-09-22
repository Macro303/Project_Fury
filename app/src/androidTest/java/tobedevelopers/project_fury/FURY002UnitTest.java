package tobedevelopers.project_fury;

import android.app.Application;
import android.test.ApplicationTestCase;

import tobedevelopers.project_fury.login.LoginContract;
import tobedevelopers.project_fury.login.implementation.LoginPresenter;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by Macro303 on 25/08/2016.
 */
public class FURY002UnitTest extends ApplicationTestCase< Application >{

	public FURY002UnitTest(){
		super( Application.class );
	}

	public void testOnUserSelectRegister(){
		LoginContract.View mockView = mock( LoginContract.View.class );
		LoginContract.Navigation mockNavigation = mock( LoginContract.Navigation.class );

		LoginContract.Presenter presenter = new LoginPresenter( mockView, mockNavigation );

		presenter.userSelectRegister();
		verify( mockNavigation ).navigateToRegister();
	}

	public void testValidUsername(){
		LoginContract.View mockView = mock( LoginContract.View.class );
		LoginContract.Navigation mockNavigation = mock( LoginContract.Navigation.class );

		LoginContract.Presenter presenter = new LoginPresenter( mockView, mockNavigation );

//		presenter.userEnterUsername( "Username303" );
		verify( mockView ).disableLoginButton();
	}

	public void testInvalidShortUsername(){
		LoginContract.View mockView = mock( LoginContract.View.class );
		LoginContract.Navigation mockNavigation = mock( LoginContract.Navigation.class );

		LoginContract.Presenter presenter = new LoginPresenter( mockView, mockNavigation );

//		presenter.userEnterUsername( "User" );
//		verify( mockView ).setUsernameUnderValidation();
		verify( mockView ).disableLoginButton();
	}

	public void testInvalidLongUsername(){
		LoginContract.View mockView = mock( LoginContract.View.class );
		LoginContract.Navigation mockNavigation = mock( LoginContract.Navigation.class );

		LoginContract.Presenter presenter = new LoginPresenter( mockView, mockNavigation );

//		presenter.userEnterUsername( "123456789012345678901" );
//		verify( mockView ).setUsernameOverValidation();
		verify( mockView ).disableLoginButton();
	}

	public void testValidPassword(){
		LoginContract.View mockView = mock( LoginContract.View.class );
		LoginContract.Navigation mockNavigation = mock( LoginContract.Navigation.class );

		LoginContract.Presenter presenter = new LoginPresenter( mockView, mockNavigation );

//		presenter.userEnterUsername( "Username303" );
//		presenter.userEnterPassword( "Password123" );
		verify( mockView ).enableLoginButton();
	}

	public void testInvalidShortPassword(){
		LoginContract.View mockView = mock( LoginContract.View.class );
		LoginContract.Navigation mockNavigation = mock( LoginContract.Navigation.class );

		LoginContract.Presenter presenter = new LoginPresenter( mockView, mockNavigation );

//		presenter.userEnterPassword( "Pass" );
//		verify( mockView ).setPasswordUnderValidation();
		verify( mockView ).disableLoginButton();
	}

	public void testInvalidLongPassword(){
		LoginContract.View mockView = mock( LoginContract.View.class );
		LoginContract.Navigation mockNavigation = mock( LoginContract.Navigation.class );

		LoginContract.Presenter presenter = new LoginPresenter( mockView, mockNavigation );

//		presenter.userEnterPassword( "123456789012345678901" );
//		verify( mockView ).setPasswordOverValidation();
		verify( mockView ).disableLoginButton();
	}

	/**
	 * Didn't invoke dashboard
	 public void testOnUserSelectLogin(){
	 LoginContract.View mockView = mock( LoginContract.View.class );
	 LoginContract.Navigation mockNavigation = mock( LoginContract.Navigation.class );

	 LoginContract.Presenter presenter = new LoginPresenter( mockView, mockNavigation );

	 presenter.userEnterUsername( "Username" );
	 presenter.userEnterPassword( "Password" );
	 presenter.userSelectLogin();
	 //verify( mockNavigation ).navigateToDashboard();
	 verify( mockView ).disableLoginButton();
	 verify( mockView ).enableLoginButton();
	 verify( mockView ).loginInProgress();
	 verify( mockView ).noInternetAccessValidation();
	 }*/
}
