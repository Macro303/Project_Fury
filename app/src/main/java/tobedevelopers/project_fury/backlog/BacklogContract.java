package tobedevelopers.project_fury.backlog;

import tobedevelopers.project_fury.BaseContract;
import tobedevelopers.project_fury.model.Project;

/**
 * Created by Macro303 on 12/08/2016.
 */
public interface BacklogContract{

	interface View extends BaseContract.View{
		void fillProjects( Project[] projects );

		void showProjectUpdatingInProgress();

		void hideProjectUpdatingInProgress();
	}

	interface Navigation extends BaseContract.Navigation{
		void navigateToCreateTask();
	}

	interface Presenter extends BaseContract.Presenter{
		void loadProjects();
	}
}
