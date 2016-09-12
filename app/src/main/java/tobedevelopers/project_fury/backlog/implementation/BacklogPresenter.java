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
	public void userSelectCreateTask(){
		BacklogContract.View view = viewWeakReference.get();
		BacklogContract.Navigation navigation = navigationWeakReference.get();

		if( view != null && navigation != null ){
			new AsyncTask< String, Void, ProjectResponse >(){

				@Override
				protected void onPreExecute(){
					viewWeakReference.get().loadingInProgress();
				}

				@Override
				protected ProjectResponse doInBackground( String... strings ){
					return model.getAllProjects();
				}

				@Override
				protected void onPostExecute( ProjectResponse response ){
					BacklogContract.View view = viewWeakReference.get();
					BacklogContract.Navigation navigation = navigationWeakReference.get();

					switch( response.getMessage() ){
						case "Success":
							Model.setSelectedProject( response.getProjects()[ 0 ] );
							navigation.navigateToCreateTask();
							break;
						case "No Internet Access":
							view.noInternetAccessValidation();
							break;
						default:
							break;
					}
				}
			}.executeOnExecutor( AsyncTask.THREAD_POOL_EXECUTOR );
//			navigation.navigateToCreateTask();
		}
	}

	@Override
	public void loadProjects(){
		BacklogContract.View view = viewWeakReference.get();
		BacklogContract.Navigation navigation = navigationWeakReference.get();

		if( view != null && navigation != null )
			new LoadProjectsAsyncTask().executeOnExecutor( AsyncTask.THREAD_POOL_EXECUTOR );
	}

	private class LoadProjectsAsyncTask extends AsyncTask< Void, Void, ProjectResponse >{
		@Override
		protected ProjectResponse doInBackground( Void... voids ){
			return model.getAllProjects();
		}

		@Override
		protected void onPostExecute( ProjectResponse projectResponse ){
			super.onPostExecute( projectResponse );
			BacklogContract.View view = viewWeakReference.get();
			BacklogContract.Navigation navigation = navigationWeakReference.get();

			if( view != null && navigation != null ){
				switch( projectResponse.getMessage() ){
					case "Success":
						view.fillProjects( projectResponse.getProjects() );
						break;
					case "No Internet Access":
						view.noInternetAccessValidation();
						break;
					default:
						view.defaultErrorMessage();
						break;
				}
			}
		}

		@Override
		protected void onPreExecute(){
			super.onPreExecute();
		}
	}
}
