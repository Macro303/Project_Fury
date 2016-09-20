package tobedevelopers.project_fury.login.implementation;

import android.os.AsyncTask;

import java.lang.ref.WeakReference;

import tobedevelopers.project_fury.login.LoginContract;
import tobedevelopers.project_fury.model.Model;
import tobedevelopers.project_fury.model.ModelContract;
import tobedevelopers.project_fury.model.Response;

/**
 * Created by A on 8/9/2016.
 */
public class LoginPresenter implements LoginContract.Presenter{

	private WeakReference< LoginContract.View > viewWeakReference;
	private WeakReference< LoginContract.Navigation > navigationWeakReference;
	private ModelContract model;

	private String mUsername;
	private String mPassword;

	public LoginPresenter( LoginContract.View view, LoginContract.Navigation navigation ){
		this.viewWeakReference = new WeakReference<>( view );
		this.navigationWeakReference = new WeakReference<>( navigation );
		this.model = new Model();
	}

	@Override
	public void userSelectRegister(){
		LoginContract.View view = viewWeakReference.get();
		LoginContract.Navigation navigation = navigationWeakReference.get();

		if( view != null && navigation != null )
			navigation.navigateToRegister();
	}

	@Override
	public void userSelectLogin(){
		LoginContract.View view = viewWeakReference.get();
		LoginContract.Navigation navigation = navigationWeakReference.get();

		if( view != null && navigation != null )
			new LoginTask().execute();
	}

	@Override
	public void userEnterUsername( String username ){
		LoginContract.View view = viewWeakReference.get();

		if( view != null ){
			mUsername = username;
			if( username.length() < 6 )
				view.setUsernameUnderValidation();
			else if( username.length() >= 20 )
				view.setUsernameOverValidation();
			view.disableLoginButton();
		}
	}

	@Override
	public void userEnterPassword( String password ){
		LoginContract.View view = viewWeakReference.get();

		if( view != null ){
			mPassword = password;
			if( password.length() < 6 ){
				view.setPasswordUnderValidation();
				view.disableLoginButton();
			}else if( password.length() > 20 )
				view.disableLoginButton();
			else if( mUsername.length() >= 6 && mUsername.length() <= 20 )
				view.enableLoginButton();
			else
				view.disableLoginButton();
			if( password.length() >= 20 )
				view.setPasswordOverValidation();

		}
	}

	private class LoginTask extends AsyncTask< String, Void, Response >{

		@Override
		protected void onPreExecute(){
			super.onPreExecute();
			LoginContract.View view = viewWeakReference.get();

			if( view != null )
				view.loginInProgress();
		}

		@Override
		protected Response doInBackground( String... strings ){
			return model.login( mUsername, mPassword );
		}

		@Override
		protected void onPostExecute( Response response ){
			LoginContract.View view = viewWeakReference.get();
			LoginContract.Navigation navigation = navigationWeakReference.get();

			if( view != null && navigation != null ){
				view.logInFinished();

				switch( response.getMessage() ){
					case "Success":
						navigation.navigateToDashboard();
						break;
					case "No Internet Access":
						view.noInternetAccessValidation();
						break;
					default:
						view.setInvalidUserValidation();
						break;
				}
			}
		}
	}

}
