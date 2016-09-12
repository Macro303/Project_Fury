package tobedevelopers.project_fury.project_board.implementation;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tobedevelopers.project_fury.R;
import tobedevelopers.project_fury.model.Model;
import tobedevelopers.project_fury.model.Task;

/**
 * Created by Macro303 on 13/08/2016.
 */
public class ProjectBoardFragment extends Fragment{

	private static final String PAGE_KEY = "Page";
	//UI References
	private RecyclerView mRecyclerView;
	private ProjectBoardRecyclerAdapter mRecyclerAdapter;
	private Task[] tasks;
	private int mPage;

	public static ProjectBoardFragment newInstance( int page ){
		Bundle args = new Bundle();
		args.putInt( PAGE_KEY, page );
		ProjectBoardFragment fragment = new ProjectBoardFragment();
		fragment.setArguments( args );
		return fragment;
	}

	@Override
	public void onCreate( Bundle savedInstanceState ){
		super.onCreate( savedInstanceState );
		mPage = getArguments().getInt( PAGE_KEY );
	}

	@Override
	public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ){
		View view = inflater.inflate( R.layout.fragment_project_board_recycler, container, false );
		mRecyclerView = ( RecyclerView ) view.findViewById( R.id.projectBoardFragment_recyclerView );
		mRecyclerView.setHasFixedSize( true );
		mRecyclerView.setLayoutManager( new LinearLayoutManager( getContext() ) );
		mRecyclerAdapter = new ProjectBoardRecyclerAdapter( getActivity() );
		mRecyclerView.setAdapter( mRecyclerAdapter );
		mRecyclerAdapter.getData( Model.getSelectedColumns()[ mPage ] );
		mRecyclerView.addOnScrollListener( new RecyclerView.OnScrollListener(){
			@Override
			public void onScrolled( RecyclerView recyclerView, int dx, int dy ){
				FloatingActionButton mCreateTaskButton = ( FloatingActionButton ) getActivity().findViewById( R.id.projectBoardActivity_createTaskButton );
				if( dy > 0 )
					mCreateTaskButton.hide();
				else if( dy < 0 )
					mCreateTaskButton.show();
			}
		} );
		return view;
	}
}
