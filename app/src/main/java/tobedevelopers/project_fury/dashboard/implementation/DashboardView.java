package tobedevelopers.project_fury.dashboard.implementation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
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

	//	@Bind( R.id.dashboardActivity_projectsList )
//	ListView mProjectsList;
//	@Bind( R.id.dashboardActivity_tasksList )
//	ListView mTasksList;
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
		mDashboardProjectRecyclerAdapter = new DashboardProjectRecyclerAdapter( this, this );
		mProjectRecyclerView.setAdapter( mDashboardProjectRecyclerAdapter );

		mTaskRecyclerView = ( RecyclerView ) findViewById( R.id.dashboard_Task_RecyclerView );
		mTaskRecyclerView.setHasFixedSize( true );
		mTaskRecyclerView.setLayoutManager( new LinearLayoutManager( getApplicationContext() ) );
		mDashboardTaskRecyclerAdapter = new DashboardTaskRecyclerAdapter( this, this );
		mTaskRecyclerView.setAdapter( mDashboardTaskRecyclerAdapter );

//		setupProjectsList();
//		setupTasksList();
		presenter.loadProjects();
		presenter.loadTasks();

	}

	@OnClick( { R.id.listHeader_projectCreateButton, R.id.listHeader_taskCreateButton } )
	public void onUserSelectAButton( View view ){
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

//	private void setupProjectsList(){
//		mProjectsList.setAdapter( new DashboardProjectRecyclerAdapter( this ) );
//		View mTop = getLayoutInflater().inflate( R.layout.list_header_dashboard_project, mProjectsList, false );
//		mProjectsList.addHeaderView( mTop, null, false );
//		mCreateProjectButton = ( ImageButton ) mTop.findViewById( R.id.listHeader_projectCreateButton );
//		mCreateProjectButton.setOnClickListener( new View.OnClickListener(){
//			@Override
//			public void onClick( View view ){
//				ToastLog.makeDebug( getApplicationContext(), "Create Project", Toast.LENGTH_SHORT );
//				setEnabledAllButtons( false );
//				presenter.userSelectCreateProject();
//			}
//		} );
//		mProjectsList.setOnItemClickListener( new AdapterView.OnItemClickListener(){
//			@Override
//			public void onItemClick( AdapterView< ? > adapterView, View view, int position, long id ){
//				if( !view.getTag().equals( "No Projects" ) ){
//					Model.setSelectedProject( ( Project ) mProjectsList.getItemAtPosition( position ) );
//					setEnabledAllButtons( false );
//					presenter.userSelectProjectInfo();
//				}
//			}
//		} );
////		setListViewHeightBasedOnChildren( mProjectsList );
//	}
//
//	private void setupTasksList(){
//		mTasksList.setAdapter( new DashboardTaskRecyclerAdapter( this ) );
//		View mTop = getLayoutInflater().inflate( R.layout.list_header_dashboard_task, mTasksList, false );
//		mTasksList.addHeaderView( mTop, null, false );
//		mCreateTaskButton = ( ImageButton ) mTop.findViewById( R.id.listHeader_taskCreateButton );
//		mCreateTaskButton.setOnClickListener( new View.OnClickListener(){
//			@Override
//			public void onClick( View view ){
//				Model.setSelectedProject( null );
//				setEnabledAllButtons( false );
//				presenter.userSelectCreateTask();
//			}
//		} );
//		mTasksList.setOnItemClickListener( new AdapterView.OnItemClickListener(){
//			@Override
//			public void onItemClick( AdapterView< ? > adapterView, View view, int position, long id ){
//				if( !view.getTag().equals( "No Tasks" ) ){
//					Model.setSelectedTask( ( Task ) mTasksList.getItemAtPosition( position ) );
//					setEnabledAllButtons( false );
//					presenter.userSelectTaskInfo();
//				}
//			}
//		} );
//		setListViewHeightBasedOnChildren( mTasksList );
//}

	private void setEnabledAllButtons( Boolean condition ){
		mCreateProjectButton.setEnabled( condition );
//		mProjectsList.setEnabled( condition );
//		mTasksList.setEnabled( condition );
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
//		( ( DashboardProjectRecyclerAdapter ) ( ( HeaderViewListAdapter ) mProjectsList.getAdapter() ).getWrappedAdapter() ).setData( projectHolder );
//		setListViewHeightBasedOnChildren( mProjectsList );
		mDashboardProjectRecyclerAdapter.setData( projectHolder );
	}

	@Override
	public void loadTasksIntoList( TaskHolder taskHolder ){
//		( ( DashboardTaskRecyclerAdapter ) ( ( HeaderViewListAdapter ) mTasksList.getAdapter() ).getWrappedAdapter() ).setData( taskHolder );
//		setListViewHeightBasedOnChildren( mTasksList );
		mDashboardTaskRecyclerAdapter.setData( taskHolder );
	}

//	private void setListViewHeightBasedOnChildren( ListView listView ){
//		ListAdapter listAdapter = listView.getAdapter();
//		if( listAdapter == null )
//			return;
//		int totalHeight = 0;
//		for( int i = 0; i < listAdapter.getCount(); i++ ){
//			View listItem = listAdapter.getView( i, null, listView );
//			listItem.measure( 0, 0 );
//			totalHeight += listItem.getMeasuredHeight();
//		}
//		ViewGroup.LayoutParams params = listView.getLayoutParams();
//		params.height = totalHeight + ( listView.getDividerHeight() * ( listAdapter.getCount() - 1 ) ) + 50;
//		listView.setLayoutParams( params );
//		listView.requestFocus();
//	}

	@Override
	public void noInternetAccessValidation(){
		setEnabledAllButtons( true );
		ToastLog.makeWarn( this, getString( R.string.error_noInternetAccess ), Toast.LENGTH_LONG );
	}

	@Override
	public void loadingInProgress(){
		runOnUiThread( new Runnable1Param< DashboardView >( this ){
			@Override
			public void run(){
				ToastLog.makeInfo( getParam1(), String.format( getString( R.string.error_inProgress ), "Loading" ), Toast.LENGTH_LONG );
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
