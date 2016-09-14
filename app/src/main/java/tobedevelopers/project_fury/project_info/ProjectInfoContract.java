package tobedevelopers.project_fury.project_info;

import java.util.List;

import tobedevelopers.project_fury.model.Column;

/**
 * Created by Macro303 on 11/08/2016.
 */
public interface ProjectInfoContract{

	interface View{

		void editProjectDescription();

		void saveProjectDescription();

		void addColumnName();

		void projectUpdatingInProgress();

		void setProjectNameUnderValidation();

		void setProjectNameOverValidation();

		void setProjectDescriptionOverValidation();

		void enableSave();

		void disableSave();

		void noInternetAccessValidation();

		void defaultErrorMessage();

		void setInvalidUserValidation();

		void fillColumnList( Column[] columns );
	}

	interface Navigation{
		void navigateToPrevious();
	}

	interface Presenter{
		void userSelectBack();

		void userSelectDeleteProject();

		void userSelectEditProject();

		void userSelectSaveProject();

		void userSelectAddColumn( String columnName );

		void userSelectSaveColumns( List< Column > columnList );

		void saveColumnsBeforeDeleting( List< Column > columnList );

		void userSelectDeleteColumn();

		void userEnterProjectName( String projectName );

		void userEnterProjectDescription( String projectDescription );

		void loadColumns();
	}
}
