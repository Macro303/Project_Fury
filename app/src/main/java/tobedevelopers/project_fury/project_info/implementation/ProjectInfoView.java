package tobedevelopers.project_fury.project_info.implementation;

import android.os.Bundle;

import tobedevelopers.project_fury.BaseActivity;
import tobedevelopers.project_fury.R;

/**
 * Created by Macro303 on 11/08/2016.
 */
public class ProjectInfoView extends BaseActivity{

	@Override
	protected void onCreate( Bundle savedInstanceState ){
		setTitle( getString( R.string.title_projectInfo ) );
		setContentView( R.layout.activity_project_info );
		super.onCreate( savedInstanceState );
	}
}
