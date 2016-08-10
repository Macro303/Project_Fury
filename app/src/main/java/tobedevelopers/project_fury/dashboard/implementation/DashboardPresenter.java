package tobedevelopers.project_fury.dashboard.implementation;

import java.lang.ref.WeakReference;

import tobedevelopers.project_fury.dashboard.DashboardContract;

/**
 * Created by Macro303 on 10/08/2016.
 */
public class DashboardPresenter implements DashboardContract.Presenter{

	private WeakReference< DashboardContract.View > viewWeakReference;
	private WeakReference< DashboardContract.Navigation > navigationWeakReference;

	public DashboardPresenter( DashboardContract.View view, DashboardContract.Navigation navigation ){
		this.viewWeakReference = new WeakReference<>( view );
		this.navigationWeakReference = new WeakReference<>( navigation );
	}

	@Override
	public void userSelectCreateProject(){
		DashboardContract.View view = viewWeakReference.get();
		DashboardContract.Navigation navigation = navigationWeakReference.get();

		if( view != null && navigation != null )
			navigation.navigateToCreateProject();
	}

	@Override
	public void userSelectProjectInfo(){
		DashboardContract.View view = viewWeakReference.get();
		DashboardContract.Navigation navigation = navigationWeakReference.get();

		if( view != null && navigation != null )
			navigation.navigateToProjectInfo();
	}

	@Override
	public void userSelectTaskInfo(){
		DashboardContract.View view = viewWeakReference.get();
		DashboardContract.Navigation navigation = navigationWeakReference.get();

		if( view != null && navigation != null )
			navigation.navigateToTaskInfo();
	}
}
