package tobedevelopers.project_fury.backlog.implementation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import tobedevelopers.project_fury.R;

/**
 * Created by A on 9/23/2016.
 */
public class BacklogEmptyFragment extends Fragment{

	private TextView mNoTasksTextView;

	public static BacklogEmptyFragment newInstance(){
		BacklogEmptyFragment fragment = new BacklogEmptyFragment();
		return fragment;
	}

	@Override
	public View onCreateView( LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState ){
		return inflater.inflate( R.layout.fragment_backlog_empty, container, false );
	}

	@Override
	public void onViewCreated( View view, Bundle savedInstanceState ){
		super.onViewCreated( view, savedInstanceState );
		mNoTasksTextView = ( TextView ) view.findViewById( R.id.backlogFragment_taskCard_noTask );
	}
}
