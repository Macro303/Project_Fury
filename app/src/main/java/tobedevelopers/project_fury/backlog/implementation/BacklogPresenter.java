package tobedevelopers.project_fury.backlog.implementation;

import java.lang.ref.WeakReference;

import tobedevelopers.project_fury.backlog.BacklogContract;

/**
 * Created by Macro303 on 12/08/2016.
 */
public class BacklogPresenter implements BacklogContract.Presenter{

	private WeakReference< BacklogContract.View > viewWeakReference;
	private WeakReference< BacklogContract.Navigation > navigationWeakReference;

	public BacklogPresenter( BacklogContract.View view, BacklogContract.Navigation navigation ){
		this.viewWeakReference = new WeakReference<>( view );
		this.navigationWeakReference = new WeakReference<>( navigation );
	}

	@Override
	public void userSelectCreateTask(){
		BacklogContract.View view = viewWeakReference.get();
		BacklogContract.Navigation navigation = navigationWeakReference.get();

		if( view != null && navigation != null )
			navigation.navigateToCreateTask();
	}
}
