package tobedevelopers.project_fury.dashboard.implementation;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import tobedevelopers.project_fury.BaseActivity;
import tobedevelopers.project_fury.NavigationListener;
import tobedevelopers.project_fury.R;
import tobedevelopers.project_fury.Runnable1Param;
import tobedevelopers.project_fury.create_project.implementation.CreateProjectView;
import tobedevelopers.project_fury.dashboard.DashboardContract;
import tobedevelopers.project_fury.project_info.implementation.ProjectInfoView;
import tobedevelopers.project_fury.task_info.implementation.TaskInfoView;

public class DashboardView extends BaseActivity implements DashboardContract.View, DashboardContract.Navigation{

	//UI References
	private DrawerLayout mDrawerLayout;
	private Toolbar mToolbar;
	private Button mCreateProjectButton;
	private Button mProjectInfoButton;
	private Button mTaskInfoButton;
	private NavigationView mNavigationView;

	private DashboardContract.Presenter presenter;

	@Override
	protected void onCreate( Bundle savedInstanceState ){
		setTitle( getString( R.string.title_dashboard ) );
		setContentView( R.layout.activity_dashboard );
		super.onCreate( savedInstanceState );
		presenter = new DashboardPresenter( this, this );

		//UI References
		mDrawerLayout = ( DrawerLayout ) findViewById( R.id.dashboardActivity_drawerLayout );
		mToolbar = ( Toolbar ) findViewById( R.id.dashboardActivity_toolbar );
		mCreateProjectButton = ( Button ) findViewById( R.id.dashboardActivity_createProjectButton );
		mProjectInfoButton = ( Button ) findViewById( R.id.dashboardActivity_projectInfoButton );
		mTaskInfoButton = ( Button ) findViewById( R.id.dashboardActivity_taskInfoButton );
		mNavigationView = ( NavigationView ) findViewById( R.id.dashboardActivity_navigationView );

		//Toolbar Config
		setSupportActionBar( mToolbar );

		//Navigation Config
		mNavigationView.setNavigationItemSelectedListener( new NavigationListener( this ) );
		ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle( this, mDrawerLayout, mToolbar, R.string.navigation_open, R.string.navigation_close );
		mDrawerLayout.addDrawerListener( drawerToggle );
		drawerToggle.syncState();

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
