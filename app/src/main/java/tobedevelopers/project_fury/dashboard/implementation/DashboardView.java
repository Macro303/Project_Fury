package tobedevelopers.project_fury.dashboard.implementation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import tobedevelopers.project_fury.BaseNavigationView;
import tobedevelopers.project_fury.R;
import tobedevelopers.project_fury.ToastLog;
import tobedevelopers.project_fury.create_project.implementation.CreateProjectView;
import tobedevelopers.project_fury.dashboard.DashboardContract;
import tobedevelopers.project_fury.dashboard.ProjectAdapter;
import tobedevelopers.project_fury.dashboard.ProjectHolder;
import tobedevelopers.project_fury.dashboard.TaskAdapter;
import tobedevelopers.project_fury.dashboard.TaskHolder;
import tobedevelopers.project_fury.model.Model;
import tobedevelopers.project_fury.model.Project;
import tobedevelopers.project_fury.model.Task;
import tobedevelopers.project_fury.project_info.implementation.ProjectInfoView;
import tobedevelopers.project_fury.runnable_param.Runnable1Param;
import tobedevelopers.project_fury.task_info.implementation.TaskInfoView;

public class DashboardView extends BaseNavigationView implements DashboardContract.View, DashboardContract.Navigation{

	@Bind( R.id.dashboardActivity_projectsList )
	ListView mProjectsList;
	@Bind( R.id.dashboardActivity_tasksList )
	ListView mTasksList;

	private DashboardContract.Presenter presenter;

	@Override
	protected void onCreate( Bundle savedInstanceState ){
		setTitle( getString( R.string.title_activity_dashboard ) );
		setContentView( R.layout.activity_dashboard );
		super.onCreate( savedInstanceState );

		presenter = new DashboardPresenter( this, this );

		ButterKnife.bind( this );

		setupProjectsList();
		setupTasksList();
//		mProjectsList.setAdapter( new ProjectAdapter( this ) );
//		mTasksList.setAdapter( new TaskAdapter( this ) );
		presenter.loadProjects();
	}

	private void setupProjectsList(){
		mProjectsList.setAdapter( new ProjectAdapter( this ) );
		View mTop = getLayoutInflater().inflate( R.layout.list_header_dashboard_project, mProjectsList, false );
		mProjectsList.addHeaderView( mTop, null, false );
		Button mCreateButton = ( Button ) mTop.findViewById( R.id.listHeader_projectCreateButton );
		mCreateButton.setOnClickListener( new View.OnClickListener(){
			@Override
			public void onClick( View view ){
				ToastLog.makeDebug( getApplicationContext(), "Create Project", Toast.LENGTH_SHORT );
				presenter.userSelectCreateProject();
			}
		} );
		mProjectsList.setOnItemClickListener( new AdapterView.OnItemClickListener(){
			@Override
			public void onItemClick( AdapterView< ? > adapterView, View view, int position, long id ){
				if( !view.getTag().equals( "No Projects" ) ){
					Model.setSelectedProject( ( Project ) mProjectsList.getItemAtPosition( position ) );
					ToastLog.makeInfo( getApplicationContext(), Model.getSelectedProject().getName(), Toast.LENGTH_SHORT );
					presenter.userSelectProjectInfo();
				}
			}
		} );
		setListViewHeightBasedOnChildren( mProjectsList );
	}

	private void setupTasksList(){
		mTasksList.setAdapter( new TaskAdapter( this ) );
		View mTop = getLayoutInflater().inflate( R.layout.list_header_dashboard_task, mTasksList, false );
		mTasksList.addHeaderView( mTop, null, false );
		mTasksList.setOnItemClickListener( new AdapterView.OnItemClickListener(){
			@Override
			public void onItemClick( AdapterView< ? > adapterView, View view, int position, long id ){
				if( !view.getTag().equals( "No Tasks" ) ){
					Model.setSelectedTask( ( Task ) mTasksList.getItemAtPosition( position ) );
					ToastLog.makeInfo( getApplicationContext(), Model.getSelectedTask().getName(), Toast.LENGTH_SHORT );
					presenter.userSelectTaskInfo();
				}
			}
		} );
		setListViewHeightBasedOnChildren( mTasksList );
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

	@Override
	public void loadProjectsIntoList( ProjectHolder projectHolder ){
		( ( ProjectAdapter ) ( ( HeaderViewListAdapter ) mProjectsList.getAdapter() ).getWrappedAdapter() ).setData( projectHolder );
		setListViewHeightBasedOnChildren( mProjectsList );
		presenter.loadTasks();
	}

	@Override
	public void loadTasksIntoList( TaskHolder taskHolder ){
		( ( TaskAdapter ) ( ( HeaderViewListAdapter ) mTasksList.getAdapter() ).getWrappedAdapter() ).setData( taskHolder );
		setListViewHeightBasedOnChildren( mTasksList );
	}

	private void setListViewHeightBasedOnChildren( ListView listView ){
		ListAdapter listAdapter = listView.getAdapter();
		if( listAdapter == null )
			return;
		int totalHeight = 0;
		for( int i = 0; i < listAdapter.getCount(); i++ ){
			View listItem = listAdapter.getView( i, null, listView );
			listItem.measure( 0, 0 );
			totalHeight += listItem.getMeasuredHeight();
		}
		ViewGroup.LayoutParams params = listView.getLayoutParams();
		params.height = totalHeight + ( listView.getDividerHeight() * ( listAdapter.getCount() - 1 ) ) + 50;
		listView.setLayoutParams( params );
		listView.requestFocus();
	}

	@Override
	public void noInternetAccessValidation(){
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
		ToastLog.makeWarn( this, getString( R.string.error_defaultError ), Toast.LENGTH_LONG );
	}
}
