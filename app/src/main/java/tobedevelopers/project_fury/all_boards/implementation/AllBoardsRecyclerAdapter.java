package tobedevelopers.project_fury.all_boards.implementation;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.ref.WeakReference;

import tobedevelopers.project_fury.R;
import tobedevelopers.project_fury.all_boards.AllBoardsContract;
import tobedevelopers.project_fury.model.Model;
import tobedevelopers.project_fury.model.Project;

/**
 * Created by Macro303 on 13/08/2016.
 */
public class AllBoardsRecyclerAdapter extends RecyclerView.Adapter< AllBoardsHolder >{

	private WeakReference< AllBoardsContract.Navigation > navigationWeakReference;

	private Activity activity;
	private Project[] projects;

	public AllBoardsRecyclerAdapter( Activity activity, AllBoardsContract.Navigation navigation, Project[] projects ){
		this.activity = activity;
		this.projects = projects;
		this.navigationWeakReference = new WeakReference< AllBoardsContract.Navigation >( navigation );
	}

	@Override
	public AllBoardsHolder onCreateViewHolder( ViewGroup parent, int viewType ){
		View view = LayoutInflater.from( parent.getContext() ).inflate( R.layout.card_all_boards, parent, false );
		return new AllBoardsHolder( view );
	}

	@Override
	public void onBindViewHolder( AllBoardsHolder holder, final int position ){

		final AllBoardsContract.Navigation navigation = navigationWeakReference.get();

		holder.mProjectInfoButton.setText( projects[ position ].getName() );
		holder.mProjectBoardButton.setOnClickListener( new View.OnClickListener(){
			@Override
			public void onClick( View view ){
				Model.setSelectedProject( projects[ position ] );
				navigation.navigateToProjectBoard();
//				activity.startActivity( new Intent( activity.getApplicationContext(), ProjectBoardView.class ) );
			}
		} );
		holder.mProjectInfoButton.setOnClickListener( new View.OnClickListener(){
			@Override
			public void onClick( View view ){
				Model.setSelectedProject( projects[ position ] );
				navigation.navigateToProjectInfo();
//				activity.startActivity( new Intent( activity.getApplicationContext(), ProjectInfoView.class ) );
			}
		} );
	}

	@Override
	public int getItemCount(){
		return projects.length;
	}


}
