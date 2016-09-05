package tobedevelopers.project_fury.task_info;

/**
 * Created by Macro303 on 11/08/2016.
 */
public interface TaskInfoContract{

	interface View{
		void setTaskEdited();

		void setTaskSaved();

		void taskUpdatingInProgress();

		void taskDeletionInProgress();

		void setTaskNameUnderValidation();

		void setTaskNameOverValidation();

		void setTaskDescriptionOverValidation();

		void noInternetAccessValidation();

		void defaultErrorMessage();

		void enableSaveTask();

		void disableSaveTask();
	}

	interface Navigation{
		void navigateToPrevious();
	}

	interface Presenter{
		void userSelectBack();

		void userSelectEditTask();

		void userSelectSaveTask( String assignee, String priority );

		void userSelectRemoveTask();

		void userEnterTaskName( String taskName );

		void userEnterTaskDescription( String taskDescription );
	}
}
