package tobedevelopers.project_fury.project_info.implementation;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Spanned;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

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
import tobedevelopers.project_fury.model.Project;
import tobedevelopers.project_fury.project_info.ProjectInfoContract;
import tobedevelopers.project_fury.runnable_param.Runnable1Param;
import tobedevelopers.project_fury.runnable_param.Runnable7Param;

/**
 * Created by Macro303 on 11/08/2016.
 */
public class ProjectInfoView extends BaseView implements ProjectInfoContract.View, ProjectInfoContract.Navigation{

	@Bind( R.id.projectInfoActivity_textLayout )
//	TextView mDeleteText;
	protected RelativeLayout mTextLayout;
	@Bind( R.id.projectInfoActivity_projectNameEditText )
	TextInputEditText mProjectNameEditText;
	@Bind( R.id.projectInfoActivity_projectDescriptionEditText )
	TextInputEditText mProjectDescriptionEditText;
	@Bind( R.id.projectInfoActivity_editProjectButton )
	Button mEditProjectButton;
	@Bind( R.id.projectInfoActivity_saveProjectButton )
	Button mSaveProjectButton;
	@Bind( R.id.projectInfoActivity_deleteProjectButton )
	Button mDeleteProjectButton;
	@Bind( R.id.projectInfoActivity_addColumnButton )
	ImageButton mAddColumnButton;

	private ProjectInfoContract.Presenter presenter;

	private RecyclerView mRecyclerView;
	private ColumnRecyclerAdapter columnRecyclerAdapter;
	private ItemTouchHelper.Callback callback;
	private ItemTouchHelper touchHelper;
	private ProgressDialog progressDialog;

	private String columnName;

	@Override
	protected void onCreate( Bundle savedInstanceState ){
		setTitle( getString( R.string.title_activity_projectInfo ) );
		setContentView( R.layout.activity_project_info );
		super.onCreate( savedInstanceState );

		presenter = new ProjectInfoPresenter( this, this );

		progressDialog = new ProgressDialog( this );

		ButterKnife.bind( this );

		//Toolbar Config
		getSupportActionBar().setDisplayHomeAsUpEnabled( true );

		//Spinner Config
		addItemsToFields();

		//List Config
		presenter.loadColumns();

		//UI References
		mRecyclerView = ( RecyclerView ) findViewById( R.id.projectInfoActivity_columnNamesList );
		mAddColumnButton.setEnabled( false );

		//Recycler Config
		mRecyclerView.setHasFixedSize( true );
		mRecyclerView.setLayoutManager( new LinearLayoutManager( getApplicationContext() ) );
		columnRecyclerAdapter = new ColumnRecyclerAdapter();
		mRecyclerView.setAdapter( columnRecyclerAdapter );
		callback = new SimpleItemTouchHelperCallback( columnRecyclerAdapter );
		touchHelper = new ItemTouchHelper( callback );
	}

	private void addItemsToFields(){
		Project selectedProject = Model.getSelectedProject();
		mProjectNameEditText.setText( selectedProject.getName() );
		String description = selectedProject.getDescription();
		if( description.equals( "null" ) )
			description = "";
		mProjectDescriptionEditText.setText( description );
	}

	@Override
	public void fillColumnList( Column[] columns ){
		columnRecyclerAdapter.setData( columns, this );
	}

	//Button Listener
	@OnClick( { R.id.projectInfoActivity_editProjectButton, R.id.projectInfoActivity_saveProjectButton, R.id.projectInfoActivity_deleteProjectButton, R.id.projectInfoActivity_addColumnButton } )
	public void onUserSelectAButton( View view ){
		switch( view.getId() ){
			case R.id.projectInfoActivity_editProjectButton:
				ToastLog.makeDebug( this, "Edit Project", Toast.LENGTH_SHORT );
				mEditProjectButton.setEnabled( false );
				presenter.userSelectEditProject();
				break;
			case R.id.projectInfoActivity_saveProjectButton:
				ToastLog.makeDebug( this, "Save Project", Toast.LENGTH_SHORT );
				setEnabledAllButtons( false );
				mAddColumnButton.setEnabled( false );
				presenter.userSelectSaveProject( columnRecyclerAdapter.columnList );
				break;
			case R.id.projectInfoActivity_deleteProjectButton:
				ToastLog.makeDebug( this, "Delete Project", Toast.LENGTH_SHORT );
				setEnabledAllButtons( false );
				mAddColumnButton.setEnabled( false );
				alertDeleteProject();
				break;
			case R.id.projectInfoActivity_addColumnButton:
				ToastLog.makeDebug( this, "Add Column", Toast.LENGTH_SHORT );
				setEnabledAllButtons( false );
				alertAddColumn();
				break;
			default:
				ToastLog.makeError( this, String.format( getString( R.string.error_message ), getTitle() ), Toast.LENGTH_SHORT );
				break;
		}
	}

	//Text Changed Listeners
	@OnTextChanged( value = { R.id.projectInfoActivity_projectNameEditText }, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED )
	public void onUserChangeProjectNameEditText( Editable editable ){
		presenter.userEnterProjectName( editable.toString() );
	}

	@OnTextChanged( value = { R.id.projectInfoActivity_projectDescriptionEditText }, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED )
	public void onUserChangedProjectDescriptionEditText( Editable editable ){
		presenter.userEnterProjectDescription( editable.toString() );
	}

	private void alertDeleteProject(){
		AlertDialog.Builder builder = new AlertDialog.Builder( this );

		builder.setMessage( R.string.dialog_deleteAlertInstructions_project )
			.setTitle( R.string.dialog_deleteAlertTitle_project );
		builder.setPositiveButton( R.string.button_dialogDelete, new DialogInterface.OnClickListener(){
			@Override
			public void onClick( DialogInterface dialogInterface, int i ){
				presenter.userSelectDeleteProject();
			}
		} );

		builder.setNegativeButton( R.string.button_dialogCancel, new DialogInterface.OnClickListener(){
			@Override
			public void onClick( DialogInterface dialogInterface, int i ){
				setEnabledAllButtons( true );
				dialogInterface.cancel();
			}
		} );

		builder.setOnDismissListener( new DialogInterface.OnDismissListener(){
			@Override
			public void onDismiss( DialogInterface dialogInterface ){
				setEnabledAllButtons( true );
			}
		} );

		AlertDialog dialog = builder.create();
		dialog.show();
	}

	public void alertDeleteColumn( final List< Column > columnList ){
		AlertDialog.Builder builder = new AlertDialog.Builder( this );

		builder.setMessage( R.string.dialog_deleteAlertInstructions_column ).setTitle( R.string.dialog_deleteAlertTitle_column );
		builder.setPositiveButton( R.string.button_dialogDelete, new DialogInterface.OnClickListener(){
			@Override
			public void onClick( DialogInterface dialogInterface, int i ){
				setEnabledAllButtons( true );
				presenter.saveColumnsBeforeDeleting( columnList );
			}
		} );

		builder.setNegativeButton( R.string.button_dialogCancel, new DialogInterface.OnClickListener(){
			@Override
			public void onClick( DialogInterface dialogInterface, int i ){
				setEnabledAllButtons( true );
				dialogInterface.cancel();
			}
		} );

		builder.setOnDismissListener( new DialogInterface.OnDismissListener(){
			@Override
			public void onDismiss( DialogInterface dialogInterface ){
				setEnabledAllButtons( true );
			}
		} );

		AlertDialog dialog = builder.create();
		dialog.show();
	}

	private void alertAddColumn(){
		AlertDialog.Builder builder = new AlertDialog.Builder( this );
		builder.setTitle( R.string.dialog_addAlertTitle_column );

		final TextInputEditText addColumnNameEditText = new TextInputEditText( this );
		addColumnNameEditText.setHint( R.string.hint_columnName );
		addColumnNameEditText.setInputType( InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS );

		InputFilter filter = new InputFilter(){
			public CharSequence filter( CharSequence source, int start, int end,
			                            Spanned dest, int dstart, int dend ){
				for( int i = start; i < end; i++ ){
					if( !Character.isLetterOrDigit( source.charAt( i ) ) ){
						return "";
					}
				}
				return null;
			}
		};

		addColumnNameEditText.setFilters( new InputFilter[]{ filter } );

		builder.setView( addColumnNameEditText );

		addColumnNameEditText.addTextChangedListener( new TextWatcher(){
			@Override
			public void beforeTextChanged( CharSequence charSequence, int i, int i1, int i2 ){

			}

			@Override
			public void onTextChanged( CharSequence charSequence, int i, int i1, int i2 ){

			}

			@Override
			public void afterTextChanged( Editable editable ){
				if( editable.toString().length() >= 3 && editable.toString().length() <= 8 ){
					columnName = editable.toString();
				}else{
					if( editable.toString().length() < 3 ){
						addColumnNameEditText.setError( String.format( getResources().getQuantityString( R.plurals.error_minCharacters, 3 ), 3 ) );
					}else if( editable.toString().length() > 8 ){
						addColumnNameEditText.setError( String.format( getResources().getQuantityString( R.plurals.error_maxCharacters, 8 ), 8 ) );
					}
				}
			}
		} );

		builder.setPositiveButton( "Create", new DialogInterface.OnClickListener(){
			@Override
			public void onClick( DialogInterface dialogInterface, int i ){
				setEnabledAllButtons( true );
				presenter.userSelectAddColumn( columnName );
				saveProjectDescription();
			}
		} );

		builder.setNegativeButton( R.string.button_dialogCancel, new DialogInterface.OnClickListener(){
			@Override
			public void onClick( DialogInterface dialogInterface, int i ){
				setEnabledAllButtons( true );
				dialogInterface.cancel();
				saveProjectDescription();
			}
		} );

		builder.setOnDismissListener( new DialogInterface.OnDismissListener(){
			@Override
			public void onDismiss( DialogInterface dialogInterface ){
				setEnabledAllButtons( true );
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
	public void editProjectDescription(){
		runOnUiThread( new Runnable7Param< Button, TextInputEditText, TextInputEditText, RelativeLayout, ItemTouchHelper, Button, ImageButton >( mEditProjectButton, mProjectNameEditText, mProjectDescriptionEditText, mTextLayout, touchHelper, mSaveProjectButton, mAddColumnButton ){
			@Override
			public void run(){
				getParam1().setVisibility( View.GONE );
				setEditTextEnabled( getParam2(), true );
				setEditTextEnabled( getParam3(), true );
				getParam4().setVisibility( View.VISIBLE );
				getParam5().attachToRecyclerView( mRecyclerView );
				getParam6().setVisibility( View.VISIBLE );
				getParam7().setEnabled( true );
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
	public void saveProjectDescription(){
		runOnUiThread( new Runnable7Param< Button, TextInputEditText, TextInputEditText, RelativeLayout, ItemTouchHelper, Button, ImageButton >( mSaveProjectButton, mProjectNameEditText, mProjectDescriptionEditText, mTextLayout, touchHelper, mEditProjectButton, mAddColumnButton ){
			@Override
			public void run(){
				getParam1().setVisibility( View.GONE );
				setEditTextEnabled( getParam2(), false );
				setEditTextEnabled( getParam3(), false );
				getParam4().setVisibility( View.INVISIBLE );
				getParam5().attachToRecyclerView( null );
				getParam6().setVisibility( View.VISIBLE );
				setEnabledAllButtons( true );
				getParam7().setEnabled( false );
			}
		} );
	}

	private void setEnabledAllButtons( Boolean condition ){
		mEditProjectButton.setEnabled( condition );
		mSaveProjectButton.setEnabled( condition );
		mDeleteProjectButton.setEnabled( condition );
	}

	@Override
	public void addColumnName(){
		presenter.loadColumns();
	}

	@Override
	public void showProjectUpdatingInProgress(){
		runOnUiThread( new Runnable1Param< ProjectInfoView >( this ){
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
	public void hideProjectUpdatingInProgress(){
		runOnUiThread( new Runnable1Param< ProjectInfoView >( this ){
			@Override
			public void run(){
				progressDialog.dismiss();
			}
		} );
	}

	@Override
	public void setProjectNameUnderValidation(){
		runOnUiThread( new Runnable1Param< TextInputEditText >( mProjectNameEditText ){
			@Override
			public void run(){
				getParam1().setError( String.format( getResources().getQuantityString( R.plurals.error_minCharacters, 3 ), 3 ) );
			}
		} );
	}

	@Override
	public void setProjectNameOverValidation(){
		runOnUiThread( new Runnable1Param< TextInputEditText >( mProjectNameEditText ){
			@Override
			public void run(){
				getParam1().setError( String.format( getResources().getQuantityString( R.plurals.error_maxCharacters, 20 ), 20 ) );
			}
		} );
	}

	@Override
	public void setProjectDescriptionOverValidation(){
		runOnUiThread( new Runnable1Param< TextInputEditText >( mProjectDescriptionEditText ){
			@Override
			public void run(){
				getParam1().setError( String.format( getResources().getQuantityString( R.plurals.error_maxCharacters, 128 ), 128 ) );
			}
		} );
	}

	@Override
	public void enableSave(){
		runOnUiThread( new Runnable1Param< Button >( mSaveProjectButton ){
			@Override
			public void run(){
				getParam1().setEnabled( true );
			}
		} );
	}

	@Override
	public void disableSave(){
		runOnUiThread( new Runnable1Param< Button >( mSaveProjectButton ){
			@Override
			public void run(){
				getParam1().setEnabled( false );
			}
		} );
	}

	@Override
	public void noInternetAccessValidation(){
		setEnabledAllButtons( true );
		ToastLog.makeWarn( this, getString( R.string.error_noInternetAccess ), Toast.LENGTH_LONG );
	}

	@Override
	public void defaultErrorMessage(){
		ToastLog.makeWarn( this, getString( R.string.error_defaultError ), Toast.LENGTH_LONG );
		//mProjectDescriptionEditText.getEditableText().clear();
	}

	@Override
	public void setInvalidUserValidation(){
		setEnabledAllButtons( true );
		runOnUiThread( new Runnable1Param< TextInputEditText >( mProjectNameEditText ){
			@Override
			public void run(){
				getParam1().setError( getString( R.string.error_alreadyExists, "Project name" ) );
				getParam1().requestFocus();
			}
		} );
	}
}
