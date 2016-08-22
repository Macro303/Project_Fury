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
		mRecyclerView.setLayoutManager( new LinearLayoutManager( getContext() ) );
		BacklogRecyclerAdapter recyclerAdapter = null;
		if( mPage == 0 )
			recyclerAdapter = new BacklogRecyclerAdapter( getActivity()/*Fragment Information*/ );
		else if( mPage == 1 )
			recyclerAdapter = new BacklogRecyclerAdapter( getActivity()/*Fragment Information*/ );
		else if( mPage == 2 )
			recyclerAdapter = new BacklogRecyclerAdapter( getActivity()/*Fragment Information*/ );
		else if( mPage == 3 )
			recyclerAdapter = new BacklogRecyclerAdapter( getActivity()/*Fragment Information*/ );
		else if( mPage == 4 )
			recyclerAdapter = new BacklogRecyclerAdapter( getActivity()/*Fragment Information*/ );
		mRecyclerView.setAdapter( recyclerAdapter );
		mRecyclerView.addOnScrollListener( new RecyclerView.OnScrollListener(){
			@Override
			public void onScrolled( RecyclerView recyclerView, int dx, int dy ){
				FloatingActionButton mCreateTaskButton = ( FloatingActionButton ) getActivity().findViewById( R.id.backlogActivity_createTaskButton );
				if( dy > 0 )
					mCreateTaskButton.hide();
				else if( dy < 0 )
					mCreateTaskButton.show();
			}
		} );
		return view;
	}
}
