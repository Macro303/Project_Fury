package tobedevelopers.project_fury;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import tobedevelopers.project_fury.register.RegisterContract;
import tobedevelopers.project_fury.register.implementation.RegisterPresenter;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by A on 8/18/2016.
 */
@FixMethodOrder( MethodSorters.NAME_ASCENDING )
public class FURY001UnitTests{

	private RegisterContract.View view;
	private RegisterContract.Navigation navigation;
	private RegisterContract.Presenter presenter;

//	public FURY001UnitTests(){
//		super( Application.class );
//	}

	@BeforeClass
	public void setUp() throws Exception{
		view = mock( RegisterContract.View.class );
		navigation = mock( RegisterContract.Navigation.class );
		presenter = new RegisterPresenter( view, navigation );
	}

	//Username Validation
	@Test
	public void testValidUsername(){
		presenter.userEnterUsername( "RegisterTest" );
		verify( view ).disableCreateAccountButton();
	}

	@Test
	public void testInvalidTooShortUsername(){
		presenter.userEnterUsername( "Reg" );
		verify( view ).setUsernameUnderValidation();
		verify( view ).disableCreateAccountButton();
	}

	@Test
	public void testInvalidTooLongUsername(){
		presenter.userEnterUsername( "123456789012345678901" );
		verify( view ).disableCreateAccountButton();
	}

	//Email Validation
	@Test
	public void testValidEmail(){
		presenter.userEnterEmail( "Email@Email.com" );
		verify( view ).disableCreateAccountButton();
	}

	@Test
	public void testInvalidEmail(){
		presenter.userEnterEmail( "Email.com+" );
		verify( view ).setEmailValidation();
		verify( view ).disableCreateAccountButton();
	}

	//Password Validation
	@Test
	public void testValidPassword(){
		presenter.userEnterPassword( "Password" );
		verify( view ).disableCreateAccountButton();
	}

	@Test
	public void testInvalidTooShortPassword(){
		presenter.userEnterPassword( "Pas" );
		verify( view ).setPasswordUnderValidation();
		verify( view ).disableCreateAccountButton();
	}

	@Test
	public void testInvalidTooLongPassword(){
		presenter.userEnterPassword( "123456789012345678901" );
		verify( view ).disableCreateAccountButton();
	}

	//Confirm Validation
	@Test
	public void testValidConfirmPassword(){
		presenter.userEnterUsername( "RegisterTest" );
		presenter.userEnterEmail( "Email@Email.com" );
		presenter.userEnterPassword( "Password" );
		presenter.userEnterConfirmPassword( "Password", "Password" );
		verify( view ).enableCreateAccountButton();
	}

	@Test
	public void testInvalidConfirmPassword(){
		presenter.userEnterConfirmPassword( "Password", "drowssaP" );
		verify( view ).disableCreateAccountButton();
	}

	//Login Button Navigation
	@Test
	public void testLoginButtonNavigation(){
		presenter.userSelectLogin();
		verify( navigation ).navigateToLogin();
	}
}
