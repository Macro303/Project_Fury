package tobedevelopers.project_fury.create_task.implementation;

import android.os.AsyncTask;

import java.lang.ref.WeakReference;

import tobedevelopers.project_fury.create_task.CreateTaskContract;
import tobedevelopers.project_fury.model.Model;
import tobedevelopers.project_fury.model.ModelContract;
import tobedevelopers.project_fury.model.ProjectResponse;
import tobedevelopers.project_fury.model.Response;

/**
 * Created by Macro303 on 12/08/2016.
 */
public class CreateTaskPresenter implements CreateTaskContract.Presenter{

	private WeakReference< CreateTaskContract.View > viewWeakReference;
	private WeakReference< CreateTaskContract.Navigation > navigationWeakReference;
	private ModelContract model;

	private String mTaskName = "";
	private String mTaskDescription = "";

	public CreateTaskPresenter( CreateTaskContract.View view, CreateTaskContract.Navigation navigation ){
		this.viewWeakReference = new WeakReference<>( view );
		this.navigationWeakReference = new WeakReference<>( navigation );
		this.model = new Model();
	}

	@Override
	public void userSelectBack(){
		CreateTaskContract.View view = viewWeakReference.get();
		CreateTaskContract.Navigation navigation = navigationWeakReference.get();

		if( view != null && navigation != null )
			navigation.navigateToPrevious();
	}

	@Override
	public void userEnterTaskName( String taskName ){
		CreateTaskContract.View view = viewWeakReference.get();

		if( view != null ){
			mTaskName = taskName;
			if( taskName.length() < 3 ){
				view.setTaskNameUnderValidation();
				view.disableCreateTaskButton();
			}else if( taskName.length() > 20 )
				view.disableCreateTaskButton();
			else
				view.enableCreateTaskButton();
			if( taskName.length() >= 20 )
				view.setTaskNameOverValidation();
		}
	}

	@Override
	public void userEnterTaskDescription( String taskDescription ){
		CreateTaskContract.View view = viewWeakReference.get();

		if( view != null ){
			mTaskDescription = taskDescription;
			if( taskDescription.length() > 128 )
				view.disableCreateTaskButton();
			if( taskDescription.length() >= 128 )
				view.setTaskDescriptionOverValidation();
		}
	}

	@Override
	public void getProjects(){
		CreateTaskContract.View view = viewWeakReference.get();
		CreateTaskContract.Navigation navigation = navigationWeakReference.get();

		if( view != null && navigation != null )
			new GetProjectsAsyncTask().executeOnExecutor( AsyncTask.THREAD_POOL_EXECUTOR );
	}

	@Override
	public void userSelectCreateTask( final String mAssignee ){
		CreateTaskContract.View view = viewWeakReference.get();
		CreateTaskContract.Navigation navigation = navigationWeakReference.get();

		if( view != null && navigation != null ){
			new AsyncTask< String, Void, Response >(){

				@Override
				protected void onPreExecute(){
					viewWeakReference.get().taskCreationInProgress();
				}

				@Override
				protected Response doInBackground( String... strings ){
					return model.createTask( Model.getSelectedProject().getProjectID(), mTaskName, mTaskDescription, mAssignee );
				}

				@Override
				protected void onPostExecute( Response response ){
					CreateTaskContract.View view = viewWeakReference.get();

					switch( response.getMessage() ){
						case "Task creation successful.":
							navigationWeakReference.get().navigateToPrevious();
							break;
						case "No Internet Access":
							view.noInternetAccessValidation();
							break;
						default:
							view.errorValidation();
							break;
					}
				}
			}.executeOnExecutor( AsyncTask.THREAD_POOL_EXECUTOR );
		}
	}

	private class GetProjectsAsyncTask extends AsyncTask< Void, Void, ProjectResponse >{
		@Override
		protected ProjectResponse doInBackground( Void... voids ){
			return model.getAllProjects();
		}

		@Override
		protected void onPostExecute( ProjectResponse projectResponse ){
			super.onPostExecute( projectResponse );
			CreateTaskContract.View view = viewWeakReference.get();
			CreateTaskContract.Navigation navigation = navigationWeakReference.get();

			if( view != null && navigation != null && projectResponse.getMessage().equals( "Success" ) )
				view.setProjectSpinner( projectResponse.getProjects() );
		}

		@Override
		protected void onPreExecute(){
			super.onPreExecute();
		}
	}
}
