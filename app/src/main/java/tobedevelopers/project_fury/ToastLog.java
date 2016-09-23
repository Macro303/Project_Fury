package tobedevelopers.project_fury;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Macro303 on 23/08/2016.
 */
public class ToastLog{

	private static void makeToast( Context context, CharSequence text, int duration ){
		Toast.makeText( context, text, duration ).show();
	}

	/**
	 * This is just for messages just coz you can show messages. This is about showing absolutely everything.
	 */
	public static void makeVerbose( Context context, String tag, CharSequence text, int duration ){
		makeToast( context, text, duration );
		Log.v( tag, text.toString() );
	}

	/**
	 * This is for any messages that need to be displayed, calls to this function will be removed in final release.
	 */
	public static void makeDebug( Context context, String tag, CharSequence text, int duration ){
		makeToast( context, text, duration );
		Log.d( tag, text.toString() );
	}

	/**
	 * This is for any information the user needs to see, but isn't necessarily an error.
	 */
	public static void makeInfo( Context context, String tag, CharSequence text, int duration ){
		makeToast( context, text, duration );
		Log.i( tag, text.toString() );
	}

	/**
	 * This is for when an error occurs that might occur from time to time
	 */
	public static void makeWarn( Context context, String tag, CharSequence text, int duration ){
		makeToast( context, text, duration );
		Log.w( tag, text.toString() );
	}

	/**
	 * This is for when an error occurs that doesn't break the system, but still shouldn't happen
	 */
	public static void makeError( Context context, String tag, CharSequence text, int duration ){
		makeToast( context, text, duration );
		Log.e( tag, text.toString() );
	}

	/**
	 * This is for when an error occurs that crashes the system, if this occurs the world is ending
	 */
	public static void makeAssert( Context context, String tag, CharSequence text, int duration ){
		makeToast( context, text, duration );
		Log.wtf( tag, text.toString() );
	}
}
