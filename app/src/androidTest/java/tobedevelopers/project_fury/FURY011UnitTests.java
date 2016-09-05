package tobedevelopers.project_fury;

import android.app.Application;
import android.test.ApplicationTestCase;

import tobedevelopers.project_fury.task_info.TaskInfoContract;
import tobedevelopers.project_fury.task_info.implementation.TaskInfoPresenter;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by a on 5/09/2016.
 */
public class FURY011UnitTests extends ApplicationTestCase< Application >{

	public FURY011UnitTests(){
		super( Application.class );
	}

	//Task Name Validation
	public void testInvalidTooShortTaskName(){
		TaskInfoContract.View viewMock = mock( TaskInfoContract.View.class );
		TaskInfoContract.Navigation navigationMock = mock( TaskInfoContract.Navigation.class );

		TaskInfoContract.Presenter presenter = new TaskInfoPresenter( viewMock, navigationMock );

		presenter.userEnterTaskName( "No" );
		verify( viewMock ).setTaskNameUnderValidation();
	}

	public void testInvalidTooLongProjectName(){
		TaskInfoContract.View viewMock = mock( TaskInfoContract.View.class );
		TaskInfoContract.Navigation navigationMock = mock( TaskInfoContract.Navigation.class );

		TaskInfoContract.Presenter presenter = new TaskInfoPresenter( viewMock, navigationMock );

		presenter.userEnterTaskName( "123456789012345678901" );
		verify( viewMock ).setTaskNameOverValidation();
		verify( viewMock ).disableSaveTask();
	}

	//Task Description Validation
	public void testInvalidTooLongTaskDescription(){
		TaskInfoContract.View viewMock = mock( TaskInfoContract.View.class );
		TaskInfoContract.Navigation navigationMock = mock( TaskInfoContract.Navigation.class );

		TaskInfoContract.Presenter presenter = new TaskInfoPresenter( viewMock, navigationMock );

		presenter.userEnterTaskDescription( "Lorem ipsum dolor1 sit amet consectetur adipiscing elit Morbi a lectus sit amet elit egestas hendrerit ut a metus1 Nulla eu sed1" );
		verify( viewMock ).setTaskDescriptionOverValidation();
	}

	//Edit Task Button
	public void testEditTaskProjectButtonNavigation(){
		TaskInfoContract.View viewMock = mock( TaskInfoContract.View.class );
		TaskInfoContract.Navigation navigationMock = mock( TaskInfoContract.Navigation.class );

		TaskInfoContract.Presenter presenter = new TaskInfoPresenter( viewMock, navigationMock );

		presenter.userSelectEditTask();
		verify( viewMock ).setTaskEdited();
	}

	//Back Button
	public void testBackButton(){
		TaskInfoContract.View viewMock = mock( TaskInfoContract.View.class );
		TaskInfoContract.Navigation navigationMock = mock( TaskInfoContract.Navigation.class );

		TaskInfoContract.Presenter presenter = new TaskInfoPresenter( viewMock, navigationMock );

		presenter.userSelectBack();
		verify( navigationMock ).navigateToPrevious();
	}
}
