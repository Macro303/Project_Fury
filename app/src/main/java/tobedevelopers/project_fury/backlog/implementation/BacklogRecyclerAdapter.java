package tobedevelopers.project_fury.backlog.implementation;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tobedevelopers.project_fury.R;
import tobedevelopers.project_fury.model.Column;
import tobedevelopers.project_fury.model.Model;
import tobedevelopers.project_fury.model.Project;
import tobedevelopers.project_fury.model.Task;
import tobedevelopers.project_fury.task_info.implementation.TaskInfoView;

/**
 * Created by Macro303 on 12/08/2016.
 */
public class BacklogRecyclerAdapter extends RecyclerView.Adapter< BacklogHolder >{

	private Task[] tasks;
	private FragmentActivity activity;
	private Column[] columns;
	private Model model;

	public BacklogRecyclerAdapter( FragmentActivity activity ){
		this.activity = activity;
		this.model = new Model();
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
					new LoadTaskInfoTask().execute( current );
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

	private String getColumnName( Task current ){
		for( Column column : columns )
			if( column.getColumnID().equals( current.getColumnID() ) )
				return column.getName();
		return null;
	}

	private class LoadTaskInfoTask extends AsyncTask< Task, Void, Project >{
		@Override
		protected Project doInBackground( Task... inputs ){
			return model.getProject( inputs[ 0 ].getProjectID() ).getProjects()[ 0 ];
		}

		@Override
		protected void onPostExecute( Project result ){
			super.onPostExecute( result );
			Model.setSelectedProject( result );
			activity.startActivity( new Intent( activity, TaskInfoView.class ) );
		}

		@Override
		protected void onPreExecute(){
			super.onPreExecute();
		}
	}
}
