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

	private String username;
	private String password;

	public LoginPresenter( LoginContract.View view, LoginContract.Navigation navigation ){
		this.viewWeakReference = new WeakReference<>( view );
		this.navigationWeakReference = new WeakReference<>( navigation );
		this.model = new Model();
	}

	@Override
	public void userSelectRegister(){
		LoginContract.View view = viewWeakReference.get();
		LoginContract.Navigation navigation = navigationWeakReference.get();

		if( view != null && navigation != null ){
			navigation.navigateToRegister();
		}
	}

	@Override
	public void userSelectLogin(){
		LoginContract.View view = viewWeakReference.get();
		LoginContract.Navigation navigation = navigationWeakReference.get();

		if( view != null && navigation != null ){
			new AsyncTask< String, Void, Response >(){

				@Override
				protected void onPreExecute(){
					LoginContract.View view = viewWeakReference.get();

					if( view != null ){
						view.loginInProgress();
					}
				}

				@Override
				protected Response doInBackground( String... strings ){
					return model.getUser( username );
				}

				@Override
				protected void onPostExecute( Response response ){
					LoginContract.View view = viewWeakReference.get();
					LoginContract.Navigation navigation = navigationWeakReference.get();

					if( view != null ){
						if( response.getError().equals( "Passed" ) ){
							navigation.navigateToDashboard();
						}else if( response.getError().equals( "No Internet Access" ) ){
							view.internetAccessValidation();
						}else{
							view.userValidation();
						}
					}
				}
			}.executeOnExecutor( AsyncTask.THREAD_POOL_EXECUTOR );
		}
	}

	@Override
	public void userEnterUsername( String username ){
		LoginContract.View view = viewWeakReference.get();

		if( view != null ){
			if( username.length() >= 6 && username.length() < 20 ){
				this.username = username;
				if( password != null && !password.isEmpty() )
					view.enableLoginButton();
				else
					view.disableLoginButton();
			}else{
				if( username.length() < 6 ){
					view.usernameUnderValidation();
					view.disableLoginButton();
				}else if( username.length() > 19 ){
					view.usernameOverValidation();
					view.disableLoginButton();
				}
			}
		}
	}

	@Override
	public void userEnterPassword( String password ){
		LoginContract.View view = viewWeakReference.get();

		if( view != null ){
			if( password.length() >= 6 && password.length() < 20 ){
				this.password = password;
				if( username != null && !username.isEmpty() )
					view.enableLoginButton();
				else
					view.disableLoginButton();
			}else{
				if( password.length() < 6 ){
					view.passwordUnderValidation();
					view.disableLoginButton();
				}else if( password.length() > 19 ){
					view.passwordOverValidation();
					view.disableLoginButton();
				}
			}
		}
	}
}
