package tobedevelopers.project_fury.create_project.implementation;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.OnClick;
import tobedevelopers.project_fury.BaseView;
import tobedevelopers.project_fury.R;
import tobedevelopers.project_fury.Runnable1Param;
import tobedevelopers.project_fury.create_project.CreateProjectContract;
import tobedevelopers.project_fury.project_info.implementation.ProjectInfoView;

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

		ButterKnife.bind( this );

		presenter = new CreateProjectPresenter( this, this );
	}

	//Button Listener
	@OnClick( R.id.createProjectActivity_createProjectButton )
	public void onUserSelectCreateProject(){
		Toast.makeText( this, "Create Project", Toast.LENGTH_SHORT ).show();
		presenter.userSelectCreateProject();
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
