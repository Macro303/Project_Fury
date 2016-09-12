package tobedevelopers.project_fury.project_info;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import tobedevelopers.project_fury.R;
import tobedevelopers.project_fury.model.Column;

/**
 * Created by A on 9/10/2016.
 */
public class ColumnRecyclerAdapter extends RecyclerView.Adapter< ColumnViewHolder > implements ItemTouchHelperAdapter{

	List< Column > columnList = null;
	private WeakReference< ProjectInfoContract.Presenter > presenterWeakReference;

	public void setData( Column[] columns ){
		columnList = new ArrayList<>();
		columnList.addAll( Arrays.asList( columns ) );
		notifyDataSetChanged();
	}

	@Override
	public ColumnViewHolder onCreateViewHolder( ViewGroup parent, int viewType ){
		View view = LayoutInflater.from( parent.getContext() ).inflate( R.layout.list_item_columns, parent, false );
		return new ColumnViewHolder( view );
	}

	@Override
	public void onBindViewHolder( ColumnViewHolder holder, int position ){
		Column column = getItem( position );
		holder.bindView( column );
	}

	public Column getItem( int position ){
		return columnList != null && position < getItemCount() ? columnList.get( position ) : null;
	}

	@Override
	public int getItemCount(){
		return columnList != null ? columnList.size() : 0;
	}

	@Override
	public void onItemDismiss( int position ){
//		ProjectInfoContract.Presenter presenter = presenterWeakReference.get();
//		presenter.userSelectDeleteColumn();
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
