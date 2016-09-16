package tobedevelopers.project_fury.backlog.implementation;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tobedevelopers.project_fury.R;
import tobedevelopers.project_fury.model.Column;
import tobedevelopers.project_fury.model.Model;
import tobedevelopers.project_fury.model.Task;
import tobedevelopers.project_fury.task_info.implementation.TaskInfoView;

/**
 * Created by Macro303 on 12/08/2016.
 */
public class BacklogRecyclerAdapter extends RecyclerView.Adapter< BacklogHolder >{

	private Task[] tasks;
	private FragmentActivity activity;
	private Column[] columns;

	public BacklogRecyclerAdapter( FragmentActivity activity, Task[] tasks, Column[] columns ){
		this.activity = activity;
		this.tasks = tasks;
		this.columns = columns;
	}

	@Override
	public int getItemCount(){
		return tasks != null && tasks.length != 0 ? tasks.length : 1;
	}

	@Override
	public BacklogHolder onCreateViewHolder( ViewGroup parent, int viewType ){
		View view = LayoutInflater.from( parent.getContext() ).inflate( R.layout.card_backlog, parent, false );
		return new BacklogHolder( view );
	}

	@Override
	public void onBindViewHolder( final BacklogHolder holder, int position ){
		if( tasks != null && tasks.length != 0 ){
			holder.mTaskName.setVisibility( View.VISIBLE );
			holder.mTaskAssignee.setVisibility( View.VISIBLE );
			holder.mTaskPriority.setVisibility( View.VISIBLE );
			holder.mTaskColumn.setVisibility( View.VISIBLE );
			holder.mNoTaskViewTextView.setVisibility( View.INVISIBLE );

			holder.mCardView.setEnabled( true );

			final Task current = tasks[ position ];
			holder.mCardView.setOnClickListener( new View.OnClickListener(){
				@Override
				public void onClick( View view ){
					holder.mCardView.setEnabled( false );
					Model.setSelectedTask( current );
					new AsyncClass().executeOnExecutor( AsyncTask.THREAD_POOL_EXECUTOR );
				}
			} );
			if( current.getName().length() <= 16 )
				holder.mTaskName.setText( current.getName() );
			else
				holder.mTaskName.setText( current.getName().substring( 0, 16 ) + "..." );
			if( current.getAssignee().length() <= 16 )
				holder.mTaskAssignee.setText( current.getAssignee() );
			else
				holder.mTaskAssignee.setText( current.getAssignee().substring( 0, 16 ) + "..." );
			holder.mTaskPriority.setText( current.getPriority().getNameValue() );
			holder.mTaskColumn.setText( getColumnName( current ) );
		}else{
			holder.mTaskName.setVisibility( View.INVISIBLE );
			holder.mTaskAssignee.setVisibility( View.INVISIBLE );
			holder.mTaskPriority.setVisibility( View.INVISIBLE );
			holder.mTaskColumn.setVisibility( View.INVISIBLE );
			holder.mNoTaskViewTextView.setVisibility( View.VISIBLE );
		}

	}

	@NonNull
	private String getColumnName( Task current ){
		for( Column column : columns ){
			if( column.getColumnID().equals( current.getColumnID() ) )
				return column.getName();
		}
		return null;
	}

	private void navigateAway(){
		activity.startActivity( new Intent( activity, TaskInfoView.class ) );
	}

	private class AsyncClass extends AsyncTask< Void, Void, Void >{
		@Override
		protected Void doInBackground( Void... voids ){
			Model.setSelectedProject( new Model().getProject( Model.getSelectedTask().getProjectID() ).getProjects()[ 0 ] );
			return null;
		}

		@Override
		protected void onPostExecute( Void aVoid ){
			super.onPostExecute( aVoid );
			navigateAway();
		}

		@Override
		protected void onPreExecute(){
			super.onPreExecute();
		}
	}
}
