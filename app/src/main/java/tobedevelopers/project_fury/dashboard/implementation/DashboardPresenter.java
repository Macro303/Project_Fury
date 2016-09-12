package tobedevelopers.project_fury.dashboard.implementation;

import android.os.AsyncTask;

import java.lang.ref.WeakReference;

import tobedevelopers.project_fury.dashboard.DashboardContract;
import tobedevelopers.project_fury.dashboard.ProjectHolder;
import tobedevelopers.project_fury.dashboard.TaskHolder;
import tobedevelopers.project_fury.model.ColumnResponse;
import tobedevelopers.project_fury.model.Model;
import tobedevelopers.project_fury.model.ModelContract;
import tobedevelopers.project_fury.model.Project;
import tobedevelopers.project_fury.model.ProjectResponse;
import tobedevelopers.project_fury.model.Task;
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

		if( view != null && navigation != null )
			navigation.navigateToProjectInfo();
	}

	@Override
	public void userSelectTaskInfo(){
		DashboardContract.View view = viewWeakReference.get();
		DashboardContract.Navigation navigation = navigationWeakReference.get();

		if( view != null && navigation != null )
			new SetProjectAsyncTask().executeOnExecutor( AsyncTask.THREAD_POOL_EXECUTOR );
	}

	@Override
	public void loadProjects(){
		DashboardContract.View view = viewWeakReference.get();
		DashboardContract.Navigation navigation = navigationWeakReference.get();

		if( view != null && navigation != null )
			new LoadProjectsAsyncTask().executeOnExecutor( AsyncTask.THREAD_POOL_EXECUTOR );
	}

	@Override
	public void loadTasks(){
		DashboardContract.View view = viewWeakReference.get();
		DashboardContract.Navigation navigation = navigationWeakReference.get();

		if( view != null && navigation != null )
			new LoadTasksAsyncTask().executeOnExecutor( AsyncTask.THREAD_POOL_EXECUTOR );
	}

	private class SetProjectAsyncTask extends AsyncTask< Void, Void, ProjectResponse >{
		@Override
		protected ProjectResponse doInBackground( Void... voids ){
			return model.getProject( Model.getSelectedTask().getProjectID() );
		}

		@Override
		protected void onPostExecute( ProjectResponse result ){
			super.onPostExecute( result );
			DashboardContract.View view = viewWeakReference.get();
			DashboardContract.Navigation navigation = navigationWeakReference.get();

			switch( result.getMessage() ){
				case "Success":
					Model.setSelectedProject( result.getProjects()[ 0 ] );
					navigation.navigateToTaskInfo();
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

	private class LoadProjectsAsyncTask extends AsyncTask< Void, Void, ProjectHolder >{
		@Override
		protected ProjectHolder doInBackground( Void... voids ){
			ProjectHolder projectHolder = null;
			ProjectResponse projectResponse = model.getAllProjects();
			if( projectResponse.getMessage().equals( "Success" ) ){
				projectHolder = new ProjectHolder( projectResponse.getProjects() );
				for( Project project : projectResponse.getProjects() ){
					TaskResponse taskResponse = model.getAllProjectTasks( project.getProjectID() );
					if( taskResponse.getMessage().equals( "Success" ) )
						projectHolder.addTasks( project.getName(), taskResponse.getTasks() );
					ColumnResponse columnResponse = model.getAllProjectColumns( project.getProjectID() );
					if( columnResponse.getMessage().equals( "Success" ) )
						projectHolder.addColumns( project.getName(), columnResponse.getColumns() );
				}
			}
			return projectHolder;
		}

		@Override
		protected void onPostExecute( ProjectHolder response ){
			super.onPostExecute( response );
			DashboardContract.View view = viewWeakReference.get();
			DashboardContract.Navigation navigation = navigationWeakReference.get();

			view.loadProjectsIntoList( response );
		}

		@Override
		protected void onPreExecute(){
			super.onPreExecute();
		}
	}

	private class LoadTasksAsyncTask extends AsyncTask< Void, Void, TaskHolder >{
		@Override
		protected TaskHolder doInBackground( Void... voids ){
			TaskHolder taskHolder = new TaskHolder();
			TaskResponse taskResponse = model.getAllUserTasks();
			for( Task task : taskResponse.getTasks() ){
				ColumnResponse columnResponse = model.getColumn( task.getProjectID(), task.getColumnID() );
				taskHolder.addPair( task, columnResponse.getColumns()[ 0 ] );
			}
			return taskHolder;
		}

		@Override
		protected void onPostExecute( TaskHolder response ){
			super.onPostExecute( response );
			DashboardContract.View view = viewWeakReference.get();
			DashboardContract.Navigation navigation = navigationWeakReference.get();

			view.loadTasksIntoList( response );
		}

		@Override
		protected void onPreExecute(){
			super.onPreExecute();
		}
	}
}
