package tobedevelopers.project_fury.all_boards.implementation;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import tobedevelopers.project_fury.BaseNavigationView;
import tobedevelopers.project_fury.R;
import tobedevelopers.project_fury.ToastLog;
import tobedevelopers.project_fury.all_boards.AllBoardsContract;
import tobedevelopers.project_fury.create_project.implementation.CreateProjectView;
import tobedevelopers.project_fury.model.Project;
import tobedevelopers.project_fury.project_board.implementation.ProjectBoardView;
import tobedevelopers.project_fury.project_info.implementation.ProjectInfoView;
import tobedevelopers.project_fury.runnable_param.Runnable1Param;

/**
 * Created by Macro303 on 12/08/2016.
 */
public class AllBoardsView extends BaseNavigationView implements AllBoardsContract.View, AllBoardsContract.Navigation{

	//UI References
	private RecyclerView mRecyclerView;
	private AllBoardsRecyclerAdapter mAdapter;

	private FloatingActionButton mCreateProjectButton;

	private AllBoardsContract.Presenter presenter;

	private ProgressDialog mProgressDialog;

	@Override
	protected void onCreate( Bundle savedInstanceState ){
		setTitle( getString( R.string.title_activity_allBoards ) );
		setContentView( R.layout.activity_all_boards );
		super.onCreate( savedInstanceState );
		presenter = new AllBoardsPresenter( this, this );

		//UI References
		mRecyclerView = ( RecyclerView ) findViewById( R.id.allBoardsActivity_recyclerView );
		mCreateProjectButton = ( FloatingActionButton ) findViewById( R.id.allBoards_createProjectButton );
		mProgressDialog = new ProgressDialog( this );

		//Recycler Config
		mRecyclerView.setHasFixedSize( true );
		mAdapter = new AllBoardsRecyclerAdapter( this );
		mRecyclerView.setAdapter( mAdapter );
		mRecyclerView.setLayoutManager( new LinearLayoutManager( getApplicationContext() ) );

		presenter.userOpensBoard();

		//Button config
		mCreateProjectButton.setOnClickListener( new View.OnClickListener(){
			@Override
			public void onClick( View view ){
				mCreateProjectButton.setEnabled( false );
				presenter.userSelectCreateProject();
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
	public void setRecyclerItems( Project[] projects ){
		mAdapter.setData( projects );
	}

	@Override
	public void showProjectUpdatingInProgress(){
		runOnUiThread( new Runnable1Param< AllBoardsView >( this ){
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
		runOnUiThread( new Runnable1Param< AllBoardsView >( this ){
			@Override
			public void run(){
				mProgressDialog.dismiss();
			}
		} );
	}

	@Override
	public void navigateToProjectInfo(){
		runOnUiThread( new Runnable1Param< AllBoardsView >( this ){
			@Override
			public void run(){
				startActivity( new Intent( getParam1(), ProjectInfoView.class ) );
			}
		} );
	}

	@Override
	public void navigateToProjectBoard(){
		runOnUiThread( new Runnable1Param< AllBoardsView >( this ){
			@Override
			public void run(){
				startActivity( new Intent( getParam1(), ProjectBoardView.class ) );
			}
		} );
	}

	@Override
	public void navigateToCreateProject(){
		runOnUiThread( new Runnable1Param< AllBoardsView >( this ){
			@Override
			public void run(){
				startActivity( new Intent( getParam1(), CreateProjectView.class ) );
			}
		} );
	}

	@Override
	protected void onRestart(){
		super.onRestart();
		finish();
		startActivity( getIntent() );
	}
}
