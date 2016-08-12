package tobedevelopers.project_fury.backlog;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tobedevelopers.project_fury.R;
import tobedevelopers.project_fury.task_info.implementation.TaskInfoView;

/**
 * Created by Macro303 on 12/08/2016.
 */
public class BacklogRecyclerAdapter extends RecyclerView.Adapter< BacklogHolder >{

	private FragmentActivity activity;

	public BacklogRecyclerAdapter( FragmentActivity activity ){
		this.activity = activity;
	}

	@Override
	public void onBindViewHolder( BacklogHolder holder, int position ){
		holder.mCardView.setOnClickListener( new View.OnClickListener(){
			@Override
			public void onClick( View view ){
				activity.startActivity( new Intent( activity, TaskInfoView.class ) );
			}
		} );
		holder.mTaskInfoButton.setOnClickListener( new View.OnClickListener(){
			@Override
			public void onClick( View view ){
				activity.startActivity( new Intent( activity, TaskInfoView.class ) );
			}
		} );
	}

	@Override
	public int getItemCount(){
		return 5;
	}

	@Override
	public BacklogHolder onCreateViewHolder( ViewGroup parent, int viewType ){
		View view = LayoutInflater.from( parent.getContext() ).inflate( R.layout.card_backlog, parent, false );
		return new BacklogHolder( view );
	}
}
