package tobedevelopers.project_fury.backlog;

import tobedevelopers.project_fury.backlog.implementation.Holder;

/**
 * Created by Macro303 on 12/08/2016.
 */
public interface BacklogContract{

	interface View{
		void noInternetAccessValidation();

//		void loadingProjectsInProgress();

		void fillProjects( Holder holder );

		void defaultErrorMessage();

		void showProjectUpdatingInProgress();

		void hideProjectUpdatingInProgress();
	}

	interface Navigation{
		void navigateToCreateTask();
	}

	interface Presenter{
//		void userSelectCreateTask();

		void loadProjects();

		void cancelAllAsyncTasks( Boolean condition );
	}
}
