package tobedevelopers.project_fury.dashboard.implementation;

import android.os.AsyncTask;

import java.lang.ref.WeakReference;

import tobedevelopers.project_fury.dashboard.DashboardContract;
import tobedevelopers.project_fury.model.Model;
import tobedevelopers.project_fury.model.ModelContract;
import tobedevelopers.project_fury.model.ProjectResponse;
import tobedevelopers.project_fury.model.TaskResponse;

/**
 * Created by Macro303 on 10/08/2016.
 */
public class DashboardPresenter implements DashboardContract.Presenter{

	private WeakReference< DashboardContract.View > viewWeakReference;
	private WeakReference< DashboardContract.Navigation > navigationWeakReference;
	private ModelContract model;

	public DashboardPresenter( DashboardContract.View view, DashboardContract.Navigation navigation ){
		this.viewWeakReference = new WeakReference<>( view );
		this.navigationWeakReference = new WeakReference<>( navigation );
		this.model = new Model();
	}

	@Override
	public void userSelectCreateProject(){
		DashboardContract.View view = viewWeakReference.get();
		DashboardContract.Navigation navigation = navigationWeakReference.get();

		if( view != null && navigation != null )
			navigation.navigateToCreateProject();
	}

	@Override
	public void userSelectProjectInfo(){
		DashboardContract.View view = viewWeakReference.get();
		DashboardContract.Navigation navigation = navigationWeakReference.get();

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
					DashboardContract.View view = viewWeakReference.get();
					DashboardContract.Navigation navigation = navigationWeakReference.get();

					switch( response.getMessage() ){
						case "Success":
							Model.setSelectedProject( response.getProjects()[ 0 ] );
							navigation.navigateToProjectInfo();
							break;
						case "No Internet Access":
							view.noInternetAccessValidation();
							break;
						default:
							break;
					}
				}
			}.executeOnExecutor( AsyncTask.THREAD_POOL_EXECUTOR );
		}
	}

	@Override
	public void userSelectTaskInfo(){
		DashboardContract.View view = viewWeakReference.get();
		DashboardContract.Navigation navigation = navigationWeakReference.get();

		if( view != null && navigation != null ){
			new AsyncTask< String, Void, TaskResponse >(){

				@Override
				protected void onPreExecute(){
					viewWeakReference.get().loadingInProgress();
				}

				@Override
				protected TaskResponse doInBackground( String... strings ){
					Model.setSelectedProject( model.getAllProjects().getProjects()[ 0 ] );
					return model.getAllProjectTasks( Model.getSelectedProject().getProjectID() );
				}

				@Override
				protected void onPostExecute( TaskResponse response ){
					DashboardContract.View view = viewWeakReference.get();
					DashboardContract.Navigation navigation = navigationWeakReference.get();

					switch( response.getMessage() ){
						case "Success":
							Model.setSelectedTask( response.getTasks()[ 0 ] );
							navigation.navigateToTaskInfo();
							break;
						case "No Internet Access":
							view.noInternetAccessValidation();
							break;
						default:
							break;
					}
				}
			}.executeOnExecutor( AsyncTask.THREAD_POOL_EXECUTOR );
		}
	}

	@Override
	public void loadProjects(){
		DashboardContract.View view = viewWeakReference.get();
		DashboardContract.Navigation navigation = navigationWeakReference.get();

		if( view != null && navigation != null )
			new LoadProjectsAsyncTask().executeOnExecutor( AsyncTask.THREAD_POOL_EXECUTOR );
	}

	private class LoadProjectsAsyncTask extends AsyncTask< Void, Void, ProjectResponse >{
		@Override
		protected ProjectResponse doInBackground( Void... voids ){
			return model.getAllProjects();
		}

		@Override
		protected void onPostExecute( ProjectResponse response ){
			super.onPostExecute( response );
			DashboardContract.View view = viewWeakReference.get();
			DashboardContract.Navigation navigation = navigationWeakReference.get();

			switch( response.getMessage() ){
				case "Success":
					view.loadProjectsIntoList( response.getProjects() );
					break;
				case "No Internet Access":
					view.noInternetAccessValidation();
					break;
				default:
					view.defaultErrorMessage();
					break;
			}
		}

		@Override
		protected void onPreExecute(){
			super.onPreExecute();
		}
	}
}
