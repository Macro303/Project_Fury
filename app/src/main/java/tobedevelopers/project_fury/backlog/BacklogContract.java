package tobedevelopers.project_fury.backlog;

import tobedevelopers.project_fury.model.Project;

/**
 * Created by Macro303 on 12/08/2016.
 */
public interface BacklogContract{

	interface View{
		void noInternetAccessValidation();

		void loadingInProgress();

		void fillProjects( Project[] projects );

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
