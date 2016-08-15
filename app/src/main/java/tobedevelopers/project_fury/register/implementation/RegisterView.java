package tobedevelopers.project_fury.register.implementation;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.OnClick;
import tobedevelopers.project_fury.BaseView;
import tobedevelopers.project_fury.R;
import tobedevelopers.project_fury.register.RegisterContract;

public class RegisterView extends BaseView implements RegisterContract.View, RegisterContract.Navigation{

	private RegisterContract.Presenter presenter;

	@Override
	protected void onCreate( Bundle savedInstanceState ){
		setTitle( getString( R.string.title_activity_register ) );
		setContentView( R.layout.activity_register );
		super.onCreate( savedInstanceState );

		presenter = new RegisterPresenter( this, this );

		ButterKnife.bind( this );
	}

	//Button Listener
	@OnClick( R.id.registerActivity_createAccountButton )
	public void onUserSelectAButton( View view ){
		switch( view.getId() ){
			case R.id.registerActivity_createAccountButton:
				Toast.makeText( this, "Create Account", Toast.LENGTH_SHORT ).show();
				presenter.userSelectCreateAccount();
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
}
