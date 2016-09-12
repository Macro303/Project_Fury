package tobedevelopers.project_fury.backlog;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import tobedevelopers.project_fury.model.Project;

/**
 * Created by Macro303 on 12/08/2016.
 */
public class BacklogFragmentPagerAdapter extends FragmentStatePagerAdapter{

	private Project[] projects;

	public BacklogFragmentPagerAdapter( FragmentManager fragmentManager ){
		super( fragmentManager );
		this.projects = new Project[]{ new Project( "No Projects", "No Projects", new String[]{ "Unassigned" } ) };
	}

	public void setData( Project[] projects ){
		if( projects.length >= 1 )
			this.projects = projects;
		else
			this.projects = new Project[]{ new Project( "No Projects", "No Projects", new String[]{ "Unassigned" } ) };
		notifyDataSetChanged();
	}

	@Override
	public int getCount(){
		return projects.length;
	}

	@Override
	public CharSequence getPageTitle( int position ){
		return projects[ position ].getName();
	}

	@Override
	public Fragment getItem( int position ){
		return BacklogFragment.newInstance( position, projects[ position ] );
	}
}
