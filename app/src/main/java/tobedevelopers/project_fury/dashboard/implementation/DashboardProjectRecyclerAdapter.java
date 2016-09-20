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
import tobedevelopers.project_fury.model.Project;
import tobedevelopers.project_fury.model.Task;

/**
 * Created by Macro303 on 7/09/2016.
 */
public class DashboardProjectRecyclerAdapter extends RecyclerView.Adapter< DashboardProjectViewHolder >{

	//	private static LayoutInflater inflater = null;
	private ProjectHolder projectHolder;
	private Context context;
	private WeakReference< DashboardContract.Navigation > navigationWeakReference;

	public DashboardProjectRecyclerAdapter( Context context, DashboardContract.Navigation navigation ){
		this.context = context;
		this.navigationWeakReference = new WeakReference<>( navigation );
		this.projectHolder = new ProjectHolder( new Project[]{} );
//		inflater = ( LayoutInflater ) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
	}

	public void setData( ProjectHolder projectHolder ){
		this.projectHolder = projectHolder;
		notifyDataSetChanged();
	}

//	@Override
//	public int getCount(){
//		if( projectHolder != null && projectHolder.getProjects() != null && projectHolder.getProjects().length > 0 )
//			return projectHolder.getProjects().length;
//		return 1;
//	}

	//	@Override
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
		final DashboardContract.Navigation navigation = navigationWeakReference.get();

		if( projectHolder != null && projectHolder.getProjects() != null && projectHolder.getProjects().length > 0 ){
			Project current = ( Project ) getItem( position );
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
					navigation.navigateToProjectInfo();
				}
			} );
//			view.setTag( "Projects" );
		}else{
			holder.mProjectName.setText( context.getString( R.string.error_noCurrentProjects ) );
			holder.mNumberProgressBar.setVisibility( View.GONE );
//			view.setTag( "No Projects" );
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

//	@Override
//	public View getView( int position, View convertView, ViewGroup parent ){
//		View view = convertView;
//		if( view == null )
//			view = inflater.inflate( R.layout.list_item_dashboard_project, null );
//		TextView mProjectName = ( TextView ) view.findViewById( R.id.listItem_projectName );
//		NumberProgressBar mProgressbar = ( NumberProgressBar ) view.findViewById( R.id.listItem_projectProgress );
//		if( projectHolder != null && projectHolder.getProjects() != null && projectHolder.getProjects().length > 0 ){
//			Project current = ( Project ) getItem( position );
//			mProjectName.setVisibility( View.VISIBLE );
//			mProgressbar.setVisibility( View.VISIBLE );
//			mProjectName.setText( current.getName() );
//			if( projectHolder.getTasks() != null && projectHolder.getTasks().size() > 0 ){
//				mProgressbar.setMax( projectHolder.getTasks().get( current.getName() ).length );
//				mProgressbar.setProgress( calculateProgress( current.getName() ) );
//			}else{
//				mProgressbar.setMax( 100 );
//				mProgressbar.setProgress( 1 );
//			}
//			view.setTag( "Projects" );
//		}else{
//			mProjectName.setText( context.getString( R.string.error_noCurrentProjects ) );
//			mProgressbar.setVisibility( View.GONE );
//			view.setTag( "No Projects" );
//		}
//		return view;
//	}

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
