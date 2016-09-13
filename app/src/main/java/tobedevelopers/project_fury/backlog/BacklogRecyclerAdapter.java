package tobedevelopers.project_fury.backlog;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tobedevelopers.project_fury.R;
import tobedevelopers.project_fury.model.Model;
import tobedevelopers.project_fury.model.Task;
import tobedevelopers.project_fury.task_info.implementation.TaskInfoView;

/**
 * Created by Macro303 on 12/08/2016.
 */
public class BacklogRecyclerAdapter extends RecyclerView.Adapter< BacklogHolder >{

//	private FragmentActivity activity;
//	private Project project;
//	private Task[] tasks;
//
//	public BacklogRecyclerAdapter( FragmentActivity activity, Project project ){
//		this.activity = activity;
//		this.project = project;
//		if( !project.getName().equals( "No Project" ) )
//			setData();
//	}
//
//	private void setData(){
//		new AsyncTask< Void, Void, TaskResponse >(){
//			@Override
//			protected TaskResponse doInBackground( Void... voids ){
//				return new Model().getAllProjectTasks( project.getProjectID() );
//			}
//
//			@Override
//			protected void onPostExecute( TaskResponse taskResponse ){
//				super.onPostExecute( taskResponse );
//				System.out.println( taskResponse.getMessage() );
//				if( taskResponse.getMessage().equals( "Success" ) )
//					tasks = taskResponse.getTasks();
//			}
//
//			@Override
//			protected void onPreExecute(){
//				super.onPreExecute();
//			}
//		}.executeOnExecutor( AsyncTask.THREAD_POOL_EXECUTOR );
//	}
//
//	@Override
//	public void onBindViewHolder( BacklogHolder holder, int position ){
//		if( tasks != null ){
//			holder.mCardView.setOnClickListener( new View.OnClickListener(){
//				@Override
//				public void onClick( View view ){
//					activity.startActivity( new Intent( activity, TaskInfoView.class ) );
//				}
//			} );
//			holder.mTaskName.setText( tasks[ position ].getName() );
//			holder.mTaskAssignee.setText( tasks[ position ].getAssignee() );
//			holder.mTaskPriority.setText( tasks[ position ].getPriority().getNameValue() );
//			holder.mTaskColumn.setText( tasks[ position ].getColumnID() );
//		}else{
//			holder.mTaskName.setText( "No Tasks Currently Created" );
//		}
//	}
//
//	@Override
//	public int getItemCount(){
//		return tasks != null ? tasks.length : 1;
//	}
//
//	@Override
//	public BacklogHolder onCreateViewHolder( ViewGroup parent, int viewType ){
//		View view = LayoutInflater.from( parent.getContext() ).inflate( R.layout.card_backlog, parent, false );
//		return new BacklogHolder( view );
//	}

	private Task[] tasks;
	private Context context;

	public BacklogRecyclerAdapter( Context context, Task[] tasks ){
		this.context = context;
		this.tasks = tasks;
	}

	@Override
	public int getItemCount(){
		return tasks != null ? tasks.length : 1;
	}

	@Override
	public BacklogHolder onCreateViewHolder( ViewGroup parent, int viewType ){
		return new BacklogHolder( LayoutInflater.from( context ).inflate( R.layout.card_backlog, parent, false ) );
	}

	@Override
	public void onBindViewHolder( BacklogHolder holder, int position ){
		if( tasks != null ){
			final Task current = tasks[ position ];
			holder.mCardView.setOnClickListener( new View.OnClickListener(){
				@Override
				public void onClick( View view ){
					Model.setSelectedTask( current );
					context.startActivity( new Intent( context, TaskInfoView.class ) );
				}
			} );
			holder.mTaskName.setText( current.getName() );
			holder.mTaskAssignee.setText( current.getAssignee() );
			holder.mTaskPriority.setText( current.getPriority().getNameValue() );
			holder.mTaskColumn.setText( current.getColumnID() );
		}else
			holder.mTaskName.setText( "No Tasks" );
	}
}
