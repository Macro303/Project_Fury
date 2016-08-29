package tobedevelopers.project_fury;

import android.app.Application;
import android.test.ApplicationTestCase;

import tobedevelopers.project_fury.create_task.CreateTaskContract;
import tobedevelopers.project_fury.create_task.implementation.CreateTaskPresenter;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by A on 8/29/2016.
 */
public class FURY008UnitTests extends ApplicationTestCase< Application >{

	public FURY008UnitTests(){
		super( Application.class );
	}

	//Task Name Validation
	public void testValidTaskName(){
		CreateTaskContract.View viewMock = mock( CreateTaskContract.View.class );
		CreateTaskContract.Navigation navigationMock = mock( CreateTaskContract.Navigation.class );

		CreateTaskContract.Presenter presenter = new CreateTaskPresenter( viewMock, navigationMock );

		presenter.userEnterTaskName( "Update a Project" );
		verify( viewMock ).enableCreateTaskButton();
	}

	public void testInvalidTooShortTaskName(){
		CreateTaskContract.View viewMock = mock( CreateTaskContract.View.class );
		CreateTaskContract.Navigation navigationMock = mock( CreateTaskContract.Navigation.class );

		CreateTaskContract.Presenter presenter = new CreateTaskPresenter( viewMock, navigationMock );

		presenter.userEnterTaskName( "Up" );
		verify( viewMock ).setTaskNameUnderValidation();
		verify( viewMock ).disableCreateTaskButton();
	}

	public void testInvalidTooLongTaskName(){
		CreateTaskContract.View viewMock = mock( CreateTaskContract.View.class );
		CreateTaskContract.Navigation navigationMock = mock( CreateTaskContract.Navigation.class );

		CreateTaskContract.Presenter presenter = new CreateTaskPresenter( viewMock, navigationMock );

		presenter.userEnterTaskName( "123456789012345678901" );
		verify( viewMock ).setTaskNameOverValidation();
		verify( viewMock ).disableCreateTaskButton();
	}

	public void testTaskDescriptionTooLongValidation(){
		CreateTaskContract.View viewMock = mock( CreateTaskContract.View.class );
		CreateTaskContract.Navigation navigationMock = mock( CreateTaskContract.Navigation.class );

		CreateTaskContract.Presenter presenter = new CreateTaskPresenter( viewMock, navigationMock );

		presenter.userEnterTaskDescription( "Lorem ipsum dolor1 sit amet consectetur adipiscing elit Morbi a lectus sit amet elit egestas hendrerit ut a metus1 Nulla eu sed11" );
		verify( viewMock ).setTaskDescriptionOverValidation();
		verify( viewMock ).disableCreateTaskButton();
	}

	//Create Task Navigation
	public void testCreateTaskButtonNavigationToPrevious(){
		CreateTaskContract.View viewMock = mock( CreateTaskContract.View.class );
		CreateTaskContract.Navigation navigationMock = mock( CreateTaskContract.Navigation.class );

		CreateTaskContract.Presenter presenter = new CreateTaskPresenter( viewMock, navigationMock );

		presenter.userSelectCreateTask();
		verify( navigationMock ).navigateToPreviousAfterCreate();
	}

	//Back button Navigation
	public void testBackButtonNavigateToPrevious(){
		CreateTaskContract.View viewMock = mock( CreateTaskContract.View.class );
		CreateTaskContract.Navigation navigationMock = mock( CreateTaskContract.Navigation.class );

		CreateTaskContract.Presenter presenter = new CreateTaskPresenter( viewMock, navigationMock );

		presenter.userSelectBack();
		verify( navigationMock ).navigateToPrevious();
	}
}
