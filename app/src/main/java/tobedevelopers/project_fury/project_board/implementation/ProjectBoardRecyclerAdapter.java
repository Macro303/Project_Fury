package tobedevelopers.project_fury.project_board.implementation;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tobedevelopers.project_fury.R;
import tobedevelopers.project_fury.model.Column;
import tobedevelopers.project_fury.model.Model;
import tobedevelopers.project_fury.model.Task;
import tobedevelopers.project_fury.model.TaskResponse;
import tobedevelopers.project_fury.task_info.implementation.TaskInfoView;

/**
 * Created by Macro303 on 13/08/2016.
 */
public class ProjectBoardRecyclerAdapter extends RecyclerView.Adapter< ProjectBoardHolder >{

	private FragmentActivity activity;
	private Task[] tasks;
	private Model model;

	public ProjectBoardRecyclerAdapter( FragmentActivity activity ){
		this.activity = activity;
		this.model = new Model();
	}

	public void getData( final Column column ){

		new AsyncTask< String, Void, TaskResponse >(){

			@Override
			protected TaskResponse doInBackground( String... strings ){
				return model.getAllColumnTasks( column.getProjectID(), column.getColumnID() );
			}

			@Override
			protected void onPostExecute( TaskResponse response ){
				super.onPostExecute( response );
				setData( response.getTasks() );
			}
		}.executeOnExecutor( AsyncTask.THREAD_POOL_EXECUTOR );

	}

	public void setData( Task[] tasks ){
		this.tasks = tasks;
		notifyDataSetChanged();
	}

	@Override
	public ProjectBoardHolder onCreateViewHolder( ViewGroup parent, int viewType ){
		View view = LayoutInflater.from( parent.getContext() ).inflate( R.layout.card_project_board, parent, false );
		return new ProjectBoardHolder( view );
	}

	@Override
	public void onBindViewHolder( ProjectBoardHolder holder, final int position ){

		holder.mCardView.setOnClickListener( new View.OnClickListener(){
			@Override
			public void onClick( View view ){
				Model.setSelectedTask( tasks[ position ] );
				activity.startActivity( new Intent( activity, TaskInfoView.class ) );

			}
		} );
		holder.mTaskInfoButton.setText( tasks[ position ].getName() );
		holder.mTaskInfoButton.setOnClickListener( new View.OnClickListener(){
			@Override
			public void onClick( View view ){
				Model.setSelectedTask( tasks[ position ] );
				activity.startActivity( new Intent( activity, TaskInfoView.class ) );
			}
		} );
	}

	@Override
	public int getItemCount(){
		return tasks != null ? tasks.length : 0;
	}

}
