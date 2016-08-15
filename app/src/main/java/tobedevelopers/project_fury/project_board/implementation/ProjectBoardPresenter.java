package tobedevelopers.project_fury.project_board.implementation;

import java.lang.ref.WeakReference;

import tobedevelopers.project_fury.project_board.ProjectBoardContract;

/**
 * Created by Macro303 on 13/08/2016.
 */
public class ProjectBoardPresenter implements ProjectBoardContract.Presenter{

	private WeakReference< ProjectBoardContract.View > viewWeakReference;
	private WeakReference< ProjectBoardContract.Navigation > navigationWeakReference;

	public ProjectBoardPresenter( ProjectBoardContract.View view, ProjectBoardContract.Navigation navigation ){
		this.viewWeakReference = new WeakReference<>( view );
		this.navigationWeakReference = new WeakReference<>( navigation );
	}

	@Override
	public void userSelectCreateTask(){
		ProjectBoardContract.View view = viewWeakReference.get();
		ProjectBoardContract.Navigation navigation = navigationWeakReference.get();

		if( view != null && navigation != null )
			navigation.navigateToCreateTask();
	}
}
