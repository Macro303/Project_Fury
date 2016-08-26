package tobedevelopers.project_fury.create_project.implementation;

import java.lang.ref.WeakReference;

import tobedevelopers.project_fury.create_project.CreateProjectContract;
import tobedevelopers.project_fury.model.Model;
import tobedevelopers.project_fury.model.ModelContract;

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

		if( view != null && navigation != null ){
/*			new AsyncTask< String, Void, Response >(){

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
					RegisterContract.View view = viewWeakReference.get();
					switch( response.getError() ){
						case "Passed":
							navigationWeakReference.get().navigateToLogin();
							break;
						case "No Internet Access":
							view.noInternetAccessValidation();
							break;
						default:
							view.setUsernameAlreadyUsedValidation();
							break;
					}
				}
			}.executeOnExecutor( AsyncTask.THREAD_POOL_EXECUTOR );*/
			navigation.navigateToPrevious();
		}
	}

	@Override
	public void userEnterProjectName( String projectName ){
		CreateProjectContract.View view = viewWeakReference.get();

		if( view != null ){
			mProjectName = projectName;
			if( projectName.length() < 3 ){
				view.setProjectNameUnderValidation();
				view.disableCreateProjectButton();
			}else if( projectName.length() >= 20 ){
				view.setProjectNameOverValidation();
				view.disableCreateProjectButton();
			}else
				view.enableCreateProjectButton();
		}
	}

	@Override
	public void userEnterProjectDescription( String projectDescription ){
		CreateProjectContract.View view = viewWeakReference.get();

		if( view != null ){
			mProjectDescription = projectDescription;
			if( projectDescription.length() >= 128 ){
				view.setProjectDescriptionOverValidation();
				view.disableCreateProjectButton();
			}
		}
	}
}
