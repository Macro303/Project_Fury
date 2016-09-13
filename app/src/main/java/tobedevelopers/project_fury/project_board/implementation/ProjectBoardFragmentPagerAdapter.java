package tobedevelopers.project_fury.project_board.implementation;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import tobedevelopers.project_fury.model.Column;
import tobedevelopers.project_fury.model.Model;

/**
 * Created by Macro303 on 13/08/2016.
 */
public class ProjectBoardFragmentPagerAdapter extends FragmentStatePagerAdapter{

	private Column[] columns;

	public ProjectBoardFragmentPagerAdapter( FragmentManager fm ){
		super( fm );
	}

	public void setData( Column[] tabs ){
		this.columns = tabs;
		Model.setSelectedColumns( columns );
		notifyDataSetChanged();
	}

	@Override
	public int getCount(){
		return columns != null ? columns.length : 0;
	}

	@Override
	public String getPageTitle( int position ){
		return columns != null ? columns[ position ].getName() : "";
	}

	@Override
	public Fragment getItem( int position ){
		return ProjectBoardFragment.newInstance( position );
	}
}
