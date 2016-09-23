package tobedevelopers.project_fury.project_board.implementation;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Arrays;

import tobedevelopers.project_fury.R;
import tobedevelopers.project_fury.model.Model;
import tobedevelopers.project_fury.model.Task;
import tobedevelopers.project_fury.model.TaskResponse;

/**
 * Created by Macro303 on 13/08/2016.
 */
public class ProjectBoardFragment extends Fragment{

	private static final String PAGE_KEY = "Page";

	//UI References
	private RecyclerView mRecyclerView;
	private ProjectBoardRecyclerAdapter mRecyclerAdapter;
	private int mPage;

	// Model references
	private Model model;

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
		this.model = new Model();
	}

	@Override
	public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ){
		return inflater.inflate( R.layout.fragment_project_board_recycler, container, false );
	}

	@Override
	public void onViewCreated( View view, @Nullable Bundle savedInstanceState ){
		super.onViewCreated( view, savedInstanceState );
		mRecyclerView = ( RecyclerView ) view.findViewById( R.id.projectBoardFragment_recyclerView );
		mRecyclerView.setHasFixedSize( true );

		mRecyclerAdapter = new ProjectBoardRecyclerAdapter( getActivity() );
		mRecyclerView.setAdapter( mRecyclerAdapter );

		mRecyclerView.setLayoutManager( new LinearLayoutManager( getActivity() ) );

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
	}

	@Override
	public void onResume(){
		super.onResume();
		new LoadTasksTask().execute( Model.getSelectedColumns().get( mPage ).getProjectID(), Model.getSelectedColumns().get( mPage ).getColumnID() );
	}

	private class LoadTasksTask extends AsyncTask< String, Void, TaskResponse >{

		@Override
		protected void onPreExecute(){
			super.onPreExecute();
		}

		@Override
		protected TaskResponse doInBackground( String... strings ){
			return model.getAllColumnTasks( strings[ 0 ], strings[ 1 ] );
		}

		@Override
		protected void onPostExecute( TaskResponse response ){
			super.onPostExecute( response );
			Task[] tasks = response.getTasks();
			Arrays.sort( tasks );
			mRecyclerAdapter.setData( tasks );
			mRecyclerAdapter.notifyDataSetChanged();
		}
	}
}
