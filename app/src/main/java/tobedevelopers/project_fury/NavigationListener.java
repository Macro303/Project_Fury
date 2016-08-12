package tobedevelopers.project_fury;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import tobedevelopers.project_fury.backlog.implementation.BacklogView;
import tobedevelopers.project_fury.dashboard.implementation.DashboardView;
import tobedevelopers.project_fury.login.implementation.LoginView;
import tobedevelopers.project_fury.settings.implementation.SettingsView;

/**
 * Created by Macro303 on 10/08/2016.
 */
public class NavigationListener implements NavigationView.OnNavigationItemSelectedListener{

	private Activity activity;

	public NavigationListener( Activity activity ){
		this.activity = activity;
	}

	@Override
	public boolean onNavigationItemSelected( MenuItem item ){
		if( item.isChecked() )
			item.setChecked( false );
		else
			item.setChecked( true );
		item.collapseActionView();
		try{
			switch( item.getItemId() ){
				case R.id.navigationMenu_dashboardItem:
					Log.d( activity.getApplicationContext().getString( R.string.app_name ), "Dashboard View Called" );
					activity.startActivity( new Intent( activity.getApplicationContext(), DashboardView.class ) );
					return true;
				case R.id.navigationMenu_backlogItem:
					Log.d( activity.getApplicationContext().getString( R.string.app_name ), "Backlog View Called" );
					activity.startActivity( new Intent( activity.getApplicationContext(), BacklogView.class ) );
					return true;
				case R.id.navigationMenu_settingsItem:
					Log.d( activity.getApplicationContext().getString( R.string.app_name ), "Settings View Called" );
					activity.startActivity( new Intent( activity.getApplicationContext(), SettingsView.class ) );
					return true;
				case R.id.navigationMenu_logOutItem:
					Log.d( activity.getApplicationContext().getString( R.string.app_name ), "Login View Called" );
					activity.startActivity( new Intent( activity.getApplicationContext(), LoginView.class ) );
					return true;
				default:
					Toast.makeText( activity.getApplicationContext(), "Something Happened In The Navigation Code", Toast.LENGTH_LONG ).show();
					Log.w( activity.getString( R.string.app_name ), "Something Happened In The Navigation Code" );
					return true;
			}
		}finally{
			activity.finish();
		}
	}
}
