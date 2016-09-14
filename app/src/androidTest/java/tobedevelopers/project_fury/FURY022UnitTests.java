package tobedevelopers.project_fury;

import android.app.Application;
import android.test.ApplicationTestCase;

import tobedevelopers.project_fury.all_boards.AllBoardsContract;
import tobedevelopers.project_fury.all_boards.implementation.AllBoardsPresenter;
import tobedevelopers.project_fury.project_board.ProjectBoardContract;
import tobedevelopers.project_fury.project_board.implementation.ProjectBoardPresenter;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by Andrew on 14/09/2016.
 */
public class FURY022UnitTests extends ApplicationTestCase< Application >{
	public FURY022UnitTests(){
		super( Application.class );
	}

	public void testCreateTaskFloatingActionButton(){
		ProjectBoardContract.View viewMock = mock( ProjectBoardContract.View.class );
		ProjectBoardContract.Navigation navigationMock = mock( ProjectBoardContract.Navigation.class );

		ProjectBoardContract.Presenter presenter = new ProjectBoardPresenter( viewMock, navigationMock );

		presenter.userSelectCreateTask();
		verify( navigationMock ).navigateToCreateTask();
	}

	public void testCreateProjectFloatingActionButton(){
		AllBoardsContract.View viewMock = mock( AllBoardsContract.View.class );
		AllBoardsContract.Navigation navigationMock = mock( AllBoardsContract.Navigation.class );

		AllBoardsContract.Presenter presenter = new AllBoardsPresenter( viewMock,navigationMock );

		presenter.userSelectCreateProject();
		verify( navigationMock ).navigateToCreateProject();
	}

}
