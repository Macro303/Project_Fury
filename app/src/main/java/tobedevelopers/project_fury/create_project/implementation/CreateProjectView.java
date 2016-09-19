package tobedevelopers.project_fury.create_project.implementation;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.text.Editable;
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
import tobedevelopers.project_fury.create_project.CreateProjectContract;
import tobedevelopers.project_fury.runnable_param.Runnable1Param;

/**
 * Created by Macro303 on 11/08/2016.
 */
public class CreateProjectView extends BaseView implements CreateProjectContract.View, CreateProjectContract.Navigation{

	@Bind( R.id.createProjectActivity_projectNameEditText )
	TextInputEditText mProjectNameEditText;
	@Bind( R.id.createProjectActivity_projectDescriptionEditText )
	TextInputEditText mProjectDescriptionEditText;
	@Bind( R.id.createProjectActivity_createProjectButton )
	Button mCreateProjectButton;

	private CreateProjectContract.Presenter presenter;

	@Override
	protected void onCreate( Bundle savedInstanceState ){
		setTitle( getString( R.string.title_activity_createProject ) );
		setContentView( R.layout.activity_create_project );
		super.onCreate( savedInstanceState );

		presenter = new CreateProjectPresenter( this, this );

		ButterKnife.bind( this );
	}

	//Button Listener
	@OnClick( R.id.createProjectActivity_createProjectButton )
	public void onUserSelectAButton( View view ){
		switch( view.getId() ){
			case R.id.createProjectActivity_createProjectButton:
				mCreateProjectButton.setEnabled( false );
				ToastLog.makeDebug( this, "Create Project", Toast.LENGTH_SHORT );
				presenter.userSelectCreateProject();
				break;
			default:
				ToastLog.makeError( this, String.format( getString( R.string.error_message ), getTitle() ), Toast.LENGTH_SHORT );
				break;
		}
	}

	//Text Change Listener
	@OnTextChanged( value = { R.id.createProjectActivity_projectNameEditText }, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED )
	public void onUserChangedProjectNameEditText( Editable editable ){
		presenter.userEnterProjectName( editable.toString() );
	}

	@OnTextChanged( value = { R.id.createProjectActivity_projectDescriptionEditText }, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED )
	public void onUserChangedProjectDescriptionEditText( Editable editable ){
		presenter.userEnterProjectDescription( editable.toString() );
	}

	@Override
	public void navigateToPrevious(){
		runOnUiThread( new Runnable1Param< CreateProjectView >( this ){
			@Override
			public void run(){
				finish();
			}
		} );
	}

	@Override
	public void enableCreateProjectButton(){
		runOnUiThread( new Runnable1Param< CreateProjectView >( this ){
			@Override
			public void run(){
				getParam1().mCreateProjectButton.setEnabled( true );
			}
		} );
	}

	@Override
	public void disableCreateProjectButton(){
		runOnUiThread( new Runnable1Param< CreateProjectView >( this ){
			@Override
			public void run(){
				getParam1().mCreateProjectButton.setEnabled( false );
			}
		} );
	}

	@Override
	public void projectCreationInProgress(){
		runOnUiThread( new Runnable1Param< CreateProjectView >( this ){
			@Override
			public void run(){
				ToastLog.makeInfo( getParam1(), String.format( getString( R.string.error_inProgress ), "Project Creation" ), Toast.LENGTH_LONG );
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
		mCreateProjectButton.setEnabled( true );
		ToastLog.makeWarn( this, getString( R.string.error_noInternetAccess ), Toast.LENGTH_LONG );
	}

	@Override
	public void setProjectAlreadyUsedValidation(){
		mCreateProjectButton.setEnabled( true );
		runOnUiThread( new Runnable1Param< TextInputEditText >( mProjectNameEditText ){
			@Override
			public void run(){
				mProjectNameEditText.requestFocus();
				getParam1().setError( String.format( getString( R.string.error_alreadyExists ), "Project Name" ) );
			}
		} );
	}
}
