package tobedevelopers.project_fury.all_boards.implementation;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import tobedevelopers.project_fury.BaseNavigationView;
import tobedevelopers.project_fury.R;
import tobedevelopers.project_fury.all_boards.AllBoardsContract;
import tobedevelopers.project_fury.all_boards.AllBoardsRecyclerAdapter;

/**
 * Created by Macro303 on 12/08/2016.
 */
public class AllBoardsView extends BaseNavigationView implements AllBoardsContract.View, AllBoardsContract.Navigation{

	//UI References
	private RecyclerView mRecyclerView;

	private AllBoardsContract.Presenter presenter;

	@Override
	protected void onCreate( Bundle savedInstanceState ){
		setTitle( getString( R.string.title_activity_allBoards ) );
		setContentView( R.layout.activity_all_boards );
		super.onCreate( savedInstanceState );
		presenter = new AllBoardsPresenter( this, this );

		//UI References
		mRecyclerView = ( RecyclerView ) findViewById( R.id.allBoardsActivity_recyclerView );

		//Recycler Config
		mRecyclerView.setHasFixedSize( true );
		mRecyclerView.setLayoutManager( new LinearLayoutManager( getApplicationContext() ) );
		AllBoardsRecyclerAdapter recyclerAdapter = new AllBoardsRecyclerAdapter( this/*Fragment Information*/ );
		mRecyclerView.setAdapter( recyclerAdapter );
	}
}
