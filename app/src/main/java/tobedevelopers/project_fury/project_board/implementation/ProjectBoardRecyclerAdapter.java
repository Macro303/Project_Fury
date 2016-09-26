package tobedevelopers.project_fury.project_board.implementation;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tobedevelopers.project_fury.R;
import tobedevelopers.project_fury.model.Task;

/**
 * Created by Macro303 on 13/08/2016.
 */
public class ProjectBoardRecyclerAdapter extends RecyclerView.Adapter< ProjectBoardViewHolder >{

	private FragmentActivity activity;
	private Task[] tasks;

	public ProjectBoardRecyclerAdapter( FragmentActivity activity ){
		this.activity = activity;
	}


	public void setData( Task[] tasks ){
		this.tasks = tasks;
		notifyDataSetChanged();
	}

	@Override
	public ProjectBoardViewHolder onCreateViewHolder( ViewGroup parent, int viewType ){
		View view = LayoutInflater.from( parent.getContext() ).inflate( R.layout.card_project_board, parent, false );
		return new ProjectBoardViewHolder( view, activity );
	}

	@Override
	public void onBindViewHolder( final ProjectBoardViewHolder holder, final int position ){
		if( tasks != null && tasks.length > 0 ){
			holder.bindView( tasks[ position ] );
		}else{
			holder.bindView( null );
		}
	}

	@Override
	public int getItemCount(){
		return tasks != null && tasks.length != 0 ? tasks.length : 1;
	}
}
