package tobedevelopers.project_fury.all_boards.implementation;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;

import tobedevelopers.project_fury.BaseView;
import tobedevelopers.project_fury.NavigationListener;
import tobedevelopers.project_fury.R;
import tobedevelopers.project_fury.all_boards.AllBoardsContract;

/**
 * Created by Macro303 on 12/08/2016.
 */
public class AllBoardsView extends BaseView implements AllBoardsContract.View, AllBoardsContract.Navigation{

	//UI References
	private DrawerLayout mDrawerLayout;
	private Toolbar mToolbar;
	private NavigationView mNavigationView;

	private AllBoardsContract.Presenter presenter;

	@Override
	protected void onCreate( Bundle savedInstanceState ){
		setTitle( getString( R.string.title_activity_allBoards ) );
		setContentView( R.layout.activity_all_boards );
		super.onCreate( savedInstanceState );
		presenter = new AllBoardsPresenter( this, this );

		//UI References
		mDrawerLayout = ( DrawerLayout ) findViewById( R.id.allBoardsActivity_drawerLayout );
		mToolbar = ( Toolbar ) findViewById( R.id.allBoardsActivity_toolbar );
		mNavigationView = ( NavigationView ) findViewById( R.id.allBoardsActivity_navigationView );

		//Toolbar Config
		setSupportActionBar( mToolbar );

		//Navigation Config
		mNavigationView.setNavigationItemSelectedListener( new NavigationListener( this ) );
		ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle( this, mDrawerLayout, mToolbar, R.string.navigation_open, R.string.navigation_close );
		mDrawerLayout.addDrawerListener( drawerToggle );
		drawerToggle.syncState();
	}
}
