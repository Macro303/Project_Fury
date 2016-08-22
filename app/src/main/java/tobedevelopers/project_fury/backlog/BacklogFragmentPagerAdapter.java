package tobedevelopers.project_fury.backlog;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Macro303 on 12/08/2016.
 */
public class BacklogFragmentPagerAdapter extends FragmentStatePagerAdapter{

	private String[] tabTitles = new String[]{ "One", "Two", "Three", "Four", "Five" };

	public BacklogFragmentPagerAdapter( FragmentManager fragmentManager ){
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
		return BacklogFragment.newInstance( position );
	}
}
