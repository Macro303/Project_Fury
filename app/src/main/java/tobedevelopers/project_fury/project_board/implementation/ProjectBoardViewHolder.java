package tobedevelopers.project_fury.project_board.implementation;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import tobedevelopers.project_fury.R;
import tobedevelopers.project_fury.model.Model;
import tobedevelopers.project_fury.model.Task;
import tobedevelopers.project_fury.task_info.implementation.TaskInfoView;

/**
 * Created by Macro303 on 13/08/2016.
 */
public class ProjectBoardViewHolder extends RecyclerView.ViewHolder{

	//UI References
	private CardView mCardView;
	private TextView mTaskName;
	private TextView mAssigneeName;
	private TextView mNoTasks;
	private TextView mPriorityName;

	private FragmentActivity activity;

	public ProjectBoardViewHolder( View view, FragmentActivity activity ){
		super( view );
		mCardView = ( CardView ) view.findViewById( R.id.projectBoardFragment_taskCard );
		mTaskName = ( TextView ) view.findViewById( R.id.projectBoardFragment_taskCard_taskName );
		mAssigneeName = ( TextView ) view.findViewById( R.id.projectBoardFragment_taskCard_assigneeName );
		mNoTasks = ( TextView ) view.findViewById( R.id.projectBoardFragment_taskCard_noTasks );
		mPriorityName = ( TextView ) view.findViewById( R.id.projectBoardFragment_taskCard_priorityName );

		this.activity = activity;
	}

	public void bindView( Task current ){
		if( current != null ){
			mTaskName.setText( current.getName().toUpperCase() );
			mPriorityName.setText( current.getPriority().getNameValue() );
			mAssigneeName.setText( current.getAssignee() );
			setVisibility( current, true );
		}else{
			setVisibility( current, false );
		}
	}

	private void setVisibility( final Task current, boolean value ){
		mNoTasks.setVisibility( value ? View.INVISIBLE : View.VISIBLE );
		mAssigneeName.setVisibility( value ? View.VISIBLE : View.GONE );
		mPriorityName.setVisibility( value ? View.VISIBLE : View.GONE );
		mTaskName.setVisibility( value ? View.VISIBLE : View.GONE );

		mCardView.setEnabled( value );

		if( value ){
			mCardView.setOnClickListener( new View.OnClickListener(){
				@Override
				public void onClick( View view ){
					mCardView.setEnabled( false );
					Model.setSelectedTask( current );
					activity.startActivity( new Intent( activity, TaskInfoView.class ) );
				}
			} );
		}
	}
}
