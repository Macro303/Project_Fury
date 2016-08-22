package tobedevelopers.project_fury.project_board;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tobedevelopers.project_fury.R;
import tobedevelopers.project_fury.task_info.implementation.TaskInfoView;

/**
 * Created by Macro303 on 13/08/2016.
 */
public class ProjectBoardRecyclerAdapter extends RecyclerView.Adapter< ProjectBoardHolder >{

	private FragmentActivity activity;

	public ProjectBoardRecyclerAdapter( FragmentActivity activity ){
		this.activity = activity;
	}

	@Override
	public void onBindViewHolder( ProjectBoardHolder holder, int position ){
		holder.mCardView.setOnClickListener( new View.OnClickListener(){
			@Override
			public void onClick( View view ){
				activity.startActivity( new Intent( activity, TaskInfoView.class ) );
			}
		} );
		holder.mTaskInfoButton.setOnClickListener( new View.OnClickListener(){
			@Override
			public void onClick( View view ){
				activity.startActivity( new Intent( activity, TaskInfoView.class ) );
			}
		} );
	}

	@Override
	public int getItemCount(){
		return 5;
	}

	@Override
	public ProjectBoardHolder onCreateViewHolder( ViewGroup parent, int viewType ){
		View view = LayoutInflater.from( parent.getContext() ).inflate( R.layout.card_project_board, parent, false );
		return new ProjectBoardHolder( view );
	}
}
