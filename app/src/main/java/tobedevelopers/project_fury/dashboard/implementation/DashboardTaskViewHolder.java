package tobedevelopers.project_fury.dashboard.implementation;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import tobedevelopers.project_fury.R;

/**
 * Created by A on 9/20/2016.
 */
public class DashboardTaskViewHolder extends RecyclerView.ViewHolder{

	public TextView mColumnName;
	public TextView mTaskName;
	public CardView mCardView;

	public DashboardTaskViewHolder( View view ){
		super( view );
		mColumnName = ( TextView ) view.findViewById( R.id.listItem_columnName );
		mTaskName = ( TextView ) view.findViewById( R.id.listItem_taskName );
		mCardView = ( CardView ) view.findViewById( R.id.dashboard_Task_CardView );
	}
}
