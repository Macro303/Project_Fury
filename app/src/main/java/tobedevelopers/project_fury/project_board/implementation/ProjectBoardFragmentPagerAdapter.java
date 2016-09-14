package tobedevelopers.project_fury.project_board.implementation;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

import tobedevelopers.project_fury.model.Column;
import tobedevelopers.project_fury.model.Model;

/**
 * Created by Macro303 on 13/08/2016.
 */
public class ProjectBoardFragmentPagerAdapter extends FragmentStatePagerAdapter{

	private List<Column> columns;

	public ProjectBoardFragmentPagerAdapter( FragmentManager fm ){
		super( fm );
	}

	public void setData( List<Column> columns ){
		this.columns = columns;
		Model.setSelectedColumns( columns );
		notifyDataSetChanged();
	}

	@Override
	public int getCount(){
		return columns != null ? columns.size() : 0;
	}

	@Override
	public String getPageTitle( int position ){
		return columns != null ? columns.get( position ).getName() : "";
	}

	@Override
	public Fragment getItem( int position ){
		return ProjectBoardFragment.newInstance( position );
	}
}
