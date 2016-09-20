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
import tobedevelopers.project_fury.model.Model;
import tobedevelopers.project_fury.model.Task;

/**
 * Created by Macro303 on 12/09/2016.
 */
public class DashboardTaskRecyclerAdapter extends RecyclerView.Adapter< DashboardTaskViewHolder >{

	private TaskHolder taskHolder;
	private Context context;
	private WeakReference< DashboardContract.Presenter > presenterWeakReference;

	public DashboardTaskRecyclerAdapter( Context context, DashboardContract.Presenter presenter ){
		this.context = context;
		this.taskHolder = new TaskHolder();
		this.presenterWeakReference = new WeakReference<>( presenter );
	}

	public void setData( TaskHolder taskHolder ){
		this.taskHolder = taskHolder;
		notifyDataSetChanged();
	}

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
		final DashboardContract.Presenter presenter = presenterWeakReference.get();

		if( taskHolder.getPairList().size() > 0 ){
			final Pair< Task, Column > current = taskHolder.getPairList().get( position );
			holder.mCardView.setEnabled( true );
			holder.mTaskName.setVisibility( View.VISIBLE );
			holder.mColumnName.setVisibility( View.VISIBLE );
			holder.mTaskName.setText( current.first.getName() );
			holder.mColumnName.setText( current.second.getName() );
			holder.mCardView.setOnClickListener( new View.OnClickListener(){
				@Override
				public void onClick( View view ){
					holder.mCardView.setEnabled( false );
					Model.setSelectedTask( current.first );
					presenter.userSelectTaskInfo();
				}
			} );
		}else{
			holder.mTaskName.setText( context.getString( R.string.error_noCurrentTasks ) );
			holder.mColumnName.setVisibility( View.GONE );
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
}
