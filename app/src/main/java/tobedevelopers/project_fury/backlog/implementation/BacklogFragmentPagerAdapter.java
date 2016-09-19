package tobedevelopers.project_fury.backlog.implementation;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

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
		return holder != null ? holder.getProjects() != null ? holder.getProjects()[ position ].getName() : "No Projects" : "No Projects";
	}

	@Override
	public Fragment getItem( int position ){
		return new BacklogFragment().setData( holder.getTasks( holder.getProjects()[ position ].getName() ), holder.getColumns( holder.getProjects()[ position ].getName() ), holder.getProjects()[ position ] );
	}
}
