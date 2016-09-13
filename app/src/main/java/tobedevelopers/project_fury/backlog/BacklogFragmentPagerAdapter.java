package tobedevelopers.project_fury.backlog;

import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import tobedevelopers.project_fury.model.Model;
import tobedevelopers.project_fury.model.Project;

/**
 * Created by Macro303 on 12/08/2016.
 */
public class BacklogFragmentPagerAdapter extends FragmentStatePagerAdapter{

	private Project[] projects;
	private Holder holder;

	public BacklogFragmentPagerAdapter( FragmentManager fragmentManager ){
		super( fragmentManager );
	}

	public void setData( Project[] projects ){
		this.projects = projects;
		holder = new Holder( projects );
		new AsyncClass().executeOnExecutor( AsyncTask.THREAD_POOL_EXECUTOR );
	}

	public void initializeData(){
		notifyDataSetChanged();
	}

	@Override
	public int getCount(){
		return projects != null ? projects.length : 0;
	}

	@Override
	public CharSequence getPageTitle( int position ){
		return projects != null ? projects[ position ].getName() : "No Projects";
	}

	@Override
	public Fragment getItem( int position ){
		Model.setSelectedProject( projects[ position ] );
		return new BacklogFragment().setDataAgain( holder.getTasks().get( projects[ position ].getName() ) );
	}

	private class AsyncClass extends AsyncTask< Void, Void, Void >{
		@Override
		protected Void doInBackground( Void... params ){
			Model model = new Model();
			for( Project project : projects ){
				holder.addTasks( project.getName(), model.getAllProjectTasks( project.getProjectID() ).getTasks() );
			}
			return null;
		}

		@Override
		protected void onPostExecute( Void result ){
			super.onPostExecute( result );
			initializeData();
		}

		@Override
		protected void onPreExecute(){
			super.onPreExecute();
		}
	}
}
