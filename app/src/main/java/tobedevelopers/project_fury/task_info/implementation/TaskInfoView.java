package tobedevelopers.project_fury.task_info.implementation;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.OnClick;
import tobedevelopers.project_fury.BaseView;
import tobedevelopers.project_fury.R;
import tobedevelopers.project_fury.task_info.TaskInfoContract;

/**
 * Created by Macro303 on 11/08/2016.
 */
public class TaskInfoView extends BaseView implements TaskInfoContract.View, TaskInfoContract.Navigation{

	private TaskInfoContract.Presenter presenter;

	@Override
	protected void onCreate( Bundle savedInstanceState ){
		setTitle( getString( R.string.title_activity_taskInfo ) );
		setContentView( R.layout.activity_task_info );
		super.onCreate( savedInstanceState );

		ButterKnife.bind( this );

		presenter = new TaskInfoPresenter( this, this );
	}

	//Buttons Listener
	@OnClick( { R.id.taskInfoActivity_editTaskButton, R.id.taskInfoActivity_removeTaskButton } )
	public void onUserSelectAButton( View view ){
		switch( view.getId() ){
			case R.id.taskInfoActivity_editTaskButton:
				Toast.makeText( this, "Edit Task", Toast.LENGTH_SHORT ).show();
				presenter.userSelectEditTask();
				break;
			case R.id.taskInfoActivity_removeTaskButton:
				Toast.makeText( this, "Remove Task", Toast.LENGTH_SHORT ).show();
				presenter.userSelectRemoveTask();
				break;
			default:
				Toast.makeText( this, "An Error has occured", Toast.LENGTH_SHORT ).show();
				break;
		}
	}

	@Override
	public void navigateToPrevious(){
		finish();
	}

	@Override
	public void displayTaskEdited(){
		Toast.makeText( getApplicationContext(), "The Task Was Edited", Toast.LENGTH_SHORT ).show();
	}
}
