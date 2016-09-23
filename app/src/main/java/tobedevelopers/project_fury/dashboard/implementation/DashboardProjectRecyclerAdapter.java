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
 * Created by Macro303 on 7/09/2016.
 */
public class DashboardProjectRecyclerAdapter extends RecyclerView.Adapter< DashboardProjectViewHolder >{

	private ProjectHolder projectHolder;
	private Context context;
	private WeakReference< DashboardContract.Presenter > presenterWeakReference;

	public DashboardProjectRecyclerAdapter( Context context, DashboardContract.Presenter presenter ){
		this.context = context;
		this.presenterWeakReference = new WeakReference<>( presenter );
	}

	public void setData( ProjectHolder projectHolder ){
		this.projectHolder = projectHolder;
		notifyDataSetChanged();
	}

	@Override
	public DashboardProjectViewHolder onCreateViewHolder( ViewGroup parent, int viewType ){
		View view = LayoutInflater.from( parent.getContext() ).inflate( R.layout.card_dashboard_project, parent, false );
		return new DashboardProjectViewHolder( view, presenterWeakReference.get(), context );
	}

	@Override
	public void onBindViewHolder( final DashboardProjectViewHolder holder, int position ){
		if( projectHolder != null && projectHolder.getProjects().length > 0 )
			holder.bindView( projectHolder.getProjects()[ position ], projectHolder );
		else
			holder.bindView( null, null );
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
}
