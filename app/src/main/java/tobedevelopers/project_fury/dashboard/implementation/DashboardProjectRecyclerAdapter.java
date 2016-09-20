package tobedevelopers.project_fury.dashboard.implementation;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.ref.WeakReference;

import tobedevelopers.project_fury.R;
import tobedevelopers.project_fury.dashboard.DashboardContract;
import tobedevelopers.project_fury.model.Column;
import tobedevelopers.project_fury.model.Model;
import tobedevelopers.project_fury.model.Project;
import tobedevelopers.project_fury.model.Task;

/**
 * Created by Macro303 on 7/09/2016.
 */
public class DashboardProjectRecyclerAdapter extends RecyclerView.Adapter< DashboardProjectViewHolder >{

	private ProjectHolder projectHolder;
	private Context context;
	private WeakReference< DashboardContract.Presenter > presenterWeakReference;

	public DashboardProjectRecyclerAdapter( Context context, DashboardContract.Presenter presenter ){
		this.context = context;
		this.presenterWeakReference = new WeakReference<>( presenter );
		this.projectHolder = new ProjectHolder( new Project[]{} );
	}

	public void setData( ProjectHolder projectHolder ){
		this.projectHolder = projectHolder;
		notifyDataSetChanged();
	}

	public Object getItem( int position ){
		return projectHolder != null &&
			       projectHolder.getProjects() != null &&
			       projectHolder.getProjects().length > 0 ? projectHolder.getProjects()[ position ] : null;
	}

	@Override
	public DashboardProjectViewHolder onCreateViewHolder( ViewGroup parent, int viewType ){
		View view = LayoutInflater.from( parent.getContext() ).inflate( R.layout.card_dashboard_project, parent, false );
		return new DashboardProjectViewHolder( view );
	}

	@Override
	public void onBindViewHolder( final DashboardProjectViewHolder holder, int position ){
		final DashboardContract.Presenter presenter = presenterWeakReference.get();

		if( projectHolder != null && projectHolder.getProjects() != null && projectHolder.getProjects().length > 0 ){
			final Project current = ( Project ) getItem( position );
			holder.mCardView.setEnabled( true );
			holder.mProjectName.setVisibility( View.VISIBLE );
			holder.mNumberProgressBar.setVisibility( View.VISIBLE );
			holder.mProjectName.setText( current.getName() );
			if( projectHolder.getTasks() != null && projectHolder.getTasks().size() > 0 ){
				holder.mNumberProgressBar.setMax( projectHolder.getTasks().get( current.getName() ).length );
				holder.mNumberProgressBar.setProgress( calculateProgress( current.getName() ) );
			}else{
				holder.mNumberProgressBar.setMax( 100 );
				holder.mNumberProgressBar.setProgress( 1 );
			}
			holder.mCardView.setOnClickListener( new View.OnClickListener(){
				@Override
				public void onClick( View view ){
					holder.mCardView.setEnabled( false );
					Model.setSelectedProject( current );
					presenter.userSelectProjectInfo();
				}
			} );
		}else{
			holder.mProjectName.setText( context.getString( R.string.error_noCurrentProjects ) );
			holder.mNumberProgressBar.setVisibility( View.GONE );
		}
	}


	@Override
	public long getItemId( int position ){
		return position;
	}

	@Override
	public int getItemCount(){
		if( projectHolder != null && projectHolder.getProjects() != null && projectHolder.getProjects().length > 0 )
			return projectHolder.getProjects().length;
		return 1;
	}

	private int calculateProgress( String currentName ){
		int count = 0;
		for( Task task : projectHolder.getTasks().get( currentName ) )
			if( task.getColumnID().equals( findColumn( currentName, "Archived" ).getColumnID() ) )
				count++;
		return count;
	}

	@NonNull
	private Column findColumn( String currentName, String columnName ){
		for( Column column : projectHolder.getColumns().get( currentName ) )
			if( column.getName().equals( columnName ) )
				return column;
		return null;
	}
}
