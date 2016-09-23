package tobedevelopers.project_fury.create_project;

import tobedevelopers.project_fury.BaseContract;

/**
 * Created by Macro303 on 11/08/2016.
 */
public interface CreateProjectContract{

	interface View extends BaseContract.View{
		void enableCreateProjectButton();

		void disableCreateProjectButton();

		void setProjectNameUnderValidation();

		void setProjectNameOverValidation();

		void setProjectDescriptionOverValidation();

		void setProjectAlreadyUsedValidation();

		void showProjectUpdatingInProgress();

		void hideProjectUpdatingInProgress();
	}

	interface Navigation extends BaseContract.Navigation{
		void navigateToPrevious();
	}

	interface Presenter extends BaseContract.Presenter{
		void userSelectCreateProject();

		void userEnterProjectName( String projectName );

		void userEnterProjectDescription( String projectDescription );
	}
}
