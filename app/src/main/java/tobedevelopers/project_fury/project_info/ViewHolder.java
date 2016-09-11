package tobedevelopers.project_fury.project_info;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import tobedevelopers.project_fury.R;

/**
 * Created by A on 9/10/2016.
 */
public class ViewHolder extends RecyclerView.ViewHolder{

	public TextView columnName;


	public ViewHolder( View view ){
		super( view );
		columnName = ( TextView ) view.findViewById( R.id.listItem_columnName );
	}
}
