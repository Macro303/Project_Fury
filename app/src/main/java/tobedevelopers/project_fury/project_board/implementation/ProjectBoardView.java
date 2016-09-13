package tobedevelopers.project_fury.project_board.implementation;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Toast;

import tobedevelopers.project_fury.BaseView;
import tobedevelopers.project_fury.R;
import tobedevelopers.project_fury.ToastLog;
import tobedevelopers.project_fury.create_task.implementation.CreateTaskView;
import tobedevelopers.project_fury.model.Column;
import tobedevelopers.project_fury.model.Model;
import tobedevelopers.project_fury.project_board.ProjectBoardContract;
import tobedevelopers.project_fury.runnable_param.Runnable1Param;

/**
 * Created by Macro303 on 13/08/2016.
 */
public class ProjectBoardView extends BaseView implements ProjectBoardContract.View, ProjectBoardContract.Navigation{

	//UI References
	private TabLayout mTabLayout;
	private ViewPager mViewPager;
	private ProjectBoardFragmentPagerAdapter mProjectBoardFragmentPagerAdapter;
	private FloatingActionButton mCreateTaskButton;

	private ProjectBoardContract.Presenter presenter;

	@Override
	protected void onCreate( Bundle savedInstanceState ){
		setTitle( Model.getSelectedProject().getName() + " Board" );
		setContentView( R.layout.activity_project_board );
		super.onCreate( savedInstanceState );
		presenter = new ProjectBoardPresenter( this, this );

		//UI References
		mTabLayout = ( TabLayout ) findViewById( R.id.projectBoardActivity_tabLayout );
		mViewPager = ( ViewPager ) findViewById( R.id.projectBoardActivity_viewPager );
		mCreateTaskButton = ( FloatingActionButton ) findViewById( R.id.projectBoardActivity_createTaskButton );

		//Tab Config
		mProjectBoardFragmentPagerAdapter = new ProjectBoardFragmentPagerAdapter( getSupportFragmentManager() );
		presenter.userLoadsBoard();
		mViewPager.setAdapter( mProjectBoardFragmentPagerAdapter );
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
		runOnUiThread( new Runnable1Param< ProjectBoardView >( this ){
			@Override
			public void run(){
				startActivity( new Intent( getParam1(), CreateTaskView.class ) );
			}
		} );
	}

	@Override
	public void noInternetAccessValidation(){
		ToastLog.makeWarn( this, getString( R.string.error_noInternetAccess ), Toast.LENGTH_LONG );
	}

	@Override
	public void displayDefaultErrorMessage(){
		ToastLog.makeWarn( this, getString( R.string.error_defaultError ), Toast.LENGTH_LONG );
	}

	@Override
	public void setTabTitles( Column[] columns ){
		mProjectBoardFragmentPagerAdapter.setData( columns );
	}

	@Override
	protected void onRestart(){
		super.onRestart();
		finish();
		startActivity( getIntent() );
	}

}
