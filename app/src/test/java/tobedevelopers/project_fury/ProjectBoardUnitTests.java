package tobedevelopers.project_fury;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Suite;

import tobedevelopers.project_fury.project_board.ProjectBoardContract;
import tobedevelopers.project_fury.project_board.implementation.ProjectBoardPresenter;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by Macro303 on 28/09/2016.
 */
@RunWith( Suite.class )
@Suite.SuiteClasses( { ProjectBoardUnitTests.NavigationTests.class } )
public class ProjectBoardUnitTests{

	private abstract static class ProjectBoardTests{
		protected ProjectBoardContract.View view;
		protected ProjectBoardContract.Navigation navigation;
		protected ProjectBoardContract.Presenter presenter;

		@Before
		public void setUp() throws Exception{
			view = mock( ProjectBoardContract.View.class );
			navigation = mock( ProjectBoardContract.Navigation.class );
			presenter = new ProjectBoardPresenter( view, navigation );
		}

		@After
		public void tearDown() throws Exception{
			view = null;
			navigation = null;
			presenter = null;
		}
	}

	@FixMethodOrder( MethodSorters.NAME_ASCENDING )
	public static class NavigationTests extends ProjectBoardTests{
		@Test
		public void test01Task(){
			presenter.userSelectCreateTask();
			verify( navigation ).navigateToCreateTask();
		}
	}
}
