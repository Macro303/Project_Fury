package tobedevelopers.project_fury.all_boards.implementation;

import android.os.AsyncTask;

import java.lang.ref.WeakReference;

import tobedevelopers.project_fury.all_boards.AllBoardsContract;
import tobedevelopers.project_fury.model.Model;
import tobedevelopers.project_fury.model.ModelContract;
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
		AllBoardsContract.Navigation navigation = navigationWeakReference.get();

		if( view != null && navigation != null )
			new LoadProjectsTask().execute();
	}

	@Override
	public void userSelectCreateProject(){
		AllBoardsContract.Navigation navigation = navigationWeakReference.get();

		if( navigation != null )
			navigation.navigateToCreateProject();
	}

	private class LoadProjectsTask extends AsyncTask< Void, Void, ProjectResponse >{

		@Override
		protected void onPreExecute(){
			super.onPreExecute();
			AllBoardsContract.View view = viewWeakReference.get();

			if( view != null )
				view.showProjectUpdatingInProgress();
		}

		@Override
		protected ProjectResponse doInBackground( Void... inputs ){
			return model.getAllProjects();
		}

		@Override
		protected void onPostExecute( ProjectResponse result ){
			super.onPostExecute( result );
			AllBoardsContract.View view = viewWeakReference.get();

			if( view != null ){
				view.hideProjectUpdatingInProgress();

				switch( result.getMessage() ){
					case "Success":
						view.setRecyclerItems( result.getProjects() );
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
