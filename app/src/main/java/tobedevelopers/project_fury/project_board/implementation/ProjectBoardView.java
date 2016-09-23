package tobedevelopers.project_fury.project_board.implementation;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Toast;

import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
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
	@Bind( R.id.projectBoardActivity_tabLayout )
	protected TabLayout mTabLayout;
	@Bind( R.id.projectBoardActivity_viewPager )
	protected ViewPager mViewPager;
	@Bind( R.id.projectBoardActivity_createTaskButton )
	protected FloatingActionButton mCreateTaskButton;

	private ProjectBoardFragmentPagerAdapter mAdapter;
	private ProgressDialog mProgressDialog;

	private ProjectBoardContract.Presenter presenter;

	@Override
	protected void onCreate( Bundle savedInstanceState ){
		setTitle( Model.getSelectedProject().getName() + " Board" );
		setContentView( R.layout.activity_project_board );
		super.onCreate( savedInstanceState );
		presenter = new ProjectBoardPresenter( this, this );

		ButterKnife.bind( this );

		//UI References
		mProgressDialog = new ProgressDialog( this );

		//Tab Config
		mAdapter = new ProjectBoardFragmentPagerAdapter( getSupportFragmentManager() );
		mViewPager.setAdapter( mAdapter );
		mTabLayout.setupWithViewPager( mViewPager );

		presenter.userLoadsBoard();
	}

	//Button Listener
	@OnClick( { R.id.projectBoardActivity_createTaskButton } )
	public void onUserSelectAButton( View view ){
		switch( view.getId() ){
			case R.id.projectBoardActivity_createTaskButton:
				mCreateTaskButton.setEnabled( false );
				presenter.userSelectCreateTask();
				break;
			default:
				ToastLog.makeError( this, getString( R.string.app_name ), String.format( getString( R.string.error_message ), getTitle() ), Toast.LENGTH_SHORT );
				break;
		}
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
		super.noInternetAccessValidation();
		mCreateTaskButton.setEnabled( true );
	}

	@Override
	public void defaultErrorMessage(){
		super.defaultErrorMessage();
		mCreateTaskButton.setEnabled( true );
	}

	@Override
	public void setTabTitles( Column[] columns ){
		Arrays.sort( columns );
		mAdapter.setData( columns );
	}

	@Override
	public void showProjectUpdatingInProgress(){
		runOnUiThread( new Runnable1Param< ProjectBoardView >( this ){
			@Override
			public void run(){
				mProgressDialog.setProgressStyle( ProgressDialog.STYLE_SPINNER );
				mProgressDialog.setMessage( "Updating..." );
				mProgressDialog.setIndeterminate( true );
				mProgressDialog.setCancelable( false );
				mProgressDialog.show();
			}
		} );
	}

	@Override
	public void hideProjectUpdatingInProgress(){
		runOnUiThread( new Runnable1Param< ProjectBoardView >( this ){
			@Override
			public void run(){
				mProgressDialog.dismiss();
			}
		} );
	}

}
