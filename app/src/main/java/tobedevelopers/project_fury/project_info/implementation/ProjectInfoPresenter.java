package tobedevelopers.project_fury.project_info.implementation;

import android.os.AsyncTask;

import java.lang.ref.WeakReference;
import java.util.List;

import tobedevelopers.project_fury.model.Column;
import tobedevelopers.project_fury.model.ColumnResponse;
import tobedevelopers.project_fury.model.Model;
import tobedevelopers.project_fury.model.ModelContract;
import tobedevelopers.project_fury.model.Response;
import tobedevelopers.project_fury.project_info.ProjectInfoContract;

/**
 * Created by Macro303 on 11/08/2016.
 */
public class ProjectInfoPresenter implements ProjectInfoContract.Presenter{

	private WeakReference< ProjectInfoContract.View > viewWeakReference;
	private WeakReference< ProjectInfoContract.Navigation > navigationWeakReference;
	private ModelContract model;

	private String mProjectDescription;
	private String mProjectName;
	private String mColumnName;

	public ProjectInfoPresenter( ProjectInfoContract.View view, ProjectInfoContract.Navigation navigation ){
		this.viewWeakReference = new WeakReference<>( view );
		this.navigationWeakReference = new WeakReference<>( navigation );
		this.model = new Model();
	}

	@Override
	public void userSelectBack(){
		ProjectInfoContract.Navigation navigation = navigationWeakReference.get();

		if( navigation != null )
			navigation.navigateToPrevious();
	}

	@Override
	public void userSelectEditProject(){
		ProjectInfoContract.View view = viewWeakReference.get();

		if( view != null )
			view.editProjectDescription();
	}

	@Override
	public void userSelectSaveProject( final List< Column > columnList ){
		ProjectInfoContract.View view = viewWeakReference.get();
		ProjectInfoContract.Navigation navigation = navigationWeakReference.get();

		Column[] columns = columnList.toArray( new Column[ columnList.size() ] );

		if( view != null && navigation != null )
			new SaveProjectTask().execute( columns );
	}

	@Override
	public void userSelectAddColumn( String columnName ){
		ProjectInfoContract.View view = viewWeakReference.get();
		ProjectInfoContract.Navigation navigation = navigationWeakReference.get();
		mColumnName = columnName;

		if( view != null && navigation != null )
			new AddColumnTask().execute( mColumnName );
	}

	@Override
	public void saveColumnsBeforeDeleting( final List< Column > columnList ){
		ProjectInfoContract.View view = viewWeakReference.get();
		ProjectInfoContract.Navigation navigation = navigationWeakReference.get();

		Column[] columns = columnList.toArray( new Column[ columnList.size() ] );

		if( view != null && navigation != null )
			new SaveColumnsBeforeDeletingTask().execute( columns );
	}

	@Override
	public void userSelectDeleteColumn(){
		ProjectInfoContract.View view = viewWeakReference.get();
		ProjectInfoContract.Navigation navigation = navigationWeakReference.get();

		if( view != null && navigation != null )
			new DeleteColumnTask().execute();
	}

	@Override
	public void userEnterProjectName( String projectName ){
		ProjectInfoContract.View view = viewWeakReference.get();

		if( view != null ){
			mProjectName = projectName;
			boolean error = false;
			if( projectName.length() < 3 ){
				view.setProjectNameUnderValidation();
				error = true;
			}else if( projectName.length() >= 20 ){
				view.setProjectNameOverValidation();
				error = false;
			}
			if( error ){
				view.disableSave();
			}else{
				view.enableSave();
			}
		}
	}

	@Override
	public void userSelectDeleteProject(){
		ProjectInfoContract.View view = viewWeakReference.get();
		ProjectInfoContract.Navigation navigation = navigationWeakReference.get();

		if( view != null && navigation != null )
			new DeleteProjectTask().execute();
	}

	@Override
	public void userEnterProjectDescription( String projectDescription ){
		ProjectInfoContract.View view = viewWeakReference.get();

		if( view != null ){
			mProjectDescription = projectDescription;
			if( projectDescription.length() >= 128 ){
				view.setProjectDescriptionOverValidation();
			}
		}
	}

	@Override
	public void loadColumns(){
		ProjectInfoContract.View view = viewWeakReference.get();
		ProjectInfoContract.Navigation navigation = navigationWeakReference.get();

		if( view != null && navigation != null )
			new LoadColumnsTask().execute();
	}

	private class SaveProjectTask extends AsyncTask< Column, Void, Response >{

		@Override
		protected void onPreExecute(){
			super.onPreExecute();
			ProjectInfoContract.View view = viewWeakReference.get();

			if( view != null )
				view.showProjectUpdatingInProgress();
		}

		@Override
		protected Response doInBackground( Column... inputs ){
			for( Column column : inputs ){
				Response response = model.updateColumn( column.getProjectID(), column.getColumnID(), column.getName(), column.getPosition() );
				if( !response.getMessage().equals( "Update successful." ) )
					return response;
			}

			if( mProjectDescription.equals( "null" ) )
				mProjectDescription = "";
			return model.updateProject( Model.getSelectedProject().getProjectID(), mProjectName, mProjectDescription );

		}

		@Override
		protected void onPostExecute( Response result ){
			super.onPostExecute( result );
			ProjectInfoContract.View view = viewWeakReference.get();

			if( view != null ){
				view.hideProjectUpdatingInProgress();

				switch( result.getMessage() ){
					case "Update successful.":
						view.saveProjectDescription();
						break;
					case "No Internet Access":
						view.noInternetAccessValidation();
						break;
					case "Project already exists.":
						view.setInvalidNameValidation();
						break;
					default:
						view.saveProjectDescription();
						view.defaultErrorMessage();
						break;
				}
			}
		}
	}

	private class AddColumnTask extends AsyncTask< String, Void, Response >{

		@Override
		protected void onPreExecute(){
			super.onPreExecute();
			ProjectInfoContract.View view = viewWeakReference.get();

			if( view != null )
				view.showProjectUpdatingInProgress();
		}


		@Override
		protected Response doInBackground( String... inputs ){
			return model.createColumn( Model.getSelectedProject().getProjectID(), inputs[ 0 ] );
		}

		@Override
		protected void onPostExecute( Response results ){
			super.onPostExecute( results );
			ProjectInfoContract.View view = viewWeakReference.get();

			if( view != null ){
				view.hideProjectUpdatingInProgress();

				switch( results.getMessage() ){
					case "Column creation successful.":
						view.addColumnName();
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

	private class SaveColumnsBeforeDeletingTask extends AsyncTask< Column, Void, String >{

		@Override
		protected void onPreExecute(){
			super.onPreExecute();
			ProjectInfoContract.View view = viewWeakReference.get();

			if( view != null ){
				view.showProjectUpdatingInProgress();
			}
		}

		@Override
		protected String doInBackground( Column... inputs ){

			String value = "Update successful.";
			for( Column column : inputs ){
				Response response = model.updateColumn( column.getProjectID(), column.getColumnID(), column.getName(), column.getPosition() );
				if( !response.getMessage().equals( "Update successful." ) )
					value = response.getMessage();
			}
			return value;
		}

		@Override
		protected void onPostExecute( String result ){
			super.onPostExecute( result );
			ProjectInfoContract.View view = viewWeakReference.get();

			if( view != null ){
				view.hideProjectUpdatingInProgress();

				switch( result ){
					case "Update successful.":
						userSelectDeleteColumn();
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

	private class DeleteColumnTask extends AsyncTask< Void, Void, Response >{

		@Override
		protected void onPreExecute(){
			super.onPreExecute();
			ProjectInfoContract.View view = viewWeakReference.get();

			if( view != null ){
				view.showProjectUpdatingInProgress();
			}
		}

		@Override
		protected Response doInBackground( Void... inputs ){
			return model.deleteColumn( Model.getSelectedProject().getProjectID(), Model.getSelectedColumn().getColumnID() );
		}

		@Override
		protected void onPostExecute( Response result ){
			super.onPostExecute( result );
			ProjectInfoContract.View view = viewWeakReference.get();

			if( view != null ){
				view.hideProjectUpdatingInProgress();

				switch( result.getMessage() ){
					case "Delete successful.":
						loadColumns();
						view.saveProjectDescription();
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

	private class DeleteProjectTask extends AsyncTask< Void, Void, Response >{

		@Override
		protected void onPreExecute(){
			super.onPreExecute();
			ProjectInfoContract.View view = viewWeakReference.get();

			if( view != null )
				view.showProjectUpdatingInProgress();
		}

		@Override
		protected Response doInBackground( Void... inputs ){
			return model.deleteProject( Model.getSelectedProject().getProjectID() );
		}

		@Override
		protected void onPostExecute( Response result ){
			super.onPostExecute( result );
			ProjectInfoContract.View view = viewWeakReference.get();
			ProjectInfoContract.Navigation navigation = navigationWeakReference.get();

			if( view != null ){
				view.hideProjectUpdatingInProgress();

				switch( result.getMessage() ){
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

	private class LoadColumnsTask extends AsyncTask< Void, Void, ColumnResponse >{
		@Override
		protected void onPreExecute(){
			super.onPreExecute();
		}

		@Override
		protected ColumnResponse doInBackground( Void... inputs ){
			return model.getAllProjectColumns( Model.getSelectedProject().getProjectID() );
		}

		@Override
		protected void onPostExecute( ColumnResponse result ){
			super.onPostExecute( result );
			ProjectInfoContract.View view = viewWeakReference.get();

			if( view != null ){
				switch( result.getMessage() ){
					case "Success":
						view.fillColumnList( result.getColumns() );
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
