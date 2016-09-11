package tobedevelopers.project_fury.project_info;

import android.support.v7.appcompat.BuildConfig;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import tobedevelopers.project_fury.R;
import tobedevelopers.project_fury.model.Column;

/**
 * Created by A on 9/10/2016.
 */
public class ColumnViewHolder extends RecyclerView.ViewHolder{

	private TextView columnName;


	public ColumnViewHolder( View view ){
		super( view );
		columnName = ( TextView ) view.findViewById( R.id.listItem_columnName );
	}

	public void bindView( Column data ){
		if( data != null ){
			columnName.setText( data.getName() );
		}else{
			if( BuildConfig.DEBUG ){
				throw new IllegalArgumentException( "Null data passed to ColumnViewHolder" );
			}
		}
	}
}
