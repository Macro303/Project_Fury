package tobedevelopers.project_fury.task_info.implementation;

import android.os.AsyncTask;

import java.lang.ref.WeakReference;

import tobedevelopers.project_fury.model.Model;
import tobedevelopers.project_fury.model.ModelContract;
import tobedevelopers.project_fury.model.Response;
import tobedevelopers.project_fury.task_info.TaskInfoContract;

/**
 * Created by Macro303 on 11/08/2016.
 */
public class TaskInfoPresenter implements TaskInfoContract.Presenter{

	private WeakReference< TaskInfoContract.View > viewWeakReference;
	private WeakReference< TaskInfoContract.Navigation > navigationWeakReference;
	private ModelContract model;

	private String mTaskName;
	private String mTaskDescription;

	public TaskInfoPresenter( TaskInfoContract.View view, TaskInfoContract.Navigation navigation ){
		this.viewWeakReference = new WeakReference<>( view );
		this.navigationWeakReference = new WeakReference<>( navigation );
		model = new Model();
	}

	@Override
	public void userSelectBack(){
		TaskInfoContract.View view = viewWeakReference.get();
		TaskInfoContract.Navigation navigation = navigationWeakReference.get();

		if( view != null && navigation != null )
			navigation.navigateToPrevious();
	}

	@Override
	public void userSelectEditTask(){
		TaskInfoContract.View view = viewWeakReference.get();
		TaskInfoContract.Navigation navigation = navigationWeakReference.get();

		if( view != null && navigation != null )
			view.setTaskEdited();
	}

	@Override
	public void userSelectRemoveTask(){
		TaskInfoContract.View view = viewWeakReference.get();
		TaskInfoContract.Navigation navigation = navigationWeakReference.get();

		if( view != null && navigation != null )
			navigation.navigateToPrevious();
	}

	@Override
	public void userEnterTaskName( String taskName ){
		TaskInfoContract.View view = viewWeakReference.get();

		if( view != null ){
			mTaskName = taskName;
			boolean error = false;
			if( taskName.length() < 3 ){
				view.setTaskNameUnderValidation();
				error = true;
			}else if( taskName.length() >= 20 ){
				view.setTaskNameOverValidation();
				error = true;
			}
			if( error )
				view.disableSaveTask();
			else
				view.enableSaveTask();
		}
	}

	@Override
	public void userEnterTaskDescription( String taskDescription ){
		TaskInfoContract.View view = viewWeakReference.get();

		if( view != null ){
			mTaskDescription = taskDescription;
			if( taskDescription.length() >= 128 )
				view.setTaskDescriptionOverValidation();
		}
	}

	@Override
	public void userSelectSaveTask( final String mAssignee, final String mPriority ){
		TaskInfoContract.View view = viewWeakReference.get();
		TaskInfoContract.Navigation navigation = navigationWeakReference.get();

		if( view != null && navigation != null ){
			new AsyncTask< String, Void, Response >(){

				@Override
				protected void onPreExecute(){
					viewWeakReference.get().taskUpdatingInProgress();
				}

				@Override
				protected Response doInBackground( String... strings ){
					return model.updateTask( Model.getSelectedProject().getProjectID(), Model.getSelectedTask().getTaskID(), mTaskName, mTaskDescription, mAssignee, mPriority.toUpperCase() );
				}

				@Override
				protected void onPostExecute( Response response ){
					TaskInfoContract.View view = viewWeakReference.get();

					System.out.println( "Response: " + response.toString() );
					switch( response.getMessage() ){
						case "Update successful.":
							navigationWeakReference.get().navigateToPrevious();
							break;
						case "No Internet Access":
							view.noInternetAccessValidation();
							break;
						default:
							view.defaultErrorMessage();
							break;
					}
				}
			}.executeOnExecutor( AsyncTask.THREAD_POOL_EXECUTOR );
		}
	}
}
