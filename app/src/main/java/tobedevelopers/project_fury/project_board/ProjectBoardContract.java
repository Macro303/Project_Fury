package tobedevelopers.project_fury.project_board;

/**
 * Created by Macro303 on 13/08/2016.
 */
public interface ProjectBoardContract{

	interface View{

	}

	interface Navigation{
		void navigateToCreateTask();
	}

	interface Presenter{
		void userSelectCreateTask();
	}
}
