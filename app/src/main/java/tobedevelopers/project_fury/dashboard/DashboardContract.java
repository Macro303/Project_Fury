package tobedevelopers.project_fury.dashboard;

import tobedevelopers.project_fury.BaseContract;
import tobedevelopers.project_fury.dashboard.implementation.ProjectHolder;
import tobedevelopers.project_fury.dashboard.implementation.TaskHolder;

/**
 * Created by Macro303 on 10/08/2016.
 */
public interface DashboardContract{

	interface View extends BaseContract.View{
		void loadingProjectsInProgress();

		void loadingProjectsFinished();

		void loadingTasksInProgress();

		void loadingTasksFinished();

		void loadProjectsIntoList( ProjectHolder holder );

		void loadTasksIntoList( TaskHolder holder );
	}

	interface Navigation extends BaseContract.Navigation{
		void navigateToCreateProject();

		void navigateToCreateTask();

		void navigateToProjectInfo();

		void navigateToTaskInfo();
	}

	interface Presenter extends BaseContract.Presenter{
		void userSelectCreateProject();

		void userSelectCreateTask();

		void userSelectProjectInfo();

		void userSelectTaskInfo();

		void loadProjects();

		void loadTasks();

		void cancelAllAsyncTasks( Boolean condition );
	}
}
