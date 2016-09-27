package tobedevelopers.project_fury;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Suite;

import tobedevelopers.project_fury.create_project.CreateProjectContract;
import tobedevelopers.project_fury.create_project.implementation.CreateProjectPresenter;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by Macro303 on 28/09/2016.
 */
@RunWith( Suite.class )
@Suite.SuiteClasses( { CreateProjectUnitTests.NameTests.class, CreateProjectUnitTests.DescriptionTests.class } )
public class CreateProjectUnitTests{

	private abstract static class CreateProjectTests{
		protected CreateProjectContract.View view;
		protected CreateProjectContract.Navigation navigation;
		protected CreateProjectContract.Presenter presenter;

		@Before
		public void setUp() throws Exception{
			view = mock( CreateProjectContract.View.class );
			navigation = mock( CreateProjectContract.Navigation.class );
			presenter = new CreateProjectPresenter( view, navigation );
		}

		@After
		public void tearDown() throws Exception{
			view = null;
			navigation = null;
			presenter = null;
		}
	}

	@FixMethodOrder( MethodSorters.NAME_ASCENDING )
	public static class NameTests extends CreateProjectTests{
		@Test
		public void test01Valid(){
			presenter.userEnterProjectName( "Test Project" );
			verify( view ).enableCreateProjectButton();
		}

		@Test
		public void test02InvalidUnder(){
			presenter.userEnterProjectName( "Te" );
			verify( view ).setProjectNameUnderValidation();
			verify( view ).disableCreateProjectButton();
		}

		@Test
		public void test03InvalidOver(){
			presenter.userEnterProjectName( "123456789012345678901" );
			verify( view ).setProjectNameOverValidation();
			verify( view ).disableCreateProjectButton();
		}
	}

	@FixMethodOrder( MethodSorters.NAME_ASCENDING )
	public static class DescriptionTests extends CreateProjectTests{
		@Test
		public void test01Valid(){
			presenter.userEnterProjectName( "Test Project" );
			presenter.userEnterProjectDescription( "Test Project Description" );
			verify( view ).enableCreateProjectButton();
		}

		@Test
		public void test02InvalidOver(){
			presenter.userEnterProjectDescription( "Lorem ipsum dolor1 sit amet consectetur adipiscing elit Morbi a lectus sit amet elit egestas hendrerit ut a metus1 Nulla eu sed11" );
			verify( view ).setProjectDescriptionOverValidation();
			verify( view ).disableCreateProjectButton();
		}
	}
}
