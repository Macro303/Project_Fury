package tobedevelopers.project_fury;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Suite;

import tobedevelopers.project_fury.task_info.TaskInfoContract;
import tobedevelopers.project_fury.task_info.implementation.TaskInfoPresenter;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by Macro303 on 28/09/2016.
 */
@RunWith( Suite.class )
@Suite.SuiteClasses( { TaskInfoUnitTests.NameTests.class, TaskInfoUnitTests.DescriptionTests.class, TaskInfoUnitTests.NavigationTests.class } )
public class TaskInfoUnitTests{

	private abstract static class TaskInfoTests{
		protected TaskInfoContract.View view;
		protected TaskInfoContract.Navigation navigation;
		protected TaskInfoContract.Presenter presenter;

		@Before
		public void setUp() throws Exception{
			view = mock( TaskInfoContract.View.class );
			navigation = mock( TaskInfoContract.Navigation.class );
			presenter = new TaskInfoPresenter( view, navigation );
		}

		@After
		public void tearDown() throws Exception{
			view = null;
			navigation = null;
			presenter = null;
		}
	}

	@FixMethodOrder( MethodSorters.NAME_ASCENDING )
	public static class NameTests extends TaskInfoTests{
		@Test
		public void test01InvalidUnder(){
			presenter.userEnterTaskName( "Te" );
			verify( view ).setTaskNameUnderValidation();
		}

		@Test
		public void test02InvalidOver(){
			presenter.userEnterTaskName( "123456789012345678901" );
			verify( view ).setTaskNameOverValidation();
		}
	}

	@FixMethodOrder( MethodSorters.NAME_ASCENDING )
	public static class DescriptionTests extends TaskInfoTests{
		@Test
		public void test01InvalidOver(){
			presenter.userEnterTaskDescription( "Lorem ipsum dolor1 sit amet consectetur adipiscing elit Morbi a lectus sit amet elit egestas hendrerit ut a metus1 Nulla eu sed11" );
			verify( view ).setTaskDescriptionOverValidation();
		}
	}

	@FixMethodOrder( MethodSorters.NAME_ASCENDING )
	public static class NavigationTests extends TaskInfoTests{
		@Test
		public void test01Edit(){
			presenter.userSelectEditTask();
			verify( view ).setTaskEdited();
		}

		@Test
		public void test02Previous(){
			presenter.userSelectBack();
			verify( navigation ).navigateToPrevious();
		}
	}
}
