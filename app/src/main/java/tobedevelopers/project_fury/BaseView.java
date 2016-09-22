package tobedevelopers.project_fury;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by Macro303 on 10/08/2016.
 */
public abstract class BaseView extends AppCompatActivity implements BaseContract.View{
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

	@Override
	protected void onRestart(){
		super.onRestart();
		finish();
		startActivity( getIntent() );
		Log.i( getString( R.string.app_name ), getTitle() + " Restarted" );
	}

	public void noInternetAccessValidation(){
		Log.i( getString( R.string.app_name ), getString( R.string.error_noInternetAccess ) );
	}

	public void defaultErrorMessage(){
		Log.i( getString( R.string.app_name ), getString( R.string.error_defaultError ) );
	}
}