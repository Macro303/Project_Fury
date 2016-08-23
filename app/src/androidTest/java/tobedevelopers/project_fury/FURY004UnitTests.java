package tobedevelopers.project_fury;

import android.app.Application;
import android.test.ApplicationTestCase;

import tobedevelopers.project_fury.create_project.CreateProjectContract;
import tobedevelopers.project_fury.create_project.implementation.CreateProjectPresenter;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by A on 8/23/2016.
 */
public class FURY004UnitTests extends ApplicationTestCase< Application >{

	public FURY004UnitTests(){
		super( Application.class );
	}

	//Project Name Validation
	public void testValidProjectName(){
		CreateProjectContract.View viewMock = mock( CreateProjectContract.View.class );
		CreateProjectContract.Navigation navigationMock = mock( CreateProjectContract.Navigation.class );

		CreateProjectContract.Presenter presenter = new CreateProjectPresenter( viewMock, navigationMock );

		presenter.userEnterProjectName( "Project Fury" );
		verify( viewMock ).enableCreateProjectButton();
	}

	public void testInvalidTooShortProjectName(){
		CreateProjectContract.View viewMock = mock( CreateProjectContract.View.class );
		CreateProjectContract.Navigation navigationMock = mock( CreateProjectContract.Navigation.class );

		CreateProjectContract.Presenter presenter = new CreateProjectPresenter( viewMock, navigationMock );

		presenter.userEnterProjectName( "bo" );
		verify( viewMock ).setProjectNameUnder3CharValidation();
		verify( viewMock ).disableCreateProjectButton();
	}

	public void testInvalidTooLongProjectName(){
		CreateProjectContract.View viewMock = mock( CreateProjectContract.View.class );
		CreateProjectContract.Navigation navigationMock = mock( CreateProjectContract.Navigation.class );

		CreateProjectContract.Presenter presenter = new CreateProjectPresenter( viewMock, navigationMock );

		presenter.userEnterProjectName( "123456789012345678901" );
		verify( viewMock ).setProjectNameOver20CharValidation();
		verify( viewMock ).disableCreateProjectButton();
	}

	//Project Description Validation
	public void testValidProjectDescriptionNull(){
		CreateProjectContract.View viewMock = mock( CreateProjectContract.View.class );
		CreateProjectContract.Navigation navigationMock = mock( CreateProjectContract.Navigation.class );

		CreateProjectContract.Presenter presenter = new CreateProjectPresenter( viewMock, navigationMock );

		presenter.userEnterProjectDescription( "" );
		verify( viewMock ).enableCreateProjectButton();
	}

	public void testValidProjectDescription(){
		CreateProjectContract.View viewMock = mock( CreateProjectContract.View.class );
		CreateProjectContract.Navigation navigationMock = mock( CreateProjectContract.Navigation.class );

		CreateProjectContract.Presenter presenter = new CreateProjectPresenter( viewMock, navigationMock );

		presenter.userEnterProjectDescription( "This project is about making an application to do fun things and have pretty pictures" );
		verify( viewMock ).enableCreateProjectButton();
	}

	public void testProjectDescriptionTooLongValidation(){
		CreateProjectContract.View viewMock = mock( CreateProjectContract.View.class );
		CreateProjectContract.Navigation navigationMock = mock( CreateProjectContract.Navigation.class );

		CreateProjectContract.Presenter presenter = new CreateProjectPresenter( viewMock, navigationMock );

		presenter.userEnterProjectDescription( "Lorem ipsum dolor1 sit amet consectetur adipiscing elit Morbi a lectus sit amet elit egestas hendrerit ut a metus1 Nulla eu sed1" );
		verify( viewMock ).setProjectDescriptionOver128CharValidation();
		verify( viewMock ).disableCreateProjectButton();
	}

	//Create Project Navigation
	public void testCreateProjectButtonNavigation(){
		CreateProjectContract.View viewMock = mock( CreateProjectContract.View.class );
		CreateProjectContract.Navigation navigationMock = mock( CreateProjectContract.Navigation.class );

		CreateProjectContract.Presenter presenter = new CreateProjectPresenter( viewMock, navigationMock );

		presenter.userSelectCreateProject();
		verify( navigationMock ).navigateToPrevious();
	}
}
