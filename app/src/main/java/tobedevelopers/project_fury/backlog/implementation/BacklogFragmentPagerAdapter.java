package tobedevelopers.project_fury.backlog.implementation;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import tobedevelopers.project_fury.model.Column;
import tobedevelopers.project_fury.model.Project;
import tobedevelopers.project_fury.model.Task;

/**
 * Created by Macro303 on 12/08/2016.
 */
public class BacklogFragmentPagerAdapter extends FragmentStatePagerAdapter{

	private Holder holder;

	public BacklogFragmentPagerAdapter( FragmentManager fragmentManager ){
		super( fragmentManager );
	}

	public void setData( Holder holder ){
		this.holder = holder;
		notifyDataSetChanged();
	}

	@Override
	public int getCount(){
		return holder != null ? holder.getProjects() != null ? holder.getProjects().length : 0 : 0;
	}

	@Override
	public CharSequence getPageTitle( int position ){
		Project[] projects = holder.getProjects();
		return projects[ position ].getName();
	}

	@Override
	public Fragment getItem( int position ){
		Project[] projects = holder.getProjects();
		Task[] tasks = holder.getTasks( projects[ position ].getName() );
		Column[] columns = holder.getColumns( projects[ position ].getName() );
		return new BacklogFragment().setData( tasks, columns, projects[ position ] );
	}
}
