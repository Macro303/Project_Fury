package tobedevelopers.project_fury.project_info.implementation;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import tobedevelopers.project_fury.R;
import tobedevelopers.project_fury.model.Column;

/**
 * Created by A on 9/10/2016.
 */
public class ColumnViewHolder extends RecyclerView.ViewHolder{

	private CardView mCardView;
	private TextView mColumnName;
	private ImageView mDragImage;

	public ColumnViewHolder( View view ){
		super( view );
		mCardView = ( CardView ) view.findViewById( R.id.projectInfoFragment_columnCard );
		mColumnName = ( TextView ) view.findViewById( R.id.projectInfoFragment_columnCard_columnName );
		mDragImage = ( ImageView ) view.findViewById( R.id.projectInfoFragment_columnCard_dragImage );
	}

	public void bindView( Column data ){
		if( data != null )
			mColumnName.setText( data.getName().toUpperCase() );
	}
}
