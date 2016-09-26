package tobedevelopers.project_fury.backlog.implementation;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tobedevelopers.project_fury.R;
import tobedevelopers.project_fury.model.Column;
import tobedevelopers.project_fury.model.Task;

/**
 * Created by Macro303 on 12/08/2016.
 */
public class BacklogRecyclerAdapter extends RecyclerView.Adapter< BacklogHolder >{

	private Task[] tasks;
	private FragmentActivity activity;
	private Column[] columns;

	public BacklogRecyclerAdapter( FragmentActivity activity ){
		this.activity = activity;
	}

	public void setData( Column[] columns, Task[] tasks ){
		this.columns = columns;
		this.tasks = tasks;
	}

	@Override
	public int getItemCount(){
		return tasks != null && tasks.length != 0 ? tasks.length : 1;
	}

	@Override
	public BacklogHolder onCreateViewHolder( ViewGroup parent, int viewType ){
		View view = LayoutInflater.from( parent.getContext() ).inflate( R.layout.card_backlog, parent, false );
		return new BacklogHolder( view, activity );
	}

	@Override
	public void onBindViewHolder( final BacklogHolder holder, int position ){
		if( tasks != null && tasks.length > 0 ){
			holder.bindView( tasks[ position ], columns );
		}else{
			holder.bindView( null, null );
		}
	}
}
