package tobedevelopers.project_fury.backlog.implementation;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import tobedevelopers.project_fury.R;
import tobedevelopers.project_fury.model.Column;
import tobedevelopers.project_fury.model.Model;
import tobedevelopers.project_fury.model.Project;
import tobedevelopers.project_fury.model.Task;
import tobedevelopers.project_fury.task_info.implementation.TaskInfoView;

/**
 * Created by Macro303 on 12/08/2016.
 */
public class BacklogViewHolder extends RecyclerView.ViewHolder{

	//UI References
	private CardView mCardView;
	private TextView mTaskName;
	private TextView mTaskAssignee;
	private TextView mTaskPriority;
	private TextView mTaskColumn;
	private TextView mNoTask;

	private Column[] columns;
	private Model model;
	private FragmentActivity activity;

	public BacklogViewHolder( View view, FragmentActivity activity ){
		super( view );

		mCardView = ( CardView ) view.findViewById( R.id.backlogFragment_taskCard );
		mTaskName = ( TextView ) view.findViewById( R.id.backlogFragment_taskCard_taskName );
		mTaskAssignee = ( TextView ) view.findViewById( R.id.backlogFragment_taskCard_taskAssignee );
		mTaskPriority = ( TextView ) view.findViewById( R.id.backlogFragment_taskCard_taskPriority );
		mTaskColumn = ( TextView ) view.findViewById( R.id.backlogFragment_taskCard_taskColumn );
		mNoTask = ( TextView ) view.findViewById( R.id.backlogFragment_taskCard_noTask );

		this.model = new Model();
		this.activity = activity;
	}

	public void bindView( Task current, Column[] columns ){
		this.columns = columns;

		if( current != null ){
			if( current.getName().length() <= 16 )
				mTaskName.setText( current.getName().toUpperCase() );
			else
				mTaskName.setText( current.getName().substring( 0, 16 ) + "..." );
			if( current.getAssignee().length() <= 16 )
				mTaskAssignee.setText( current.getAssignee() );
			else
				mTaskAssignee.setText( current.getAssignee().substring( 0, 16 ) + "..." );
			mTaskPriority.setText( current.getPriority().getNameValue() );
			mTaskColumn.setText( getColumnName( current ).toUpperCase() );
			setVisibility( current, true );
		}else{
			setVisibility( current, false );
		}
	}

	private void setVisibility( final Task current, boolean value ){
		mTaskName.setVisibility( value ? View.VISIBLE : View.INVISIBLE );
		mTaskAssignee.setVisibility( value ? View.VISIBLE : View.GONE );
		mTaskPriority.setVisibility( value ? View.VISIBLE : View.INVISIBLE );
		mTaskColumn.setVisibility( value ? View.VISIBLE : View.GONE );
		mNoTask.setVisibility( value ? View.GONE : View.VISIBLE );

		mCardView.setEnabled( value );

		if( value ){
			mCardView.setOnClickListener( new View.OnClickListener(){
				@Override
				public void onClick( View view ){
					mCardView.setEnabled( false );
					Model.setSelectedTask( current );
					new LoadTaskInfoTask().execute( current );
				}
			} );
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
