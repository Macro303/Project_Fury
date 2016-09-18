package tobedevelopers.project_fury.create_task;

import tobedevelopers.project_fury.model.Project;

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

		void errorValidation();

		void setProjectSpinner( Project[] projects );
	}

	interface Navigation{
		void navigateToPrevious();
	}

	interface Presenter{
		void userSelectCreateTask( final String mAssignee );

		void userSelectBack();

		void userEnterTaskName( String taskName );

		void userEnterTaskDescription( String taskDescription );

		void getProjects();
	}
}
