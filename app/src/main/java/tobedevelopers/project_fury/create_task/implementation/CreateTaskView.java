package tobedevelopers.project_fury.create_task.implementation;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.text.Editable;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import tobedevelopers.project_fury.BaseView;
import tobedevelopers.project_fury.R;
import tobedevelopers.project_fury.ToastLog;
import tobedevelopers.project_fury.create_task.CreateTaskContract;
import tobedevelopers.project_fury.model.Model;
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
	@Bind( R.id.createTaskActivity_assigneeSpinner )
	Spinner mAssigneeSpinner;

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

		//Spinner Config
		addItemsToSpinner();
	}

	private void addItemsToSpinner(){
		String[] users = Model.getSelectedProject().getUsersOnProject();
		List< String > list = new LinkedList<>( Arrays.asList( users ) );
		list.add( 0, getString( R.string.spinner_taskAssignee ) );
		users = list.toArray( new String[ list.size() ] );
		ArrayAdapter< String > dataAdapter = new ArrayAdapter<>( this, android.R.layout.simple_spinner_item, users );
		mAssigneeSpinner.setAdapter( dataAdapter );
		dataAdapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
	}

	//Button Listener
	@OnClick( R.id.createTaskActivity_createTaskButton )
	public void onUserSelectAButton( View view ){
		switch( view.getId() ){
			case R.id.createTaskActivity_createTaskButton:
				ToastLog.makeDebug( this, "Create Task", Toast.LENGTH_SHORT ).show();
				presenter.userSelectCreateTask( mAssigneeSpinner.getSelectedItem().toString() );
				break;
			default:
				ToastLog.makeError( this, String.format( getString( R.string.error_message ), getTitle() ), Toast.LENGTH_SHORT ).show();
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
				ToastLog.makeInfo( getParam1(), String.format( getString( R.string.error_inProgress ), "Task Creation" ), Toast.LENGTH_LONG ).show();
			}
		} );
	}

	@Override
	public void setTaskNameUnderValidation(){
		runOnUiThread( new Runnable1Param< TextInputEditText >( mTaskNameEditText ){
			@Override
			public void run(){
				getParam1().setError( String.format( getResources().getQuantityString( R.plurals.error_minCharacters, 3 ), 3 ) );
			}
		} );
	}

	@Override
	public void setTaskNameOverValidation(){
		runOnUiThread( new Runnable1Param< TextInputEditText >( mTaskNameEditText ){
			@Override
			public void run(){
				getParam1().setError( String.format( getResources().getQuantityString( R.plurals.error_maxCharacters, 20 ), 20 ) );
			}
		} );
	}

	@Override
	public void setTaskDescriptionOverValidation(){
		runOnUiThread( new Runnable1Param< TextInputEditText >( mTaskDescriptionEditText ){
			@Override
			public void run(){
				getParam1().setError( String.format( getResources().getQuantityString( R.plurals.error_maxCharacters, 128 ), 128 ) );
			}
		} );
	}

	@Override
	public void noInternetAccessValidation(){
		ToastLog.makeWarn( this, getString( R.string.error_noInternetAccess ), Toast.LENGTH_LONG ).show();
	}

	@Override
	public void errorValidation(){
		ToastLog.makeWarn( this, getString( R.string.error_message ), Toast.LENGTH_LONG ).show();
	}
}
