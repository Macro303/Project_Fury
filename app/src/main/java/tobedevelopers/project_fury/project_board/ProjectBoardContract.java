package tobedevelopers.project_fury.project_board;

import tobedevelopers.project_fury.BaseContract;
import tobedevelopers.project_fury.model.Column;

/**
 * Created by Macro303 on 13/08/2016.
 */
public interface ProjectBoardContract extends BaseContract{

	interface View extends BaseContract.View{
		void setTabTitles( Column[] columns );

		void showProjectUpdatingInProgress();

		void hideProjectUpdatingInProgress();
	}

	interface Navigation extends BaseContract.Navigation{
		void navigateToCreateTask();
	}

	interface Presenter extends BaseContract.Presenter{
		void userSelectCreateTask();

		void userLoadsBoard();
	}
}
