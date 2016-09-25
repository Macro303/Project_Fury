package tobedevelopers.project_fury.task_info.implementation;

import android.app.ProgressDialog;
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
import tobedevelopers.project_fury.model.Column;
import tobedevelopers.project_fury.model.Model;
import tobedevelopers.project_fury.model.Task;
import tobedevelopers.project_fury.runnable_param.Runnable1Param;
import tobedevelopers.project_fury.runnable_param.Runnable7Param;
import tobedevelopers.project_fury.task_info.TaskInfoContract;

/**
 * Created by Macro303 on 11/08/2016.
 */
public class TaskInfoView extends BaseView implements TaskInfoContract.View, TaskInfoContract.Navigation{

	@Bind( R.id.taskInfoActivity_taskNameEditText )
	protected TextInputEditText mTaskName;
	@Bind( R.id.taskInfoActivity_taskDescriptionEditText )
	protected TextInputEditText mTaskDescription;
	@Bind( R.id.taskInfoActivity_assigneeSpinner )
	protected AppCompatSpinner mAssignee;
	@Bind( R.id.taskInfoActivity_prioritySpinner )
	protected AppCompatSpinner mPriority;
	@Bind( R.id.taskInfoActivity_columnSpinner )
	protected AppCompatSpinner mColumn;
	@Bind( R.id.taskInfoActivity_deleteTaskButton )
	protected Button mDeleteTask;
	@Bind( R.id.taskInfoActivity_updateTaskButton )
	protected Button mUpdateTask;
	@Bind( R.id.taskInfoActivity_saveTaskButton )
	protected Button mSaveTask;

	private TaskInfoContract.Presenter presenter;
	private String[] initialValues = new String[ 5 ];
	private Column[] columns;
	private ProgressDialog progressDialog;

	@Override
	protected void onCreate( Bundle savedInstanceState ){
		setTitle( getString( R.string.title_activity_taskInfo ) );
		setContentView( R.layout.activity_task_info );
		super.onCreate( savedInstanceState );

		presenter = new TaskInfoPresenter( this, this );

		progressDialog = new ProgressDialog( this );

		ButterKnife.bind( this );

		//Toolbar Config
		getSupportActionBar().setDisplayHomeAsUpEnabled( true );

		//Spinner Config
		setAssigneeSpinner();

		//InitialValues Config
		setInitialValues();
		initialValues = new String[]{ mTaskName.getEditableText().toString(), mTaskDescription.getEditableText().toString(), "Unassigned", "Unassigned", "New" };

		//Column Spinner Config
		presenter.getColumnsOnProject();
	}

	private void setInitialValues(){
		Task selectedTask = Model.getSelectedTask();
		mTaskName.setText( selectedTask.getName() );
		String description = selectedTask.getDescription();
		if( description.equals( "null" ) )
			description = "";
		mTaskDescription.setText( description );
		setSpinnerValue( mAssignee, selectedTask.getAssignee(), 2 );
		setSpinnerValue( mPriority, selectedTask.getPriority().getNameValue(), 3 );
	}

	private void setAssigneeSpinner(){
		String[] users = Model.getSelectedProject().getUsersOnProject();
		Arrays.sort( users );
		List< String > list = new LinkedList<>( Arrays.asList( users ) );
		list.add( 0, getString( R.string.spinner_taskAssignee ) );
		users = list.toArray( new String[ list.size() ] );
		ArrayAdapter< String > dataAdapter = new ArrayAdapter<>( this, android.R.layout.simple_spinner_item, users );
		mAssignee.setAdapter( dataAdapter );
		mAssignee.setEnabled( false );
		dataAdapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
		mPriority.setEnabled( false );
	}

	@Override
	public void setColumnSpinner( Column[] columns ){
		this.columns = columns;
		Arrays.sort( this.columns );
		String[] columnNames = new String[ columns.length ];
		for( int i = 0; i < columns.length; i++ )
			columnNames[ i ] = columns[ i ].getName();
		ArrayAdapter< String > dataAdapter = new ArrayAdapter<>( this, android.R.layout.simple_spinner_item, columnNames );
		mColumn.setAdapter( dataAdapter );
		mColumn.setEnabled( false );
		dataAdapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
		setSpinnerValue( mColumn );
	}

	private void setSpinnerValue( Spinner spinner ){
		int count = 0;
		for( int value = 0; value < spinner.getCount(); value++ )
			for( Column column : columns )
				if( column.getColumnID().equals( Model.getSelectedTask().getColumnID() ) && spinner.getItemAtPosition( value ).toString().equals( column.getName() ) )
					count = value;
		spinner.setSelection( count );
		initialValues[ 4 ] = spinner.getSelectedItem().toString();
	}

	private void setSpinnerValue( Spinner spinner, String assignee, int placement ){
		int count = 0;
		for( int value = 0; value < spinner.getCount(); value++ )
			if( spinner.getItemAtPosition( value ).toString().equals( assignee ) )
				count = value;
		spinner.setSelection( count );
		initialValues[ placement ] = spinner.getSelectedItem().toString();
	}

	//Button Listeners
	@OnClick( { R.id.taskInfoActivity_updateTaskButton, R.id.taskInfoActivity_saveTaskButton, R.id.taskInfoActivity_deleteTaskButton } )
	public void onUserSelectAButton( View view ){
		switch( view.getId() ){
			case R.id.taskInfoActivity_updateTaskButton:
				mUpdateTask.setEnabled( false );
				presenter.userSelectEditTask();
				break;
			case R.id.taskInfoActivity_saveTaskButton:
				mSaveTask.setEnabled( false );
				mDeleteTask.setEnabled( false );
				presenter.userSelectSaveTask( mAssignee.getSelectedItem().toString(), mPriority.getSelectedItem().toString(), columns[ mColumn.getSelectedItemPosition() ] );
				break;
			case R.id.taskInfoActivity_deleteTaskButton:
				mUpdateTask.setEnabled( false );
				mSaveTask.setEnabled( false );
				mDeleteTask.setEnabled( false );
				alertDeleteProject();
				break;
			default:
				ToastLog.makeError( this, getString( R.string.app_name ), String.format( getString( R.string.error_message ), getTitle() ), Toast.LENGTH_SHORT );
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
				mDeleteTask.setEnabled( true );
				mUpdateTask.setEnabled( true );
				mSaveTask.setEnabled( true );
			}
		} );

		builder.setOnDismissListener( new DialogInterface.OnDismissListener(){
			@Override
			public void onDismiss( DialogInterface dialogInterface ){
				mDeleteTask.setEnabled( true );
				mUpdateTask.setEnabled( true );
				mSaveTask.setEnabled( true );
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
	public void showTaskUpdatingInProgress(){
		runOnUiThread( new Runnable1Param< TaskInfoView >( this ){
			@Override
			public void run(){
				progressDialog.setProgressStyle( ProgressDialog.STYLE_SPINNER );
				progressDialog.setMessage( "Updating..." );
				progressDialog.setIndeterminate( true );
				progressDialog.setCancelable( false );
				progressDialog.show();
			}
		} );
	}

	@Override
	public void hideTaskUpdatingInProgress(){
		runOnUiThread( new Runnable1Param< TaskInfoView >( this ){
			@Override
			public void run(){
				progressDialog.dismiss();
			}
		} );
	}

	@Override
	public void defaultErrorMessage(){
		super.defaultErrorMessage();
		mTaskName.setText( initialValues[ 0 ] );
		mTaskDescription.setText( initialValues[ 1 ] );
		setSpinnerValue( mAssignee, initialValues[ 2 ], 2 );
		setSpinnerValue( mPriority, initialValues[ 3 ], 3 );
		setSpinnerValue( mColumn );
	}

	public void setSpinnerEnabled( Spinner spinner, boolean enabled ){
		spinner.setEnabled( enabled );
		spinner.setAlpha( enabled ? 1.0f : 0.4f );
	}

	@Override
	public void setTaskEdited(){
		runOnUiThread( new Runnable7Param< Button, TextInputEditText, TextInputEditText, AppCompatSpinner, AppCompatSpinner, AppCompatSpinner, Button >( mUpdateTask, mTaskName, mTaskDescription, mAssignee, mPriority, mColumn, mSaveTask ){
			@Override
			public void run(){
				getParam1().setVisibility( View.GONE );
				setEditTextEnabled( getParam2(), true );
				setEditTextEnabled( getParam3(), true );
				setSpinnerEnabled( getParam4(), true );
				setSpinnerEnabled( getParam5(), true );
				setSpinnerEnabled( getParam6(), true );
				getParam7().setVisibility( View.VISIBLE );
			}
		} );
	}

	private void setEditTextEnabled( TextInputEditText item, boolean value ){
		item.setClickable( value );
		item.setCursorVisible( value );
		item.setEnabled( value );
		item.setFocusable( value );
		item.setFocusableInTouchMode( value );
	}

	@Override
	public void setTaskSaved(){
		runOnUiThread( new Runnable7Param< Button, TextInputEditText, TextInputEditText, AppCompatSpinner, AppCompatSpinner, AppCompatSpinner, Button >( mUpdateTask, mTaskName, mTaskDescription, mAssignee, mPriority, mColumn, mSaveTask ){
			@Override
			public void run(){
				getParam1().setVisibility( View.VISIBLE );
				setEditTextEnabled( getParam2(), false );
				setEditTextEnabled( getParam3(), false );
				setSpinnerEnabled( getParam4(), false );
				setSpinnerEnabled( getParam5(), false );
				setSpinnerEnabled( getParam6(), false );
				getParam7().setVisibility( View.GONE );
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
