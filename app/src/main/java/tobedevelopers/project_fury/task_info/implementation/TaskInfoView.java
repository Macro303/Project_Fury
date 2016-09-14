package tobedevelopers.project_fury.task_info.implementation;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatSpinner;
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
import tobedevelopers.project_fury.model.Model;
import tobedevelopers.project_fury.model.Task;
import tobedevelopers.project_fury.runnable_param.Runnable1Param;
import tobedevelopers.project_fury.runnable_param.Runnable6Param;
import tobedevelopers.project_fury.task_info.TaskInfoContract;

/**
 * Created by Macro303 on 11/08/2016.
 */
public class TaskInfoView extends BaseView implements TaskInfoContract.View, TaskInfoContract.Navigation{

	@Bind( R.id.taskInfoActivity_taskNameEditText )
	TextInputEditText mTaskName;
	@Bind( R.id.taskInfoActivity_taskDescriptionEditText )
	TextInputEditText mTaskDescription;
	@Bind( R.id.taskInfoActivity_assigneeSpinner )
	AppCompatSpinner mAssignee;
	@Bind( R.id.taskInfoActivity_prioritySpinner )
	AppCompatSpinner mPriority;
	@Bind( R.id.taskInfoActivity_deleteTaskButton )
	Button mDeleteTask;
	@Bind( R.id.taskInfoActivity_updateTaskButton )
	Button mUpdateTask;
	@Bind( R.id.taskInfoActivity_saveTaskButton )
	Button mSaveTask;

	private TaskInfoContract.Presenter presenter;
	private String[] initialValues;

	@Override
	protected void onCreate( Bundle savedInstanceState ){
		setTitle( getString( R.string.title_activity_taskInfo ) );
		setContentView( R.layout.activity_task_info );
		super.onCreate( savedInstanceState );

		presenter = new TaskInfoPresenter( this, this );

		ButterKnife.bind( this );

		//Toolbar Config
		getSupportActionBar().setDisplayHomeAsUpEnabled( true );

		//Spinner Config
		setAssigneeSpinner();

		//InitialValues Config
		setInitialValues();
		initialValues = new String[]{ mTaskName.getEditableText().toString(), mTaskDescription.getEditableText().toString(), mAssignee.getSelectedItem().toString(), mPriority.getSelectedItem().toString() };
	}

	private void setInitialValues(){
		Task selectedTask = Model.getSelectedTask();
		mTaskName.setText( selectedTask.getName() );
		String description = selectedTask.getDescription();
		if( description.equals( "null" ) )
			description = "";
		mTaskDescription.setText( description );
		setSpinnerValue( mAssignee, selectedTask.getAssignee() );
		setSpinnerValue( mPriority, selectedTask.getPriority().getNameValue() );
	}

	private void setAssigneeSpinner(){
		String[] users = Model.getSelectedProject().getUsersOnProject();
		List< String > list = new LinkedList<>( Arrays.asList( users ) );
		list.add( 0, getString( R.string.spinner_taskAssignee ) );
		users = list.toArray( new String[ list.size() ] );
		ArrayAdapter< String > dataAdapter = new ArrayAdapter<>( this, android.R.layout.simple_spinner_item, users );
		mAssignee.setAdapter( dataAdapter );
		mAssignee.setEnabled( false );
		dataAdapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
		mPriority.setEnabled( false );
	}

	private void setSpinnerValue( Spinner spinner, String assignee ){
		int count = 0;
		for( int value = 0; value < spinner.getCount(); value++ )
			if( spinner.getItemAtPosition( value ).toString().equals( assignee ) )
				count = value;
		spinner.setSelection( count );
	}

	//Button Listeners
	@OnClick( { R.id.taskInfoActivity_updateTaskButton, R.id.taskInfoActivity_saveTaskButton, R.id.taskInfoActivity_deleteTaskButton } )
	public void onUserSelectAButton( View view ){
		switch( view.getId() ){
			case R.id.taskInfoActivity_updateTaskButton:
				ToastLog.makeDebug( this, "Update Task", Toast.LENGTH_SHORT );
				presenter.userSelectEditTask();
				break;
			case R.id.taskInfoActivity_saveTaskButton:
				ToastLog.makeDebug( this, "Save Task", Toast.LENGTH_SHORT );
				presenter.userSelectSaveTask( mAssignee.getSelectedItem().toString(), mPriority.getSelectedItem().toString() );
				break;
			case R.id.taskInfoActivity_deleteTaskButton:
				ToastLog.makeDebug( this, "Remove Task", Toast.LENGTH_SHORT );
				alertDeleteProject();
				break;
			default:
				ToastLog.makeError( this, String.format( getString( R.string.error_message ), getTitle() ), Toast.LENGTH_SHORT );
				break;
		}
	}

	@OnTextChanged( value = { R.id.taskInfoActivity_taskNameEditText }, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED )
	public void onUserChangedTaskNameEditText( Editable editable ){
		presenter.userEnterTaskName( editable.toString() );
	}

	@OnTextChanged( value = { R.id.taskInfoActivity_taskDescriptionEditText }, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED )
	public void onUserChangedTaskDescriptionEditText( Editable editable ){
		presenter.userEnterTaskDescription( editable.toString() );
	}

	private void alertDeleteProject(){
		AlertDialog.Builder builder = new AlertDialog.Builder( this );

		builder.setMessage( R.string.dialog_deleteAlertInstructions_task )
			.setTitle( R.string.dialog_deleteAlertTitle_task );
		builder.setPositiveButton( R.string.button_dialogDelete, new DialogInterface.OnClickListener(){
			@Override
			public void onClick( DialogInterface dialogInterface, int i ){
				presenter.userSelectRemoveTask();
			}
		} );

		builder.setNegativeButton( R.string.button_dialogCancel, new DialogInterface.OnClickListener(){
			@Override
			public void onClick( DialogInterface dialogInterface, int i ){
			}
		} );

		AlertDialog dialog = builder.create();
		dialog.show();
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
	public void defaultErrorMessage(){
		ToastLog.makeWarn( this, getString( R.string.error_defaultError ), Toast.LENGTH_LONG );
		mTaskName.setText( initialValues[ 0 ] );
		mTaskDescription.setText( initialValues[ 1 ] );
		setSpinnerValue( mAssignee, initialValues[ 2 ] );
		setSpinnerValue( mPriority, initialValues[ 3 ] );
	}

	public void setSpinnerEnabled( Spinner spinner, boolean enabled ){
		spinner.setEnabled( enabled );
		spinner.setAlpha( enabled ? 1.0f : 0.4f );
	}

	@Override
	public void setTaskEdited(){
		runOnUiThread( new Runnable6Param< Button, TextInputEditText, TextInputEditText, AppCompatSpinner, AppCompatSpinner, Button >( mUpdateTask, mTaskName, mTaskDescription, mAssignee, mPriority, mSaveTask ){
			@Override
			public void run(){
				getParam1().setVisibility( View.GONE );
				getParam2().setFocusable( true );
				getParam2().setEnabled( true );
				getParam2().setClickable( true );
				getParam2().setFocusableInTouchMode( true );
				getParam2().setCursorVisible( true );
				getParam3().setFocusable( true );
				getParam3().setEnabled( true );
				getParam3().setClickable( true );
				getParam3().setCursorVisible( true );
				getParam3().setFocusableInTouchMode( true );
				setSpinnerEnabled( getParam4(), true );
				setSpinnerEnabled( getParam5(), true );
				getParam6().setVisibility( View.VISIBLE );
			}
		} );
	}

	@Override
	public void setTaskSaved(){
		runOnUiThread( new Runnable6Param< Button, TextInputEditText, TextInputEditText, AppCompatSpinner, AppCompatSpinner, Button >( mUpdateTask, mTaskName, mTaskDescription, mAssignee, mPriority, mSaveTask ){
			@Override
			public void run(){
				getParam1().setVisibility( View.VISIBLE );
				getParam2().setFocusable( false );
				getParam2().setEnabled( false );
				getParam2().setClickable( false );
				getParam2().setFocusableInTouchMode( false );
				getParam2().setCursorVisible( false );
				getParam3().setFocusable( false );
				getParam3().setEnabled( false );
				getParam3().setClickable( false );
				getParam3().setCursorVisible( false );
				getParam3().setFocusableInTouchMode( false );
				setSpinnerEnabled( getParam4(), false );
				setSpinnerEnabled( getParam5(), false );
				getParam6().setVisibility( View.GONE );
			}
		} );
	}

	@Override
	public void taskUpdatingInProgress(){
		runOnUiThread( new Runnable1Param< TaskInfoView >( this ){
			@Override
			public void run(){
				ToastLog.makeInfo( getParam1(), String.format( getString( R.string.error_inProgress ), "Task Update" ), Toast.LENGTH_LONG );
			}
		} );
	}

	@Override
	public void taskDeletionInProgress(){
		runOnUiThread( new Runnable1Param< TaskInfoView >( this ){
			@Override
			public void run(){
				ToastLog.makeInfo( getParam1(), String.format( getString( R.string.error_inProgress ), "Task Deletion" ), Toast.LENGTH_LONG );
			}
		} );
	}

	@Override
	public void setTaskNameUnderValidation(){
		runOnUiThread( new Runnable1Param< TextInputEditText >( mTaskName ){
			@Override
			public void run(){
				getParam1().setError( String.format( getResources().getQuantityString( R.plurals.error_minCharacters, 3 ), 3 ) );
			}
		} );
	}

	@Override
	public void setTaskNameOverValidation(){
		runOnUiThread( new Runnable1Param< TextInputEditText >( mTaskName ){
			@Override
			public void run(){
				getParam1().setError( String.format( getResources().getQuantityString( R.plurals.error_maxCharacters, 20 ), 20 ) );
			}
		} );
	}

	@Override
	public void setTaskDescriptionOverValidation(){
		runOnUiThread( new Runnable1Param< TextInputEditText >( mTaskDescription ){
			@Override
			public void run(){
				getParam1().setError( String.format( getResources().getQuantityString( R.plurals.error_maxCharacters, 128 ), 128 ) );
			}
		} );
	}

	@Override
	public void noInternetAccessValidation(){
		ToastLog.makeWarn( this, getString( R.string.error_noInternetAccess ), Toast.LENGTH_LONG );
	}

	@Override
	public void disableSaveTask(){
		runOnUiThread( new Runnable1Param< Button >( mSaveTask ){
			@Override
			public void run(){
				getParam1().setEnabled( false );
			}
		} );
	}

	@Override
	public void enableSaveTask(){
		runOnUiThread( new Runnable1Param< Button >( mSaveTask ){
			@Override
			public void run(){
				getParam1().setEnabled( true );
			}
		} );
	}
}
