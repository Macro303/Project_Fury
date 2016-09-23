package tobedevelopers.project_fury;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import tobedevelopers.project_fury.all_boards.implementation.AllBoardsView;
import tobedevelopers.project_fury.backlog.implementation.BacklogView;
import tobedevelopers.project_fury.dashboard.implementation.DashboardView;
import tobedevelopers.project_fury.login.implementation.LoginView;
import tobedevelopers.project_fury.settings.implementation.SettingsView;

/**
 * Created by Macro303 on 13/08/2016.
 */
public abstract class BaseNavigationView extends BaseView{

	//UI References
	@Bind( R.id.navigation_drawerLayout )
	protected DrawerLayout mDrawerLayout;
	@Bind( R.id.navigation_toolbar )
	protected Toolbar mToolbar;
	@Bind( R.id.navigation_navigationView )
	protected NavigationView mNavigationView;

	@Override
	protected void onCreate( Bundle savedInstanceState ){
		super.onCreate( savedInstanceState );
		ButterKnife.bind( this );

		//Toolbar Config
		setSupportActionBar( mToolbar );

		//Navigation Config
		mNavigationView.setNavigationItemSelectedListener( new NavigationView.OnNavigationItemSelectedListener(){
			@Override
			public boolean onNavigationItemSelected( MenuItem item ){
				if( item.isChecked() )
					item.setChecked( false );
				else
					item.setChecked( true );
				item.collapseActionView();
				finish();
				switch( item.getItemId() ){
					case R.id.navigationMenu_dashboardItem:
						Log.d( getString( R.string.app_name ), "Dashboard View Called" );
						startActivity( new Intent( getApplicationContext(), DashboardView.class ) );
						return true;
					case R.id.navigationMenu_backlogItem:
						Log.d( getString( R.string.app_name ), "Backlog View Called" );
						startActivity( new Intent( getApplicationContext(), BacklogView.class ) );
						return true;
					case R.id.navigationMenu_settingsItem:
						Log.d( getString( R.string.app_name ), "Settings View Called" );
						startActivity( new Intent( getApplicationContext(), SettingsView.class ) );
						return true;
					case R.id.navigationMenu_allBoardsItem:
						Log.d( getString( R.string.app_name ), "All Boards View Called" );
						startActivity( new Intent( getApplicationContext(), AllBoardsView.class ) );
						return true;
					case R.id.navigationMenu_logOutItem:
						Log.d( getString( R.string.app_name ), "Login View Called" );
						startActivity( new Intent( getApplicationContext(), LoginView.class ) );
						return true;
					default:
						ToastLog.makeError( getApplicationContext(), getString( R.string.app_name ), "Something happened in the Navigation Code", Toast.LENGTH_LONG );
						return true;
				}
			}
		} );
		ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle( this, mDrawerLayout, mToolbar, R.string.navigation_open, R.string.navigation_close );
		mDrawerLayout.addDrawerListener( drawerToggle );
		drawerToggle.syncState();
	}
}
