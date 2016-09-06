package tobedevelopers.project_fury;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Macro303 on 23/08/2016.
 */
public class ToastLog{

	/**
	 * This is just for messages just coz you can show messages. This is about showing absolutely everything.
	 */
	public static void makeVerbose( Context context, CharSequence text, int duration ){
		Log.v( context.getString( R.string.app_name ), text.toString() );
//		Toast.makeText( context, text, duration ).show();
	}

	/**
	 * This is for any messages that need to be displayed, calls to this function will be removed in final release.
	 */
	public static void makeDebug( Context context, CharSequence text, int duration ){
		Log.d( context.getString( R.string.app_name ), text.toString() );
//		Toast.makeText( context, text, duration ).show();
	}

	/**
	 * This is for any information the user needs to see, but isn't necessarily an error.
	 */
	public static void makeInfo( Context context, CharSequence text, int duration ){
		Log.i( context.getString( R.string.app_name ), text.toString() );
		Toast.makeText( context, text, duration ).show();
	}

	/**
	 * This is for when an error occurs that can occasionally happen but in a perfect scenario shouldn't happen
	 */
	public static void makeWarn( Context context, CharSequence text, int duration ){
		Log.w( context.getString( R.string.app_name ), text.toString() );
		Toast.makeText( context, text, duration ).show();
	}

	/**
	 * This is for when an error occurs that doesn't break the system, but still shouldn't happen
	 */
	public static void makeError( Context context, CharSequence text, int duration ){
		Log.e( context.getString( R.string.app_name ), text.toString() );
		Toast.makeText( context, text, duration ).show();
	}

	/**
	 * This is for when an error occurs that crashes the system, if this occurs the world is ending
	 */
	public static void makeAssert( Context context, CharSequence text, int duration ){
		Log.wtf( context.getString( R.string.app_name ), text.toString() );
		Toast.makeText( context, text, duration ).show();
	}
}
