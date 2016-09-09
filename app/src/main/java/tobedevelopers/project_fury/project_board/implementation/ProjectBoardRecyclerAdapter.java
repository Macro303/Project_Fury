package tobedevelopers.project_fury.project_board.implementation;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tobedevelopers.project_fury.R;
import tobedevelopers.project_fury.model.Task;

/**
 * Created by Macro303 on 13/08/2016.
 */
public class ProjectBoardRecyclerAdapter extends RecyclerView.Adapter< ProjectBoardHolder >{

	private FragmentActivity activity;
	private Task[] tasks;

	public ProjectBoardRecyclerAdapter( FragmentActivity activity ){

		this.activity = activity;
//		this.tasks = tasks;
	}

	@Override
	public void onBindViewHolder( ProjectBoardHolder holder, int position ){
		holder.mCardView.setOnClickListener( new View.OnClickListener(){
			@Override
			public void onClick( View view ){
//				activity.startActivity( new Intent( activity, TaskInfoView.class ) );

			}
		} );
		holder.mTaskInfoButton.setOnClickListener( new View.OnClickListener(){
			@Override
			public void onClick( View view ){
//				activity.startActivity( new Intent( activity, TaskInfoView.class ) );
			}
		} );
	}

	@Override
	public int getItemCount(){
		return tasks.length;
	}

	@Override
	public ProjectBoardHolder onCreateViewHolder( ViewGroup parent, int viewType ){
		View view = LayoutInflater.from( parent.getContext() ).inflate( R.layout.card_project_board, parent, false );
		return new ProjectBoardHolder( view );
	}
}
