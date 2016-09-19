package tobedevelopers.project_fury.project_info.implementation;

import android.support.v7.appcompat.BuildConfig;
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

	private TextView columnName;
	private ImageView image;

	public ColumnViewHolder( View view ){
		super( view );
		columnName = ( TextView ) view.findViewById( R.id.listItem_columnName );
		image = ( ImageView ) view.findViewById( R.id.listItem_image );
	}

	public void bindView( Column data ){
		if( data != null ){
			columnName.setText( data.getName() );
			image.getDrawable();
		}else{
			if( BuildConfig.DEBUG ){
				throw new IllegalArgumentException( "Null data passed to ColumnViewHolder" );
			}
		}
	}
}
