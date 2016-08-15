package tobedevelopers.project_fury.dashboard.implementation;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.OnClick;
import tobedevelopers.project_fury.BaseNavigationView;
import tobedevelopers.project_fury.R;
import tobedevelopers.project_fury.Runnable1Param;
import tobedevelopers.project_fury.create_project.implementation.CreateProjectView;
import tobedevelopers.project_fury.dashboard.DashboardContract;
import tobedevelopers.project_fury.project_info.implementation.ProjectInfoView;
import tobedevelopers.project_fury.task_info.implementation.TaskInfoView;

public class DashboardView extends BaseNavigationView implements DashboardContract.View, DashboardContract.Navigation{

	//UI References
	private Button mCreateProjectButton;
	private Button mProjectInfoButton;
	private Button mTaskInfoButton;

	private DashboardContract.Presenter presenter;

	@Override
	protected void onCreate( Bundle savedInstanceState ){
		setTitle( getString( R.string.title_activity_dashboard ) );
		setContentView( R.layout.activity_dashboard );
		super.onCreate( savedInstanceState );
		presenter = new DashboardPresenter( this, this );

		ButterKnife.bind( this );

		//UI References
		mCreateProjectButton = ( Button ) findViewById( R.id.dashboardActivity_createProjectButton );
		mProjectInfoButton = ( Button ) findViewById( R.id.dashboardActivity_projectInfoButton );
		mTaskInfoButton = ( Button ) findViewById( R.id.dashboardActivity_taskInfoButton );

		//Button Config
		mCreateProjectButton.setOnClickListener( new View.OnClickListener(){
			@Override
			public void onClick( View view ){
				presenter.userSelectCreateProject();
			}
		} );
		mProjectInfoButton.setOnClickListener( new View.OnClickListener(){
			@Override
			public void onClick( View view ){
				presenter.userSelectProjectInfo();
			}
		} );
		mTaskInfoButton.setOnClickListener( new View.OnClickListener(){
			@Override
			public void onClick( View view ){
				presenter.userSelectTaskInfo();
			}
		} );
	}

	//Button Listener
	@OnClick( { R.id.dashboardActivity_createProjectButton, R.id.dashboardActivity_projectInfoButton, R.id.dashboardActivity_taskInfoButton } )
	public void onUserSelectAButton( View view ){
		switch( view.getId() ){
			case R.id.dashboardActivity_createProjectButton:
				Toast.makeText( this, "Create Project", Toast.LENGTH_SHORT ).show();
				presenter.userSelectCreateProject();
				break;
			case R.id.dashboardActivity_projectInfoButton:
				Toast.makeText( this, "Project Info", Toast.LENGTH_SHORT ).show();
				presenter.userSelectCreateProject();
				break;
			case R.id.dashboardActivity_taskInfoButton:
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
	public void navigateToCreateProject(){
		runOnUiThread( new Runnable1Param< DashboardView >( this ){
			@Override
			public void run(){
				startActivity( new Intent( getParam1(), CreateProjectView.class ) );
			}
		} );
	}

	@Override
	public void navigateToProjectInfo(){
		runOnUiThread( new Runnable1Param< DashboardView >( this ){
			@Override
			public void run(){
				startActivity( new Intent( getParam1(), ProjectInfoView.class ) );
			}
		} );
	}

	@Override
	public void navigateToTaskInfo(){
		runOnUiThread( new Runnable1Param< DashboardView >( this ){
			@Override
			public void run(){
				startActivity( new Intent( getParam1(), TaskInfoView.class ) );
			}
		} );
	}
}
