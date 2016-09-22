package tobedevelopers.project_fury.backlog.implementation;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import butterknife.Bind;
import butterknife.ButterKnife;
import tobedevelopers.project_fury.BaseNavigationView;
import tobedevelopers.project_fury.R;
import tobedevelopers.project_fury.backlog.BacklogContract;
import tobedevelopers.project_fury.create_task.implementation.CreateTaskView;
import tobedevelopers.project_fury.runnable_param.Runnable1Param;

/**
 * Created by Macro303 on 12/08/2016.
 */
public class BacklogView extends BaseNavigationView implements BacklogContract.View, BacklogContract.Navigation{

	@Bind( R.id.backlogActivity_tabLayout )
	protected TabLayout mTabLayout;
	@Bind( R.id.backlogActivity_viewPager )
	protected ViewPager mViewPager;

	private BacklogContract.Presenter presenter;

	private Holder holder;
	private ProgressDialog progressDialog;
	private BacklogFragmentPagerAdapter mAdapter;

	@Override
	protected void onCreate( Bundle savedInstanceState ){
		setTitle( getString( R.string.title_activity_backlog ) );
		setContentView( R.layout.activity_backlog );
		super.onCreate( savedInstanceState );

		presenter = new BacklogPresenter( this, this );

		progressDialog = new ProgressDialog( this );

		ButterKnife.bind( this );

		//Tab Config
		mAdapter = new BacklogFragmentPagerAdapter( getSupportFragmentManager() );
		mViewPager.setAdapter( mAdapter );
		mTabLayout.setupWithViewPager( mViewPager );

		presenter.loadProjects();
	}

	@Override
	protected void onPause(){
		super.onPause();
		presenter.cancelAllAsyncTasks( true );
	}

	@Override
	public void fillProjects( Holder holder ){
		this.holder = holder;
		mAdapter.setData( this.holder );
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

	@Override
	public void showProjectUpdatingInProgress(){
		runOnUiThread( new Runnable1Param< BacklogView >( this ){
			@Override
			public void run(){
				progressDialog.setProgressStyle( ProgressDialog.STYLE_SPINNER );
				progressDialog.setMessage( "Updating..." );
				progressDialog.setIndeterminate( true );
				progressDialog.setCancelable( false );
				progressDialog.show();
			}
		} );
	}

	@Override
	public void hideProjectUpdatingInProgress(){
		runOnUiThread( new Runnable1Param< BacklogView >( this ){
			@Override
			public void run(){
				progressDialog.dismiss();
			}
		} );
	}
}
