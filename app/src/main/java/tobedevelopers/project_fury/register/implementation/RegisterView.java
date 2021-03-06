package tobedevelopers.project_fury.register.implementation;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.text.Editable;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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
import tobedevelopers.project_fury.register.RegisterContract;
import tobedevelopers.project_fury.runnable_param.Runnable1Param;

public class RegisterView extends BaseView implements RegisterContract.View, RegisterContract.Navigation{

	@Bind( R.id.registerActivity_loadingProgressBar )
	protected ProgressBar mLoadingProgressbar;
	@Bind( R.id.registerActivity_createAccountButton )
	protected Button mCreateAccountButton;
	@Bind( R.id.registerActivity_returnToLoginButton )
	protected Button mReturnToLoginButton;
	@Bind( R.id.registerActivity_usernameEditText )
	protected TextInputEditText mUsernameEditText;
	@Bind( R.id.registerActivity_emailEditText )
	protected TextInputEditText mEmailEditText;
	@Bind( R.id.registerActivity_passwordEditText )
	protected TextInputEditText mPasswordEditText;
	@Bind( R.id.registerActivity_confirmPasswordEditText )
	protected TextInputEditText mConfirmPasswordEditText;

	private RegisterContract.Presenter presenter;

	@Override
	protected void onCreate( Bundle savedInstanceState ){
		setTitle( getString( R.string.title_activity_register ) );
		setContentView( R.layout.activity_register );
		super.onCreate( savedInstanceState );

		presenter = new RegisterPresenter( this, this );

		ButterKnife.bind( this );
	}

	//Buttons Listeners
	@OnClick( { R.id.registerActivity_createAccountButton, R.id.registerActivity_returnToLoginButton } )
	public void onUserSelectAButton( View view ){
		switch( view.getId() ){
			case R.id.registerActivity_createAccountButton:
				mCreateAccountButton.setEnabled( false );
				mReturnToLoginButton.setEnabled( false );
				presenter.userSelectCreateAccount();
				break;
			case R.id.registerActivity_returnToLoginButton:
				presenter.userSelectLogin();
				break;
			default:
				ToastLog.makeError( this, getString( R.string.app_name ), String.format( getString( R.string.error_message ), getTitle() ), Toast.LENGTH_SHORT );
				break;
		}
	}

	//Text Change Listeners
	@OnTextChanged( value = { R.id.registerActivity_usernameEditText }, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED )
	public void onUserChangedUserNameEditText( Editable editable ){
		mPasswordEditText.getEditableText().clear();
		mConfirmPasswordEditText.getEditableText().clear();
		presenter.userEnterUsername( editable.toString() );
	}

	@OnTextChanged( value = { R.id.registerActivity_emailEditText }, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED )
	public void onUserChangedEmailEditText( Editable editable ){
		mPasswordEditText.getEditableText().clear();
		mConfirmPasswordEditText.getEditableText().clear();
		presenter.userEnterEmail( editable.toString() );
	}

	@OnTextChanged( value = { R.id.registerActivity_passwordEditText }, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED )
	public void onUserChangedPasswordEditText( Editable editable ){
		mConfirmPasswordEditText.getEditableText().clear();
		presenter.userEnterPassword( editable.toString() );
	}

	@OnTextChanged( value = { R.id.registerActivity_confirmPasswordEditText }, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED )
	public void onUserChangeConfirmPasswordEditText( Editable editable ){
		presenter.userEnterConfirmPassword( editable.toString(), mPasswordEditText.getEditableText().toString() );
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
				mLoadingProgressbar.setVisibility( View.VISIBLE );
			}
		} );
	}

	@Override
	public void registrationFinished(){
		runOnUiThread( new Runnable1Param< RegisterView >( this ){
			@Override
			public void run(){
				mLoadingProgressbar.setVisibility( View.GONE );
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
	public void setUsernameOverCharValidation(){
		runOnUiThread( new Runnable1Param< TextInputEditText >( mUsernameEditText ){
			@Override
			public void run(){
				getParam1().setError( String.format( getResources().getQuantityString( R.plurals.error_maxCharacters, 20 ), 20 ) );
			}
		} );
	}

	@Override
	public void setUsernameAlreadyUsedValidation(){
		mReturnToLoginButton.setEnabled( true );
		runOnUiThread( new Runnable1Param< TextInputEditText >( mUsernameEditText ){
			@Override
			public void run(){
				mLoadingProgressbar.setVisibility( View.GONE );
				getParam1().setError( String.format( getString( R.string.error_alreadyExists ), "Username" ) );
				mUsernameEditText.requestFocus();
				mPasswordEditText.getEditableText().clear();
				mConfirmPasswordEditText.getEditableText().clear();
			}
		} );
	}

	@Override
	public void setEmailValidation(){
		runOnUiThread( new Runnable1Param< TextInputEditText >( mEmailEditText ){
			@Override
			public void run(){
				getParam1().setError( getString( R.string.error_emailValidation ) );
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
	public void setConfirmPasswordValidation(){
		runOnUiThread( new Runnable1Param< TextInputEditText >( mConfirmPasswordEditText ){
			@Override
			public void run(){
				getParam1().setError( getString( R.string.error_confirmPasswordValidation ) );
			}
		} );
	}

	@Override
	public void defaultErrorMessage(){
		super.defaultErrorMessage();
		mReturnToLoginButton.setEnabled( true );
	}

	@Override
	public void noInternetAccessValidation(){
		super.noInternetAccessValidation();
		mReturnToLoginButton.setEnabled( true );
	}
}
