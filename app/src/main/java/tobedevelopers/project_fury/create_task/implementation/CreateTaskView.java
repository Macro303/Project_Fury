package tobedevelopers.project_fury.create_task.implementation;

import android.os.Bundle;

import tobedevelopers.project_fury.BaseView;
import tobedevelopers.project_fury.R;

/**
 * Created by Macro303 on 12/08/2016.
 */
public class CreateTaskView extends BaseView{

	@Override
	protected void onCreate( Bundle savedInstanceState ){
		setTitle( getString( R.string.title_activity_createTask ) );
		setContentView( R.layout.activity_create_task );
		super.onCreate( savedInstanceState );
	}
}
