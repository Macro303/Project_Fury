package tobedevelopers.project_fury.project_info;

/**
 * Created by Macro303 on 11/08/2016.
 */
public interface ProjectInfoContract{

	interface View{

//		void displayUserAdded();

		void editProjectDescription();

		void saveProjectDescription();

		void projectUpdatingInProgress();

		void setProjectNameUnderValidation();

		void setProjectNameOverValidation();

		void setProjectDescriptionOverValidation();

		void noInternetAccessValidation();

		void defaultErrorMessage();

		void setInvalidUserValidation();
	}

	interface Navigation{
		void navigateToPrevious();
	}

	interface Presenter{
		void userSelectBack();

//		void userSelectAddUser();
//
//		void userSelectRemoveMe();

		void userSelectDeleteProject();

		void userSelectEditProject();

		void userSelectSaveProject();

		void userEnterProjectName( String projectName );

		void userEnterProjectDescription( String projectDescription );
	}
}
