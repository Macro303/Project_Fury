package tobedevelopers.project_fury.all_boards.implementation;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
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
	@Bind( R.id.allBoardsActivity_recyclerView )
	protected RecyclerView mRecyclerView;
	@Bind( R.id.allBoardsActivity_createProjectButton )
	protected FloatingActionButton mCreateProjectButton;

	private AllBoardsRecyclerAdapter mAdapter;
	private ProgressDialog mProgressDialog;

	private AllBoardsContract.Presenter presenter;

	@Override
	protected void onCreate( Bundle savedInstanceState ){
		setTitle( getString( R.string.title_activity_allBoards ) );
		setContentView( R.layout.activity_all_boards );
		super.onCreate( savedInstanceState );
		presenter = new AllBoardsPresenter( this, this );

		ButterKnife.bind( this );

		//UI References
		mProgressDialog = new ProgressDialog( this );

		//Recycler Config
		mRecyclerView.setHasFixedSize( true );
		mAdapter = new AllBoardsRecyclerAdapter( this );
		mRecyclerView.setAdapter( mAdapter );
		mRecyclerView.setLayoutManager( new LinearLayoutManager( getApplicationContext() ) );

		presenter.userOpensBoard();
	}

	//Button Listener
	@OnClick( { R.id.allBoardsActivity_createProjectButton } )
	public void onUserSelectAButton( View view ){
		switch( view.getId() ){
			case R.id.allBoardsActivity_createProjectButton:
				mCreateProjectButton.setEnabled( false );
				presenter.userSelectCreateProject();
				break;
			default:
				ToastLog.makeError( this, getString( R.string.app_name ), String.format( getString( R.string.error_message ), getTitle() ), Toast.LENGTH_SHORT );
				break;
		}
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
}
