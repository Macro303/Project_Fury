package tobedevelopers.project_fury.create_task.implementation;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.text.Editable;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import tobedevelopers.project_fury.BaseView;
import tobedevelopers.project_fury.R;
import tobedevelopers.project_fury.ToastLog;
import tobedevelopers.project_fury.create_task.CreateTaskContract;
import tobedevelopers.project_fury.runnable_param.Runnable1Param;

/**
 * Created by Macro303 on 12/08/2016.
 */
public class CreateTaskView extends BaseView implements CreateTaskContract.View, CreateTaskContract.Navigation{

	@Bind( R.id.createTaskActivity_taskNameEditText )
	TextInputEditText mTaskNameEditText;
	@Bind( R.id.createTaskActivity_taskDescriptionEditText )
	TextInputEditText mTaskDescriptionEditText;
	@Bind( R.id.createTaskActivity_createTaskButton )
	Button mCreateTaskButton;

	private CreateTaskContract.Presenter presenter;

	@Override
	protected void onCreate( Bundle savedInstanceState ){
		setTitle( getString( R.string.title_activity_createTask ) );
		setContentView( R.layout.activity_create_task );
		super.onCreate( savedInstanceState );
		presenter = new CreateTaskPresenter( this, this );

		ButterKnife.bind( this );

		//Toolbar Config
		getSupportActionBar().setDisplayHomeAsUpEnabled( true );
	}

	//Button Listener
	@OnClick( R.id.createTaskActivity_createTaskButton )
	public void onUserSelectAButton( View view ){
		switch( view.getId() ){
			case R.id.createTaskActivity_createTaskButton:
				Toast.makeText( this, "Create Task", Toast.LENGTH_SHORT ).show();
				presenter.userSelectCreateTask();
				break;
			default:
				Toast.makeText( this, String.format( getString( R.string.error_message ), getTitle() ), Toast.LENGTH_SHORT ).show();
				Log.w( getString( R.string.app_name ), String.format( getString( R.string.error_message ), getTitle() ) );
				break;
		}
	}

	//Text Change Listener
	@OnTextChanged( value = { R.id.createTaskActivity_taskNameEditText }, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED )
	public void onUserChangedTaskNameEditText( Editable editable ){
		presenter.userEnterTaskName( editable.toString() );
	}

	@OnTextChanged( value = { R.id.createTaskActivity_taskDescriptionEditText }, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED )
	public void onUserChangedTaskDescriptionEditText( Editable editable ){
		presenter.userEnterTaskDescription( editable.toString() );
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
		runOnUiThread( new Runnable1Param< CreateTaskView >( this ){
			@Override
			public void run(){
				finish();
			}
		} );
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

	@Override
	public void enableCreateTaskButton(){
		runOnUiThread( new Runnable1Param< CreateTaskView >( this ){
			@Override
			public void run(){
				getParam1().mCreateTaskButton.setEnabled( true );
			}
		} );
	}

	@Override
	public void disableCreateTaskButton(){
		runOnUiThread( new Runnable1Param< CreateTaskView >( this ){
			@Override
			public void run(){
				getParam1().mCreateTaskButton.setEnabled( false );
			}
		} );
	}

	@Override
	public void taskCreationInProgress(){
		runOnUiThread( new Runnable1Param< CreateTaskView >( this ){
			@Override
			public void run(){
				ToastLog.makeInfo( getParam1(), getString( R.string.error_taskCreationInProgress ), Toast.LENGTH_LONG ).show();
			}
		} );
	}

	@Override
	public void setTaskNameUnderValidation(){
		runOnUiThread( new Runnable1Param< TextInputEditText >( mTaskNameEditText ){
			@Override
			public void run(){
				getParam1().setError( getString( R.string.error_min3Characters ) );
			}
		} );
	}

	@Override
	public void setTaskNameOverValidation(){
		runOnUiThread( new Runnable1Param< TextInputEditText >( mTaskNameEditText ){
			@Override
			public void run(){
				getParam1().setError( getString( R.string.error_max20Characters ) );
			}
		} );
	}

	@Override
	public void setTaskDescriptionOverValidation(){
		runOnUiThread( new Runnable1Param< TextInputEditText >( mTaskDescriptionEditText ){
			@Override
			public void run(){
				getParam1().setError( getString( R.string.error_max128Characters ) );
			}
		} );
	}

	@Override
	public void noInternetAccessValidation(){
		ToastLog.makeWarn( this, getString( R.string.error_noInternetAccess ), Toast.LENGTH_LONG ).show();
	}
}
