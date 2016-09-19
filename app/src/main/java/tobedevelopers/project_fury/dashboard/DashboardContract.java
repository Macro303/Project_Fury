package tobedevelopers.project_fury.dashboard;

import tobedevelopers.project_fury.dashboard.implementation.ProjectHolder;
import tobedevelopers.project_fury.dashboard.implementation.TaskHolder;

/**
 * Created by Macro303 on 10/08/2016.
 */
public interface DashboardContract{

	interface View{
		void noInternetAccessValidation();

		void loadingInProgress();

		void loadProjectsIntoList( ProjectHolder holder );

		void loadTasksIntoList( TaskHolder holder );

		void defaultErrorMessage();
	}

	interface Navigation{
		void navigateToCreateProject();

		void navigateToCreateTask();

		void navigateToProjectInfo();

		void navigateToTaskInfo();
	}

	interface Presenter{
		void userSelectCreateProject();

		void userSelectCreateTask();

		void userSelectProjectInfo();

		void userSelectTaskInfo();

		void loadProjects();

		void loadTasks();
	}
}
