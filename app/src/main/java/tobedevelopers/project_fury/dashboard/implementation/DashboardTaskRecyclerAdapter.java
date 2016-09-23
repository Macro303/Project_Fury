package tobedevelopers.project_fury.dashboard.implementation;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.ref.WeakReference;

import tobedevelopers.project_fury.R;
import tobedevelopers.project_fury.dashboard.DashboardContract;

/**
 * Created by Macro303 on 12/09/2016.
 */
public class DashboardTaskRecyclerAdapter extends RecyclerView.Adapter< DashboardTaskViewHolder >{

	private TaskHolder taskHolder;
	private Context context;
	private WeakReference< DashboardContract.Presenter > presenterWeakReference;

	public DashboardTaskRecyclerAdapter( Context context, DashboardContract.Presenter presenter ){
		this.context = context;
		this.presenterWeakReference = new WeakReference<>( presenter );
	}

	public void setData( TaskHolder taskHolder ){
		this.taskHolder = taskHolder;
		notifyDataSetChanged();
	}

	@Override
	public DashboardTaskViewHolder onCreateViewHolder( ViewGroup parent, int viewType ){
		View view = LayoutInflater.from( parent.getContext() ).inflate( R.layout.card_dashboard_task, parent, false );
		return new DashboardTaskViewHolder( view, presenterWeakReference.get(), context );
	}

	@Override
	public void onBindViewHolder( final DashboardTaskViewHolder holder, int position ){
		if( taskHolder != null && taskHolder.getPairList().size() > 0 )
			holder.bindView( taskHolder.getPairList().get( position ) );
		else
			holder.bindView( null );
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
