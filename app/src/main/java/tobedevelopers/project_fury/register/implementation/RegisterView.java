package tobedevelopers.project_fury.register.implementation;

import android.os.Bundle;
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

		ButterKnife.bind( this );

		presenter = new RegisterPresenter( this, this );
	}

	//Button Listener
	@OnClick( R.id.registerActivity_createAccountButton )
	public void onUserSelectCreateAccount(){
		Toast.makeText( this, "Registered", Toast.LENGTH_SHORT ).show();
		presenter.userSelectCreateAccount();
	}

	@Override
	public void navigateToLogin(){
		finish();
	}
}
