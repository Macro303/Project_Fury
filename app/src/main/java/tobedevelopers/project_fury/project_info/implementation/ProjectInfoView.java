package tobedevelopers.project_fury.project_info.implementation;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import tobedevelopers.project_fury.BaseView;
import tobedevelopers.project_fury.R;
import tobedevelopers.project_fury.project_info.ProjectInfoContract;

/**
 * Created by Macro303 on 11/08/2016.
 */
public class ProjectInfoView extends BaseView implements ProjectInfoContract.View, ProjectInfoContract.Navigation{

	//UI References
	private Button mAddUserButton;
	private Button mRemoveMeButton;
	private Button mAddColumnButton;
	private Button mRemoveColumnButton;
	private Button mEditProjectButton;

	private ProjectInfoContract.Presenter presenter;

	@Override
	protected void onCreate( Bundle savedInstanceState ){
		setTitle( getString( R.string.title_activity_projectInfo ) );
		setContentView( R.layout.activity_project_info );
		super.onCreate( savedInstanceState );
		presenter = new ProjectInfoPresenter( this, this );

		//UI References
		mAddUserButton = ( Button ) findViewById( R.id.projectInfoActivity_addUserButton );
		mRemoveMeButton = ( Button ) findViewById( R.id.projectInfoActivity_removeMeButton );
		mAddColumnButton = ( Button ) findViewById( R.id.projectInfoActivity_addColumnButton );
		mRemoveColumnButton = ( Button ) findViewById( R.id.projectInfoActivity_removeColumnButton );
		mEditProjectButton = ( Button ) findViewById( R.id.projectInfoActivity_editProjectButton );

		//Toolbar Config
		getSupportActionBar().setDisplayHomeAsUpEnabled( true );

		//Button Config
		mAddUserButton.setOnClickListener( new View.OnClickListener(){
			@Override
			public void onClick( View view ){
				presenter.userSelectAddUser();
			}
		} );
		mRemoveMeButton.setOnClickListener( new View.OnClickListener(){
			@Override
			public void onClick( View view ){
				presenter.userSelectRemoveMe();
			}
		} );
		mAddColumnButton.setOnClickListener( new View.OnClickListener(){
			@Override
			public void onClick( View view ){
				presenter.userSelectAddColumn();
			}
		} );
		mRemoveColumnButton.setOnClickListener( new View.OnClickListener(){
			@Override
			public void onClick( View view ){
				presenter.userSelectRemoveColumn();
			}
		} );
		mEditProjectButton.setOnClickListener( new View.OnClickListener(){
			@Override
			public void onClick( View view ){
				presenter.userSelectEditProject();
			}
		} );
	}

	@Override
	public boolean onOptionsItemSelected( MenuItem item ){
		switch( item.getItemId() ){
			case android.R.id.home:
				navigateToPrevious();
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
}
