package tobedevelopers.project_fury.dashboard.implementation;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.ref.WeakReference;

import tobedevelopers.project_fury.R;
import tobedevelopers.project_fury.dashboard.DashboardContract;
import tobedevelopers.project_fury.model.Column;
import tobedevelopers.project_fury.model.Task;

/**
 * Created by Macro303 on 12/09/2016.
 */
public class DashboardTaskRecyclerAdapter extends RecyclerView.Adapter< DashboardTaskViewHolder >{

	//	private static LayoutInflater inflater = null;
	private TaskHolder taskHolder;
	private Context context;
	private WeakReference< DashboardContract.Navigation > navigationWeakReference;

	public DashboardTaskRecyclerAdapter( Context context, DashboardContract.Navigation navigation ){
		this.context = context;
		this.taskHolder = new TaskHolder();
		this.navigationWeakReference = new WeakReference<>( navigation );
//		inflater = ( LayoutInflater ) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
	}

	public void setData( TaskHolder taskHolder ){
		this.taskHolder = taskHolder;
		notifyDataSetChanged();
	}

//	@Override
//	public int getCount(){
//		if( taskHolder.getPairList().size() > 0 )
//			return taskHolder.getPairList().size();
//		return 1;
//	}

	//	@Override
	public Object getItem( int position ){
		return taskHolder.getPairList().get( position ).first;
	}

	@Override
	public DashboardTaskViewHolder onCreateViewHolder( ViewGroup parent, int viewType ){
		View view = LayoutInflater.from( parent.getContext() ).inflate( R.layout.card_dashboard_task, parent, false );
		return new DashboardTaskViewHolder( view );
	}

	@Override
	public void onBindViewHolder( final DashboardTaskViewHolder holder, int position ){
		final DashboardContract.Navigation navigation = navigationWeakReference.get();

		if( taskHolder.getPairList().size() > 0 ){
			Pair< Task, Column > current = taskHolder.getPairList().get( position );
			holder.mTaskName.setVisibility( View.VISIBLE );
			holder.mColumnName.setVisibility( View.VISIBLE );
			holder.mTaskName.setText( current.first.getName() );
			holder.mColumnName.setText( current.second.getName() );
			holder.mCardView.setOnClickListener( new View.OnClickListener(){
				@Override
				public void onClick( View view ){
					holder.mCardView.setEnabled( false );
//					navigation.navigateToTaskInfo();
				}
			} );
//			view.setTag( "Tasks" );
		}else{
			holder.mTaskName.setText( context.getString( R.string.error_noCurrentTasks ) );
			holder.mColumnName.setVisibility( View.GONE );
//			view.setTag( "No Tasks" );
		}
	}

	@Override
	public long getItemId( int position ){
		return position;
	}

	@Override
	public int getItemCount(){
		if( taskHolder.getPairList().size() > 0 )
			return taskHolder.getPairList().size();
		return 1;
	}

//	@Override
//	public View getView( int position, View convertView, ViewGroup parent ){
//		View view = convertView;
//		if( view == null )
//			view = inflater.inflate( R.layout.list_item_dashboard_task, null );
//		TextView mTaskName = ( TextView ) view.findViewById( R.id.listItem_taskName );
//		TextView mTaskColumn = ( TextView ) view.findViewById( R.id.listItem_columnName );
//		if( taskHolder.getPairList().size() > 0 ){
//			Pair< Task, Column > current = taskHolder.getPairList().get( position );
//			mTaskName.setVisibility( View.VISIBLE );
//			mTaskColumn.setVisibility( View.VISIBLE );
//			mTaskName.setText( current.first.getName() );
//			mTaskColumn.setText( current.second.getName() );
//			view.setTag( "Tasks" );
//		}else{
//			mTaskName.setText( context.getString( R.string.error_noCurrentTasks ) );
//			mTaskColumn.setVisibility( View.GONE );
//			view.setTag( "No Tasks" );
//		}
//		return view;
//	}
}
