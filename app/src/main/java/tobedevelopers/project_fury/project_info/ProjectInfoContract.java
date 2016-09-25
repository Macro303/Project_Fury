package tobedevelopers.project_fury.project_info;

import java.util.List;

import tobedevelopers.project_fury.BaseContract;
import tobedevelopers.project_fury.model.Column;

/**
 * Created by Macro303 on 11/08/2016.
 */
public interface ProjectInfoContract{

	interface View extends BaseContract.View{
		void editProjectDescription();

		void saveProjectDescription();

		void addColumnName();

		void showProjectUpdatingInProgress();

		void hideProjectUpdatingInProgress();

		void setProjectNameUnderValidation();

		void setProjectNameOverValidation();

		void setProjectDescriptionOverValidation();

		void enableSave();

		void disableSave();

		void setInvalidNameValidation();

		void fillColumnList( Column[] columns );

		void alertDeleteColumn( List< Column > columnList );
	}

	interface Navigation extends BaseContract.Navigation{
		void navigateToPrevious();
	}

	interface Presenter extends BaseContract.Presenter{
		void userSelectBack();

		void userSelectDeleteProject();

		void userSelectEditProject();

		void userSelectSaveProject( List< Column > columnList );

		void userSelectAddColumn( String columnName );

		void saveColumnsBeforeDeleting( List< Column > columnList );

		void userSelectDeleteColumn();

		void userEnterProjectName( String projectName );

		void userEnterProjectDescription( String projectDescription );

		void loadColumns();
	}
}
