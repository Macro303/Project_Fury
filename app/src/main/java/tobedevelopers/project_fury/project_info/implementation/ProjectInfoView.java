package tobedevelopers.project_fury.project_info.implementation;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.OnClick;
import tobedevelopers.project_fury.BaseView;
import tobedevelopers.project_fury.R;
import tobedevelopers.project_fury.project_info.ProjectInfoContract;

/**
 * Created by Macro303 on 11/08/2016.
 */
public class ProjectInfoView extends BaseView implements ProjectInfoContract.View, ProjectInfoContract.Navigation{

	private ProjectInfoContract.Presenter presenter;

	@Override
	protected void onCreate( Bundle savedInstanceState ){
		setTitle( getString( R.string.title_activity_projectInfo ) );
		setContentView( R.layout.activity_project_info );
		super.onCreate( savedInstanceState );

		ButterKnife.bind( this );

		presenter = new ProjectInfoPresenter( this, this );
	}

	//Buttons Listener
	@OnClick( { R.id.projectInfoActivity_addUserButton, R.id.projectInfoActivity_removeMeButton, R.id.projectInfoActivity_addColumnButton, R.id.projectInfoActivity_removeColumnButton, R.id.projectInfoActivity_editProjectButton } )
	public void onUserSelectAButton( View view ){
		switch( view.getId() ){
			case R.id.projectInfoActivity_addUserButton:
				//Toast.makeText( this, "Add User", Toast.LENGTH_SHORT ).show();
				presenter.userSelectAddUser();
				break;
			case R.id.projectInfoActivity_removeMeButton:
				//Toast.makeText( this, "Remove User", Toast.LENGTH_SHORT ).show();
				presenter.userSelectRemoveMe();
				break;
			case R.id.projectInfoActivity_addColumnButton:
				//Toast.makeText( this, "Add Column", Toast.LENGTH_SHORT ).show();
				presenter.userSelectAddColumn();
				break;
			case R.id.projectInfoActivity_removeColumnButton:
				//Toast.makeText( this, "Remove Column", Toast.LENGTH_SHORT ).show();
				presenter.userSelectRemoveColumn();
				break;
			case R.id.projectInfoActivity_editProjectButton:
				//Toast.makeText( this, "Edit Project", Toast.LENGTH_SHORT ).show();
				presenter.userSelectEditProject();
				break;
			default:
				Toast.makeText( this, "An Error has occured", Toast.LENGTH_SHORT ).show();
				break;
		}
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
}
