package tobedevelopers.project_fury.task_info.implementation;

import java.lang.ref.WeakReference;

import tobedevelopers.project_fury.task_info.TaskInfoContract;

/**
 * Created by Macro303 on 11/08/2016.
 */
public class TaskInfoPresenter implements TaskInfoContract.Presenter{

	private WeakReference< TaskInfoContract.View > viewWeakReference;
	private WeakReference< TaskInfoContract.Navigation > navigationWeakReference;

	public TaskInfoPresenter( TaskInfoContract.View view, TaskInfoContract.Navigation navigation ){
		this.viewWeakReference = new WeakReference<>( view );
		this.navigationWeakReference = new WeakReference<>( navigation );
	}

	@Override
	public void userSelectBack(){
		TaskInfoContract.View view = viewWeakReference.get();
		TaskInfoContract.Navigation navigation = navigationWeakReference.get();

		if( view != null && navigation != null )
			navigation.navigateToPrevious();
	}

	@Override
	public void userSelectEditTask(){
		TaskInfoContract.View view = viewWeakReference.get();
		TaskInfoContract.Navigation navigation = navigationWeakReference.get();

		if( view != null && navigation != null )
			view.displayTaskEdited();
	}

	@Override
	public void userSelectRemoveTask(){
		TaskInfoContract.View view = viewWeakReference.get();
		TaskInfoContract.Navigation navigation = navigationWeakReference.get();

		if( view != null && navigation != null )
			navigation.navigateToPrevious();
	}
}
