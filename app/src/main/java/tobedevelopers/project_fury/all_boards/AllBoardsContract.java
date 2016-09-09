package tobedevelopers.project_fury.all_boards;

import tobedevelopers.project_fury.model.Project;

/**
 * Created by Macro303 on 12/08/2016.
 */
public interface AllBoardsContract{

	interface View{
		void updatingBoardInProgress();

		void noInternetAccessValidation();

		void displayDefaultErrorMessage();

		void setRecyclerItems( Project[] projects );

	}

	interface Navigation{
		void navigateToProjectInfo();

		void navigateToProjectBoard();

		void navigateToCreateProject();
	}

	interface Presenter{
		void userOpensBoard();

		void userSelectCreateProject();
	}
}
