package tobedevelopers.project_fury.register.implementation;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
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
	TextInputEditText mUserNameEditText;
	@Bind( R.id.registerActivity_emailEditText )
	TextInputEditText mEmailEditText;
	@Bind( R.id.registerActivity_passwordEditText )
	TextInputEditText mPasswordEditText;
	@Bind( R.id.registerActivity_confirmPasswordEditText )
	TextInputEditText mConfirmPasswordEditText;

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
				Toast.makeText(getParam1(), getString(R.string.error_registrationInProgress), Toast.LENGTH_SHORT).show();
			}
		} );
	}

	@Override
	public void setUsernameValidation(){
		runOnUiThread(new Runnable1Param<TextInputEditText>(mUserNameEditText) {
			@Override
			public void run(){
				getParam1().setError(getString(R.string.error_usernameMinCharacter));
			}
		} );
	}

	@Override
	public void setUsernameOver20CharValidation() {
		runOnUiThread(new Runnable1Param<TextInputEditText>(mUserNameEditText) {
			@Override
			public void run() {
				getParam1().setError(getString(R.string.error_usernameMaxCharacter));
			}
		});
	}

	@Override
	public void setUsernameAlreadyUsedValidation(){
		runOnUiThread(new Runnable1Param<TextInputEditText>(mUserNameEditText) {
			@Override
			public void run(){
				getParam1().setError(getString(R.string.error_usernameAlreadyAUser));
				mPasswordEditText.getEditableText().clear();
				mConfirmPasswordEditText.getEditableText().clear();
			}
		} );
	}

	@Override
	public void setEmailValidation(){
		runOnUiThread(new Runnable1Param<TextInputEditText>(mEmailEditText) {
			@Override
			public void run(){
				getParam1().setError(getString(R.string.error_emailValidation));
			}
		} );
	}

	@Override
	public void setPasswordValidation(){
		runOnUiThread(new Runnable1Param<TextInputEditText>(mPasswordEditText) {
			@Override
			public void run(){
				getParam1().setError(getString(R.string.error_passwordMinCharacter));
			}
		} );
	}

	@Override
	public void setPasswordOver20CharValidation() {
		runOnUiThread(new Runnable1Param<TextInputEditText>(mPasswordEditText) {
			@Override
			public void run() {
				getParam1().setError(getString(R.string.error_passwordMaxCharacter));
			}
		});
	}

	@Override
	public void setConfirmPasswordValidation() {
		runOnUiThread(new Runnable1Param<TextInputEditText>(mConfirmPasswordEditText) {
			@Override
			public void run() {
				getParam1().setError(getString(R.string.error_confirmPasswordValidation));
			}
		});
	}

	@Override
	public void noInternetAccessValidation(){
		Toast.makeText(this, getString(R.string.error_noInternetAccess), Toast.LENGTH_LONG).show();
	}
}
