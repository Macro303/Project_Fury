package tobedevelopers.project_fury.register;

import tobedevelopers.project_fury.BaseContract;

/**
 * Created by Macro303 on 10/08/2016.
 */
public interface RegisterContract{

	interface View extends BaseContract.View{
		void enableCreateAccountButton();

		void disableCreateAccountButton();

		void registrationInProgress();

		void registrationFinished();

		void setUsernameUnderValidation();

		void setUsernameOverCharValidation();

		void setUsernameAlreadyUsedValidation();

		void setEmailValidation();

		void setPasswordUnderValidation();

		void setPasswordOverValidation();

		void setConfirmPasswordValidation();
	}

	interface Navigation extends BaseContract.Navigation{
		void navigateToLogin();
	}

	interface Presenter extends BaseContract.Presenter{
		void userSelectCreateAccount();

		void userSelectLogin();

		void userEnterUsername( String username );

		void userEnterEmail( String email );

		void userEnterPassword( String password );

		void userEnterConfirmPassword( String confirmPassword, String password );
	}
}
