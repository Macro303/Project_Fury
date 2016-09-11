package tobedevelopers.project_fury.project_info;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import tobedevelopers.project_fury.R;
import tobedevelopers.project_fury.model.Column;

/**
 * Created by A on 9/10/2016.
 */
public class ColumnRecyclerAdapter extends RecyclerView.Adapter< ViewHolder > implements ItemTouchHelperAdapter{

	List< Column > columnList;
	private Context context;
	private Column[] columns;


	public ColumnRecyclerAdapter( Context context, Column[] columns ){
		this.context = context;
//		this.columns = columns;
		columnList = new ArrayList<>();
		columnList.addAll( Arrays.asList( columns ) );
	}

	@Override
	public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType ){
		View view = LayoutInflater.from( parent.getContext() ).inflate( R.layout.list_item_columns, parent, false );
		return new ViewHolder( view );
	}

	@Override
	public void onBindViewHolder( ViewHolder holder, int position ){
		holder.columnName.setText( columnList.get( position ).getName() );
	}

	@Override
	public int getItemCount(){
		return columnList != null ? columnList.size() : 0;
//		return columnList.size();
	}

	@Override
	public void onItemDismiss( int position ){
		columnList.remove( columnList.get( position ) );
		notifyItemRemoved( position );
	}

	@Override
	public boolean onItemMove( int fromPosition, int toPosition ){
		if( fromPosition < toPosition ){
			for( int i = fromPosition; i < toPosition; i++ ){
				Collections.swap( columnList, i, i + 1 );
			}
		}else{
			for( int i = fromPosition; i > toPosition; i-- ){
				Collections.swap( columnList, i, i - 1 );
			}
		}
		notifyItemMoved( fromPosition, toPosition );
		return true;
	}
}
