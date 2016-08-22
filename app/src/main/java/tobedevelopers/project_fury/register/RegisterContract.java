package tobedevelopers.project_fury.register;

/**
 * Created by Macro303 on 10/08/2016.
 */
public interface RegisterContract{

	interface View{
		void enableCreateAccountButton();

		void disableCreateAccountButton();

		void registrationInProgress();

		void setUsernameValidation();

		void setUsernameOver20CharValidation();

		void setUsernameAlreadyUsedValidation();

		void setEmailValidation();

		void setPasswordValidation();

		void setPasswordOver20CharValidation();

		void setConfirmPasswordValidation();
	}

	interface Navigation{
		void navigateToLogin();
	}

	interface Presenter{
		void userSelectCreateAccount();

		void userSelectLogin();

		void userEnterUsername( String username );

		void userEnterEmail( String email );

		void userEnterPassword( String password );

		void userEnterConfirmPassword( String confirmPassword, String password );
	}
}
