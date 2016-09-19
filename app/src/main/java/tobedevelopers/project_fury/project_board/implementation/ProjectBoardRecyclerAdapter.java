package tobedevelopers.project_fury.project_board.implementation;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tobedevelopers.project_fury.R;
import tobedevelopers.project_fury.model.Model;
import tobedevelopers.project_fury.model.Task;
import tobedevelopers.project_fury.task_info.implementation.TaskInfoView;

/**
 * Created by Macro303 on 13/08/2016.
 */
public class ProjectBoardRecyclerAdapter extends RecyclerView.Adapter< ProjectBoardHolder >{

	private FragmentActivity activity;
	private Task[] tasks;

	public ProjectBoardRecyclerAdapter( FragmentActivity activity ){
		this.activity = activity;
	}


	public void setData( Task[] tasks ){
		this.tasks = tasks;
	}

	@Override
	public ProjectBoardHolder onCreateViewHolder( ViewGroup parent, int viewType ){
		View view = LayoutInflater.from( parent.getContext() ).inflate( R.layout.card_project_board, parent, false );
		return new ProjectBoardHolder( view );
	}

	@Override
	public void onBindViewHolder( final ProjectBoardHolder holder, final int position ){
		if( tasks != null && tasks.length != 0 ){
			holder.mNoTaskViewTextView.setVisibility( View.INVISIBLE );
			holder.mAssigneeTextView.setVisibility( View.VISIBLE );
			holder.mPriorityTextView.setVisibility( View.VISIBLE );
			holder.mTaskTextView.setVisibility( View.VISIBLE );

			holder.mCardView.setEnabled( true );

			holder.mTaskTextView.setText( tasks[ position ].getName() );
			holder.mPriorityTextView.setText( tasks[ position ].getPriority().getNameValue() );
			holder.mAssigneeTextView.setText( tasks[ position ].getAssignee() );
			holder.mCardView.setOnClickListener( new View.OnClickListener(){
				@Override
				public void onClick( View view ){
					holder.mCardView.setEnabled( false );
					Model.setSelectedTask( tasks[ position ] );
					activity.startActivity( new Intent( activity, TaskInfoView.class ) );
				}
			} );
		}else{
			holder.mNoTaskViewTextView.setVisibility( View.VISIBLE );
			holder.mAssigneeTextView.setVisibility( View.INVISIBLE );
			holder.mPriorityTextView.setVisibility( View.INVISIBLE );
			holder.mTaskTextView.setVisibility( View.INVISIBLE );
		}


	}

	@Override
	public int getItemCount(){
		return tasks != null && tasks.length != 0 ? tasks.length : 1;
	}
}