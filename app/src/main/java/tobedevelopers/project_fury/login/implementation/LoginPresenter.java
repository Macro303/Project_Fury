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

	public LoginPresenter( LoginContract.View view, LoginContract.Navigation navigation ){
		this.viewWeakReference = new WeakReference<>( view );
		this.navigationWeakReference = new WeakReference<>( navigation );
		this.model = new Model();
	}

	@Override
	public void userSelectRegister(){
		LoginContract.Navigation navigation = navigationWeakReference.get();

		if( navigation != null )
			navigation.navigateToRegister();
	}

	@Override
	public void userSelectLogin( String mUsername, String mPassword ){
		LoginContract.View view = viewWeakReference.get();
		LoginContract.Navigation navigation = navigationWeakReference.get();

		if( view != null && navigation != null )
			new LoginTask().execute( mUsername, mPassword );
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
		protected Response doInBackground( String... inputs ){
			return model.login( inputs[ 0 ], inputs[ 1 ] );
		}

		@Override
		protected void onPostExecute( Response result ){
			super.onPostExecute( result );
			LoginContract.View view = viewWeakReference.get();
			LoginContract.Navigation navigation = navigationWeakReference.get();

			if( view != null && navigation != null ){
				view.logInFinished();

				switch( result.getMessage() ){
					case "Success":
						navigation.navigateToDashboard();
						break;
					case "No Internet Access":
						view.noInternetAccessValidation();
						break;
					case "401 Error":
						view.setInvalidUserValidation();
						break;
					default:
						view.defaultErrorMessage();
						break;
				}
			}
		}
	}

}
