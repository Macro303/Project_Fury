package tobedevelopers.project_fury.task_info;

import tobedevelopers.project_fury.model.Column;

/**
 * Created by Macro303 on 11/08/2016.
 */
public interface TaskInfoContract{

	interface View{
		void setTaskEdited();

		void setTaskSaved();

		void setColumnSpinner( Column[] columns );

		void showTaskUpdatingInProgress();

		void hideTaskUpdatingInProgress();

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

		void userSelectSaveTask( String assignee, String priority, Column column );

		void userSelectRemoveTask();

		void userEnterTaskName( String taskName );

		void userEnterTaskDescription( String taskDescription );

		void getColumnsOnProject();
	}
}
