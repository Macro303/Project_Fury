package tobedevelopers.project_fury.create_task.implementation;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.OnClick;
import tobedevelopers.project_fury.BaseView;
import tobedevelopers.project_fury.R;
import tobedevelopers.project_fury.Runnable1Param;
import tobedevelopers.project_fury.create_task.CreateTaskContract;
import tobedevelopers.project_fury.task_info.implementation.TaskInfoView;

/**
 * Created by Macro303 on 12/08/2016.
 */
public class CreateTaskView extends BaseView implements CreateTaskContract.View, CreateTaskContract.Navigation{

	private CreateTaskContract.Presenter presenter;

	@Override
	protected void onCreate( Bundle savedInstanceState ){
		setTitle( getString( R.string.title_activity_createTask ) );
		setContentView( R.layout.activity_create_task );
		super.onCreate( savedInstanceState );

		ButterKnife.bind( this );

		presenter = new CreateTaskPresenter( this, this );
	}

	//Button Listener
	@OnClick( R.id.createTaskActivity_createTaskButton )
	public void onUserSelectCreateTask(){
		Toast.makeText( this, "Create Task", Toast.LENGTH_SHORT ).show();
		presenter.userSelectCreateTask();
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
