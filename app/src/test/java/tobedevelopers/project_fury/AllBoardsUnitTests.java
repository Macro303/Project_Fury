package tobedevelopers.project_fury;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Suite;

import tobedevelopers.project_fury.all_boards.AllBoardsContract;
import tobedevelopers.project_fury.all_boards.implementation.AllBoardsPresenter;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by Macro303 on 28/09/2016.
 */
@RunWith( Suite.class )
@Suite.SuiteClasses( { AllBoardsUnitTests.NavigationTests.class } )
public class AllBoardsUnitTests{

	private abstract static class AllBoardsTests{
		protected AllBoardsContract.View view;
		protected AllBoardsContract.Navigation navigation;
		protected AllBoardsContract.Presenter presenter;

		@Before
		public void setUp() throws Exception{
			view = mock( AllBoardsContract.View.class );
			navigation = mock( AllBoardsContract.Navigation.class );
			presenter = new AllBoardsPresenter( view, navigation );
		}

		@After
		public void tearDown() throws Exception{
			view = null;
			navigation = null;
			presenter = null;
		}
	}

	@FixMethodOrder( MethodSorters.NAME_ASCENDING )
	public static class NavigationTests extends AllBoardsTests{
		@Test
		public void test01Project(){
			presenter.userSelectCreateProject();
			verify( navigation ).navigateToCreateProject();
		}
	}
}
