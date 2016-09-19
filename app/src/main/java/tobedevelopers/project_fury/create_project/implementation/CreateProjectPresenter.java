package tobedevelopers.project_fury.create_project.implementation;

import android.os.AsyncTask;

import java.lang.ref.WeakReference;

import tobedevelopers.project_fury.create_project.CreateProjectContract;
import tobedevelopers.project_fury.model.Model;
import tobedevelopers.project_fury.model.ModelContract;
import tobedevelopers.project_fury.model.Response;

/**
 * Created by Macro303 on 11/08/2016.
 */
public class CreateProjectPresenter implements CreateProjectContract.Presenter{

	private WeakReference< CreateProjectContract.View > viewWeakReference;
	private WeakReference< CreateProjectContract.Navigation > navigationWeakReference;
	private ModelContract modelContract;

	private String mProjectName;
	private String mProjectDescription;

	public CreateProjectPresenter( CreateProjectContract.View view, CreateProjectContract.Navigation navigation ){
		this.viewWeakReference = new WeakReference<>( view );
		this.navigationWeakReference = new WeakReference<>( navigation );
		this.modelContract = new Model();
	}

	@Override
	public void userSelectCreateProject(){
		CreateProjectContract.View view = viewWeakReference.get();
		CreateProjectContract.Navigation navigation = navigationWeakReference.get();

		if( view != null && navigation != null )
			new CreateProjectTask().execute();
	}

	@Override
	public void userEnterProjectName( String projectName ){
		CreateProjectContract.View view = viewWeakReference.get();

		if( view != null ){
			mProjectName = projectName;
			if( projectName.length() < 3 ){
				view.setProjectNameUnderValidation();
				view.disableCreateProjectButton();
			}else if( projectName.length() > 20 )
				view.disableCreateProjectButton();
			else
				view.enableCreateProjectButton();
			if( projectName.length() >= 20 )
				view.setProjectNameOverValidation();
		}
	}

	@Override
	public void userEnterProjectDescription( String projectDescription ){
		CreateProjectContract.View view = viewWeakReference.get();

		if( view != null ){
			mProjectDescription = projectDescription;
			if( projectDescription.length() > 128 )
				view.disableCreateProjectButton();
			if( projectDescription.length() >= 128 )
				view.setProjectDescriptionOverValidation();
		}
	}

	private class CreateProjectTask extends AsyncTask< String, Void, Response >{

		@Override
		protected void onPreExecute(){
			viewWeakReference.get().projectCreationInProgress();
		}

		@Override
		protected Response doInBackground( String... strings ){
			return modelContract.createProject( mProjectName, mProjectDescription );
		}

		@Override
		protected void onPostExecute( Response response ){
			CreateProjectContract.View view = viewWeakReference.get();
			CreateProjectContract.Navigation navigation = navigationWeakReference.get();

			switch( response.getMessage() ){
				case "Project creation successful.":
					navigation.navigateToPrevious();
					break;
				case "No Internet Access":
					view.noInternetAccessValidation();
					break;
				default:
					view.setProjectAlreadyUsedValidation();
					break;
			}
		}
	}
}
