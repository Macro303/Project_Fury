package tobedevelopers.project_fury;

import android.app.Application;
import android.test.ApplicationTestCase;

import tobedevelopers.project_fury.project_info.ProjectInfoContract;
import tobedevelopers.project_fury.project_info.implementation.ProjectInfoPresenter;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by A on 9/1/2016.
 */
public class FURY010UnitTests extends ApplicationTestCase< Application >{

	public FURY010UnitTests(){
		super( Application.class );
	}

	//Project Description Validation
	public void testInvalidTooLongProjectDescription(){
		ProjectInfoContract.View viewMock = mock( ProjectInfoContract.View.class );
		ProjectInfoContract.Navigation navigationMock = mock( ProjectInfoContract.Navigation.class );

		ProjectInfoContract.Presenter presenter = new ProjectInfoPresenter( viewMock, navigationMock );

		presenter.userEnterProjectDescription( "Lorem ipsum dolor1 sit amet consectetur adipiscing elit Morbi a lectus sit amet elit egestas hendrerit ut a metus1 Nulla eu sed1" );
		verify( viewMock ).setProjectDescriptionOverValidation();
	}

	//Edit Project Button
	public void testEditProjectButtonNavigation(){
		ProjectInfoContract.View viewMock = mock( ProjectInfoContract.View.class );
		ProjectInfoContract.Navigation navigationMock = mock( ProjectInfoContract.Navigation.class );

		ProjectInfoContract.Presenter presenter = new ProjectInfoPresenter( viewMock, navigationMock );

		presenter.userSelectEditProject();
		verify( viewMock ).editProjectDescription();
	}
}
