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
	public TextView mTaskTextView;
	public TextView mPriorityTextView;
	public TextView mAssigneeTextView;

	public ProjectBoardHolder( View view ){
		super( view );
		mCardView = ( CardView ) view.findViewById( R.id.projectBoardFragment_cardView );
		mTaskTextView = ( TextView ) view.findViewById( R.id.projectBoardFragment_taskTextView );
		mPriorityTextView = ( TextView ) view.findViewById( R.id.projectBoardFragment_priorityTextView );
		mAssigneeTextView = ( TextView ) view.findViewById( R.id.projectBoardFragment_userAssignedTextView );
	}

}
