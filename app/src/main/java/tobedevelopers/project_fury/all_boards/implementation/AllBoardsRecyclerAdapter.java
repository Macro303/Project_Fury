package tobedevelopers.project_fury.all_boards.implementation;

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

	private Project[] projects;

	public AllBoardsRecyclerAdapter( AllBoardsContract.Navigation navigation ){
		this.navigationWeakReference = new WeakReference< AllBoardsContract.Navigation >( navigation );
	}

	public void setData( Project[] projects ){
		this.projects = projects;
		notifyDataSetChanged();
	}

	@Override
	public AllBoardsHolder onCreateViewHolder( ViewGroup parent, int viewType ){
		View view = LayoutInflater.from( parent.getContext() ).inflate( R.layout.card_all_boards, parent, false );
		return new AllBoardsHolder( view );
	}

	@Override
	public void onBindViewHolder( final AllBoardsHolder holder, final int position ){

		final AllBoardsContract.Navigation navigation = navigationWeakReference.get();

		if( projects != null && projects.length != 0 ){

			holder.mNoProjectTextView.setVisibility( View.GONE );
			holder.mProjectBoardImageView.setVisibility( View.VISIBLE );
			holder.mProjectInfoButton.setVisibility( View.VISIBLE );

			holder.mProjectInfoButton.setEnabled( true );
			holder.mCardView.setEnabled( true );

			holder.mProjectInfoButton.setText( projects[ position ].getName() );
			holder.mCardView.setOnClickListener( new View.OnClickListener(){
				@Override
				public void onClick( View view ){
					holder.mProjectInfoButton.setEnabled( false );
					holder.mCardView.setEnabled( false );
					Model.setSelectedProject( projects[ position ] );
					navigation.navigateToProjectBoard();
				}
			} );
			holder.mProjectInfoButton.setOnClickListener( new View.OnClickListener(){
				@Override
				public void onClick( View view ){
					holder.mProjectInfoButton.setEnabled( false );
					holder.mCardView.setEnabled( false );
					Model.setSelectedProject( projects[ position ] );
					navigation.navigateToProjectInfo();
				}
			} );
		}else{
			holder.mNoProjectTextView.setVisibility( View.VISIBLE );
			holder.mProjectInfoButton.setVisibility( View.INVISIBLE );
			holder.mProjectBoardImageView.setVisibility( View.INVISIBLE );
		}
	}

	@Override
	public int getItemCount(){
		return projects != null && projects.length != 0 ? projects.length : 1;
	}


}
