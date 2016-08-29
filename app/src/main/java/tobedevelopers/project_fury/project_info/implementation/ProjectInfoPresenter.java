package tobedevelopers.project_fury.project_info.implementation;

import java.lang.ref.WeakReference;

import tobedevelopers.project_fury.model.Model;
import tobedevelopers.project_fury.model.ModelContract;
import tobedevelopers.project_fury.project_info.ProjectInfoContract;

/**
 * Created by Macro303 on 11/08/2016.
 */
public class ProjectInfoPresenter implements ProjectInfoContract.Presenter{

	private WeakReference< ProjectInfoContract.View > viewWeakReference;
	private WeakReference< ProjectInfoContract.Navigation > navigationWeakReference;
	private ModelContract model;

	private String mProjectName;
	private String mProjectDescription;

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

//	@Override
//	public void userSelectAddColumn(){
//		ProjectInfoContract.View view = viewWeakReference.get();
//		ProjectInfoContract.Navigation navigation = navigationWeakReference.get();
//
//		if( view != null && navigation != null )
//			view.displayColumnAdded();
//	}
//
//	@Override
//	public void userSelectAddUser(){
//		ProjectInfoContract.View view = viewWeakReference.get();
//		ProjectInfoContract.Navigation navigation = navigationWeakReference.get();
//
//		if( view != null && navigation != null )
//			view.displayUserAdded();
//	}
//
//	@Override
//	public void userSelectRemoveMe(){
//		ProjectInfoContract.View view = viewWeakReference.get();
//		ProjectInfoContract.Navigation navigation = navigationWeakReference.get();
//
//		if( view != null && navigation != null )
//			navigation.navigateToPrevious();
//	}
//
//	@Override
//	public void userSelectRemoveColumn(){
//		ProjectInfoContract.View view = viewWeakReference.get();
//		ProjectInfoContract.Navigation navigation = navigationWeakReference.get();
//
//		if( view != null && navigation != null )
//			view.displayColumnRemoved();
//	}

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
			view.saveProjectDescription();
		}
	}

//	@Override
//	public void userEnterProjectName( String projectName ){
//		ProjectInfoContract.View view = viewWeakReference.get();
//
//		if( view != null ){
//			mProjectName = projectName;
//			if( projectName.length() < 3 ){
//				view.setProjectNameUnderValidation();
//			}else if( projectName.length() >= 20 ){
//				view.setProjectNameOverValidation();
//			}
//		}
//	}

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
}
