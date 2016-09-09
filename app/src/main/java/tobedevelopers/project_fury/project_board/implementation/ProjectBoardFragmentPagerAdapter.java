package tobedevelopers.project_fury.project_board.implementation;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import tobedevelopers.project_fury.model.Column;

/**
 * Created by Macro303 on 13/08/2016.
 */
public class ProjectBoardFragmentPagerAdapter extends FragmentStatePagerAdapter{

	private Column[] tabs;

	public ProjectBoardFragmentPagerAdapter( FragmentManager fragmentManager, Column[] tabs ){
		super( fragmentManager );
		this.tabs = tabs;
	}

	@Override
	public int getCount(){
		return tabs.length;
	}

	@Override
	public String getPageTitle( int position ){
		return tabs[ position ].getName();
	}

	@Override
	public Fragment getItem( int position ){
		return ProjectBoardFragment.newInstance( position );
	}
}
