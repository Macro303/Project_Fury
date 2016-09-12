package tobedevelopers.project_fury.dashboard;

import android.content.Context;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import tobedevelopers.project_fury.R;
import tobedevelopers.project_fury.model.Column;
import tobedevelopers.project_fury.model.Task;

/**
 * Created by Macro303 on 12/09/2016.
 */
public class TaskAdapter extends BaseAdapter{

	private static LayoutInflater inflater = null;
	private TaskHolder taskHolder;
	private Context context;

	public TaskAdapter( Context context ){
		this.context = context;
		this.taskHolder = new TaskHolder();
		inflater = ( LayoutInflater ) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
	}

	public void setData( TaskHolder taskHolder ){
		this.taskHolder = taskHolder;
		notifyDataSetChanged();
	}

	@Override
	public int getCount(){
		if( taskHolder.getPairList().size() > 0 )
			return taskHolder.getPairList().size();
		return 1;
	}

	@Override
	public Object getItem( int position ){
		return taskHolder.getPairList().get( position ).first;
	}

	@Override
	public long getItemId( int position ){
		return position;
	}

	@Override
	public View getView( int position, View convertView, ViewGroup parent ){
		View view = convertView;
		if( view == null )
			view = inflater.inflate( R.layout.list_item_dashboard_task, null );
		TextView mTaskName = ( TextView ) view.findViewById( R.id.listItem_taskName );
		TextView mTaskColumn = ( TextView ) view.findViewById( R.id.listItem_taskColumnName );
		if( taskHolder.getPairList().size() > 0 ){
			Pair< Task, Column > current = taskHolder.getPairList().get( position );
			System.out.println( current.toString() );
			mTaskName.setVisibility( View.VISIBLE );
			mTaskColumn.setVisibility( View.VISIBLE );
			mTaskName.setText( current.first.getName() );
			mTaskColumn.setText( current.second.getName() );
			view.setTag( "Tasks" );
		}else{
			mTaskName.setText( context.getString( R.string.error_noCurrentTasks ) );
			mTaskColumn.setVisibility( View.GONE );
			view.setTag( "No Tasks" );
		}
		return view;
	}
}
