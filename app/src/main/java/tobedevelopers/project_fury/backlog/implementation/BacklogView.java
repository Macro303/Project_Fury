package tobedevelopers.project_fury.backlog.implementation;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import tobedevelopers.project_fury.BaseNavigationView;
import tobedevelopers.project_fury.R;
import tobedevelopers.project_fury.ToastLog;
import tobedevelopers.project_fury.backlog.BacklogContract;
import tobedevelopers.project_fury.backlog.BacklogFragmentPagerAdapter;
import tobedevelopers.project_fury.backlog.Holder;
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
	@Bind( R.id.backlogActivity_createTaskButton )
	protected FloatingActionButton mCreateTaskButton;

	private BacklogContract.Presenter presenter;
	private Holder holder;

	@Override
	protected void onCreate( Bundle savedInstanceState ){
		setTitle( getString( R.string.title_activity_backlog ) );
		setContentView( R.layout.activity_backlog );
		super.onCreate( savedInstanceState );

		presenter = new BacklogPresenter( this, this );

		ButterKnife.bind( this );

		//Tab Config
		mViewPager.setAdapter( new BacklogFragmentPagerAdapter( getSupportFragmentManager() ) );
		mTabLayout.setupWithViewPager( mViewPager );

//		Button Config
//		mCreateTaskButton.setOnClickListener( new View.OnClickListener(){
//			@Override
//			public void onClick( View view ){
//				Model.setSelectedProject( holder.getProjects()[ mTabLayout.getSelectedTabPosition() ] );
//				presenter.userSelectCreateTask();
//			}
//		} );

		presenter.loadProjects();
	}

	@Override
	protected void onRestart(){
		super.onRestart();
		finish();
		startActivity( getIntent() );
	}

	@Override
	public void fillProjects( Holder holder ){
		this.holder = holder;
		( ( BacklogFragmentPagerAdapter ) mViewPager.getAdapter() ).setData( this.holder );
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
	public void loadingInProgress(){
		runOnUiThread( new Runnable1Param< BacklogView >( this ){
			@Override
			public void run(){
				ToastLog.makeInfo( getParam1(), String.format( getString( R.string.error_inProgress ), "Loading" ), Toast.LENGTH_LONG );
			}
		} );
	}

	@Override
	public void noInternetAccessValidation(){
		ToastLog.makeWarn( this, getString( R.string.error_noInternetAccess ), Toast.LENGTH_LONG );
	}

	@Override
	public void defaultErrorMessage(){
		ToastLog.makeWarn( this, getString( R.string.error_defaultError ), Toast.LENGTH_LONG );
	}
}
