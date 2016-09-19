package tobedevelopers.project_fury.backlog.implementation;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import tobedevelopers.project_fury.R;

/**
 * Created by Macro303 on 12/08/2016.
 */
public class BacklogHolder extends RecyclerView.ViewHolder{

	//UI References
	public CardView mCardView;
	public TextView mTaskName;
	public TextView mTaskAssignee;
	public TextView mTaskPriority;
	public TextView mTaskColumn;
	public TextView mNoTaskViewTextView;

	public BacklogHolder( View view ){
		super( view );
		mCardView = ( CardView ) view.findViewById( R.id.backlogFragment_cardView );
		mTaskName = ( TextView ) view.findViewById( R.id.backlogFragment_taskName );
		mTaskAssignee = ( TextView ) view.findViewById( R.id.backlogFragment_taskAssignee );
		mTaskPriority = ( TextView ) view.findViewById( R.id.backlogFragment_taskPriority );
		mTaskColumn = ( TextView ) view.findViewById( R.id.backlogFragment_taskColumn );
		mNoTaskViewTextView = ( TextView ) view.findViewById( R.id.backlogFragment_noTaskTextView );
	}
}
