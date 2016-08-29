package tobedevelopers.project_fury.project_info.implementation;

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
import tobedevelopers.project_fury.project_info.ProjectInfoContract;
import tobedevelopers.project_fury.runnable_param.Runnable1Param;

/**
 * Created by Macro303 on 11/08/2016.
 */
public class ProjectInfoView extends BaseView implements ProjectInfoContract.View, ProjectInfoContract.Navigation{

	@Bind( R.id.projectInfoActivity_projectNameEditText )
	TextInputEditText mProjectNameEditText;
	@Bind( R.id.projectInfoActivity_projectDescriptionEditText )
	TextInputEditText mProjectDescriptionEditText;
	@Bind( R.id.projectInfoActivity_editProjectButton )
	Button mEditProjectButton;

	private ProjectInfoContract.Presenter presenter;

	@Override
	protected void onCreate( Bundle savedInstanceState ){
		setTitle( getString( R.string.title_activity_projectInfo ) );
		setContentView( R.layout.activity_project_info );
		super.onCreate( savedInstanceState );

		presenter = new ProjectInfoPresenter( this, this );

		ButterKnife.bind( this );

		//Toolbar Config
		getSupportActionBar().setDisplayHomeAsUpEnabled( true );
	}

	//Button Listener
	@OnClick( { /*R.id.projectInfoActivity_addUserButton, R.id.projectInfoActivity_removeMeButton, R.id.projectInfoActivity_addColumnButton, R.id.projectInfoActivity_removeColumnButton,*/ R.id.projectInfoActivity_editProjectButton } )
	public void onUserSelectAButton( View view ){
		switch( view.getId() ){
			/*case R.id.projectInfoActivity_addUserButton:
				Toast.makeText( this, "Add User", Toast.LENGTH_SHORT ).show();
				presenter.userSelectAddUser();
				break;
			case R.id.projectInfoActivity_removeMeButton:
				Toast.makeText( this, "Remove Me", Toast.LENGTH_SHORT ).show();
				presenter.userSelectRemoveMe();
				break;
			case R.id.projectInfoActivity_addColumnButton:
				Toast.makeText( this, "Add Column", Toast.LENGTH_SHORT ).show();
				presenter.userSelectAddColumn();
				break;
			case R.id.projectInfoActivity_removeColumnButton:
				Toast.makeText( this, "Remove Column", Toast.LENGTH_SHORT ).show();
				presenter.userSelectRemoveColumn();
				break;*/
			case R.id.projectInfoActivity_editProjectButton:
				Toast.makeText( this, "Edit Project", Toast.LENGTH_SHORT ).show();
				presenter.userSelectEditProject();
				break;
			default:
				Toast.makeText( this, String.format( getString( R.string.error_message ), getTitle() ), Toast.LENGTH_SHORT ).show();
				Log.w( getString( R.string.app_name ), String.format( getString( R.string.error_message ), getTitle() ) );
				break;
		}
	}

	//Text Change Listener
	@OnTextChanged( value = { R.id.projectInfoActivity_projectNameEditText }, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED )
	public void onUserChangedProjectNameEditText( Editable editable ){
		presenter.userEnterProjectName( editable.toString() );
	}

	@OnTextChanged( value = { R.id.projectInfoActivity_projectDescriptionEditText }, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED )
	public void onUserChangedProjectDescriptionEditText( Editable editable ){
		presenter.userEnterProjectDescription( editable.toString() );
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
	public void displayColumnAdded(){
		Toast.makeText( getApplicationContext(), "A New Column Was Added", Toast.LENGTH_SHORT ).show();
	}

	@Override
	public void displayUserAdded(){
		Toast.makeText( getApplicationContext(), "A New User Was Added", Toast.LENGTH_SHORT ).show();
	}

	@Override
	public void displayColumnRemoved(){
		Toast.makeText( getApplicationContext(), "A Column Was Removed", Toast.LENGTH_SHORT ).show();
	}

	@Override
	public void displayProjectEdited(){
		Toast.makeText( getApplicationContext(), "The Project Was Edited", Toast.LENGTH_SHORT ).show();
	}

	@Override
	public void projectUpdatingInProgress(){
		runOnUiThread( new Runnable1Param< ProjectInfoView >( this ){
			@Override
			public void run(){
				ToastLog.makeInfo( getParam1(), getString( R.string.error_inProgress ), Toast.LENGTH_LONG ).show();
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
	public void noInternetAccessValidation(){
		ToastLog.makeWarn( this, getString( R.string.error_noInternetAccess ), Toast.LENGTH_LONG ).show();
	}
}
