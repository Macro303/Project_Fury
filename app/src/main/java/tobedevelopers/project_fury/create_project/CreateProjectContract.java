package tobedevelopers.project_fury.create_project;

/**
 * Created by Macro303 on 11/08/2016.
 */
public interface CreateProjectContract{

	interface View{

	}

	interface Navigation{
		void navigateToProjectInfo();
	}

	interface Presenter{
		void userSelectCreateProject();

	}
}
