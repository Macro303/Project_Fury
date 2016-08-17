package tobedevelopers.project_fury.register.implementation;

import java.lang.ref.WeakReference;

import tobedevelopers.project_fury.register.RegisterContract;

/**
 * Created by Macro303 on 10/08/2016.
 */
public class RegisterPresenter implements RegisterContract.Presenter{

	private WeakReference< RegisterContract.View > viewWeakReference;
	private WeakReference< RegisterContract.Navigation > navigationWeakReference;

	private String mPassword;

	public RegisterPresenter( RegisterContract.View view, RegisterContract.Navigation navigation ){
		this.viewWeakReference = new WeakReference<>( view );
		this.navigationWeakReference = new WeakReference<>( navigation );
	}

	@Override
	public void userSelectCreateAccount(){
		RegisterContract.View view = viewWeakReference.get();
		RegisterContract.Navigation navigation = navigationWeakReference.get();

		if( view != null && navigation != null )
			navigation.navigateToLogin();
	}

	@Override
	public void userSelectLogin(){
		RegisterContract.View view = viewWeakReference.get();
		RegisterContract.Navigation navigation = navigationWeakReference.get();

		if( view != null && navigation != null )
			navigation.navigateToLogin();
	}

	@Override
	public void userEnterUsername( String username ){
		RegisterContract.View view = viewWeakReference.get();

		if( view != null ){
			if( username.length() >= 6 && username.length() < 20 ){
				view.enableEmailEditText();
			}else{
				if( username.length() < 6 ){
					view.setUsernameValidation();
					view.disableEmailEditText();
					view.disablePasswordEditText();
					view.disableConfirmPassword();
					view.disableCreateAccountButton();
				}
			}
		}
	}

	@Override
	public void userEnterEmail( String email ){
		RegisterContract.View view = viewWeakReference.get();
		String mEmailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
		if( view != null ){
			if( email.matches( mEmailPattern ) && email.length() > 0 ){
				view.enablePasswordEditText();
			}else{
				view.setEmailValidation();
				view.disablePasswordEditText();
				view.disableConfirmPassword();
				view.disableCreateAccountButton();
			}
		}
	}

	@Override
	public void userEnterPassword( String password ){
		RegisterContract.View view = viewWeakReference.get();

		if( view != null ){
			if( password.length() >= 6 && password.length() < 20 ){
				view.enableConfirmPasswordEditText();
				mPassword = password;
			}else{
				if( password.length() < 6 ){
					view.setPasswordValidation();
					view.disableConfirmPassword();
					view.disableCreateAccountButton();
				}
			}
		}
	}

	@Override
	public void userEnterConfirmPassword( String confirmPassword ){
		RegisterContract.View view = viewWeakReference.get();

		if( view != null ){
			if( confirmPassword.equals( mPassword ) ){
				view.enableCreateAccountButton();
			}else{
//				view.setConfirmPasswordValidation();
				view.disableCreateAccountButton();
			}
		}
	}
}
