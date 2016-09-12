package tobedevelopers.project_fury.all_boards.implementation;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import tobedevelopers.project_fury.R;

/**
 * Created by Macro303 on 13/08/2016.
 */
public class AllBoardsHolder extends RecyclerView.ViewHolder{

	//UI References
	public Button mProjectBoardButton;
	public Button mProjectInfoButton;

	public AllBoardsHolder( View view ){
		super( view );
		mProjectBoardButton = ( Button ) view.findViewById( R.id.allBoardsActivity_projectBoardButton );
		mProjectInfoButton = ( Button ) view.findViewById( R.id.allBoardsActivity_projectInfoButton );
	}
}
