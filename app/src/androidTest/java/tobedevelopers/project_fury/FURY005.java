package tobedevelopers.project_fury;

import android.app.Application;
import android.test.ApplicationTestCase;

import tobedevelopers.project_fury.backlog.BacklogContract;
import tobedevelopers.project_fury.backlog.implementation.BacklogPresenter;
import tobedevelopers.project_fury.create_project.CreateProjectContract;
import tobedevelopers.project_fury.create_project.implementation.CreateProjectPresenter;
import tobedevelopers.project_fury.create_task.CreateTaskContract;
import tobedevelopers.project_fury.create_task.implementation.CreateTaskPresenter;
import tobedevelopers.project_fury.dashboard.DashboardContract;
import tobedevelopers.project_fury.dashboard.implementation.DashboardPresenter;
import tobedevelopers.project_fury.login.LoginContract;
import tobedevelopers.project_fury.login.implementation.LoginPresenter;
import tobedevelopers.project_fury.project_info.ProjectInfoContract;
import tobedevelopers.project_fury.project_info.implementation.ProjectInfoPresenter;
import tobedevelopers.project_fury.register.RegisterContract;
import tobedevelopers.project_fury.register.implementation.RegisterPresenter;
import tobedevelopers.project_fury.task_info.TaskInfoContract;
import tobedevelopers.project_fury.task_info.implementation.TaskInfoPresenter;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by Macro303 on 9/08/2016.
 */
public class FURY005 extends ApplicationTestCase< Application >{
	public FURY005(){
		super( Application.class );
	}

	//Login Activity Navigation
	public void testOnUserSelectLogin(){
		LoginContract.View mockView = mock( LoginContract.View.class );
		LoginContract.Navigation mockNavigation = mock( LoginContract.Navigation.class );

		LoginContract.Presenter presenter = new LoginPresenter( mockView, mockNavigation );

		presenter.userSelectLogin();
		verify( mockNavigation ).navigateToDashboard();
	}

	public void testOnUserSelectRegister(){
		LoginContract.View mockView = mock( LoginContract.View.class );
		LoginContract.Navigation mockNavigation = mock( LoginContract.Navigation.class );

		LoginContract.Presenter presenter = new LoginPresenter( mockView, mockNavigation );

		presenter.userSelectRegister();
		verify( mockNavigation ).navigateToRegister();
	}

	//Register Activity Navigation
	public void testOnUserSelectCreateAccount(){
		RegisterContract.View mockView = mock( RegisterContract.View.class );
		RegisterContract.Navigation mockNavigation = mock( RegisterContract.Navigation.class );

		RegisterContract.Presenter presenter = new RegisterPresenter( mockView, mockNavigation );

		presenter.userSelectCreateAccount();
		verify( mockNavigation ).navigateToLogin();
	}

	//Dashboard Activity Navigation
	public void testOnUserSelectCreateProject(){
		DashboardContract.View mockView = mock( DashboardContract.View.class );
		DashboardContract.Navigation mockNavigation = mock( DashboardContract.Navigation.class );

		DashboardContract.Presenter presenter = new DashboardPresenter( mockView, mockNavigation );

		presenter.userSelectCreateProject();
		verify( mockNavigation ).navigateToCreateProject();
	}

	public void testOnUserSelectProjectInformation(){
		DashboardContract.View mockView = mock( DashboardContract.View.class );
		DashboardContract.Navigation mockNavigation = mock( DashboardContract.Navigation.class );

		DashboardContract.Presenter presenter = new DashboardPresenter( mockView, mockNavigation );

		presenter.userSelectProjectInfo();
		verify( mockNavigation ).navigateToProjectInfo();
	}

	public void testOnUserSelectTaskInformation(){
		DashboardContract.View mockView = mock( DashboardContract.View.class );
		DashboardContract.Navigation mockNavigation = mock( DashboardContract.Navigation.class );

		DashboardContract.Presenter presenter = new DashboardPresenter( mockView, mockNavigation );

		presenter.userSelectTaskInfo();
		verify( mockNavigation ).navigateToTaskInfo();
	}

	//Backlog Activity Navigation
	public void testOnUserSelectAddTask(){
		BacklogContract.View mockView = mock( BacklogContract.View.class );
		BacklogContract.Navigation mockNavigation = mock( BacklogContract.Navigation.class );

		BacklogContract.Presenter presenter = new BacklogPresenter( mockView, mockNavigation );

		presenter.userSelectAddTask();
		verify( mockNavigation ).navigateToCreateTask();
	}

	//Project Information Activity Navigation
	public void testOnUserSelectBackButton(){
		ProjectInfoContract.View mockView = mock( ProjectInfoContract.View.class );
		ProjectInfoContract.Navigation mockNavigation = mock( ProjectInfoContract.Navigation.class );

		ProjectInfoContract.Presenter presenter = new ProjectInfoPresenter( mockView, mockNavigation );

		presenter.userSelectBack();
		verify( mockNavigation ).navigateToPrevious();
	}

	//Task Information Activity Navigation
	public void testOnUserSelectNavigationBack(){
		TaskInfoContract.View mockView = mock( TaskInfoContract.View.class );
		TaskInfoContract.Navigation mockNavigation = mock( TaskInfoContract.Navigation.class );

		TaskInfoContract.Presenter presenter = new TaskInfoPresenter( mockView, mockNavigation );

		presenter.userSelectBack();
		verify( mockNavigation ).navigateToPrevious();
	}

	//Create Project Activity Navigation
	public void testOnUserCreateProject(){
		CreateProjectContract.View mockView = mock( CreateProjectContract.View.class );
		CreateProjectContract.Navigation mockNavigation = mock( CreateProjectContract.Navigation.class );

		CreateProjectContract.Presenter presenter = new CreateProjectPresenter( mockView, mockNavigation );

		presenter.userSelectCreateProject();
		verify( mockNavigation ).navigateToProjectInfo();
	}

	//Create Task Activity Navigation
	public void testOnUserSelectCreateTask(){
		CreateTaskContract.View mockView = mock( CreateTaskContract.View.class );
		CreateTaskContract.Navigation mockNavigation = mock( CreateTaskContract.Navigation.class );

		CreateTaskContract.Presenter presenter = new CreateTaskPresenter( mockView, mockNavigation );

		presenter.userSelectCreateTask();
		verify( mockNavigation ).navigateToTaskInfo();
	}
}
