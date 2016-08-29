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
	public static Toast makeVerbose( Context context, CharSequence text, int duration ){
		Log.v( context.getString( R.string.app_name ), text.toString() );
		return Toast.makeText( context, text, duration );
	}

	/**
	 * This is for any messages that need to be displayed, calls to this function will be removed in final release.
	 */
	public static Toast makeDebug( Context context, CharSequence text, int duration ){
		Log.d( context.getString( R.string.app_name ), text.toString() );
		return Toast.makeText( context, text, duration );
	}

	/**
	 * This is for any information the user needs to see, but isn't necessarily an error.
	 */
	public static Toast makeInfo( Context context, CharSequence text, int duration ){
		Log.i( context.getString( R.string.app_name ), text.toString() );
		return Toast.makeText( context, text, duration );
	}

	/**
	 * This is for when an error occurs that can occasionally happen but in a perfect scenario shouldn't happen
	 */
	public static Toast makeWarn( Context context, CharSequence text, int duration ){
		Log.w( context.getString( R.string.app_name ), text.toString() );
		return Toast.makeText( context, text, duration );
	}

	/**
	 * This is for when an error occurs that doesn't break the system, but still shouldn't happen
	 */
	public static Toast makeError( Context context, CharSequence text, int duration ){
		Log.e( context.getString( R.string.app_name ), text.toString() );
		return Toast.makeText( context, text, duration );
	}

	/**
	 * This is for when an error occurs that crashes the system, if this occurs the world is ending
	 */
	public static Toast makeAssert( Context context, CharSequence text, int duration ){
		Log.wtf( context.getString( R.string.app_name ), text.toString() );
		return Toast.makeText( context, text, duration );
	}
}
