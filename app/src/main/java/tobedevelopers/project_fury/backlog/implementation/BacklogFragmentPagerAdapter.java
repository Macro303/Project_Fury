package tobedevelopers.project_fury.backlog.implementation;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

import tobedevelopers.project_fury.model.Model;
import tobedevelopers.project_fury.model.Project;

/**
 * Created by Macro303 on 12/08/2016.
 */
public class BacklogFragmentPagerAdapter extends FragmentStatePagerAdapter{

	//	private Holder holder;
	private List< Project > projects;

	public BacklogFragmentPagerAdapter( FragmentManager fragmentManager ){
		super( fragmentManager );
	}

	public void setData( List< Project > projects ){
		this.projects = projects;
		Model.setSelectedProjects( projects );
	}

	@Override
	public int getCount(){
		return projects != null && projects.size() != 0 ? projects.size() : 1;
	}

	@Override
	public CharSequence getPageTitle( int position ){
		return projects != null && projects.size() != 0 ? projects.get( position ).getName() : "";
	}

	@Override
	public Fragment getItem( int position ){
		return projects != null && projects.size() != 0 ? BacklogFragment.newInstance( position ) : BacklogEmptyFragment.newInstance();
	}

	@Override
	public int getItemPosition( Object object ){
		return POSITION_NONE;
	}
}
