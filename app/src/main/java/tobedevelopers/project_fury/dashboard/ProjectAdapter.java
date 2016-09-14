package tobedevelopers.project_fury.dashboard;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.daimajia.numberprogressbar.NumberProgressBar;

import tobedevelopers.project_fury.R;
import tobedevelopers.project_fury.model.Column;
import tobedevelopers.project_fury.model.Project;
import tobedevelopers.project_fury.model.Task;

/**
 * Created by Macro303 on 7/09/2016.
 */
public class ProjectAdapter extends BaseAdapter{

	private static LayoutInflater inflater = null;
	private ProjectHolder projectHolder;
	private Context context;

	public ProjectAdapter( Context context ){
		this.context = context;
		this.projectHolder = new ProjectHolder( new Project[]{} );
//		this.projectHolder = projectHolder;
		inflater = ( LayoutInflater ) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
	}

	public void setData( ProjectHolder projectHolder ){
		this.projectHolder = projectHolder;
		notifyDataSetChanged();
	}

	@Override
	public int getCount(){
		if( projectHolder != null && projectHolder.getProjects() != null && projectHolder.getProjects().length > 0 )
			return projectHolder.getProjects().length;
		return 1;
	}

	@Override
	public Object getItem( int position ){
		return projectHolder.getProjects()[ position ];
	}

	@Override
	public long getItemId( int position ){
		return position;
	}

	@Override
	public View getView( int position, View convertView, ViewGroup parent ){
		View view = convertView;
		if( view == null )
			view = inflater.inflate( R.layout.list_item_dashboard_project, null );
		TextView mProjectName = ( TextView ) view.findViewById( R.id.listItem_projectName );
		NumberProgressBar mProgressbar = ( NumberProgressBar ) view.findViewById( R.id.listItem_projectProgress );
		if( projectHolder != null && projectHolder.getProjects() != null && projectHolder.getProjects().length > 0 ){
			Project current = ( Project ) getItem( position );
			mProjectName.setVisibility( View.VISIBLE );
			mProgressbar.setVisibility( View.VISIBLE );
			mProjectName.setText( current.getName() );
			if( projectHolder.getTasks() != null && projectHolder.getTasks().size() > 0 ){
				mProgressbar.setMax( projectHolder.getTasks().get( current.getName() ).length );
				mProgressbar.setProgress( calculateProgress( current.getName() ) );
			}else{
				mProgressbar.setMax( 100 );
				mProgressbar.setProgress( 1 );
			}
			view.setTag( "Projects" );
		}else{
			mProjectName.setText( context.getString( R.string.error_noCurrentProjects ) );
			mProgressbar.setVisibility( View.GONE );
			view.setTag( "No Projects" );
		}
		return view;
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
