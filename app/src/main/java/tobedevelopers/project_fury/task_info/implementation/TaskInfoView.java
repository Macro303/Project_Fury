package tobedevelopers.project_fury.task_info.implementation;

import android.os.Bundle;

import tobedevelopers.project_fury.BaseActivity;
import tobedevelopers.project_fury.R;

/**
 * Created by Macro303 on 11/08/2016.
 */
public class TaskInfoView extends BaseActivity{

	@Override
	protected void onCreate( Bundle savedInstanceState ){
		setTitle( getString( R.string.title_activity_taskInfo ) );
		setContentView( R.layout.activity_task_info );
		super.onCreate( savedInstanceState );
	}
}
