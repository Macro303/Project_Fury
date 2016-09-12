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
import tobedevelopers.project_fury.model.Project;

/**
 * Created by Macro303 on 12/08/2016.
 */
public class BacklogFragment extends Fragment{

	//UI References
	private RecyclerView mRecyclerView;

	private int mPage;
	private Project project;

	public static BacklogFragment newInstance( int page, Project project ){
		Bundle args = new Bundle();
		args.putInt( "Page", page );
		args.putParcelable( "Current", project );
		BacklogFragment fragment = new BacklogFragment();
		fragment.setArguments( args );
		return fragment;
	}

	@Override
	public void onCreate( Bundle savedInstanceState ){
		super.onCreate( savedInstanceState );
		mPage = getArguments().getInt( "Page" );
		project = getArguments().getParcelable( "Current" );
	}

	@Override
	public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ){
		View view = inflater.inflate( R.layout.fragment_backlog_recycler, container, false );
		mRecyclerView = ( RecyclerView ) view.findViewById( R.id.backlogFragment_recyclerView );
		mRecyclerView.setHasFixedSize( true );
		mRecyclerView.setLayoutManager( new LinearLayoutManager( getContext() ) );
		mRecyclerView.setAdapter( new BacklogRecyclerAdapter( getActivity(), project ) );
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
