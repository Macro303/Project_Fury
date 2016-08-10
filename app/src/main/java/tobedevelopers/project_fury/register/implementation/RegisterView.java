package tobedevelopers.project_fury.register.implementation;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import tobedevelopers.project_fury.BaseActivity;
import tobedevelopers.project_fury.R;
import tobedevelopers.project_fury.register.RegisterContract;

public class RegisterView extends BaseActivity implements RegisterContract.View, RegisterContract.Navigation{

	//UI References
	private Button mCreateAccountButton;

	private RegisterContract.Presenter presenter;

	@Override
	protected void onCreate( Bundle savedInstanceState ){
		setTitle( getString( R.string.title_register ) );
		setContentView( R.layout.activity_register );
		super.onCreate( savedInstanceState );
		presenter = new RegisterPresenter( this, this );

		//UI References
		mCreateAccountButton = ( Button ) findViewById( R.id.registerActivity_createAccountButton );

		//Button Config
		mCreateAccountButton.setOnClickListener( new View.OnClickListener(){
			@Override
			public void onClick( View view ){
				presenter.userSelectCreateAccount();
			}
		} );
	}
}
