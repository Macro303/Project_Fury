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
//			new AsyncTask< String, Void, Response >(){
//
//				@Override
//				protected void onPreExecute(){
//					CreateProjectContract.View view = viewWeakReference.get();
//
//					if( view != null ){
//						view.projectCreationInProgress();
//					}
//				}
//
//				@Override
//				protected Response doInBackground( String... strings ){
//					return modelContract.
//				}
//
//				@Override
//				protected void onPostExecute( Response response ){
//					RegisterContract.View view = viewWeakReference.get();
//					RegisterContract.Navigation navigation = navigationWeakReference.get();
//
//					if( view != null ){
//						if( response.getError().equals( "Passed" ) ){
//							navigation.navigateToLogin();
//						}else if( response.getError().equals( "No Internet Access" ) ){
//							view.noInternetAccessValidation();
//						}else{
//							view.setUsernameAlreadyUsedValidation();
//						}
//					}
//				}
//			}.executeOnExecutor( AsyncTask.THREAD_POOL_EXECUTOR );
			navigation.navigateToPrevious();
		}
	}

	@Override
	public void userEnterProjectName( String projectName ){
		CreateProjectContract.View view = viewWeakReference.get();

		if( view != null ){
			if( projectName.length() >= 3 && projectName.length() < 20 ){
				view.enableCreateProjectButton();
			}else if( projectName.length() < 3 ){
				view.setProjectNameUnder3CharValidation();
				view.disableCreateProjectButton();
			}else if( projectName.length() > 19 ){
				view.setProjectNameOver20CharValidation();
				view.disableCreateProjectButton();
			}
		}
	}

	@Override
	public void userEnterProjectDescription( String projectDescription ){
		CreateProjectContract.View view = viewWeakReference.get();

		if( view != null ){
			if( projectDescription.length() > 127 ){
				view.setProjectDescriptionOver128CharValidation();
				view.disableCreateProjectButton();
			}
		}
	}
}
