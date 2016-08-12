package tobedevelopers.project_fury.create_project.implementation;

import android.os.Bundle;

import tobedevelopers.project_fury.BaseActivity;
import tobedevelopers.project_fury.R;

/**
 * Created by Macro303 on 11/08/2016.
 */
public class CreateProjectView extends BaseActivity{

	@Override
	protected void onCreate( Bundle savedInstanceState ){
		setTitle( getString( R.string.title_activity_createProject ) );
		setContentView( R.layout.activity_create_project );
		super.onCreate( savedInstanceState );
	}
}
