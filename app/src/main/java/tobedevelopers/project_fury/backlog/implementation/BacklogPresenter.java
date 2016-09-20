package tobedevelopers.project_fury.backlog.implementation;

import android.os.AsyncTask;

import java.lang.ref.WeakReference;

import tobedevelopers.project_fury.backlog.BacklogContract;
import tobedevelopers.project_fury.model.Model;
import tobedevelopers.project_fury.model.ModelContract;
import tobedevelopers.project_fury.model.Project;

/**
 * Created by Macro303 on 12/08/2016.
 */
public class BacklogPresenter implements BacklogContract.Presenter{

	private WeakReference< BacklogContract.View > viewWeakReference;
	private WeakReference< BacklogContract.Navigation > navigationWeakReference;
	private ModelContract model;
	private LoadProjectsTask loadProjectsTask;

	public BacklogPresenter( BacklogContract.View view, BacklogContract.Navigation navigation ){
		this.viewWeakReference = new WeakReference<>( view );
		this.navigationWeakReference = new WeakReference<>( navigation );
		this.model = new Model();
	}

//	@Override
//	public void userSelectCreateTask(){
//		BacklogContract.View view = viewWeakReference.get();
//		BacklogContract.Navigation navigation = navigationWeakReference.get();
//
//		if( view != null && navigation != null )
//			new CreateTask().execute();
//	}

	@Override
	public void loadProjects(){
		BacklogContract.View view = viewWeakReference.get();
		BacklogContract.Navigation navigation = navigationWeakReference.get();

		if( view != null && navigation != null ){
			loadProjectsTask = new LoadProjectsTask();
			loadProjectsTask.execute();
		}
	}

	@Override
	public void cancelAllAsyncTasks( Boolean condition ){
		loadProjectsTask.cancel( condition );
	}

//	private class CreateTask extends AsyncTask< String, Void, ProjectResponse >{
//
//		@Override
//		protected void onPreExecute(){
//			viewWeakReference.get().loadingInProgress();
//		}
//
//		@Override
//		protected ProjectResponse doInBackground( String... strings ){
//			return model.getAllProjects();
//		}
//
//		@Override
//		protected void onPostExecute( ProjectResponse response ){
//			BacklogContract.View view = viewWeakReference.get();
//			BacklogContract.Navigation navigation = navigationWeakReference.get();
//
//			switch( response.getMessage() ){
//				case "Success":
//					Model.setSelectedProject( response.getProjects()[ 0 ] );
//					navigation.navigateToCreateTask();
//					break;
//				case "No Internet Access":
//					view.noInternetAccessValidation();
//					break;
//				default:
//					break;
//			}
//		}
//	}

	private class LoadProjectsTask extends AsyncTask< Void, Void, Holder >{
		@Override
		protected void onPreExecute(){
			super.onPreExecute();
			BacklogContract.View view = viewWeakReference.get();

			if( view != null )
				view.showProjectUpdatingInProgress();
		}

		@Override
		protected Holder doInBackground( Void... voids ){
			Holder holder = null;
			holder = new Holder( model.getAllProjects().getProjects() );
			for( Project project : holder.getProjects() ){
				if( isCancelled() ){
					break;
				}else{
					holder.addTasks( project.getName(), model.getAllProjectTasks( project.getProjectID() ).getTasks() );
					holder.addColumns( project.getName(), model.getAllProjectColumns( project.getProjectID() ).getColumns() );
				}
			}
			return holder;
		}

		@Override
		protected void onPostExecute( Holder result ){
			super.onPostExecute( result );
			BacklogContract.View view = viewWeakReference.get();

			if( view != null )
				view.hideProjectUpdatingInProgress();
				view.fillProjects( result );
		}

		@Override
		protected void onCancelled( Holder result ){
			super.onCancelled( result );
		}

	}
}
