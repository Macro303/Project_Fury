package tobedevelopers.project_fury.dashboard.implementation;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import tobedevelopers.project_fury.dashboard.Holder;
import tobedevelopers.project_fury.dashboard.ProjectAdapter;
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
		presenter.loadProjects();
	}

	//Button Listener
	/*@OnClick( { R.id.dashboardActivity_createProjectButton, R.id.dashboardActivity_projectInfoButton, R.id.dashboardActivity_taskInfoButton } )
	public void onUserSelectAButton( View view ){
		switch( view.getId() ){
			case R.id.dashboardActivity_createProjectButton:
				ToastLog.makeDebug( this, "Create Project", Toast.LENGTH_SHORT );
				presenter.userSelectCreateProject();
				break;
			case R.id.dashboardActivity_projectInfoButton:
				ToastLog.makeDebug( this, "Project Info", Toast.LENGTH_SHORT );
				presenter.userSelectProjectInfo();
				break;
			case R.id.dashboardActivity_taskInfoButton:
				ToastLog.makeDebug( this, "Task Info", Toast.LENGTH_SHORT );
				presenter.userSelectTaskInfo();
				break;
			default:
				ToastLog.makeError( this, String.format( getString( R.string.error_message ), getTitle() ), Toast.LENGTH_SHORT );
				break;
		}
	}*/

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
	public void loadProjectsIntoList( Holder holder ){
		mProjectsList.setAdapter( new ProjectAdapter( this, holder ) );
		LayoutInflater inflater = LayoutInflater.from( this );
		View mTop = inflater.inflate( R.layout.list_header_dashboard_project, null );
		mProjectsList.addHeaderView( mTop );
		setListViewHeightBasedOnChildren( mProjectsList );
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
		params.height = totalHeight + ( listView.getDividerHeight() * ( listAdapter.getCount() - 1 ) );
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
