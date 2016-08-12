package tobedevelopers.project_fury.project_info.implementation;

import java.lang.ref.WeakReference;

import tobedevelopers.project_fury.project_info.ProjectInfoContract;

/**
 * Created by Macro303 on 11/08/2016.
 */
public class ProjectInfoPresenter implements ProjectInfoContract.Presenter{

	private WeakReference< ProjectInfoContract.View > viewWeakReference;
	private WeakReference< ProjectInfoContract.Navigation > navigationWeakReference;

	public ProjectInfoPresenter( ProjectInfoContract.View view, ProjectInfoContract.Navigation navigation ){
		this.viewWeakReference = new WeakReference<>( view );
		this.navigationWeakReference = new WeakReference<>( navigation );
	}

	@Override
	public void userSelectAddColumn(){
		ProjectInfoContract.View view = viewWeakReference.get();
		ProjectInfoContract.Navigation navigation = navigationWeakReference.get();

		if( view != null && navigation != null )
			view.displayColumnAdded();
	}

	@Override
	public void userSelectAddUser(){
		ProjectInfoContract.View view = viewWeakReference.get();
		ProjectInfoContract.Navigation navigation = navigationWeakReference.get();

		if( view != null && navigation != null )
			view.displayUserAdded();
	}

	@Override
	public void userSelectRemoveMe(){
		ProjectInfoContract.View view = viewWeakReference.get();
		ProjectInfoContract.Navigation navigation = navigationWeakReference.get();

		if( view != null && navigation != null )
			navigation.navigateToDashboard();
	}

	@Override
	public void userSelectRemoveColumn(){
		ProjectInfoContract.View view = viewWeakReference.get();
		ProjectInfoContract.Navigation navigation = navigationWeakReference.get();

		if( view != null && navigation != null )
			view.displayColumnRemoved();
	}

	@Override
	public void userSelectEditProject(){
		ProjectInfoContract.View view = viewWeakReference.get();
		ProjectInfoContract.Navigation navigation = navigationWeakReference.get();

		if( view != null && navigation != null )
			view.displayProjectEdited();
	}
}
