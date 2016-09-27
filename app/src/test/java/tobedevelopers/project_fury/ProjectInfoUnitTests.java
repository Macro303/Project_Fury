package tobedevelopers.project_fury;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Suite;

import tobedevelopers.project_fury.project_info.ProjectInfoContract;
import tobedevelopers.project_fury.project_info.implementation.ProjectInfoPresenter;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by Macro303 on 28/09/2016.
 */
@RunWith( Suite.class )
@Suite.SuiteClasses( { ProjectInfoUnitTests.NameTests.class, ProjectInfoUnitTests.DescriptionTests.class, ProjectInfoUnitTests.NavigationTests.class } )
public class ProjectInfoUnitTests{

	private abstract static class ProjectInfoTests{
		protected ProjectInfoContract.View view;
		protected ProjectInfoContract.Navigation navigation;
		protected ProjectInfoContract.Presenter presenter;

		@Before
		public void setUp() throws Exception{
			view = mock( ProjectInfoContract.View.class );
			navigation = mock( ProjectInfoContract.Navigation.class );
			presenter = new ProjectInfoPresenter( view, navigation );
		}

		@After
		public void tearDown() throws Exception{
			view = null;
			navigation = null;
			presenter = null;
		}
	}

	@FixMethodOrder( MethodSorters.NAME_ASCENDING )
	public static class NameTests extends ProjectInfoTests{
		@Test
		public void test01InvalidUnder(){
			presenter.userEnterProjectName( "Te" );
			verify( view ).setProjectNameUnderValidation();
		}

		@Test
		public void test02InvalidOver(){
			presenter.userEnterProjectName( "123456789012345678901" );
			verify( view ).setProjectNameOverValidation();
		}
	}

	@FixMethodOrder( MethodSorters.NAME_ASCENDING )
	public static class DescriptionTests extends ProjectInfoTests{
		@Test
		public void test01InvalidOver(){
			presenter.userEnterProjectDescription( "Lorem ipsum dolor1 sit amet consectetur adipiscing elit Morbi a lectus sit amet elit egestas hendrerit ut a metus1 Nulla eu sed11" );
			verify( view ).setProjectDescriptionOverValidation();
		}
	}

	@FixMethodOrder( MethodSorters.NAME_ASCENDING )
	public static class NavigationTests extends ProjectInfoTests{
		@Test
		public void test01Edit(){
			presenter.userSelectEditProject();
			verify( view ).editProjectDescription();
		}

		@Test
		public void test02Previous(){
			presenter.userSelectBack();
			verify( navigation ).navigateToPrevious();
		}
	}
}
