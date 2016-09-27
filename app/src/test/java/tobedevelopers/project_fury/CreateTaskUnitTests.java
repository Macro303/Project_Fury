package tobedevelopers.project_fury;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Suite;

import tobedevelopers.project_fury.create_task.CreateTaskContract;
import tobedevelopers.project_fury.create_task.implementation.CreateTaskPresenter;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by Macro303 on 28/09/2016.
 */
@RunWith( Suite.class )
@Suite.SuiteClasses( { CreateTaskUnitTests.NameTests.class, CreateTaskUnitTests.DescriptionTests.class, CreateTaskUnitTests.NavigationTests.class } )
public class CreateTaskUnitTests{

	private abstract static class CreateTaskTests{
		protected CreateTaskContract.View view;
		protected CreateTaskContract.Navigation navigation;
		protected CreateTaskContract.Presenter presenter;

		@Before
		public void setUp() throws Exception{
			view = mock( CreateTaskContract.View.class );
			navigation = mock( CreateTaskContract.Navigation.class );
			presenter = new CreateTaskPresenter( view, navigation );
		}

		@After
		public void tearDown() throws Exception{
			view = null;
			navigation = null;
			presenter = null;
		}
	}

	@FixMethodOrder( MethodSorters.NAME_ASCENDING )
	public static class NameTests extends CreateTaskTests{
		@Test
		public void test01Valid(){
			presenter.userEnterTaskName( "Test Task" );
			verify( view ).enableCreateTaskButton();
		}

		@Test
		public void test02InvalidUnder(){
			presenter.userEnterTaskName( "Te" );
			verify( view ).setTaskNameUnderValidation();
			verify( view ).disableCreateTaskButton();
		}

		@Test
		public void test03InvalidOver(){
			presenter.userEnterTaskName( "123456789012345678901" );
			verify( view ).setTaskNameOverValidation();
			verify( view ).disableCreateTaskButton();
		}
	}

	@FixMethodOrder( MethodSorters.NAME_ASCENDING )
	public static class DescriptionTests extends CreateTaskTests{
		@Test
		public void test01Valid(){
			presenter.userEnterTaskName( "Test Task" );
			presenter.userEnterTaskDescription( "Test Task Description" );
			verify( view ).enableCreateTaskButton();
		}

		@Test
		public void test02InvalidOver(){
			presenter.userEnterTaskDescription( "Lorem ipsum dolor1 sit amet consectetur adipiscing elit Morbi a lectus sit amet elit egestas hendrerit ut a metus1 Nulla eu sed11" );
			verify( view ).setTaskDescriptionOverValidation();
			verify( view ).disableCreateTaskButton();
		}
	}

	@FixMethodOrder( MethodSorters.NAME_ASCENDING )
	public static class NavigationTests extends CreateTaskTests{
		@Test
		public void test01Previous(){
			presenter.userSelectBack();
			verify( navigation ).navigateToPrevious();
		}
	}
}
