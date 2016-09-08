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
	private Holder holder;
	private Context context;

	public ProjectAdapter( Context context, Holder holder ){
		this.context = context;
		this.holder = holder;
		inflater = ( LayoutInflater ) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
	}

	@Override
	public int getCount(){
		return holder.getProjects().length;
	}

	@Override
	public Object getItem( int position ){
		return holder.getProjects()[ position ];
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
		Project current = ( Project ) getItem( position );
		mProjectName.setText( current.getName() );
		mProgressbar.setMax( holder.getTasks().get( current.getName() ).length );
		mProgressbar.setProgress( calculateProgress( current.getName() ) );
		return view;
	}

	private int calculateProgress( String currentName ){
		int count = 0;
		for( Task task : holder.getTasks().get( currentName ) )
			if( task.getColumnID().equals( findColumn( currentName, "Archived" ).getColumnID() ) )
				count++;
		return count;
	}

	@NonNull
	private Column findColumn( String currentName, String columnName ){
		for( Column column : holder.getColumns().get( currentName ) )
			if( column.getName().equals( columnName ) )
				return column;
		return null;
	}
}
