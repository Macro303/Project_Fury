package tobedevelopers.project_fury.backlog;

/**
 * Created by Macro303 on 12/08/2016.
 */
public interface BacklogContract{

	interface View{

	}

	interface Navigation{
		void navigateToCreateTask();
	}

	interface Presenter{
		void userSelectCreateTask();
	}
}
