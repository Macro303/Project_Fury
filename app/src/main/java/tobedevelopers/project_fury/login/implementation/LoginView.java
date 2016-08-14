package tobedevelopers.project_fury.login.implementation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.OnClick;
import tobedevelopers.project_fury.BaseView;
import tobedevelopers.project_fury.R;
import tobedevelopers.project_fury.Runnable1Param;
import tobedevelopers.project_fury.dashboard.implementation.DashboardView;
import tobedevelopers.project_fury.login.LoginContract;
import tobedevelopers.project_fury.register.implementation.RegisterView;

public class LoginView extends BaseView implements LoginContract.View, LoginContract.Navigation{

	private LoginContract.Presenter presenter;

	@Override
	protected void onCreate( Bundle savedInstanceState ){
		setTitle( getString( R.string.title_activity_login ) );
		setContentView( R.layout.activity_login );
		super.onCreate( savedInstanceState );

		ButterKnife.bind( this );

		presenter = new LoginPresenter( this, this );
	}

	//Buttons Listener
	@OnClick( { R.id.loginActivity_loginButton, R.id.loginActivity_registerButton } )
	public void onUserSelectAButton( View view ){
		switch( view.getId() ){
			case R.id.loginActivity_loginButton:
				//Toast.makeText( this, "Login", Toast.LENGTH_SHORT ).show();
				presenter.userSelectLogin();
				break;
			case R.id.loginActivity_registerButton:
				//Toast.makeText( this, "Register", Toast.LENGTH_SHORT ).show();
				presenter.userSelectRegister();
				break;
			default:
				Toast.makeText( this, "An Error has occured", Toast.LENGTH_SHORT ).show();
				break;
		}
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
				startActivity( new Intent( getParam1(), DashboardView.class ) );
			}
		} );
	}
}
