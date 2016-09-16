package tobedevelopers.project_fury.backlog.implementation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tobedevelopers.project_fury.R;
import tobedevelopers.project_fury.model.Column;
import tobedevelopers.project_fury.model.Project;
import tobedevelopers.project_fury.model.Task;

/**
 * Created by Macro303 on 12/08/2016.
 */
public class BacklogFragment extends Fragment{

	//UI References
	private RecyclerView mRecyclerView;
	private Task[] tasks;
	private Column[] columns;
	private Project project;

	public BacklogFragment setData( Task[] tasks, Column[] columns, Project project ){
		this.tasks = tasks;
		this.columns = columns;
		this.project = project;
		return this;
	}

	@Override
	public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ){
		View view = inflater.inflate( R.layout.fragment_backlog_recycler, container, false );
		mRecyclerView = ( RecyclerView ) view.findViewById( R.id.backlogFragment_recyclerView );
		mRecyclerView.setHasFixedSize( true );
		mRecyclerView.setLayoutManager( new LinearLayoutManager( getContext() ) );
		mRecyclerView.setAdapter( new BacklogRecyclerAdapter( getActivity(), tasks, columns ) );
//		mRecyclerView.addOnScrollListener( new RecyclerView.OnScrollListener(){
//			@Override
//			public void onScrolled( RecyclerView recyclerView, int dx, int dy ){
//				FloatingActionButton mCreateTaskButton = ( FloatingActionButton ) getActivity().findViewById( R.id.backlogActivity_createTaskButton );
//				if( dy > 0 )
//					mCreateTaskButton.hide();
//				else if( dy < 0 )
//					mCreateTaskButton.show();
//			}
//		} );
		return view;
	}
}
