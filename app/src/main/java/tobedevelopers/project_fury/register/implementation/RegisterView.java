package tobedevelopers.project_fury.register.implementation;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import tobedevelopers.project_fury.BaseView;
import tobedevelopers.project_fury.R;
import tobedevelopers.project_fury.Runnable1Param;
import tobedevelopers.project_fury.register.RegisterContract;

public class RegisterView extends BaseView implements RegisterContract.View, RegisterContract.Navigation{

	@Bind( R.id.registerActivity_createAccountButton )
	Button createAccountButton;
	private RegisterContract.Presenter presenter;
	private EditText usernameEditText;
	private EditText emailEditText;
	private EditText passwordEditText;
	private EditText confirmPasswordEditText;

	@Override
	protected void onCreate( Bundle savedInstanceState ){
		setTitle( getString( R.string.title_activity_register ) );
		setContentView( R.layout.activity_register );
		super.onCreate( savedInstanceState );

		presenter = new RegisterPresenter( this, this );

		ButterKnife.bind( this );

		usernameEditText = ( EditText ) findViewById( R.id.registerActivity_usernameEditText );
		usernameEditText.addTextChangedListener( new TextWatcher(){
			@Override
			public void beforeTextChanged( CharSequence charSequence, int i, int i1, int i2 ){

			}

			@Override
			public void onTextChanged( CharSequence charSequence, int i, int i1, int i2 ){

			}

			@Override
			public void afterTextChanged( Editable editable ){
				presenter.userEnterUsername( editable.toString() );
			}
		} );

		emailEditText = ( EditText ) findViewById( R.id.registerActivity_emailEditText );
		emailEditText.addTextChangedListener( new TextWatcher(){
			@Override
			public void beforeTextChanged( CharSequence charSequence, int i, int i1, int i2 ){

			}

			@Override
			public void onTextChanged( CharSequence charSequence, int i, int i1, int i2 ){

			}

			@Override
			public void afterTextChanged( Editable editable ){
				presenter.userEnterEmail( editable.toString() );
			}
		} );

		passwordEditText = ( EditText ) findViewById( R.id.registerActivity_passwordEditText );
		passwordEditText.addTextChangedListener( new TextWatcher(){
			@Override
			public void beforeTextChanged( CharSequence charSequence, int i, int i1, int i2 ){

			}

			@Override
			public void onTextChanged( CharSequence charSequence, int i, int i1, int i2 ){

			}

			@Override
			public void afterTextChanged( Editable editable ){
				presenter.userEnterPassword( editable.toString() );
			}
		} );

		confirmPasswordEditText = ( EditText ) findViewById( R.id.registerActivity_confirmPasswordEditText );
		confirmPasswordEditText.addTextChangedListener( new TextWatcher(){
			@Override
			public void beforeTextChanged( CharSequence charSequence, int i, int i1, int i2 ){

			}

			@Override
			public void onTextChanged( CharSequence charSequence, int i, int i1, int i2 ){

			}

			@Override
			public void afterTextChanged( Editable editable ){
				presenter.userEnterConfirmPassword( editable.toString() );
			}
		} );
	}

	//Button Listener
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

	@Override
	public void navigateToLogin(){
		finish();
	}

	@Override
	public void enableCreateAccountButton(){
		runOnUiThread( new Runnable1Param< RegisterView >( this ){
			@Override
			public void run(){
				getParam1().createAccountButton.setEnabled( true );
			}
		} );
	}

	@Override
	public void enableEmailEditText(){
		runOnUiThread( new Runnable1Param< RegisterView >( this ){
			@Override
			public void run(){
				getParam1().emailEditText.setEnabled( true );
			}
		} );
	}

	@Override
	public void enablePasswordEditText(){
		runOnUiThread( new Runnable1Param< RegisterView >( this ){
			@Override
			public void run(){
				getParam1().passwordEditText.setEnabled( true );
			}
		} );
	}

	@Override
	public void enableConfirmPasswordEditText(){
		runOnUiThread( new Runnable1Param< RegisterView >( this ){
			@Override
			public void run(){
				getParam1().confirmPasswordEditText.setEnabled( true );
			}
		} );
	}

	@Override
	public void disableCreateAccountButton(){
		runOnUiThread( new Runnable1Param< RegisterView >( this ){
			@Override
			public void run(){
				getParam1().createAccountButton.setEnabled( false );
			}
		} );
	}

	@Override
	public void disableEmailEditText(){
		runOnUiThread( new Runnable1Param< RegisterView >( this ){
			@Override
			public void run(){
				getParam1().emailEditText.setEnabled( false );
			}
		} );
	}

	@Override
	public void disablePasswordEditText(){
		runOnUiThread( new Runnable1Param< RegisterView >( this ){
			@Override
			public void run(){
				getParam1().passwordEditText.setEnabled( false );
			}
		} );
	}

	@Override
	public void disableConfirmPassword(){
		runOnUiThread( new Runnable1Param< RegisterView >( this ){
			@Override
			public void run(){
				getParam1().confirmPasswordEditText.setEnabled( false );
			}
		} );
	}
}
