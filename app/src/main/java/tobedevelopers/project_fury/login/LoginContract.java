package tobedevelopers.project_fury.login;

import tobedevelopers.project_fury.BaseContract;

/**
 * Created by A on 8/9/2016.
 */
public interface LoginContract{

	interface View extends BaseContract.View{
		void enableLoginButton();

		void disableLoginButton();

		void loginInProgress();

		void logInFinished();

		void setInvalidUserValidation();
	}

	interface Navigation extends BaseContract.Navigation{
		void navigateToRegister();

		void navigateToDashboard();
	}

	interface Presenter extends BaseContract.Presenter{
		void userSelectRegister();

		void userSelectLogin( String Username, String password );
	}
}
