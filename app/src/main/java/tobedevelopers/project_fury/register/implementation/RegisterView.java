package tobedevelopers.project_fury.register.implementation;

import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import tobedevelopers.project_fury.BaseView;
import tobedevelopers.project_fury.R;
import tobedevelopers.project_fury.Runnable1Param;
import tobedevelopers.project_fury.register.RegisterContract;

public class RegisterView extends BaseView implements RegisterContract.View, RegisterContract.Navigation{

	@Bind( R.id.registerActivity_createAccountButton )
	Button mCreateAccountButton;
	@Bind( R.id.registerActivity_usernameEditText )
	EditText mUserNameEditText;
	@Bind( R.id.registerActivity_emailEditText )
	EditText mEmailEditText;
	@Bind( R.id.registerActivity_passwordEditText )
	EditText mPasswordEditText;
	@Bind( R.id.registerActivity_confirmPasswordEditText )
	EditText mConfirmPasswordEditText;

	private RegisterContract.Presenter presenter;
	private String mPassword;

	@Override
	protected void onCreate( Bundle savedInstanceState ){
		setTitle( getString( R.string.title_activity_register ) );
		setContentView( R.layout.activity_register );
		super.onCreate( savedInstanceState );

		presenter = new RegisterPresenter( this, this );

		ButterKnife.bind( this );
	}

	//Buttons Listeners
	@OnClick({R.id.registerActivity_createAccountButton, R.id.registerActivity_returnToLoginButton})
	public void onUserSelectAButton( View view ){
		switch( view.getId() ){
			case R.id.registerActivity_createAccountButton:
				//Toast.makeText( this, "Create Account", Toast.LENGTH_SHORT ).show();
				presenter.userSelectCreateAccount();
				break;
			case R.id.registerActivity_returnToLoginButton:
				//Toast.makeText( this, "Login", Toast.LENGTH_SHORT ).show();
				presenter.userSelectLogin();
				break;
			default:
				Toast.makeText( this, String.format( getString( R.string.error_message ), getTitle() ), Toast.LENGTH_SHORT ).show();
				Log.w( getString( R.string.app_name ), String.format( getString( R.string.error_message ), getTitle() ) );
				break;
		}
	}

	//Text Change Listeners
	@OnTextChanged( value = { R.id.registerActivity_usernameEditText }, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED )
	public void onUserChangedUserNameEditText( Editable editable ){
		presenter.userEnterUsername( editable.toString() );
	}

	@OnTextChanged( value = { R.id.registerActivity_emailEditText }, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED )
	public void onUserChangedEmailEditText( Editable editable ){
		presenter.userEnterEmail( editable.toString() );
	}

	@OnTextChanged( value = { R.id.registerActivity_passwordEditText }, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED )
	public void onUserChangedPasswordEditText( Editable editable ){
		presenter.userEnterPassword( editable.toString() );
		mPassword = editable.toString();
	}

	@OnTextChanged( value = { R.id.registerActivity_confirmPasswordEditText }, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED )
	public void onUserChangeConfirmPasswordEditText( Editable editable ){
		presenter.userEnterConfirmPassword( editable.toString(), mPassword );
	}

	public void hideSoftKeyboard(){
		if( getCurrentFocus() != null ){
			InputMethodManager inputMethodManager = ( InputMethodManager ) getSystemService( INPUT_METHOD_SERVICE );
			inputMethodManager.hideSoftInputFromWindow( getCurrentFocus().getWindowToken(), 0 );
		}
	}

	@Override
	public void navigateToLogin(){
		finish();
	}

	@Override
	public void enableCreateAccountButton(){
		runOnUiThread( new Runnable1Param< RegisterView >( this ){
			@Override
			public void run(){
				getParam1().mCreateAccountButton.setEnabled( true );
				hideSoftKeyboard();
			}
		} );
	}

	@Override
	public void disableCreateAccountButton(){
		runOnUiThread( new Runnable1Param< RegisterView >( this ){
			@Override
			public void run(){
				getParam1().mCreateAccountButton.setEnabled( false );
			}
		} );
	}

	@Override
	public void registrationInProgress(){
		runOnUiThread( new Runnable1Param< RegisterView >( this ){
			@Override
			public void run(){
				Toast.makeText( getParam1(), "Registration in progress, please wait...", Toast.LENGTH_SHORT ).show();
			}
		} );
	}

	@Override
	public void setUsernameValidation(){
		runOnUiThread( new Runnable1Param< EditText >( mUserNameEditText ){
			@Override
			public void run(){
				getParam1().setError( "Minimum of 6 Characters" );
			}
		} );
	}

	@Override
	public void setUsernameOver20CharValidation() {
		runOnUiThread(new Runnable1Param<EditText>(mUserNameEditText) {
			@Override
			public void run() {
				getParam1().setError("Maximum of 20 Characters");
			}
		});
	}

	@Override
	public void setUsernameAlreadyUsedValidation(){
		runOnUiThread( new Runnable1Param< EditText >( mUserNameEditText ){
			@Override
			public void run(){
				getParam1().setError( "Username already in use" );
			}
		} );
	}

	@Override
	public void setEmailValidation(){
		runOnUiThread( new Runnable1Param< EditText >( mEmailEditText ){
			@Override
			public void run(){
				getParam1().setError( "Please enter a valid email" );
			}
		} );
	}

	@Override
	public void setPasswordValidation(){
		runOnUiThread( new Runnable1Param< EditText >( mPasswordEditText ){
			@Override
			public void run(){
				getParam1().setError( "Minimum of 6 Characters" );
			}
		} );
	}

	@Override
	public void setPasswordOver20CharValidation() {
		runOnUiThread(new Runnable1Param<EditText>(mPasswordEditText) {
			@Override
			public void run() {
				getParam1().setError("Maximum of 20 Characters");
			}
		});
	}

	@Override
	public void setConfirmPasswordValidation() {
		runOnUiThread(new Runnable1Param<EditText>(mConfirmPasswordEditText) {
			@Override
			public void run() {
				getParam1().setError("Password does not currently match");
			}
		});
	}

	@Override
	public void noInternetAccessValidation(){
		Toast.makeText( this, "No Internet Access", Toast.LENGTH_LONG ).show();
	}
}
