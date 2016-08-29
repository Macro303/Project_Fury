package tobedevelopers.project_fury.create_task;

/**
 * Created by Macro303 on 12/08/2016.
 */
public interface CreateTaskContract{

	interface View{
		void enableCreateTaskButton();

		void disableCreateTaskButton();

		void taskCreationInProgress();

		void setTaskNameUnderValidation();

		void setTaskNameOverValidation();

		void setTaskDescriptionOverValidation();

		void noInternetAccessValidation();
	}

	interface Navigation{
		void navigateToPrevious();
	}

	interface Presenter{
		void userSelectCreateTask();

		void userSelectBack();

		void userEnterTaskName( String taskName );

		void userEnterTaskDescription( String taskDescription );
	}
}
