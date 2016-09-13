package tobedevelopers.project_fury.project_board.implementation;

import android.os.AsyncTask;

import java.lang.ref.WeakReference;

import tobedevelopers.project_fury.model.ColumnResponse;
import tobedevelopers.project_fury.model.Model;
import tobedevelopers.project_fury.model.ModelContract;
import tobedevelopers.project_fury.project_board.ProjectBoardContract;

/**
 * Created by Macro303 on 13/08/2016.
 */
public class ProjectBoardPresenter implements ProjectBoardContract.Presenter{

	private WeakReference< ProjectBoardContract.View > viewWeakReference;
	private WeakReference< ProjectBoardContract.Navigation > navigationWeakReference;
	private ModelContract model;

	public ProjectBoardPresenter( ProjectBoardContract.View view, ProjectBoardContract.Navigation navigation ){
		this.viewWeakReference = new WeakReference<>( view );
		this.navigationWeakReference = new WeakReference<>( navigation );
		this.model = new Model();
	}

	@Override
	public void userSelectCreateTask(){
		ProjectBoardContract.View view = viewWeakReference.get();
		ProjectBoardContract.Navigation navigation = navigationWeakReference.get();

		if( view != null && navigation != null )
			navigation.navigateToCreateTask();
	}

	@Override
	public void userLoadsBoard(){
		ProjectBoardContract.View view = viewWeakReference.get();

		if( view != null ){
			new AsyncTask< String, Void, ColumnResponse >(){

				@Override
				protected ColumnResponse doInBackground( String... strings ){
					return model.getAllProjectColumns( Model.getSelectedProject().getProjectID() );
				}

				@Override
				protected void onPostExecute( ColumnResponse response ){
					super.onPostExecute( response );
					ProjectBoardContract.View view = viewWeakReference.get();

					if( view != null ){
						switch( response.getMessage() ){
							case "Success":
								view.setTabTitles( response.getColumns() );
								break;
							case "No Internet Access":
								view.noInternetAccessValidation();
								break;
							default:
								view.displayDefaultErrorMessage();
								break;
						}
					}
				}
			}.executeOnExecutor( AsyncTask.THREAD_POOL_EXECUTOR );
		}
	}

}
