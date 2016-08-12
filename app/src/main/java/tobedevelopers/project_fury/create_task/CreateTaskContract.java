package tobedevelopers.project_fury.create_task;

/**
 * Created by Macro303 on 12/08/2016.
 */
public interface CreateTaskContract{

	interface View{

	}

	interface Navigation{
		void navigateToTaskInfo();
	}

	interface Presenter{
		void userSelectCreateTask();
	}
}
