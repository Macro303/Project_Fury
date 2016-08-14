package tobedevelopers.project_fury.dashboard.implementation;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.OnClick;
import tobedevelopers.project_fury.BaseView;
import tobedevelopers.project_fury.NavigationListener;
import tobedevelopers.project_fury.R;
import tobedevelopers.project_fury.Runnable1Param;
import tobedevelopers.project_fury.create_project.implementation.CreateProjectView;
import tobedevelopers.project_fury.dashboard.DashboardContract;
import tobedevelopers.project_fury.project_info.implementation.ProjectInfoView;
import tobedevelopers.project_fury.task_info.implementation.TaskInfoView;

public class DashboardView extends BaseView implements DashboardContract.View, DashboardContract.Navigation{

	//UI References
	private DrawerLayout mDrawerLayout;
	private Toolbar mToolbar;
	private NavigationView mNavigationView;

	private DashboardContract.Presenter presenter;

	@Override
	protected void onCreate( Bundle savedInstanceState ){
		setTitle( getString( R.string.title_activity_dashboard ) );
		setContentView( R.layout.activity_dashboard );
		super.onCreate( savedInstanceState );

		ButterKnife.bind( this );

		presenter = new DashboardPresenter( this, this );

		//UI References
		mDrawerLayout = ( DrawerLayout ) findViewById( R.id.dashboardActivity_drawerLayout );
		mToolbar = ( Toolbar ) findViewById( R.id.dashboardActivity_toolbar );
		mNavigationView = ( NavigationView ) findViewById( R.id.dashboardActivity_navigationView );

		//Toolbar Config
		setSupportActionBar( mToolbar );

		//Navigation Config
		mNavigationView.setNavigationItemSelectedListener( new NavigationListener( this ) );
		ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle( this, mDrawerLayout, mToolbar, R.string.navigation_open, R.string.navigation_close );
		mDrawerLayout.addDrawerListener( drawerToggle );
		drawerToggle.syncState();
	}

	//Buttons Listener
	@OnClick( { R.id.dashboardActivity_createProjectButton, R.id.dashboardActivity_projectInfoButton, R.id.dashboardActivity_taskInfoButton } )
	public void onUserSelectAButton( View view ){
		switch( view.getId() ){
			case R.id.dashboardActivity_createProjectButton:
				//Toast.makeText( this, "Create Project", Toast.LENGTH_SHORT ).show();
				presenter.userSelectCreateProject();
				break;
			case R.id.dashboardActivity_projectInfoButton:
				//Toast.makeText( this, "Project Information", Toast.LENGTH_SHORT ).show();
				presenter.userSelectProjectInfo();
				break;
			case R.id.dashboardActivity_taskInfoButton:
				//Toast.makeText( this, "Task Information", Toast.LENGTH_SHORT ).show();
				presenter.userSelectTaskInfo();
				break;
			default:
				Toast.makeText( this, "An Error has occured", Toast.LENGTH_SHORT ).show();
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
