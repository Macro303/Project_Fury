package tobedevelopers.project_fury.all_boards;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tobedevelopers.project_fury.R;
import tobedevelopers.project_fury.project_board.implementation.ProjectBoardView;
import tobedevelopers.project_fury.project_info.implementation.ProjectInfoView;

/**
 * Created by Macro303 on 13/08/2016.
 */
public class AllBoardsRecyclerAdapter extends RecyclerView.Adapter< AllBoardsHolder >{

	private Activity activity;

	public AllBoardsRecyclerAdapter( Activity activity ){
		this.activity = activity;
	}

	@Override
	public void onBindViewHolder( AllBoardsHolder holder, int position ){
		holder.mProjectBoardButton.setOnClickListener( new View.OnClickListener(){
			@Override
			public void onClick( View view ){
				activity.startActivity( new Intent( activity.getApplicationContext(), ProjectBoardView.class ) );
			}
		} );
		holder.mProjectInfoButton.setOnClickListener( new View.OnClickListener(){
			@Override
			public void onClick( View view ){
				activity.startActivity( new Intent( activity.getApplicationContext(), ProjectInfoView.class ) );
			}
		} );
	}

	@Override
	public int getItemCount(){
		return 5;
	}

	@Override
	public AllBoardsHolder onCreateViewHolder( ViewGroup parent, int viewType ){
		View view = LayoutInflater.from( parent.getContext() ).inflate( R.layout.card_all_boards, parent, false );
		return new AllBoardsHolder( view );
	}
}
