package tobedevelopers.project_fury;

import android.app.Application;
import android.test.ApplicationTestCase;

import tobedevelopers.project_fury.register.RegisterContract;
import tobedevelopers.project_fury.register.implementation.RegisterPresenter;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by A on 8/18/2016.
 */
public class FURY001UnitTests extends ApplicationTestCase< Application >{

	public FURY001UnitTests(){
		super( Application.class );
	}

	//Username Validation
	public void testValidUsername(){
		RegisterContract.View viewMock = mock( RegisterContract.View.class );
		RegisterContract.Navigation navigationMock = mock( RegisterContract.Navigation.class );

		RegisterContract.Presenter presenter = new RegisterPresenter( viewMock, navigationMock );

		presenter.userEnterUsername( "Andrea1234" );
		verify(viewMock).disableCreateAccountButton();
	}

	public void testInvalidTooShortUsername(){
		RegisterContract.View viewMock = mock( RegisterContract.View.class );
		RegisterContract.Navigation navigationMock = mock( RegisterContract.Navigation.class );

		RegisterContract.Presenter presenter = new RegisterPresenter( viewMock, navigationMock );

		presenter.userEnterUsername( "and" );
		verify( viewMock ).setUsernameUnderValidation();
		verify( viewMock ).disableCreateAccountButton();
	}

	public void testInvalidTooLongUsername(){
		RegisterContract.View viewMock = mock( RegisterContract.View.class );
		RegisterContract.Navigation navigationMock = mock( RegisterContract.Navigation.class );

		RegisterContract.Presenter presenter = new RegisterPresenter( viewMock, navigationMock );

		presenter.userEnterUsername( "123456789012345678901" );
		verify( viewMock ).disableCreateAccountButton();
	}

	//Email Validation
	public void testValidEmail(){
		RegisterContract.View viewMock = mock( RegisterContract.View.class );
		RegisterContract.Navigation navigationMock = mock( RegisterContract.Navigation.class );

		RegisterContract.Presenter presenter = new RegisterPresenter( viewMock, navigationMock );

		presenter.userEnterEmail( "awolff@live.ca" );
		verify(viewMock).disableCreateAccountButton();
	}

	public void testInvalidEmail(){
		RegisterContract.View viewMock = mock( RegisterContract.View.class );
		RegisterContract.Navigation navigationMock = mock( RegisterContract.Navigation.class );

		RegisterContract.Presenter presenter = new RegisterPresenter( viewMock, navigationMock );

		presenter.userEnterEmail( "awolff.com+" );
		verify( viewMock ).setEmailValidation();
		verify( viewMock ).disableCreateAccountButton();
	}

	//Password Validation
	public void testValidPassword(){
		RegisterContract.View viewMock = mock( RegisterContract.View.class );
		RegisterContract.Navigation navigationMock = mock( RegisterContract.Navigation.class );

		RegisterContract.Presenter presenter = new RegisterPresenter( viewMock, navigationMock );

		presenter.userEnterPassword( "Password123" );
		verify(viewMock).disableCreateAccountButton();
	}

	public void testInvalidTooShortPassword(){
		RegisterContract.View viewMock = mock( RegisterContract.View.class );
		RegisterContract.Navigation navigationMock = mock( RegisterContract.Navigation.class );

		RegisterContract.Presenter presenter = new RegisterPresenter( viewMock, navigationMock );

		presenter.userEnterPassword( "pass" );
		verify( viewMock ).setPasswordUnderValidation();
		verify( viewMock ).disableCreateAccountButton();
	}

	public void testInvalidTooLongPassword(){
		RegisterContract.View viewMock = mock( RegisterContract.View.class );
		RegisterContract.Navigation navigationMock = mock( RegisterContract.Navigation.class );

		RegisterContract.Presenter presenter = new RegisterPresenter( viewMock, navigationMock );

		presenter.userEnterPassword( "abcdefgh123456789012345" );
		verify( viewMock ).disableCreateAccountButton();
	}

	//Confirm Validation
	public void testValidConfirmPassword(){
		RegisterContract.View viewMock = mock( RegisterContract.View.class );
		RegisterContract.Navigation navigationMock = mock( RegisterContract.Navigation.class );

		RegisterContract.Presenter presenter = new RegisterPresenter( viewMock, navigationMock );

		presenter.userEnterConfirmPassword( "123456", "123456" );
		verify( viewMock ).enableCreateAccountButton();
	}

	public void testInvalidConfirmPassword(){
		RegisterContract.View viewMock = mock( RegisterContract.View.class );
		RegisterContract.Navigation navigationMock = mock( RegisterContract.Navigation.class );

		RegisterContract.Presenter presenter = new RegisterPresenter( viewMock, navigationMock );

		presenter.userEnterConfirmPassword( "123456", "654321" );
		verify( viewMock ).disableCreateAccountButton();
	}

	//Login Button Navigation
	public void testLoginButtonNavigation(){
		RegisterContract.View viewMock = mock( RegisterContract.View.class );
		RegisterContract.Navigation navigationMock = mock( RegisterContract.Navigation.class );

		RegisterContract.Presenter presenter = new RegisterPresenter( viewMock, navigationMock );

		presenter.userSelectLogin();
		verify( navigationMock ).navigateToLogin();
	}
}
