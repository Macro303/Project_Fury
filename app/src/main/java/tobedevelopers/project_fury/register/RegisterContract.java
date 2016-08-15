package tobedevelopers.project_fury.register;

/**
 * Created by Macro303 on 10/08/2016.
 */
public interface RegisterContract{

	interface View{

	}

	interface Navigation{
		void navigateToLogin();
	}

	interface Presenter{
		void userSelectCreateAccount();
	}
}
