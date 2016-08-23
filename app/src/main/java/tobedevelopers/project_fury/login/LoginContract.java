package tobedevelopers.project_fury.login;

/**
 * Created by A on 8/9/2016.
 */
public interface LoginContract{

	interface View{
		void enableLoginButton();

		void disableLoginButton();

		void loginInProgress();

		void usernameUnderValidation();

		void usernameOverValidation();

		void passwordUnderValidation();

		void passwordOverValidation();

		void internetAccessValidation();

		void userValidation();
	}

	interface Navigation{
		void navigateToRegister();

		void navigateToDashboard();
	}

	interface Presenter{
		void userSelectRegister();

		void userSelectLogin();

		void userEnterUsername( String username );

		void userEnterPassword( String password );
	}
}
