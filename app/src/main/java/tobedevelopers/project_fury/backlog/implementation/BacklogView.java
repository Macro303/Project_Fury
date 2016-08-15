package tobedevelopers.project_fury.backlog.implementation;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import tobedevelopers.project_fury.BaseNavigationView;
import tobedevelopers.project_fury.R;
import tobedevelopers.project_fury.Runnable1Param;
import tobedevelopers.project_fury.backlog.BacklogContract;
import tobedevelopers.project_fury.backlog.BacklogFragmentPagerAdapter;
import tobedevelopers.project_fury.create_task.implementation.CreateTaskView;

/**
 * Created by Macro303 on 12/08/2016.
 */
public class BacklogView extends BaseNavigationView implements BacklogContract.View, BacklogContract.Navigation{

	//UI References
	private TabLayout mTabLayout;
	private ViewPager mViewPager;
	private FloatingActionButton mCreateTaskButton;

	private BacklogContract.Presenter presenter;

	@Override
	protected void onCreate( Bundle savedInstanceState ){
		setTitle( getString( R.string.title_activity_backlog ) );
		setContentView( R.layout.activity_backlog );
		super.onCreate( savedInstanceState );
		presenter = new BacklogPresenter( this, this );

		//UI References
		mTabLayout = ( TabLayout ) findViewById( R.id.backlogActivity_tabLayout );
		mViewPager = ( ViewPager ) findViewById( R.id.backlogActivity_viewPager );
		mCreateTaskButton = ( FloatingActionButton ) findViewById( R.id.backlogActivity_createTaskButton );

		//Tab Config
		mViewPager.setAdapter( new BacklogFragmentPagerAdapter( getSupportFragmentManager() ) );
		mTabLayout.setupWithViewPager( mViewPager );

		//Button Config
		mCreateTaskButton.setOnClickListener( new View.OnClickListener(){
			@Override
			public void onClick( View view ){
				presenter.userSelectCreateTask();
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
