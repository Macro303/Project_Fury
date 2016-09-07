package tobedevelopers.project_fury.dashboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.daimajia.numberprogressbar.NumberProgressBar;

import tobedevelopers.project_fury.R;
import tobedevelopers.project_fury.model.Project;
import tobedevelopers.project_fury.model.Task;

/**
 * Created by Macro303 on 7/09/2016.
 */
public class ProjectAdapter extends BaseAdapter{

	private static LayoutInflater inflater = null;
	private Project[] projects;
	private Task[] tasks;
	private Context context;

	public ProjectAdapter( Context context, Project[] projects, Task[] projectTasks ){
		this.context = context;
		this.projects = projects;
		this.tasks = tasks;
		inflater = ( LayoutInflater ) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
	}

	@Override
	public int getCount(){
		return projects.length;
	}

	@Override
	public Object getItem( int position ){
		return projects[ position ];
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
		mProjectName.setText( projects[ position ].getName() );
		mProgressbar.setMax( tasks.length );
		mProgressbar.setProgress( calculateProgress() );
		return view;
	}

	private int calculateProgress(){
		int count = 0;
		for( Task task : tasks )
			if( task.getColumnIn().equals( "Archived" ) )
				count++;
		return count;
	}
}
