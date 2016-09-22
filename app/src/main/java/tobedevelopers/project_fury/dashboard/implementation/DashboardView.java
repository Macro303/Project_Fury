package tobedevelopers.project_fury.dashboard.implementation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

	@Bind( R.id.dashboard_LoadingProjectsProgressBar )
	protected ProgressBar mLoadingProjectsProgressbar;
	@Bind( R.id.dashboard_LoadingTasksProgressBar )
	protected ProgressBar mLoadingTasksProgressbar;
	@Bind( R.id.listHeader_projectCreateButton )
	ImageButton mCreateProjectButton;
	@Bind( R.id.listHeader_taskCreateButton )
	ImageButton mCreateTaskButton;
	private RecyclerView mProjectRecyclerView;
	private RecyclerView mTaskRecyclerView;
	private DashboardProjectRecyclerAdapter mDashboardProjectRecyclerAdapter;
	private DashboardTaskRecyclerAdapter mDashboardTaskRecyclerAdapter;

	private DashboardContract.Presenter presenter;

	@Override
	protected void onCreate( Bundle savedInstanceState ){
		setTitle( getString( R.string.title_activity_dashboard ) );
		setContentView( R.layout.activity_dashboard );
		super.onCreate( savedInstanceState );

		presenter = new DashboardPresenter( this, this );

		ButterKnife.bind( this );

		mProjectRecyclerView = ( RecyclerView ) findViewById( R.id.dashboard_Project_RecyclerView );
		mProjectRecyclerView.setHasFixedSize( true );
		mProjectRecyclerView.setLayoutManager( new LinearLayoutManager( getApplicationContext() ) );
		mDashboardProjectRecyclerAdapter = new DashboardProjectRecyclerAdapter( this, presenter );
		mProjectRecyclerView.setAdapter( mDashboardProjectRecyclerAdapter );

		mTaskRecyclerView = ( RecyclerView ) findViewById( R.id.dashboard_Task_RecyclerView );
		mTaskRecyclerView.setHasFixedSize( true );
		mTaskRecyclerView.setLayoutManager( new LinearLayoutManager( getApplicationContext() ) );
		mDashboardTaskRecyclerAdapter = new DashboardTaskRecyclerAdapter( this, presenter );
		mTaskRecyclerView.setAdapter( mDashboardTaskRecyclerAdapter );

		presenter.loadProjects();
		presenter.loadTasks();

	}

	@OnClick( { R.id.listHeader_projectCreateButton, R.id.listHeader_taskCreateButton } )
	public void onUserSelectAButton( android.view.View view ){
		switch( view.getId() ){
			case R.id.listHeader_projectCreateButton:
				setEnabledAllButtons( false );
				presenter.userSelectCreateProject();
				break;
			case R.id.listHeader_taskCreateButton:
				Model.setSelectedProject( null );
				setEnabledAllButtons( false );
				presenter.userSelectCreateTask();
				break;
			default:
				ToastLog.makeError( this, String.format( getString( R.string.error_message ), getTitle() ), Toast.LENGTH_SHORT );
				break;
		}
	}

	@Override
	protected void onRestart(){
		super.onRestart();
		finish();
		startActivity( getIntent() );
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
			mCreateTaskButton.setVisibility( android.view.View.VISIBLE );
			mDashboardProjectRecyclerAdapter.setData( projectHolder );
		}else{
			mCreateTaskButton.setVisibility( android.view.View.GONE );
			mDashboardProjectRecyclerAdapter.setData( projectHolder );
		}
	}

	@Override
	public void loadTasksIntoList( TaskHolder taskHolder ){
		mDashboardTaskRecyclerAdapter.setData( taskHolder );
	}

	@Override
	public void noInternetAccessValidation(){
		setEnabledAllButtons( true );
		ToastLog.makeWarn( this, getString( R.string.error_noInternetAccess ), Toast.LENGTH_LONG );
	}

	@Override
	public void loadingProjectsInProgress(){
		runOnUiThread( new Runnable1Param< DashboardView >( this ){
			@Override
			public void run(){
				mLoadingProjectsProgressbar.setVisibility( android.view.View.VISIBLE );
			}
		} );
	}

	@Override
	public void loadingProjectsFinished(){
		runOnUiThread( new Runnable1Param< DashboardView >( this ){
			@Override
			public void run(){
				mLoadingProjectsProgressbar.setVisibility( android.view.View.GONE );
			}
		} );
	}

	@Override
	public void loadingTasksInProgress(){
		runOnUiThread( new Runnable1Param< DashboardView >( this ){
			@Override
			public void run(){
				mLoadingTasksProgressbar.setVisibility( android.view.View.VISIBLE );
			}
		} );
	}

	@Override
	public void loadingTasksFinished(){
		runOnUiThread( new Runnable1Param< DashboardView >( this ){
			@Override
			public void run(){
				mLoadingTasksProgressbar.setVisibility( android.view.View.GONE );
			}
		} );
	}

	@Override
	public void defaultErrorMessage(){
		setEnabledAllButtons( true );
		ToastLog.makeWarn( this, getString( R.string.error_defaultError ), Toast.LENGTH_LONG );
	}

	@Override
	protected void onPause(){
		super.onPause();
		presenter.cancelAllAsyncTasks( true );
	}
}
