package tobedevelopers.project_fury.all_boards.implementation;

import android.os.AsyncTask;

import java.lang.ref.WeakReference;

import tobedevelopers.project_fury.all_boards.AllBoardsContract;
import tobedevelopers.project_fury.model.Model;
import tobedevelopers.project_fury.model.ModelContract;
import tobedevelopers.project_fury.model.Project;
import tobedevelopers.project_fury.model.ProjectResponse;

/**
 * Created by Macro303 on 12/08/2016.
 */
public class AllBoardsPresenter implements AllBoardsContract.Presenter{

	private WeakReference< AllBoardsContract.View > viewWeakReference;
	private WeakReference< AllBoardsContract.Navigation > navigationWeakReference;
	private ModelContract model;

	public AllBoardsPresenter( AllBoardsContract.View view, AllBoardsContract.Navigation navigation ){
		this.viewWeakReference = new WeakReference<>( view );
		this.navigationWeakReference = new WeakReference<>( navigation );
		this.model = new Model();
	}


	@Override
	public void userOpensBoard(){
		AllBoardsContract.View view = viewWeakReference.get();

		Project[] projects;

		if( view != null ){
			new AsyncTask< String, Void, ProjectResponse >(){

				@Override
				protected ProjectResponse doInBackground( String... strings ){
					return model.getAllProjects();
				}

				@Override
				protected void onPostExecute( ProjectResponse response ){
					super.onPostExecute( response );
					AllBoardsContract.View view = viewWeakReference.get();

					if( view != null ){
						switch( response.getMessage() ){
							case "Success":
								view.setRecyclerItems( response.getProjects() );
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

	@Override
	public void userSelectCreateProject(){
		AllBoardsContract.View view = viewWeakReference.get();
		AllBoardsContract.Navigation navigation = navigationWeakReference.get();

		if( view != null && navigation != null )
			navigation.navigateToCreateProject();
	}
}
