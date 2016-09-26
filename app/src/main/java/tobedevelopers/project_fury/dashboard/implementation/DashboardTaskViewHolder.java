package tobedevelopers.project_fury.dashboard.implementation;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.View;
import android.widget.TextView;

import tobedevelopers.project_fury.R;
import tobedevelopers.project_fury.dashboard.DashboardContract;
import tobedevelopers.project_fury.model.Column;
import tobedevelopers.project_fury.model.Model;
import tobedevelopers.project_fury.model.Task;

/**
 * Created by A on 9/20/2016.
 */
public class DashboardTaskViewHolder extends RecyclerView.ViewHolder{

	//UI References
	private CardView mCardView;
	private TextView mTaskName;
	private TextView mColumnName;

	private DashboardContract.Presenter presenter;
	private Context context;

	public DashboardTaskViewHolder( View view, DashboardContract.Presenter presenter, Context context ){
		super( view );
		mCardView = ( CardView ) view.findViewById( R.id.dashboardFragment_taskCard );
		mTaskName = ( TextView ) view.findViewById( R.id.dashboardFragment_taskCard_taskName );
		mColumnName = ( TextView ) view.findViewById( R.id.dashboardFragment_taskCard_columnName );

		this.presenter = presenter;
		this.context = context;
	}

	public void bindView( Pair< Task, Column > current ){
		if( current != null ){
			mTaskName.setText( current.first.getName().toUpperCase() );
			mColumnName.setText( current.second.getName().toUpperCase() );
			setVisibility( current, true );
		}else{
			mTaskName.setText( context.getString( R.string.error_noCurrentTasks ) );
			setVisibility( current, false );
		}
	}

	private void setVisibility( final Pair< Task, Column > current, boolean value ){
		mColumnName.setVisibility( value ? View.VISIBLE : View.GONE );
		mCardView.setEnabled( value );
		if( value ){
			mCardView.setOnClickListener( new View.OnClickListener(){
				@Override
				public void onClick( View view ){
					mCardView.setEnabled( false );
					Model.setSelectedTask( current.first );
					presenter.userSelectTaskInfo();
				}
			} );
		}else{
			mCardView.setOnClickListener( null );
		}
	}
}
