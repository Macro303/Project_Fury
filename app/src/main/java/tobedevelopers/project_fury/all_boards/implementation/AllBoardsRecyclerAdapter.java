package tobedevelopers.project_fury.all_boards.implementation;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.ref.WeakReference;
import java.util.Arrays;

import tobedevelopers.project_fury.R;
import tobedevelopers.project_fury.all_boards.AllBoardsContract;
import tobedevelopers.project_fury.model.Project;

/**
 * Created by Macro303 on 13/08/2016.
 */
public class AllBoardsRecyclerAdapter extends RecyclerView.Adapter< AllBoardsHolder >{

	private WeakReference< AllBoardsContract.Navigation > navigationWeakReference;

	private Project[] projects;

	public AllBoardsRecyclerAdapter( AllBoardsContract.Navigation navigation ){
		this.navigationWeakReference = new WeakReference<>( navigation );
	}

	public void setData( Project[] projects ){
		this.projects = projects;
		Arrays.sort( this.projects, Project.comparator );
		notifyDataSetChanged();
	}

	@Override
	public AllBoardsHolder onCreateViewHolder( ViewGroup parent, int viewType ){
		View view = LayoutInflater.from( parent.getContext() ).inflate( R.layout.card_all_boards, parent, false );
		return new AllBoardsHolder( view, navigationWeakReference.get() );
	}

	@Override
	public void onBindViewHolder( AllBoardsHolder holder, int position ){
		if( projects != null && projects.length > 0 )
			holder.bindView( projects[ position ] );
		else
			holder.bindView( null );
	}

	@Override
	public int getItemCount(){
		return projects != null && projects.length != 0 ? projects.length : 0;
	}


}
