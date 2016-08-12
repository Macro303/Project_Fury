package tobedevelopers.project_fury.backlog;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tobedevelopers.project_fury.R;

/**
 * Created by Macro303 on 12/08/2016.
 */
public class BacklogFragment extends Fragment{

	//UI References
	private RecyclerView mRecyclerView;

	private int mPage;
	private BacklogRecyclerAdapter mRecyclerAdapter;
	private LinearLayoutManager mLinearLayoutManager;

	public static BacklogFragment newInstance( int page ){
		Bundle args = new Bundle();
		args.putInt( "Page", page );
		BacklogFragment fragment = new BacklogFragment();
		fragment.setArguments( args );
		return fragment;
	}

	@Override
	public void onCreate( Bundle savedInstanceState ){
		super.onCreate( savedInstanceState );
		mPage = getArguments().getInt( "Page" );
	}

	@Override
	public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ){
		View view = inflater.inflate( R.layout.fragment_backlog_recycler, container, false );
		mRecyclerView = ( RecyclerView ) view.findViewById( R.id.backlogFragment_recyclerView );
		mRecyclerView.setHasFixedSize( true );
		mLinearLayoutManager = new LinearLayoutManager( getContext() );
		mRecyclerView.setLayoutManager( mLinearLayoutManager );
		if( mPage == 0 )
			mRecyclerAdapter = new BacklogRecyclerAdapter( getActivity()/*Fragment Information*/ );
		else if( mPage == 1 )
			mRecyclerAdapter = new BacklogRecyclerAdapter( getActivity()/*Fragment Information*/ );
		else if( mPage == 2 )
			mRecyclerAdapter = new BacklogRecyclerAdapter( getActivity()/*Fragment Information*/ );
		else if( mPage == 3 )
			mRecyclerAdapter = new BacklogRecyclerAdapter( getActivity()/*Fragment Information*/ );
		else if( mPage == 4 )
			mRecyclerAdapter = new BacklogRecyclerAdapter( getActivity()/*Fragment Information*/ );
		mRecyclerView.setAdapter( mRecyclerAdapter );
		mRecyclerView.addOnScrollListener( new RecyclerView.OnScrollListener(){
			@Override
			public void onScrolled( RecyclerView recyclerView, int dx, int dy ){
				FloatingActionButton mAddTaskButton = ( FloatingActionButton ) getActivity().findViewById( R.id.backlogActivity_addTaskButton );
				if( dy > 0 )
					mAddTaskButton.hide();
				else if( dy < 0 )
					mAddTaskButton.show();
			}
		} );
		return view;
	}
}
