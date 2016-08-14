package tobedevelopers.project_fury.login;

/**
 * Created by A on 8/9/2016.
 */
public interface LoginContract{

	interface View{

	}

	interface Navigation{
		void navigateToRegister();

		void navigateToDashboard();
	}

	interface Presenter{
		void userSelectRegister();

		void userSelectLogin();
	}
}
