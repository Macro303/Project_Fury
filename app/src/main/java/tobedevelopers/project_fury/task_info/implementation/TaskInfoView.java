package tobedevelopers.project_fury.task_info.implementation;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import tobedevelopers.project_fury.BaseView;
import tobedevelopers.project_fury.R;
import tobedevelopers.project_fury.task_info.TaskInfoContract;

/**
 * Created by Macro303 on 11/08/2016.
 */
public class TaskInfoView extends BaseView implements TaskInfoContract.View, TaskInfoContract.Navigation{

	//UI References
	private Button mEditTaskButton;
	private Button mRemoveTaskButton;

	private TaskInfoContract.Presenter presenter;

	@Override
	protected void onCreate( Bundle savedInstanceState ){
		setTitle( getString( R.string.title_activity_taskInfo ) );
		setContentView( R.layout.activity_task_info );
		super.onCreate( savedInstanceState );
		presenter = new TaskInfoPresenter( this, this );

		//UI References
		mEditTaskButton = ( Button ) findViewById( R.id.taskInfoActivity_editTaskButton );
		mRemoveTaskButton = ( Button ) findViewById( R.id.taskInfoActivity_removeTaskButton );

		//Button Config
		mEditTaskButton.setOnClickListener( new View.OnClickListener(){
			@Override
			public void onClick( View view ){
				presenter.userSelectEditTask();
			}
		} );
		mRemoveTaskButton.setOnClickListener( new View.OnClickListener(){
			@Override
			public void onClick( View view ){
				presenter.userSelectRemoveTask();
			}
		} );
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
