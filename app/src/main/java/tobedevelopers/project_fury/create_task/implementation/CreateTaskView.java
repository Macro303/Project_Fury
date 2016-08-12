package tobedevelopers.project_fury.create_task.implementation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import tobedevelopers.project_fury.BaseView;
import tobedevelopers.project_fury.R;
import tobedevelopers.project_fury.Runnable1Param;
import tobedevelopers.project_fury.create_task.CreateTaskContract;
import tobedevelopers.project_fury.task_info.implementation.TaskInfoView;

/**
 * Created by Macro303 on 12/08/2016.
 */
public class CreateTaskView extends BaseView implements CreateTaskContract.View, CreateTaskContract.Navigation{

	//UI References
	private Button mCreateTaskButton;

	private CreateTaskContract.Presenter presenter;

	@Override
	protected void onCreate( Bundle savedInstanceState ){
		setTitle( getString( R.string.title_activity_createTask ) );
		setContentView( R.layout.activity_create_task );
		super.onCreate( savedInstanceState );
		presenter = new CreateTaskPresenter( this, this );

		//UI References
		mCreateTaskButton = ( Button ) findViewById( R.id.createTaskActivity_createTaskButton );

		//Button Config
		mCreateTaskButton.setOnClickListener( new View.OnClickListener(){
			@Override
			public void onClick( View view ){
				presenter.userSelectCreateTask();
			}
		} );
	}

	@Override
	public void navigateToTaskInfo(){
		runOnUiThread( new Runnable1Param< CreateTaskView >( this ){
			@Override
			public void run(){
				startActivity( new Intent( getParam1(), TaskInfoView.class ) );
			}
		} );
	}
}
