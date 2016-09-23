package tobedevelopers.project_fury.project_board.implementation;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import tobedevelopers.project_fury.R;

/**
 * Created by Macro303 on 13/08/2016.
 */
public class ProjectBoardHolder extends RecyclerView.ViewHolder{

	//UI References
	public CardView mCardView;
	public TextView mTaskName;
	public TextView mAssigneeName;
	public TextView mNoTasks;
	public TextView mPriorityName;

	public ProjectBoardHolder( View view ){
		super( view );
		mCardView = ( CardView ) view.findViewById( R.id.projectBoardFragment_taskCard );
		mTaskName = ( TextView ) view.findViewById( R.id.projectBoardFragment_taskCard_taskName );
		mAssigneeName = ( TextView ) view.findViewById( R.id.projectBoardFragment_taskCard_assigneeName );
		mNoTasks = ( TextView ) view.findViewById( R.id.projectBoardFragment_taskCard_noTasks );
		mPriorityName = ( TextView ) view.findViewById( R.id.projectBoardFragment_taskCard_priorityName );
	}

}
