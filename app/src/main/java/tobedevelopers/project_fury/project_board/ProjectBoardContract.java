package tobedevelopers.project_fury.project_board;

import tobedevelopers.project_fury.model.Column;

/**
 * Created by Macro303 on 13/08/2016.
 */
public interface ProjectBoardContract{

	interface View{
		void noInternetAccessValidation();

		void displayDefaultErrorMessage();

		void setTabTitles( Column[] columns );

		void showProjectUpdatingInProgress();

		void hideProjectUpdatingInProgress();
	}

	interface Navigation{
		void navigateToCreateTask();
	}

	interface Presenter{
		void userSelectCreateTask();

		void userLoadsBoard();
	}
}
