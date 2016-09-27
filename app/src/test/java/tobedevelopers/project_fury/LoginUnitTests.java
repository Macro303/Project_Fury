package tobedevelopers.project_fury;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Suite;

import tobedevelopers.project_fury.login.LoginContract;
import tobedevelopers.project_fury.login.implementation.LoginPresenter;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by Macro303 on 27/09/2016.
 */
@RunWith( Suite.class )
@Suite.SuiteClasses( { LoginUnitTests.NavigationTests.class } )
public class LoginUnitTests{

	private abstract static class LoginTests{
		protected LoginContract.View view;
		protected LoginContract.Navigation navigation;
		protected LoginContract.Presenter presenter;

		@Before
		public void setUp() throws Exception{
			view = mock( LoginContract.View.class );
			navigation = mock( LoginContract.Navigation.class );
			presenter = new LoginPresenter( view, navigation );
		}

		@After
		public void tearDown() throws Exception{
			view = null;
			navigation = null;
			presenter = null;
		}
	}

	@FixMethodOrder( MethodSorters.NAME_ASCENDING )
	public static class NavigationTests extends LoginTests{
		@Test
		public void test01Register(){
			presenter.userSelectRegister();
			verify( navigation ).navigateToRegister();
		}
	}
}
