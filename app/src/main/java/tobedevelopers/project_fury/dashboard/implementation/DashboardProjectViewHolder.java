package tobedevelopers.project_fury.dashboard.implementation;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.daimajia.numberprogressbar.NumberProgressBar;

import tobedevelopers.project_fury.R;
import tobedevelopers.project_fury.dashboard.DashboardContract;
import tobedevelopers.project_fury.model.Column;
import tobedevelopers.project_fury.model.Model;
import tobedevelopers.project_fury.model.Project;
import tobedevelopers.project_fury.model.Task;

/**
 * Created by A on 9/20/2016.
 */
public class DashboardProjectViewHolder extends RecyclerView.ViewHolder{

	//UI References
	private CardView mCardView;
	private TextView mProjectName;
	private NumberProgressBar mProjectProgressBar;

	private DashboardContract.Presenter presenter;
	private Context context;

	public DashboardProjectViewHolder( View view, DashboardContract.Presenter presenter, Context context ){
		super( view );
		mCardView = ( CardView ) view.findViewById( R.id.dashboardFragment_projectCard );
		mProjectName = ( TextView ) view.findViewById( R.id.dashboardFragment_projectCard_projectName );
		mProjectProgressBar = ( NumberProgressBar ) view.findViewById( R.id.dashboardFragment_projectCard_projectProgressBar );

		this.presenter = presenter;
		this.context = context;
	}

	public void bindView( final Project current, ProjectHolder projectHolder ){
		if( current != null ){
			mProjectName.setText( current.getName().toUpperCase() );
			if( projectHolder.getTasks( current.getName() ).length > 0 ){
				mProjectProgressBar.setMax( projectHolder.getTasks( current.getName() ).length );
				mProjectProgressBar.setProgress( calculateProgress( projectHolder.getTasks( current.getName() ), projectHolder.getColumns( current.getName() ) ) );
			}else{
				mProjectProgressBar.setMax( 100 );
				mProjectProgressBar.setProgress( 0 );
			}
			setVisibility( current, true );
		}else{
			mProjectName.setText( context.getString( R.string.error_noCurrentProjects ) );
			setVisibility( current, false );
		}
	}

	private void setVisibility( final Project current, boolean value ){
		mProjectProgressBar.setVisibility( value ? View.VISIBLE : View.GONE );
		mCardView.setEnabled( value );
		if( value )
			mCardView.setOnClickListener( new View.OnClickListener(){
				@Override
				public void onClick( View view ){
					mCardView.setEnabled( false );
					Model.setSelectedProject( current );
					presenter.userSelectProjectInfo();
				}
			} );
		else
			mCardView.setOnClickListener( null );
	}

	@SuppressWarnings( { "ConstantConditions" } )
	private int calculateProgress( Task[] tasks, Column[] columns ){
		int count = 0;
		for( Task task : tasks )
			if( task.getColumnID().equals( findColumn( "archived", columns ).getColumnID() ) )
				count++;
		return count;
	}

	@Nullable
	private Column findColumn( String columnName, Column[] columns ){
		for( Column column : columns )
			if( column.getName().equals( columnName ) )
				return column;
		return null;
	}
}