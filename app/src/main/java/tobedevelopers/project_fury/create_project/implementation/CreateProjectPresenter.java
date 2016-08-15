package tobedevelopers.project_fury.create_project.implementation;

import java.lang.ref.WeakReference;

import tobedevelopers.project_fury.create_project.CreateProjectContract;

/**
 * Created by Macro303 on 11/08/2016.
 */
public class CreateProjectPresenter implements CreateProjectContract.Presenter{

	private WeakReference< CreateProjectContract.View > viewWeakReference;
	private WeakReference< CreateProjectContract.Navigation > navigationWeakReference;

	public CreateProjectPresenter( CreateProjectContract.View view, CreateProjectContract.Navigation navigation ){
		this.viewWeakReference = new WeakReference<>( view );
		this.navigationWeakReference = new WeakReference<>( navigation );
	}

	@Override
	public void userSelectCreateProject(){
		CreateProjectContract.View view = viewWeakReference.get();
		CreateProjectContract.Navigation navigation = navigationWeakReference.get();

		if( view != null && navigation != null ){
			navigation.navigateToPrevious();
		}
	}
}
