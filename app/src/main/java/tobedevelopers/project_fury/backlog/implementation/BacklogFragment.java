package tobedevelopers.project_fury.backlog.implementation;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tobedevelopers.project_fury.R;
import tobedevelopers.project_fury.model.ColumnResponse;
import tobedevelopers.project_fury.model.Model;
import tobedevelopers.project_fury.model.TaskResponse;

/**
 * Created by Macro303 on 12/08/2016.
 */
public class BacklogFragment extends Fragment{

	private static final String PAGE_KEY = "Page";
	//UI References
	private RecyclerView mRecyclerView;
	private BacklogRecyclerAdapter mRecyclerAdapter;
	private Model model;
	private int mPage;

	public static BacklogFragment newInstance( int page ){
		Bundle args = new Bundle();
		args.putInt( PAGE_KEY, page );
		BacklogFragment fragment = new BacklogFragment();
		fragment.setArguments( args );
		return fragment;
	}

	@Override
	public void onCreate( @Nullable Bundle savedInstanceState ){
		super.onCreate( savedInstanceState );
		mPage = getArguments().getInt( PAGE_KEY );
		this.model = new Model();
	}

	@Override
	public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ){
		return inflater.inflate( R.layout.fragment_backlog_recycler, container, false );
	}

	@Override
	public void onViewCreated( View view, @Nullable Bundle savedInstanceState ){
		super.onViewCreated( view, savedInstanceState );
		mRecyclerView = ( RecyclerView ) view.findViewById( R.id.backlogFragment_recyclerView );
		mRecyclerView.setHasFixedSize( true );

		mRecyclerAdapter = new BacklogRecyclerAdapter( getActivity() );
		mRecyclerView.setAdapter( mRecyclerAdapter );

		mRecyclerView.setLayoutManager( new LinearLayoutManager( getContext() ) );
	}

	@Override
	public void onResume(){
		super.onResume();
		new LoadColumnsAndTasksTask().execute( Model.getSelectedProjects().get( mPage ).getProjectID() );
	}

	private class LoadColumnsAndTasksTask extends AsyncTask< String, Void, Boolean >{

		@Override
		protected void onPreExecute(){
			super.onPreExecute();
		}

		@Override
		protected Boolean doInBackground( String... inputs ){
			ColumnResponse columnResponse = model.getAllProjectColumns( inputs[ 0 ] );
			TaskResponse taskResponse = model.getAllProjectTasks( inputs[ 0 ] );
			if( columnResponse.getMessage().equals( "Success" ) && taskResponse.getMessage().equals( "Success" ) ){
				mRecyclerAdapter.setData( columnResponse.getColumns(), taskResponse.getTasks() );
				return true;
			}
			return false;
		}

		@Override
		protected void onPostExecute( Boolean result ){
			super.onPostExecute( result );
			if( result )
				mRecyclerAdapter.notifyDataSetChanged();
		}

		@Override
		protected void onCancelled( Boolean result ){
			super.onCancelled( result );
		}
	}

}
