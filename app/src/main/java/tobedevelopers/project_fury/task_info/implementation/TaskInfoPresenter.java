package tobedevelopers.project_fury.task_info.implementation;

import android.os.AsyncTask;

import java.lang.ref.WeakReference;

import tobedevelopers.project_fury.model.Column;
import tobedevelopers.project_fury.model.ColumnResponse;
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
			new RemoveAsyncTask().execute();
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
				error = false;
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
	public void getColumnsOnProject(){
		TaskInfoContract.View view = viewWeakReference.get();

		if( view != null )
			new LoadColumnsTask().execute();
	}

	@Override
	public void userSelectSaveTask( String mAssignee, String mPriority, Column mColumn ){
		TaskInfoContract.View view = viewWeakReference.get();
		TaskInfoContract.Navigation navigation = navigationWeakReference.get();

		if( view != null && navigation != null )
			new UpdateAsyncTask().execute( mAssignee, mPriority, mColumn.getColumnID() );
	}

	private class LoadColumnsTask extends AsyncTask< String, Void, ColumnResponse >{
		TaskInfoContract.View view = viewWeakReference.get();

		@Override
		public void onPreExecute(){
			super.onPreExecute();
		}

		@Override
		protected ColumnResponse doInBackground( String... strings ){
			return model.getAllProjectColumns( Model.getSelectedProject().getProjectID() );
		}

		@Override
		public void onPostExecute( ColumnResponse response ){
			super.onPostExecute( response );

			switch( response.getMessage() ){
				case "Success":
					view.setColumnSpinner( response.getColumns() );
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

	private class UpdateAsyncTask extends AsyncTask< String, Void, Response >{
		@Override
		protected void onPreExecute(){
			super.onPreExecute();
			TaskInfoContract.View view = viewWeakReference.get();

			if( view != null )
				view.showTaskUpdatingInProgress();
		}

		@Override
		protected Response doInBackground( String... strings ){
			if( mTaskDescription.equals( "null" ) )
				mTaskDescription = "";
			return model.updateTask( Model.getSelectedProject().getProjectID(), Model.getSelectedTask().getTaskID(), strings[ 2 ], mTaskName, mTaskDescription, strings[ 0 ], strings[ 1 ].toUpperCase() );
		}

		@Override
		protected void onPostExecute( Response response ){
			super.onPostExecute( response );
			TaskInfoContract.View view = viewWeakReference.get();
			TaskInfoContract.Navigation navigation = navigationWeakReference.get();

			if( view != null && navigation != null ){
				view.hideTaskUpdatingInProgress();

				switch( response.getMessage() ){
					case "Update successful.":
						navigation.navigateToPrevious();
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
	}

	private class RemoveAsyncTask extends AsyncTask< String, Void, Response >{
		@Override
		protected void onPreExecute(){
			super.onPreExecute();
			TaskInfoContract.View view = viewWeakReference.get();

			if( view != null ){
				view.showTaskUpdatingInProgress();
			}
		}

		@Override
		protected Response doInBackground( String... strings ){
			return model.deleteTask( Model.getSelectedProject().getProjectID(), Model.getSelectedTask().getTaskID() );
		}

		@Override
		protected void onPostExecute( Response response ){
			super.onPostExecute( response );
			TaskInfoContract.View view = viewWeakReference.get();
			TaskInfoContract.Navigation navigation = navigationWeakReference.get();

			if( view != null && navigation != null ){
				view.hideTaskUpdatingInProgress();

				switch( response.getMessage() ){
					case "Delete successful.":
						navigation.navigateToPrevious();
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


	}
}
