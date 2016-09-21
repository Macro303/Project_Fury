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
		if( holder != null && holder.getProjects() != null && holder.getProjects().length > 0 )
			return holder.getProjects().length;
		else
			return 1;
	}

	@Override
	public CharSequence getPageTitle( int position ){
		if( holder != null && holder.getProjects() != null && holder.getProjects().length > 0 ){
			Project[] projects = holder.getProjects();
			return projects[ position ].getName();
		}else{
			return "No Projects";
		}
	}

	@Override
	public Fragment getItem( int position ){
		if( holder != null && holder.getProjects() != null && holder.getProjects().length > 0 ){
			Project[] projects = holder.getProjects();
			Task[] tasks = holder.getTasks( projects[ position ].getName() );
			Column[] columns = holder.getColumns( projects[ position ].getName() );
			return new BacklogFragment().setData( tasks, columns, projects[ position ] );
		}else{
			return new BacklogFragment();
		}
	}
}
