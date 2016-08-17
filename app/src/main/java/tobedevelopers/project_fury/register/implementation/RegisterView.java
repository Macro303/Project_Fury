package tobedevelopers.project_fury.register.implementation;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
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
	private Drawable warning;

	@Override
	protected void onCreate( Bundle savedInstanceState ){
		setTitle( getString( R.string.title_activity_register ) );
		setContentView( R.layout.activity_register );
		super.onCreate( savedInstanceState );

		presenter = new RegisterPresenter( this, this );

		ButterKnife.bind( this );
	}

	//Buttons Listeners
	@OnClick( { R.id.registerActivity_createAccountButton, R.id.registerActivity_returnToLoginButton, R.id.textView } )
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
			case R.id.textView:
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
	}

	@OnTextChanged( value = { R.id.registerActivity_confirmPasswordEditText }, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED )
	public void onUserChangeConfirmPasswordEditText( Editable editable ){
		presenter.userEnterConfirmPassword( editable.toString() );
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
			}
		} );
	}

	@Override
	public void enableEmailEditText(){
		runOnUiThread( new Runnable1Param< RegisterView >( this ){
			@Override
			public void run(){
				getParam1().mEmailEditText.setEnabled( true );
			}
		} );
	}

	@Override
	public void enablePasswordEditText(){
		runOnUiThread( new Runnable1Param< RegisterView >( this ){
			@Override
			public void run(){
				getParam1().mPasswordEditText.setEnabled( true );
			}
		} );
	}

	@Override
	public void enableConfirmPasswordEditText(){
		runOnUiThread( new Runnable1Param< RegisterView >( this ){
			@Override
			public void run(){
				getParam1().mConfirmPasswordEditText.setEnabled( true );
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
	public void disableEmailEditText(){
		runOnUiThread( new Runnable1Param< RegisterView >( this ){
			@Override
			public void run(){
				getParam1().mEmailEditText.setEnabled( false );
			}
		} );
	}

	@Override
	public void disablePasswordEditText(){
		runOnUiThread( new Runnable1Param< RegisterView >( this ){
			@Override
			public void run(){
				getParam1().mPasswordEditText.setEnabled( false );
			}
		} );
	}

	@Override
	public void disableConfirmPassword(){
		runOnUiThread( new Runnable1Param< RegisterView >( this ){
			@Override
			public void run(){
				getParam1().mConfirmPasswordEditText.setEnabled( false );
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
//
//	@Override
//	public void setConfirmPasswordValidation(){
//		runOnUiThread( new Runnable1Param<EditText>(mConfirmPasswordEditText){
//			@Override
//			public void run(){
//				getParam1().setError( "Pleas" );
//			}
//		} );
//	}
}
