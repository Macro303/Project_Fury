package tobedevelopers.project_fury.register.implementation;

import android.os.AsyncTask;

import java.lang.ref.WeakReference;

import tobedevelopers.project_fury.model.Model;
import tobedevelopers.project_fury.model.ModelContract;
import tobedevelopers.project_fury.model.Response;
import tobedevelopers.project_fury.register.RegisterContract;

/**
 * Created by Macro303 on 10/08/2016.
 */
public class RegisterPresenter implements RegisterContract.Presenter{

	private WeakReference< RegisterContract.View > viewWeakReference;
	private WeakReference< RegisterContract.Navigation > navigationWeakReference;
	private ModelContract model;

	private String mUsername;
	private String mEmail;
	private String mPassword;

	public RegisterPresenter( RegisterContract.View view, RegisterContract.Navigation navigation ){
		this.viewWeakReference = new WeakReference<>( view );
		this.navigationWeakReference = new WeakReference<>( navigation );
		this.model = new Model();

	}

	@Override
	public void userSelectCreateAccount(){
		RegisterContract.View view = viewWeakReference.get();
		RegisterContract.Navigation navigation = navigationWeakReference.get();

		if( view != null && navigation != null )
			new CreateAccountTask().execute( mUsername, mPassword, mEmail );
	}

	@Override
	public void userSelectLogin(){
		RegisterContract.Navigation navigation = navigationWeakReference.get();

		if( navigation != null )
			navigation.navigateToLogin();
	}

	@Override
	public void userEnterUsername( String username ){
		RegisterContract.View view = viewWeakReference.get();

		if( view != null ){
			mUsername = username;
			if( username.length() < 6 )
				view.setUsernameUnderValidation();
			else if( username.length() >= 20 )
				view.setUsernameOverCharValidation();
			view.disableCreateAccountButton();
		}
	}

	@Override
	public void userEnterEmail( String email ){
		RegisterContract.View view = viewWeakReference.get();
		String mEmailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

		if( view != null ){
			mEmail = email;
			if( !email.matches( mEmailPattern ) || email.length() <= 0 )
				view.setEmailValidation();
			view.disableCreateAccountButton();
		}
	}

	@Override
	public void userEnterPassword( String password ){
		RegisterContract.View view = viewWeakReference.get();

		if( view != null ){
			mPassword = password;
			if( password.length() < 6 )
				view.setPasswordUnderValidation();
			else if( password.length() >= 20 )
				view.setPasswordOverValidation();
			view.disableCreateAccountButton();
		}
	}

	@Override
	public void userEnterConfirmPassword( String confirmPassword, String password ){
		RegisterContract.View view = viewWeakReference.get();

		if( view != null ){
			if( !confirmPassword.equals( password ) ){
				view.setConfirmPasswordValidation();
				view.disableCreateAccountButton();
			}else if( mUsername != null && mEmail != null ){
				if( ( mUsername.length() >= 6 && mUsername.length() <= 20 ) && mEmail.length() > 0 && ( mPassword.length() >= 6 && mPassword.length() <= 20 ) )
					view.enableCreateAccountButton();
			}else
				view.disableCreateAccountButton();
		}
	}

	private class CreateAccountTask extends AsyncTask< String, Void, Response >{

		@Override
		protected void onPreExecute(){
			super.onPreExecute();
			RegisterContract.View view = viewWeakReference.get();

			if( view != null )
				view.registrationInProgress();
		}

		@Override
		protected Response doInBackground( String... inputs ){
			return model.registerUser( inputs[ 0 ], inputs[ 1 ], inputs[ 2 ], false );
		}

		@Override
		protected void onPostExecute( Response result ){
			super.onPostExecute( result );
			RegisterContract.View view = viewWeakReference.get();
			RegisterContract.Navigation navigation = navigationWeakReference.get();

			if( view != null && navigation != null ){
				view.registrationFinished();

				switch( result.getMessage() ){
					case "Registration Successful.":
						navigation.navigateToLogin();
						break;
					case "No Internet Access":
						view.noInternetAccessValidation();
						break;
					case "400 Error":
						view.setUsernameAlreadyUsedValidation();
						break;
					default:
						view.defaultErrorMessage();
						break;
				}
			}
		}
	}
}
