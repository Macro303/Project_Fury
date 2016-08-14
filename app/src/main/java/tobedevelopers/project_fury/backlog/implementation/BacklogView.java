package tobedevelopers.project_fury.backlog.implementation;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import tobedevelopers.project_fury.BaseView;
import tobedevelopers.project_fury.NavigationListener;
import tobedevelopers.project_fury.R;
import tobedevelopers.project_fury.Runnable1Param;
import tobedevelopers.project_fury.backlog.BacklogContract;
import tobedevelopers.project_fury.backlog.BacklogFragmentPagerAdapter;
import tobedevelopers.project_fury.create_task.implementation.CreateTaskView;

/**
 * Created by Macro303 on 12/08/2016.
 */
public class BacklogView extends BaseView implements BacklogContract.View, BacklogContract.Navigation{

	//UI References
	private DrawerLayout mDrawerLayout;
	private Toolbar mToolbar;
	private TabLayout mTabLayout;
	private ViewPager mViewPager;
	private FloatingActionButton mAddTaskButton;
	private NavigationView mNavigationView;

	private BacklogContract.Presenter presenter;

	@Override
	protected void onCreate( Bundle savedInstanceState ){
		setTitle( getString( R.string.title_activity_backlog ) );
		setContentView( R.layout.activity_backlog );
		super.onCreate( savedInstanceState );

		presenter = new BacklogPresenter( this, this );

		//UI References
		mDrawerLayout = ( DrawerLayout ) findViewById( R.id.backlogActivity_drawerLayout );
		mToolbar = ( Toolbar ) findViewById( R.id.backlogActivity_toolbar );
		mTabLayout = ( TabLayout ) findViewById( R.id.backlogActivity_tabLayout );
		mViewPager = ( ViewPager ) findViewById( R.id.backlogActivity_viewPager );
		mAddTaskButton = ( FloatingActionButton ) findViewById( R.id.backlogActivity_addTaskButton );
		mNavigationView = ( NavigationView ) findViewById( R.id.backlogActivity_navigationView );

		//Tab Config
		mViewPager.setAdapter( new BacklogFragmentPagerAdapter( getSupportFragmentManager() ) );
		mTabLayout.setupWithViewPager( mViewPager );

		//Toolbar Config
		setSupportActionBar( mToolbar );

		//Navigation Config
		mNavigationView.setNavigationItemSelectedListener( new NavigationListener( this ) );
		ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle( this, mDrawerLayout, mToolbar, R.string.navigation_open, R.string.navigation_close );
		mDrawerLayout.addDrawerListener( drawerToggle );
		drawerToggle.syncState();

		//Button Config
		mAddTaskButton.setOnClickListener( new View.OnClickListener(){
			@Override
			public void onClick( View view ){
				presenter.userSelectAddTask();
			}
		} );
	}

	@Override
	public void navigateToCreateTask(){
		runOnUiThread( new Runnable1Param< BacklogView >( this ){
			@Override
			public void run(){
				startActivity( new Intent( getParam1(), CreateTaskView.class ) );
			}
		} );
	}
}
