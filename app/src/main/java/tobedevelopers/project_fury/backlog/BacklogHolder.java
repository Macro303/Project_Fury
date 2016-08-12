package tobedevelopers.project_fury.backlog;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import tobedevelopers.project_fury.R;

/**
 * Created by Macro303 on 12/08/2016.
 */
public class BacklogHolder extends RecyclerView.ViewHolder{

	//UI References
	public CardView mCardView;
	public Button mTaskInfoButton;

	public BacklogHolder( View view ){
		super( view );
		mCardView = ( CardView ) view.findViewById( R.id.backlogFragment_cardView );
		mTaskInfoButton = ( Button ) view.findViewById( R.id.backlogFragment_taskInfoButton );
	}
}
