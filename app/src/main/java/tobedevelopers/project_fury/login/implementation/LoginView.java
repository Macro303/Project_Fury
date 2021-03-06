package tobedevelopers.project_fury.login.implementation;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import tobedevelopers.project_fury.BaseView;
import tobedevelopers.project_fury.R;
import tobedevelopers.project_fury.ToastLog;
import tobedevelopers.project_fury.dashboard.implementation.DashboardView;
import tobedevelopers.project_fury.login.LoginContract;
import tobedevelopers.project_fury.register.implementation.RegisterView;
import tobedevelopers.project_fury.runnable_param.Runnable1Param;
import tobedevelopers.project_fury.runnable_param.Runnable2Param;

public class LoginView extends BaseView implements LoginContract.View, LoginContract.Navigation{

	@Bind( R.id.loginActivity_usernameEditText )
	protected TextInputEditText mUsernameEditText;
	@Bind( R.id.loginActivity_passwordEditText )
	protected TextInputEditText mPasswordEditText;
	@Bind( R.id.loginActivity_loginButton )
	protected Button mLoginButton;
	@Bind( R.id.loginActivity_registerButton )
	protected Button mRegisterButton;
	@Bind( R.id.loginActivity_loadingProgressBar )
	protected ProgressBar mLoadingProgressbar;

	private LoginContract.Presenter presenter;

	@Override
	protected void onCreate( Bundle savedInstanceState ){
		setTitle( getString( R.string.title_activity_login ) );
		setContentView( R.layout.activity_login );
		super.onCreate( savedInstanceState );

		presenter = new LoginPresenter( this, this );

		ButterKnife.bind( this );
	}

	//Buttons Listener
	@OnClick( { R.id.loginActivity_loginButton, R.id.loginActivity_registerButton } )
	public void onUserSelectAButton( View view ){
		switch( view.getId() ){
			case R.id.loginActivity_loginButton:
				mRegisterButton.setEnabled( false );
				mLoginButton.setEnabled( false );
				presenter.userSelectLogin( mUsernameEditText.getEditableText().toString(), mPasswordEditText.getEditableText().toString() );
				break;
			case R.id.loginActivity_registerButton:
				mRegisterButton.setEnabled( false );
				mLoginButton.setEnabled( false );
				presenter.userSelectRegister();
				break;
			default:
				ToastLog.makeError( this, getString( R.string.app_name ), String.format( getString( R.string.error_message ), getTitle() ), Toast.LENGTH_SHORT );
				break;
		}
	}

	@Override
	public void navigateToRegister(){
		runOnUiThread( new Runnable1Param< LoginView >( this ){
			@Override
			public void run(){
				mLoadingProgressbar.setVisibility( View.GONE );
				startActivity( new Intent( getParam1(), RegisterView.class ) );
			}
		} );
	}

	@Override
	public void navigateToDashboard(){
		runOnUiThread( new Runnable1Param< LoginView >( this ){
			@Override
			public void run(){
				mLoadingProgressbar.setVisibility( View.GONE );
				finish();
				startActivity( new Intent( getParam1(), DashboardView.class ) );
			}
		} );
	}

	@Override
	public void enableLoginButton(){
		runOnUiThread( new Runnable1Param< LoginView >( this ){
			@Override
			public void run(){
				getParam1().mLoginButton.setEnabled( true );
			}
		} );
	}

	@Override
	public void disableLoginButton(){
		runOnUiThread( new Runnable1Param< LoginView >( this ){
			@Override
			public void run(){
				getParam1().mLoginButton.setEnabled( false );
			}
		} );
	}

	@Override
	public void loginInProgress(){
		runOnUiThread( new Runnable1Param< LoginView >( this ){
			@Override
			public void run(){
				mLoadingProgressbar.setVisibility( View.VISIBLE );
			}
		} );
	}

	@Override
	public void logInFinished(){
		runOnUiThread( new Runnable1Param< LoginView >( this ){
			@Override
			public void run(){
				mLoadingProgressbar.setVisibility( View.GONE );
			}
		} );
	}

	@Override
	public void noInternetAccessValidation(){
		super.noInternetAccessValidation();
		mLoginButton.setEnabled( true );
		mRegisterButton.setEnabled( true );
	}

	@Override
	public void setInvalidUserValidation(){
		mRegisterButton.setEnabled( true );
		mLoginButton.setEnabled( true );
		runOnUiThread( new Runnable2Param< TextInputEditText, TextInputEditText >( mUsernameEditText, mPasswordEditText ){
			@Override
			public void run(){
				getParam1().setError( getString( R.string.error_invalidUsernamePassword ) );
				getParam2().setError( getString( R.string.error_invalidUsernamePassword ) );
			}
		} );
	}
}
