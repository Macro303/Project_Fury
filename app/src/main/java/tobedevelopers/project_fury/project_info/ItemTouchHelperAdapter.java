package tobedevelopers.project_fury.project_info;

/**
 * Created by A on 9/10/2016.
 */
public interface ItemTouchHelperAdapter{
	void onItemDismiss( int position );

	boolean onItemMove( int fromPosition, int toPosition );
}
