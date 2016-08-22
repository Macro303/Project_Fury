package tobedevelopers.project_fury;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by Macro303 on 10/08/2016.
 */
public class BaseView extends AppCompatActivity{

	@Override
	protected void onCreate( Bundle savedInstanceState ){
		super.onCreate( savedInstanceState );
		Log.i( getString( R.string.app_name ), getTitle() + " Created" );
	}

	@Override
	protected void onDestroy(){
		super.onDestroy();
		Log.i( getString( R.string.app_name ), getTitle() + " Destroyed" );
	}

	@Override
	protected void onStart(){
		super.onStart();
		Log.i( getString( R.string.app_name ), getTitle() + " Started" );
	}

	@Override
	protected void onStop(){
		super.onStop();
		Log.i( getString( R.string.app_name ), getTitle() + " Stopped" );
	}

	@Override
	protected void onPause(){
		super.onPause();
		Log.i( getString( R.string.app_name ), getTitle() + " Paused" );
	}

	@Override
	protected void onResume(){
		super.onResume();
		Log.i( getString( R.string.app_name ), getTitle() + " Resumed" );
	}
}