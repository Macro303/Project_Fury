package tobedevelopers.project_fury.backlog;

import android.os.AsyncTask;
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
import tobedevelopers.project_fury.model.TaskResponse;

/**
 * Created by Macro303 on 12/08/2016.
 */
public class BacklogFragment extends Fragment{

	//UI References
	private RecyclerView mRecyclerView;
	private Task[] tasks;

	public BacklogFragment setDataAgain( Task[] tasks ){
		this.tasks = tasks;
		return this;
	}

	@Override
	public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ){
		View view = inflater.inflate( R.layout.fragment_backlog_recycler, container, false );
		mRecyclerView = ( RecyclerView ) view.findViewById( R.id.backlogFragment_recyclerView );
		mRecyclerView.setHasFixedSize( true );
		mRecyclerView.setLayoutManager( new LinearLayoutManager( getContext() ) );
		mRecyclerView.setAdapter( new BacklogRecyclerAdapter( getContext(), tasks ) );
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
		initializeData();
		return view;
	}

	public void initializeData(){
		new AsyncTask< Void, Void, TaskResponse >(){
			@Override
			protected TaskResponse doInBackground( Void... voids ){
				return new Model().getAllProjectTasks( Model.getSelectedProject().getProjectID() );
			}

			@Override
			protected void onPostExecute( TaskResponse taskResponse ){
				super.onPostExecute( taskResponse );
				setData( taskResponse.getTasks() );
			}

			@Override
			protected void onPreExecute(){
				super.onPreExecute();
			}
		}.executeOnExecutor( AsyncTask.THREAD_POOL_EXECUTOR );
	}

	public void setData( Task[] tasks ){
		this.tasks = tasks;
		mRecyclerView.getAdapter().notifyDataSetChanged();
	}
}
