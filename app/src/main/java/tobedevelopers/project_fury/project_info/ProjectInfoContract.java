package tobedevelopers.project_fury.project_info;

/**
 * Created by Macro303 on 11/08/2016.
 */
public interface ProjectInfoContract{

	interface View{
		//		void displayColumnAdded();
//
//		void displayUserAdded();
//
//		void displayColumnRemoved();
//
		void editProjectDescription();

		void saveProjectDescription();

		void projectUpdatingInProgress();

//		void setProjectNameUnderValidation();
//
//		void setProjectNameOverValidation();

		void setProjectDescriptionOverValidation();

		void noInternetAccessValidation();
	}

	interface Navigation{
		void navigateToPrevious();
	}

	interface Presenter{
		void userSelectBack();

//		void userSelectAddUser();
//
//		void userSelectRemoveMe();
//
//		void userSelectAddColumn();
//
//		void userSelectRemoveColumn();

		void userSelectEditProject();

		void userSelectSaveProject();

//		void userEnterProjectName( String projectName );

		void userEnterProjectDescription( String projectDescription );
	}
}
