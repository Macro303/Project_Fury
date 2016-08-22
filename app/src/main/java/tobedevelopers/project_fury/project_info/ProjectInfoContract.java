package tobedevelopers.project_fury.project_info;

/**
 * Created by Macro303 on 11/08/2016.
 */
public interface ProjectInfoContract{

	interface View{
		void displayColumnAdded();

		void displayUserAdded();

		void displayColumnRemoved();

		void displayProjectEdited();
	}

	interface Navigation{
		void navigateToPrevious();
	}

	interface Presenter{
		void userSelectBack();

		void userSelectAddUser();

		void userSelectRemoveMe();

		void userSelectAddColumn();

		void userSelectRemoveColumn();

		void userSelectEditProject();
	}
}
