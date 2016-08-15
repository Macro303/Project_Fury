package tobedevelopers.project_fury.login.implementation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import tobedevelopers.project_fury.BaseView;
import tobedevelopers.project_fury.R;
import tobedevelopers.project_fury.Runnable1Param;
import tobedevelopers.project_fury.dashboard.implementation.DashboardView;
import tobedevelopers.project_fury.login.LoginContract;
import tobedevelopers.project_fury.register.implementation.RegisterView;

public class LoginView extends BaseView implements LoginContract.View, LoginContract.Navigation{

	//UI References
	private Button mLoginButton;
	private Button mRegisterButton;

	private LoginContract.Presenter presenter;

	@Override
	protected void onCreate( Bundle savedInstanceState ){
		setTitle( getString( R.string.title_activity_login ) );
		setContentView( R.layout.activity_login );
		super.onCreate( savedInstanceState );

		presenter = new LoginPresenter( this, this );

		//UI References
		mLoginButton = ( Button ) findViewById( R.id.loginActivity_loginButton );
		mRegisterButton = ( Button ) findViewById( R.id.loginActivity_registerButton );

		//Button config
		mLoginButton.setOnClickListener( new View.OnClickListener(){
			@Override
			public void onClick( View view ){
				presenter.userSelectDashboard();
			}
		} );
		mRegisterButton.setOnClickListener( new View.OnClickListener(){
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
}
