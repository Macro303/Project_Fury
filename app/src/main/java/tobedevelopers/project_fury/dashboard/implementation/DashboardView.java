package tobedevelopers.project_fury.dashboard.implementation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import tobedevelopers.project_fury.BaseNavigationView;
import tobedevelopers.project_fury.R;
import tobedevelopers.project_fury.ToastLog;
import tobedevelopers.project_fury.create_project.implementation.CreateProjectView;
import tobedevelopers.project_fury.create_task.implementation.CreateTaskView;
import tobedevelopers.project_fury.dashboard.DashboardContract;
import tobedevelopers.project_fury.model.Model;
import tobedevelopers.project_fury.project_info.implementation.ProjectInfoView;
import tobedevelopers.project_fury.runnable_param.Runnable1Param;
import tobedevelopers.project_fury.task_info.implementation.TaskInfoView;

public class DashboardView extends BaseNavigationView implements DashboardContract.View, DashboardContract.Navigation{

	@Bind( R.id.dashboardActivity_projectsLoadingProgressBar )
	protected ProgressBar mProjectsLoadingProgressbar;
	@Bind( R.id.dashboardActivity_tasksLoadingProgressBar )
	protected ProgressBar mTasksLoadingProgressbar;
	@Bind( R.id.dashboardActivity_projectCreateButton )
	protected ImageButton mCreateProjectButton;
	@Bind( R.id.dashboardActivity_taskCreateButton )
	protected ImageButton mCreateTaskButton;
	@Bind( R.id.dashboardActivity_projectRecyclerView )
	protected RecyclerView mProjectRecyclerView;
	@Bind( R.id.dashboardActivity_taskRecyclerView )
	protected RecyclerView mTaskRecyclerView;

	private DashboardProjectRecyclerAdapter mProjectAdapter;
	private DashboardTaskRecyclerAdapter mTaskAdapter;

	private DashboardContract.Presenter presenter;

	@Override
	protected void onCreate( Bundle savedInstanceState ){
		setTitle( getString( R.string.title_activity_dashboard ) );
		setContentView( R.layout.activity_dashboard );
		super.onCreate( savedInstanceState );

		presenter = new DashboardPresenter( this, this );

		ButterKnife.bind( this );

		mProjectRecyclerView.setHasFixedSize( true );
		mProjectRecyclerView.setLayoutManager( new LinearLayoutManager( getApplicationContext() ) );
		mProjectAdapter = new DashboardProjectRecyclerAdapter( this, presenter );
		mProjectRecyclerView.setAdapter( mProjectAdapter );

		mTaskRecyclerView.setHasFixedSize( true );
		mTaskRecyclerView.setLayoutManager( new LinearLayoutManager( getApplicationContext() ) );
		mTaskAdapter = new DashboardTaskRecyclerAdapter( this, presenter );
		mTaskRecyclerView.setAdapter( mTaskAdapter );

		presenter.loadProjects();
		presenter.loadTasks();
	}

	@OnClick( { R.id.dashboardActivity_projectCreateButton, R.id.dashboardActivity_taskCreateButton } )
	public void onUserSelectAButton( View view ){
		switch( view.getId() ){
			case R.id.dashboardActivity_projectCreateButton:
				setEnabledAllButtons( false );
				presenter.userSelectCreateProject();
				break;
			case R.id.dashboardActivity_taskCreateButton:
				Model.setSelectedProject( null );
				setEnabledAllButtons( false );
				presenter.userSelectCreateTask();
				break;
			default:
				ToastLog.makeError( this, getString( R.string.app_name ), String.format( getString( R.string.error_message ), getTitle() ), Toast.LENGTH_SHORT );
				break;
		}
	}

	private void setEnabledAllButtons( Boolean condition ){
		mCreateProjectButton.setEnabled( condition );
		mCreateTaskButton.setEnabled( condition );
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
	public void navigateToCreateTask(){
		runOnUiThread( new Runnable1Param< DashboardView >( this ){
			@Override
			public void run(){
				startActivity( new Intent( getParam1(), CreateTaskView.class ) );
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
		setEnabledAllButtons( false );
		runOnUiThread( new Runnable1Param< DashboardView >( this ){
			@Override
			public void run(){
				startActivity( new Intent( getParam1(), TaskInfoView.class ) );
			}
		} );
	}

	@Override
	public void loadProjectsIntoList( ProjectHolder projectHolder ){
		if( projectHolder.getProjects().length != 0 ){
			mCreateTaskButton.setVisibility( View.VISIBLE );
			mProjectAdapter.setData( projectHolder );
		}else{
			mCreateTaskButton.setVisibility( View.GONE );
			mProjectAdapter.setData( projectHolder );
		}
	}

	@Override
	public void loadTasksIntoList( TaskHolder taskHolder ){
		mTaskAdapter.setData( taskHolder );
	}

	@Override
	public void noInternetAccessValidation(){
		super.noInternetAccessValidation();
		setEnabledAllButtons( true );
	}

	@Override
	public void loadingProjectsInProgress(){
		runOnUiThread( new Runnable1Param< DashboardView >( this ){
			@Override
			public void run(){
				mProjectsLoadingProgressbar.setVisibility( View.VISIBLE );
			}
		} );
	}

	@Override
	public void loadingProjectsFinished(){
		runOnUiThread( new Runnable1Param< DashboardView >( this ){
			@Override
			public void run(){
				mProjectsLoadingProgressbar.setVisibility( View.GONE );
			}
		} );
	}

	@Override
	public void loadingTasksInProgress(){
		runOnUiThread( new Runnable1Param< DashboardView >( this ){
			@Override
			public void run(){
				mTasksLoadingProgressbar.setVisibility( View.VISIBLE );
			}
		} );
	}

	@Override
	public void loadingTasksFinished(){
		runOnUiThread( new Runnable1Param< DashboardView >( this ){
			@Override
			public void run(){
				mTasksLoadingProgressbar.setVisibility( View.GONE );
			}
		} );
	}

	@Override
	public void defaultErrorMessage(){
		super.defaultErrorMessage();
		setEnabledAllButtons( true );
	}

	@Override
	protected void onPause(){
		super.onPause();
		presenter.cancelAllAsyncTasks( true );
	}
}
