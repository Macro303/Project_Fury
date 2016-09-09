package tobedevelopers.project_fury.dashboard;

/**
 * Created by Macro303 on 10/08/2016.
 */
public interface DashboardContract{

	interface View{
		void noInternetAccessValidation();

		void loadingInProgress();

		void loadProjectsIntoList( Holder holder );

		void defaultErrorMessage();
	}

	interface Navigation{
		void navigateToCreateProject();

		void navigateToProjectInfo();

		void navigateToTaskInfo();
	}

	interface Presenter{
		void userSelectCreateProject();

		void userSelectProjectInfo();

		void userSelectTaskInfo();

		void loadProjects();
	}
}
