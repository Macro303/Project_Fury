package tobedevelopers.project_fury.project_info;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tobedevelopers.project_fury.R;
import tobedevelopers.project_fury.model.Column;

/**
 * Created by A on 9/10/2016.
 */
public class ColumnRecyclerAdapter extends RecyclerView.Adapter< ViewHolder >{

	private Context context;
	private Column[] columns;

	public ColumnRecyclerAdapter( Context context, Column[] columns ){
		this.context = context;
		this.columns = columns;
	}

	@Override
	public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType ){
		View view = LayoutInflater.from( parent.getContext() ).inflate( R.layout.list_item_columns, parent, false );
		return new ViewHolder( view );
	}

	@Override
	public void onBindViewHolder( ViewHolder holder, int position ){
		holder.columnName.setText( columns[ position ].getName() );
	}

	@Override
	public int getItemCount(){
		return columns.length;
	}
}
