package tobedevelopers.project_fury.login.implementation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import tobedevelopers.project_fury.DashboardActivity;
import tobedevelopers.project_fury.R;
import tobedevelopers.project_fury.RegisterActivity;
import tobedevelopers.project_fury.Runnable1Param;
import tobedevelopers.project_fury.login.LoginContract;

public class LoginView extends AppCompatActivity implements LoginContract.View, LoginContract.Navigation{

	private Button loginButton;
	private Button registerButton;

	private LoginContract.Presenter presenter;

	@Override
	protected void onCreate( Bundle savedInstanceState ){
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_login );
		Toolbar toolbar = ( Toolbar ) findViewById( R.id.toolbar );
		setSupportActionBar( toolbar );

		presenter = new LoginPresenter( this, this );

		loginButton = ( Button ) findViewById( R.id.loginActivity_loginButton );
		loginButton.setOnClickListener( new View.OnClickListener(){
			@Override
			public void onClick( View view ){
				presenter.userSelectDashboard();
			}
		} );

		registerButton = ( Button ) findViewById( R.id.loginActivity_registerButton );
		registerButton.setOnClickListener( new View.OnClickListener(){
			@Override
			public void onClick( View view ){
				presenter.userSelectRegister();
			}
		} );
	}

	@Override
	public void navigateToRegister(){
		runOnUiThread( new Runnable1Param< LoginView >( this ){
			@Override
			public void run(){
				Intent createRegisterIntent = new Intent( getParam1(), RegisterActivity.class );
				startActivity( createRegisterIntent );
			}
		} );
	}

	@Override
	public void navigateToLogin(){
		runOnUiThread( new Runnable1Param< LoginView >( this ){
			@Override
			public void run(){
				Intent createDashboardIntent = new Intent( getParam1(), DashboardActivity.class );
				startActivity( createDashboardIntent );
			}
		} );
	}
}
