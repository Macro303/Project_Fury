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
public class RegisterPresenter implements RegisterContract.Presenter {

    private WeakReference<RegisterContract.View> viewWeakReference;
    private WeakReference<RegisterContract.Navigation> navigationWeakReference;
    private ModelContract modelContract;

    private String mUsername;
    private String mEmail;
    private String mPassword;

    public RegisterPresenter(RegisterContract.View view, RegisterContract.Navigation navigation) {
        this.viewWeakReference = new WeakReference<>(view);
        this.navigationWeakReference = new WeakReference<>(navigation);
        this.modelContract = new Model();

    }

    @Override
    public void userSelectCreateAccount() {
        RegisterContract.View view = viewWeakReference.get();
        RegisterContract.Navigation navigation = navigationWeakReference.get();

        if( view != null && navigation != null ){
            new AsyncTask< String, Void, Response >(){

                @Override
                protected void onPreExecute(){
                    RegisterContract.View view = viewWeakReference.get();

                    if( view != null ){
                        view.registrationInProgress();
                    }
                }

                @Override
                protected Response doInBackground( String... strings ){
                    return modelContract.createUser( mUsername, mPassword, mEmail );
                }

                @Override
                protected void onPostExecute( Response response ){
                    RegisterContract.View view = viewWeakReference.get();
                    RegisterContract.Navigation navigation = navigationWeakReference.get();

                    if( view != null ){
                        if( response.getError().equals( "Passed" ) ){
                            navigation.navigateToLogin();
                        }else if( response.getError().equals( "No Internet Access" ) ){
                            view.noInternetAccessValidation();
                        }else{
                            view.setUsernameAlreadyUsedValidation();
                        }
                    }
                }
            }.executeOnExecutor( AsyncTask.THREAD_POOL_EXECUTOR );
        }
    }

    @Override
    public void userSelectLogin() {
        RegisterContract.View view = viewWeakReference.get();
        RegisterContract.Navigation navigation = navigationWeakReference.get();

        if (view != null && navigation != null)
            navigation.navigateToLogin();
    }

    @Override
    public void userEnterUsername(String username) {
        RegisterContract.View view = viewWeakReference.get();

        if (view != null) {
            if (username.length() >= 6 && username.length() < 20) {
                mUsername = username;
                view.disableCreateAccountButton();
            } else {
                if (username.length() < 6) {
                    view.setUsernameValidation();
                    view.disableCreateAccountButton();
                } else if (username.length() > 19) {
                    view.setUsernameOver20CharValidation();
                    view.disableCreateAccountButton();
                }
            }
        }
    }

    @Override
    public void userEnterEmail(String email) {
        RegisterContract.View view = viewWeakReference.get();
        String mEmailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (view != null) {
            if (email.matches(mEmailPattern) && email.length() > 0) {
                mEmail = email;
                view.disableCreateAccountButton();
            } else {
                view.setEmailValidation();
                view.disableCreateAccountButton();
            }
        }
    }

    @Override
    public void userEnterPassword(String password) {
        RegisterContract.View view = viewWeakReference.get();

        if (view != null) {
            if (password.length() >= 6 && password.length() < 20) {
                mPassword = password;
                view.disableCreateAccountButton();
            } else {
                if (password.length() < 6) {
                    view.setPasswordValidation();
                    view.disableCreateAccountButton();
                } else if (password.length() > 19) {
                    view.setPasswordOver20CharValidation();
                    view.disableCreateAccountButton();
                }
            }
        }
    }

    @Override
    public void userEnterConfirmPassword(String confirmPassword, String password) {
        RegisterContract.View view = viewWeakReference.get();

        if (view != null) {
            if (confirmPassword.equals(password)) {
                view.enableCreateAccountButton();
            } else {
                view.setConfirmPasswordValidation();
                view.disableCreateAccountButton();
            }
        }
    }
}
