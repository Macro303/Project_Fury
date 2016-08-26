package tobedevelopers.project_fury.create_project;

/**
 * Created by Macro303 on 11/08/2016.
 */
public interface CreateProjectContract{

	interface View{
		void enableCreateProjectButton();

		void disableCreateProjectButton();

		void projectCreationInProgress();

		void setProjectNameUnderValidation();

		void setProjectNameOverValidation();

		void setProjectDescriptionOverValidation();

		void noInternetAccessValidation();
	}

	interface Navigation{
		void navigateToPrevious();
	}

	interface Presenter{
		void userSelectCreateProject();

		void userEnterProjectName( String projectName );

		void userEnterProjectDescription( String projectDescription );
	}
}
