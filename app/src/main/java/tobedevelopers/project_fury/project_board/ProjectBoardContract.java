package tobedevelopers.project_fury.project_board;

import tobedevelopers.project_fury.model.Column;

/**
 * Created by Macro303 on 13/08/2016.
 */
public interface ProjectBoardContract{

	interface View{
		void updatingBoardInProgress();

		void noInternetAccessValidation();

		void displayDefaultErrorMessage();

		void setTabTitles( Column[] columns );
	}

	interface Navigation{

		void navigateToCreateTask();
	}

	interface Presenter{
		void userSelectCreateTask();

		void userOpensBoard();

	}
}
