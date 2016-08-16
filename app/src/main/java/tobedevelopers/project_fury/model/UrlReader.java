package tobedevelopers.project_fury.model;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * Created by Macro303 on 16/08/2016.
 */
public class UrlReader{

	private final String urlString;

	public UrlReader( String urlString ){
		this.urlString = urlString;
	}

	private String readAll( Reader rd ) throws IOException{
		StringBuilder sb = new StringBuilder();
		int cp;
		while( ( cp = rd.read() ) != -1 )
			sb.append( ( char ) cp );
		return sb.toString();
	}

	public String getFromUrl(){
		HttpURLConnection connection = null;
		try{
			URL url = new URL( urlString );
			connection = ( HttpURLConnection ) url.openConnection();
			connection.setRequestMethod( "GET" );
			connection.connect();
			BufferedReader br = new BufferedReader( new InputStreamReader( connection.getInputStream(), Charset.forName( "UTF-8" ) ) );
			String response = readAll( br );
			connection.disconnect();
			return response;
		}catch( IOException ioe ){
			Log.e( "Project Fury", ioe.toString() );
			return null;
		}finally{
			connection.disconnect();
		}
	}
}
