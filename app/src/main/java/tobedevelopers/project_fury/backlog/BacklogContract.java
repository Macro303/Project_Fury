package tobedevelopers.project_fury.backlog;

import tobedevelopers.project_fury.backlog.implementation.Holder;

/**
 * Created by Macro303 on 12/08/2016.
 */
public interface BacklogContract{

	interface View{
		void noInternetAccessValidation();

		void loadingInProgress();

		void fillProjects( Holder holder );

		void defaultErrorMessage();
	}

	interface Navigation{
		void navigateToCreateTask();
	}

	interface Presenter{
		void userSelectCreateTask();

		void loadProjects();
	}
}
