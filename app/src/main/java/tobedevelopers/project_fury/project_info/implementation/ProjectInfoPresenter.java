package tobedevelopers.project_fury.project_info.implementation;

import android.os.AsyncTask;

import java.lang.ref.WeakReference;

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

		if( view != null ){
			new AsyncTask< String, Void, Response >(){

				@Override
				protected void onPreExecute(){
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
			}.executeOnExecutor( AsyncTask.THREAD_POOL_EXECUTOR );
		}
	}

	@Override
	public void userSelectAddColumn( final String columnName ){
		ProjectInfoContract.View view = viewWeakReference.get();

		if( view != null ){
			new AsyncTask< String, Void, Response >(){

				@Override
				protected void onPreExecute(){
					ProjectInfoContract.View view = viewWeakReference.get();

					if( view != null )
						view.projectUpdatingInProgress();
				}


				@Override
				protected Response doInBackground( String... strings ){
					return model.createColumn( Model.getSelectedProject().getProjectID(), columnName );
				}

				@Override
				protected void onPostExecute( Response response ){
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
//								view.saveProjectDescription();
								view.setInvalidUserValidation();
								break;
						}
					}
				}
			}.executeOnExecutor( AsyncTask.THREAD_POOL_EXECUTOR );
		}
	}

	@Override
	public void userSelectDeleteColumn(){

	}

	@Override
	public void userEnterProjectName( String projectName ){
		ProjectInfoContract.View view = viewWeakReference.get();

		if( view != null ){
			mProjectName = projectName;
			if( projectName.length() < 3 ){
				view.setProjectNameUnderValidation();
			}else if( projectName.length() >= 20 ){
				view.setProjectNameOverValidation();
			}
		}
	}

	@Override
	public void userSelectDeleteProject(){
		ProjectInfoContract.View view = viewWeakReference.get();
		ProjectInfoContract.Navigation navigation = navigationWeakReference.get();

		if( view != null && navigation != null ){
			new AsyncTask< String, Void, Response >(){

				@Override
				protected void onPreExecute(){
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
			}.executeOnExecutor( AsyncTask.THREAD_POOL_EXECUTOR );
		}
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

		if( view != null && navigation != null ){
			new AsyncTask< String, Void, ColumnResponse >(){
				@Override
				protected void onPreExecute(){

				}

				@Override
				protected ColumnResponse doInBackground( String... strings ){
					return model.getAllProjectColumns( Model.getSelectedProject().getProjectID() );
				}

				@Override
				protected void onPostExecute( ColumnResponse response ){
					ProjectInfoContract.View view = viewWeakReference.get();
					ProjectInfoContract.Navigation navigation = navigationWeakReference.get();

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
			}.executeOnExecutor( AsyncTask.THREAD_POOL_EXECUTOR );
		}
	}
}
