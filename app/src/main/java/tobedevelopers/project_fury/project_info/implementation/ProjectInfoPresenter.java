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
		ProjectInfoContract.View view = viewWeakReference.get();
		ProjectInfoContract.Navigation navigation = navigationWeakReference.get();

		if( view != null && navigation != null )
			navigation.navigateToPrevious();
	}

	@Override
	public void userSelectEditProject(){
		ProjectInfoContract.View view = viewWeakReference.get();

		if( view != null )
			view.editProjectDescription();
	}

	@Override
	public void userSelectSaveProject(){
		ProjectInfoContract.View view = viewWeakReference.get();

		if( view != null )
			new SaveProjectTask().execute();
	}

	@Override
	public void userSelectAddColumn( String columnName ){
		ProjectInfoContract.View view = viewWeakReference.get();
		mColumnName = columnName;

		if( view != null )
			new AddColumnTask().execute();
	}

	@Override
	public void userSelectSaveColumns( final List< Column > columnList ){
		ProjectInfoContract.View view = viewWeakReference.get();

		Column[] columns = columnList.toArray( new Column[ columnList.size() ] );

		if( view != null )
			new SaveColumnTask().execute( columns );
	}

	@Override
	public void saveColumnsBeforeDeleting( final List< Column > columnList ){
		ProjectInfoContract.View view = viewWeakReference.get();

		Column[] columns = columnList.toArray( new Column[ columnList.size() ] );

		if( view != null )
			new SaveColumnsBeforeDeletingTask().execute( columns );
	}

	@Override
	public void userSelectDeleteColumn(){
		ProjectInfoContract.View view = viewWeakReference.get();

		if( view != null )
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

	private class SaveProjectTask extends AsyncTask< String, Void, Response >{

		@Override
		protected void onPreExecute(){
			super.onPreExecute();
			ProjectInfoContract.View view = viewWeakReference.get();

			if( view != null )
				view.projectUpdatingInProgress();
		}

		@Override
		protected Response doInBackground( String... strings ){
			if( mProjectDescription.equals( "null" ) )
				mProjectDescription = "";
			return model.updateProject( Model.getSelectedProject().getProjectID(), mProjectName, mProjectDescription );
		}

		@Override
		protected void onPostExecute( Response response ){
			super.onPostExecute( response );
			ProjectInfoContract.View view = viewWeakReference.get();

			if( view != null ){
				switch( response.getMessage() ){
					case "Update successful.":
						view.saveProjectDescription();
						break;
					case "No Internet Access":
						view.noInternetAccessValidation();
						break;
					default:
						view.saveProjectDescription();
						view.setInvalidUserValidation();
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
				view.projectUpdatingInProgress();
		}


		@Override
		protected Response doInBackground( String... strings ){
			return model.createColumn( Model.getSelectedProject().getProjectID(), mColumnName );
		}

		@Override
		protected void onPostExecute( Response response ){
			super.onPostExecute( response );
			ProjectInfoContract.View view = viewWeakReference.get();

			if( view != null ){
				switch( response.getMessage() ){
					case "Column creation successful.":
						view.addColumnName();
						break;
					case "No Internet Access":
						view.noInternetAccessValidation();
						break;
					default:
						view.setInvalidUserValidation();
						break;
				}
			}
		}
	}

	private class SaveColumnTask extends AsyncTask< Column, Void, Response >{

		@Override
		protected void onPreExecute(){
			super.onPreExecute();
			ProjectInfoContract.View view = viewWeakReference.get();

			if( view != null ){
				view.projectUpdatingInProgress();
			}
		}

		@Override
		protected Response doInBackground( Column... columns ){
			for( Column column : columns ){
				return model.updateColumn( Model.getSelectedProject().getProjectID(), column.getColumnID(), column.getName(), column.getPosition() );
			}
			return null;
		}

		@Override
		protected void onPostExecute( Response response ){
			super.onPostExecute( response );
			ProjectInfoContract.View view = viewWeakReference.get();

			if( view != null ){
				switch( response.getMessage() ){
					case "Update successful.":
						userSelectSaveProject();
						break;
					case "No Internet Access":
						view.noInternetAccessValidation();
						break;
					default:
						view.setInvalidUserValidation();
						break;
				}
			}
		}
	}

	private class SaveColumnsBeforeDeletingTask extends AsyncTask< Column, Void, Response >{

		@Override
		protected void onPreExecute(){
			super.onPreExecute();
			ProjectInfoContract.View view = viewWeakReference.get();

			if( view != null ){
				view.projectUpdatingInProgress();
			}
		}

		@Override
		protected Response doInBackground( Column... columns ){
			for( Column column : columns ){
				return model.updateColumn( Model.getSelectedProject().getProjectID(), column.getColumnID(), column.getName(), column.getPosition() );
			}
			return null;
		}

		@Override
		protected void onPostExecute( Response response ){
			super.onPostExecute( response );
			ProjectInfoContract.View view = viewWeakReference.get();

			if( view != null ){
				switch( response.getMessage() ){
					case "Update successful.":
						userSelectDeleteColumn();
						break;
					case "No Internet Access":
						view.noInternetAccessValidation();
						break;
					default:
						view.setInvalidUserValidation();
						break;
				}
			}
		}
	}

	private class DeleteColumnTask extends AsyncTask< String, Void, Response >{

		@Override
		protected void onPreExecute(){
			super.onPreExecute();

			ProjectInfoContract.View view = viewWeakReference.get();

			if( view != null ){
				view.projectUpdatingInProgress();
			}
		}

		@Override
		protected Response doInBackground( String... strings ){
			return model.deleteColumn( Model.getSelectedProject().getProjectID(), Model.getSelectedColumn().getColumnID() );
		}

		@Override
		protected void onPostExecute( Response response ){
			super.onPostExecute( response );
			ProjectInfoContract.View view = viewWeakReference.get();

			if( view != null ){
				switch( response.getMessage() ){
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

	private class DeleteProjectTask extends AsyncTask< String, Void, Response >{

		@Override
		protected void onPreExecute(){
			super.onPreExecute();
			ProjectInfoContract.View view = viewWeakReference.get();

			if( view != null )
				view.projectUpdatingInProgress();
		}

		@Override
		protected Response doInBackground( String... strings ){
			return model.deleteProject( Model.getSelectedProject().getProjectID() );
		}

		@Override
		protected void onPostExecute( Response response ){
			super.onPostExecute( response );
			ProjectInfoContract.View view = viewWeakReference.get();
			ProjectInfoContract.Navigation navigation = navigationWeakReference.get();

			if( view != null ){
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

	private class LoadColumnsTask extends AsyncTask< String, Void, ColumnResponse >{
		@Override
		protected void onPreExecute(){
			super.onPreExecute();
		}

		@Override
		protected ColumnResponse doInBackground( String... strings ){
			return model.getAllProjectColumns( Model.getSelectedProject().getProjectID() );
		}

		@Override
		protected void onPostExecute( ColumnResponse response ){
			super.onPostExecute( response );
			ProjectInfoContract.View view = viewWeakReference.get();
//			ProjectInfoContract.Navigation navigation = navigationWeakReference.get();

			if( view != null ){
				switch( response.getMessage() ){
					case "Success":
						view.fillColumnList( response.getColumns() );
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
