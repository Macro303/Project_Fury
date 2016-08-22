package tobedevelopers.project_fury.project_board;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Macro303 on 13/08/2016.
 */
public class ProjectBoardFragmentPagerAdapter extends FragmentStatePagerAdapter{

	private String[] tabTitles = new String[]{ "New", "Design", "Development", "Testing", "Done" };

	public ProjectBoardFragmentPagerAdapter( FragmentManager fragmentManager ){
		super( fragmentManager );
	}

	@Override
	public int getCount(){
		return tabTitles.length;
	}

	@Override
	public CharSequence getPageTitle( int position ){
		return tabTitles[ position ];
	}

	@Override
	public Fragment getItem( int position ){
		return ProjectBoardFragment.newInstance( position );
	}
}
