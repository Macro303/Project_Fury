package tobedevelopers.project_fury.all_boards.implementation;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import tobedevelopers.project_fury.R;
import tobedevelopers.project_fury.all_boards.AllBoardsContract;
import tobedevelopers.project_fury.model.Model;
import tobedevelopers.project_fury.model.Project;

/**
 * Created by Macro303 on 13/08/2016.
 */
public class AllBoardsViewHolder extends RecyclerView.ViewHolder{

	//UI References
	private CardView mCardView;
	private Button mProjectInfoButton;
	private TextView mNoProjects;
	private ImageView mProjectBoardImage;

	private AllBoardsContract.Navigation navigation;

	public AllBoardsViewHolder( View view, AllBoardsContract.Navigation navigation ){
		super( view );
		mCardView = ( CardView ) view.findViewById( R.id.allBoardsActivity_projectCard );
		mProjectInfoButton = ( Button ) view.findViewById( R.id.allBoardsActivity_projectCard_projectInfoButton );
		mNoProjects = ( TextView ) view.findViewById( R.id.allBoardsActivity_projectCard_noProjects );
		mProjectBoardImage = ( ImageView ) view.findViewById( R.id.allBoardsActivity_projectCard_projectBoardImage );
		this.navigation = navigation;
	}

	public void bindView( Project current ){
		if( current != null ){
			mProjectInfoButton.setText( current.getName().toUpperCase() );
			setVisibility( current, true );
		}else
			setVisibility( current, false );
	}

	private void setVisibility( final Project current, boolean value ){
		mProjectInfoButton.setVisibility( value ? View.VISIBLE : View.GONE );
		mNoProjects.setVisibility( !value ? View.VISIBLE : View.GONE );
		mProjectBoardImage.setVisibility( value ? View.VISIBLE : View.GONE );
		mCardView.setEnabled( value );
		mProjectInfoButton.setEnabled( value );
		if( value ){
			mCardView.setOnClickListener( new View.OnClickListener(){
				@Override
				public void onClick( View view ){
					mProjectInfoButton.setEnabled( false );
					mCardView.setEnabled( false );
					Model.setSelectedProject( current );
					navigation.navigateToProjectBoard();
				}
			} );
			mProjectInfoButton.setOnClickListener( new View.OnClickListener(){
				@Override
				public void onClick( View view ){
					mProjectInfoButton.setEnabled( false );
					mCardView.setEnabled( false );
					Model.setSelectedProject( current );
					navigation.navigateToProjectInfo();
				}
			} );
		}else{
			mCardView.setOnClickListener( null );
			mProjectInfoButton.setOnClickListener( null );
		}
	}
}
