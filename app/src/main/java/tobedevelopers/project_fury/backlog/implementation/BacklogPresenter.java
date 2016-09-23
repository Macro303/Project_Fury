package tobedevelopers.project_fury.backlog.implementation;

import android.os.AsyncTask;

import java.lang.ref.WeakReference;

import tobedevelopers.project_fury.backlog.BacklogContract;
import tobedevelopers.project_fury.model.Model;
import tobedevelopers.project_fury.model.ModelContract;
import tobedevelopers.project_fury.model.ProjectResponse;

/**
 * Created by Macro303 on 12/08/2016.
 */
public class BacklogPresenter implements BacklogContract.Presenter{

	private WeakReference< BacklogContract.View > viewWeakReference;
	private WeakReference< BacklogContract.Navigation > navigationWeakReference;
	private ModelContract model;

	public BacklogPresenter( BacklogContract.View view, BacklogContract.Navigation navigation ){
		this.viewWeakReference = new WeakReference<>( view );
		this.navigationWeakReference = new WeakReference<>( navigation );
		this.model = new Model();
	}

	@Override
	public void loadProjects(){
		BacklogContract.View view = viewWeakReference.get();
		BacklogContract.Navigation navigation = navigationWeakReference.get();

		if( view != null && navigation != null ){
			new LoadProjectsTask().execute();
		}
	}

	private class LoadProjectsTask extends AsyncTask< Void, Void, ProjectResponse >{
		@Override
		protected void onPreExecute(){
			super.onPreExecute();
			BacklogContract.View view = viewWeakReference.get();

			if( view != null )
				view.showProjectUpdatingInProgress();
		}

		@Override
		protected ProjectResponse doInBackground( Void... inputs ){
			return model.getAllProjects();
		}

		@Override
		protected void onPostExecute( ProjectResponse result ){
			super.onPostExecute( result );
			BacklogContract.View view = viewWeakReference.get();

			if( view != null ){
				switch( result.getMessage() ){
					case "Success":
						view.hideProjectUpdatingInProgress();
						view.fillProjects( result.getProjects() );
						break;
				}
			}
		}

		@Override
		protected void onCancelled( ProjectResponse response ){
			super.onCancelled( response );
		}
	}
}
