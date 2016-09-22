package tobedevelopers.project_fury.all_boards;

import tobedevelopers.project_fury.BaseContract;
import tobedevelopers.project_fury.model.Project;

/**
 * Created by Macro303 on 12/08/2016.
 */
public interface AllBoardsContract{

	interface View extends BaseContract.View{
		void setRecyclerItems( Project[] projects );

		void showProjectUpdatingInProgress();

		void hideProjectUpdatingInProgress();
	}

	interface Navigation extends BaseContract.Navigation{
		void navigateToProjectInfo();

		void navigateToProjectBoard();

		void navigateToCreateProject();
	}

	interface Presenter extends BaseContract.Presenter{
		void userOpensBoard();

		void userSelectCreateProject();
	}
}
