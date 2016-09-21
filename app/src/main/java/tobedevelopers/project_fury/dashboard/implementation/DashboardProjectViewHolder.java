package tobedevelopers.project_fury.dashboard.implementation;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.daimajia.numberprogressbar.NumberProgressBar;

import tobedevelopers.project_fury.R;

/**
 * Created by A on 9/20/2016.
 */
public class DashboardProjectViewHolder extends RecyclerView.ViewHolder{

	public TextView mProjectName;
	public NumberProgressBar mNumberProgressBar;
	public CardView mCardView;

	public DashboardProjectViewHolder( View view ){
		super( view );
		mProjectName = ( TextView ) view.findViewById( R.id.listItem_projectName );
		mCardView = ( CardView ) view.findViewById( R.id.dashboard_Project_CardView );
		mNumberProgressBar = ( NumberProgressBar ) view.findViewById( R.id.listItem_projectProgress );
	}
}
