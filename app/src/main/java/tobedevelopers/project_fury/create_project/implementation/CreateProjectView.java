package tobedevelopers.project_fury.create_project.implementation;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.OnClick;
import tobedevelopers.project_fury.BaseView;
import tobedevelopers.project_fury.R;
import tobedevelopers.project_fury.Runnable1Param;
import tobedevelopers.project_fury.create_project.CreateProjectContract;

/**
 * Created by Macro303 on 11/08/2016.
 */
public class CreateProjectView extends BaseView implements CreateProjectContract.View, CreateProjectContract.Navigation{

	private CreateProjectContract.Presenter presenter;

	@Override
	protected void onCreate( Bundle savedInstanceState ){
		setTitle( getString( R.string.title_activity_createProject ) );
		setContentView( R.layout.activity_create_project );
		super.onCreate( savedInstanceState );

		presenter = new CreateProjectPresenter( this, this );

		ButterKnife.bind( this );
	}

	//Button Listener
	@OnClick( R.id.createProjectActivity_createProjectButton )
	public void onUserSelectAButton( View view ){
		switch( view.getId() ){
			case R.id.createProjectActivity_createProjectButton:
				Toast.makeText( this, "Create Project", Toast.LENGTH_SHORT ).show();
				presenter.userSelectCreateProject();
				break;
			default:
				Toast.makeText( this, String.format( getString( R.string.error_message ), getTitle() ), Toast.LENGTH_SHORT ).show();
				Log.w( getString( R.string.app_name ), String.format( getString( R.string.error_message ), getTitle() ) );
				break;
		}
	}

	@Override
	public void navigateToPrevious(){
		runOnUiThread( new Runnable1Param< CreateProjectView >( this ){
			@Override
			public void run(){
				finish();
			}
		} );
	}
}
