package tobedevelopers.project_fury.login.implementation;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
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
				presenter.userSelectLogin();
				ToastLog.makeDebug( this, "Login", Toast.LENGTH_SHORT );
				break;
			case R.id.loginActivity_registerButton:
				presenter.userSelectRegister();
				ToastLog.makeDebug( this, "Register", Toast.LENGTH_SHORT );
				break;
			default:
				ToastLog.makeError( this, String.format( getString( R.string.error_message ), getTitle() ), Toast.LENGTH_SHORT );
				break;
		}
	}

	//Text Change Listeners
	@OnTextChanged( value = { R.id.loginActivity_usernameEditText }, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED )
	public void onUserChangedUsernameEditText( Editable editable ){
		mPasswordEditText.getEditableText().clear();
		presenter.userEnterUsername( editable.toString() );
	}

	@OnTextChanged( value = R.id.loginActivity_passwordEditText, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED )
	public void onUserChangedPasswordEditText( Editable editable ){
		presenter.userEnterPassword( editable.toString() );
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
				ToastLog.makeDebug( getParam1(), String.format( getString( R.string.error_inProgress ), "Login" ), Toast.LENGTH_LONG );
			}
		} );
	}

	@Override
	public void setUsernameUnderValidation(){
		runOnUiThread( new Runnable1Param< TextInputEditText >( mUsernameEditText ){
			@Override
			public void run(){
				getParam1().setError( String.format( getResources().getQuantityString( R.plurals.error_minCharacters, 6 ), 6 ) );
			}
		} );
	}

	@Override
	public void setUsernameOverValidation(){
		runOnUiThread( new Runnable1Param< TextInputEditText >( mUsernameEditText ){
			@Override
			public void run(){
				getParam1().setError( String.format( getResources().getQuantityString( R.plurals.error_maxCharacters, 20 ), 20 ) );
			}
		} );
	}

	@Override
	public void setPasswordUnderValidation(){
		runOnUiThread( new Runnable1Param< TextInputEditText >( mPasswordEditText ){
			@Override
			public void run(){
				getParam1().setError( String.format( getResources().getQuantityString( R.plurals.error_minCharacters, 6 ), 6 ) );
			}
		} );
	}

	@Override
	public void setPasswordOverValidation(){
		runOnUiThread( new Runnable1Param< TextInputEditText >( mPasswordEditText ){
			@Override
			public void run(){
				getParam1().setError( String.format( getResources().getQuantityString( R.plurals.error_maxCharacters, 20 ), 20 ) );
			}
		} );
	}

	@Override
	public void noInternetAccessValidation(){
		ToastLog.makeWarn( this, getString( R.string.error_noInternetAccess ), Toast.LENGTH_LONG );
	}

	@Override
	public void setInvalidUserValidation(){
		runOnUiThread( new Runnable2Param< TextInputEditText, TextInputEditText >( mUsernameEditText, mPasswordEditText ){
			@Override
			public void run(){
				getParam1().setError( getString( R.string.error_invalidUsernamePassword ) );
				getParam2().setError( getString( R.string.error_invalidUsernamePassword ) );
			}
		} );
	}
}
