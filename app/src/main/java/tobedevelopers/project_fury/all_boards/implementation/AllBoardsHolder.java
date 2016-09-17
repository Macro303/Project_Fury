package tobedevelopers.project_fury.all_boards.implementation;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import tobedevelopers.project_fury.R;

/**
 * Created by Macro303 on 13/08/2016.
 */
public class AllBoardsHolder extends RecyclerView.ViewHolder{

	//UI References
	public ImageView mProjectBoardImageView;
	public Button mProjectInfoButton;
	public TextView mNoProjectTextView;
	public CardView mCardView;

	public AllBoardsHolder( View view ){
		super( view );
		mProjectBoardImageView = ( ImageView ) view.findViewById( R.id.allBoardsActivity_projectBoardImageView );
		mProjectInfoButton = ( Button ) view.findViewById( R.id.allBoardsActivity_projectInfoButton );
		mNoProjectTextView = ( TextView ) view.findViewById( R.id.allBoardsActivity_noTaskTextView );
		mCardView = ( CardView ) view.findViewById( R.id.allBoardsFragment_cardView );
	}
}
