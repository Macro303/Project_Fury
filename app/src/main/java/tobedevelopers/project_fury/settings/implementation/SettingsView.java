package tobedevelopers.project_fury.settings.implementation;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;

import tobedevelopers.project_fury.BaseView;
import tobedevelopers.project_fury.NavigationListener;
import tobedevelopers.project_fury.R;
import tobedevelopers.project_fury.settings.SettingsContract;

/**
 * Created by Macro303 on 12/08/2016.
 */
public class SettingsView extends BaseView implements SettingsContract.View, SettingsContract.Navigation{

	//UI References
	private DrawerLayout mDrawerLayout;
	private Toolbar mToolbar;
	private NavigationView mNavigationView;

	private SettingsContract.Presenter presenter;

	@Override
	protected void onCreate( Bundle savedInstanceState ){
		setTitle( getString( R.string.title_activity_settings ) );
		setContentView( R.layout.activity_settings );
		super.onCreate( savedInstanceState );
		presenter = new SettingsPresenter( this, this );

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
}
