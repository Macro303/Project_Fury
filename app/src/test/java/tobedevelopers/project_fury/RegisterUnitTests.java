package tobedevelopers.project_fury;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Suite;

import tobedevelopers.project_fury.register.RegisterContract;
import tobedevelopers.project_fury.register.implementation.RegisterPresenter;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by Macro303 on 27/09/2016.
 */
@RunWith( Suite.class )
@Suite.SuiteClasses( { RegisterUnitTests.UsernameTests.class, RegisterUnitTests.EmailTests.class, RegisterUnitTests.PasswordTests.class, RegisterUnitTests.ConfirmPasswordTests.class, RegisterUnitTests.NavigationTests.class } )
public class RegisterUnitTests{

	private abstract static class RegisterTests{
		protected RegisterContract.View view;
		protected RegisterContract.Navigation navigation;
		protected RegisterContract.Presenter presenter;

		@Before
		public void setUp() throws Exception{
			view = mock( RegisterContract.View.class );
			navigation = mock( RegisterContract.Navigation.class );
			presenter = new RegisterPresenter( view, navigation );
		}

		@After
		public void tearDown() throws Exception{
			view = null;
			navigation = null;
			presenter = null;
		}
	}

	@FixMethodOrder( MethodSorters.NAME_ASCENDING )
	public static class ConfirmPasswordTests extends RegisterTests{
		@Test
		public void test01Valid(){
			presenter.userEnterUsername( "UnitTest" );
			presenter.userEnterEmail( "Email@Email.com" );
			presenter.userEnterPassword( "Password" );
			presenter.userEnterConfirmPassword( "Password", "Password" );
			verify( view ).enableCreateAccountButton();
		}

		@Test
		public void test02Invalid(){
			presenter.userEnterConfirmPassword( "Password", "drowssaP" );
			verify( view ).disableCreateAccountButton();
		}
	}

	@FixMethodOrder( MethodSorters.NAME_ASCENDING )
	public static class NavigationTests extends RegisterTests{
		@Test
		public void test01Login(){
			presenter.userSelectLogin();
			verify( navigation ).navigateToLogin();
		}
	}

	@FixMethodOrder( MethodSorters.NAME_ASCENDING )
	public static class PasswordTests extends RegisterTests{
		@Test
		public void test01Valid(){
			presenter.userEnterPassword( "Password" );
			verify( view ).disableCreateAccountButton();
		}

		@Test
		public void test02InvalidUnder(){
			presenter.userEnterPassword( "Pas" );
			verify( view ).setPasswordUnderValidation();
			verify( view ).disableCreateAccountButton();
		}

		@Test
		public void test03InvalidOver(){
			presenter.userEnterPassword( "123456789012345678901" );
			verify( view ).setPasswordOverValidation();
			verify( view ).disableCreateAccountButton();
		}
	}

	@FixMethodOrder( MethodSorters.NAME_ASCENDING )
	public static class EmailTests extends RegisterTests{
		@Test
		public void test01Valid(){
			presenter.userEnterEmail( "Email@Email.com" );
			verify( view ).disableCreateAccountButton();
		}

		@Test
		public void test02Invalid(){
			presenter.userEnterEmail( "Email.com+" );
			verify( view ).setEmailValidation();
			verify( view ).disableCreateAccountButton();
		}
	}

	@FixMethodOrder( MethodSorters.NAME_ASCENDING )
	public static class UsernameTests extends RegisterTests{
		@Test
		public void test01Valid(){
			presenter.userEnterUsername( "UnitTest" );
			verify( view ).disableCreateAccountButton();
		}

		@Test
		public void test02InvalidUnder(){
			presenter.userEnterUsername( "Reg" );
			verify( view ).setUsernameUnderValidation();
			verify( view ).disableCreateAccountButton();
		}

		@Test
		public void test03InvalidOver(){
			presenter.userEnterUsername( "123456789012345678901" );
			verify( view ).setUsernameOverCharValidation();
			verify( view ).disableCreateAccountButton();
		}
	}
}