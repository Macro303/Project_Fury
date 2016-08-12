package tobedevelopers.project_fury.all_boards.implementation;

import java.lang.ref.WeakReference;

import tobedevelopers.project_fury.all_boards.AllBoardsContract;

/**
 * Created by Macro303 on 12/08/2016.
 */
public class AllBoardsPresenter implements AllBoardsContract.Presenter{

	private WeakReference< AllBoardsContract.View > viewWeakReference;
	private WeakReference< AllBoardsContract.Navigation > navigationWeakReference;

	public AllBoardsPresenter( AllBoardsContract.View view, AllBoardsContract.Navigation navigation ){
		this.viewWeakReference = new WeakReference<>( view );
		this.navigationWeakReference = new WeakReference<>( navigation );
	}
}
