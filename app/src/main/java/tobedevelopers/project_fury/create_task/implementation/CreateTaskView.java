package tobedevelopers.project_fury.create_task.implementation;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import tobedevelopers.project_fury.BaseView;
import tobedevelopers.project_fury.R;
import tobedevelopers.project_fury.Runnable1Param;
import tobedevelopers.project_fury.create_task.CreateTaskContract;

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

		//Toolbar Config
		getSupportActionBar().setDisplayHomeAsUpEnabled( true );

		//Button Config
		mCreateTaskButton.setOnClickListener( new View.OnClickListener(){
			@Override
			public void onClick( View view ){
				presenter.userSelectCreateTask();
			}
		} );
	}

	@Override
	public boolean onOptionsItemSelected( MenuItem item ){
		switch( item.getItemId() ){
			case android.R.id.home:
				presenter.userSelectBack();
				return true;
		}

		return super.onOptionsItemSelected( item );
	}

	@Override
	public void navigateToPrevious(){
		finish();
	}

	@Override
	public void navigateToPreviousAfterCreate(){
		runOnUiThread( new Runnable1Param< CreateTaskView >( this ){
			@Override
			public void run(){
				finish();
			}
		} );
	}
}
