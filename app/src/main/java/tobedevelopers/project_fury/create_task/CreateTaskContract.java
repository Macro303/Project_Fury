package tobedevelopers.project_fury.create_task;

/**
 * Created by Macro303 on 12/08/2016.
 */
public interface CreateTaskContract{

	interface View{

	}

	interface Navigation{
		void navigateToPrevious();

		void navigateToPreviousAfterCreate();
	}

	interface Presenter{
		void userSelectCreateTask();

		void userSelectBack();
	}
}
