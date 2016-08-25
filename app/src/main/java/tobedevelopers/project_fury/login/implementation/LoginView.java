package tobedevelopers.project_fury.login.implementation;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import tobedevelopers.project_fury.BaseView;
import tobedevelopers.project_fury.R;
import tobedevelopers.project_fury.Runnable.Runnable1Param;
import tobedevelopers.project_fury.Runnable.Runnable2Param;
import tobedevelopers.project_fury.ToastLog;
import tobedevelopers.project_fury.dashboard.implementation.DashboardView;
import tobedevelopers.project_fury.login.LoginContract;
import tobedevelopers.project_fury.register.implementation.RegisterView;

public class LoginView extends BaseView implements LoginContract.View, LoginContract.Navigation{

	@Bind( R.id.loginActivity_usernameEditText )
	protected TextInputEditText mUsernameEditText;
	@Bind( R.id.loginActivity_passwordEditText )
	protected TextInputEditText mPasswordEditText;
	@Bind( R.id.loginActivity_loginButton )
	protected Button mLoginButton;
	@Bind( R.id.loginActivity_registerButton )
	protected Button mRegisterButton;

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
				ToastLog.makeDebug( this, "Login", Toast.LENGTH_SHORT ).show();
				break;
			case R.id.loginActivity_registerButton:
				presenter.userSelectRegister();
				ToastLog.makeDebug( this, "Register", Toast.LENGTH_SHORT ).show();
				break;
			default:
				ToastLog.makeError( this, String.format( getString( R.string.error_message ), getTitle() ), Toast.LENGTH_SHORT ).show();
				break;
		}
	}

	//Text Change Listeners
	@OnTextChanged( value = { R.id.loginActivity_usernameEditText }, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED )
	public void onUserChangedUsernameEditText( Editable editable ){
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
				startActivity( new Intent( getParam1(), RegisterView.class ) );
			}
		} );
	}

	@Override
	public void navigateToDashboard(){
		runOnUiThread( new Runnable1Param< LoginView >( this ){
			@Override
			public void run(){
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
				getParam1().mLoginButton.setEnabled( true );
			}
		} );
	}

	@Override
	public void loginInProgress(){
		runOnUiThread( new Runnable1Param< LoginView >( this ){
			@Override
			public void run(){
				Toast.makeText( getParam1(), "Login in progress, please wait...", Toast.LENGTH_LONG ).show();
			}
		} );
	}

	@Override
	public void usernameUnderValidation(){
		runOnUiThread( new Runnable1Param< TextInputEditText >( mUsernameEditText ){
			@Override
			public void run(){
				getParam1().setError( "Minimum of 6 Characters" );
			}
		} );
	}

	@Override
	public void usernameOverValidation(){
		runOnUiThread( new Runnable1Param< TextInputEditText >( mUsernameEditText ){
			@Override
			public void run(){
				getParam1().setError( "Maximum of 20 Characters" );
			}
		} );
	}

	@Override
	public void passwordUnderValidation(){
		runOnUiThread( new Runnable1Param< TextInputEditText >( mPasswordEditText ){
			@Override
			public void run(){
				getParam1().setError( "Minimum of 6 Characters" );
			}
		} );
	}

	@Override
	public void passwordOverValidation(){
		runOnUiThread( new Runnable1Param< TextInputEditText >( mPasswordEditText ){
			@Override
			public void run(){
				getParam1().setError( "Maximum of 20 Characters" );
			}
		} );
	}

	@Override
	public void internetAccessValidation(){
		ToastLog.makeWarn( this, "No Internet Access", Toast.LENGTH_LONG ).show();
	}

	@Override
	public void userValidation(){
		runOnUiThread( new Runnable2Param< TextInputEditText, TextInputEditText >( mUsernameEditText, mPasswordEditText ){
			@Override
			public void run(){
				getParam1().setError( "Invalid Username/Password" );
				getParam2().setError( "Invalid Username/Password" );
			}
		} );
	}
}
