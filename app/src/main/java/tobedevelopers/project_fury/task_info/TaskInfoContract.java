package tobedevelopers.project_fury.task_info;

/**
 * Created by Macro303 on 11/08/2016.
 */
public interface TaskInfoContract{

	interface View{
		void displayTaskEdited();
	}

	interface Navigation{
		void navigateToPrevious();
	}

	interface Presenter{
		void userSelectBack();

		void userSelectEditTask();

		void userSelectRemoveTask();
	}
}
