package tobedevelopers.project_fury.create_project.implementation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import tobedevelopers.project_fury.BaseView;
import tobedevelopers.project_fury.R;
import tobedevelopers.project_fury.Runnable1Param;
import tobedevelopers.project_fury.create_project.CreateProjectContract;
import tobedevelopers.project_fury.project_info.implementation.ProjectInfoView;

/**
 * Created by Macro303 on 11/08/2016.
 */
public class CreateProjectView extends BaseView implements CreateProjectContract.View, CreateProjectContract.Navigation{

	//UI References
	private Button mCreateProjectButton;

	private CreateProjectContract.Presenter presenter;

	@Override
	protected void onCreate( Bundle savedInstanceState ){
		setTitle( getString( R.string.title_activity_createProject ) );
		setContentView( R.layout.activity_create_project );
		super.onCreate( savedInstanceState );

		presenter = new CreateProjectPresenter( this, this );

		//UI References
		mCreateProjectButton = ( Button ) findViewById( R.id.createProjectActivity_createProjectButton );

		mCreateProjectButton.setOnClickListener( new View.OnClickListener(){
			@Override
			public void onClick( View view ){
				presenter.userSelectCreateProject();
			}
		} );
	}

	@Override
	public void navigateToProjectInfo(){
		runOnUiThread( new Runnable1Param< CreateProjectView >( this ){
			@Override
			public void run(){
				startActivity( new Intent( getParam1(), ProjectInfoView.class ) );
			}
		} );
	}
}
