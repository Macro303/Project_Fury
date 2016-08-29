package tobedevelopers.project_fury.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.io.BufferedReader;
import java.io.DataOutputStream;
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
	private int responseCode;

	public UrlReader( String urlString ){
		this.urlString = urlString;
	}

	public int getResponseCode(){
		return responseCode;
	}

	@NonNull
	private String readAll( Reader rd ) throws IOException{
		StringBuilder sb = new StringBuilder();
		int cp;
		while( ( cp = rd.read() ) != -1 )
			sb.append( ( char ) cp );
		return sb.toString();
	}

	@Nullable
	public String get( String[] headers ){
		HttpURLConnection connection = null;
		try{
			URL url = new URL( urlString );
			connection = ( HttpURLConnection ) url.openConnection();
			connection.setRequestProperty( "Authorization", headers[ 0 ] );
			connection.setRequestMethod( "GET" );
			connection.connect();
			responseCode = connection.getResponseCode();
			if( responseCode == 400 || responseCode == 401 || responseCode == 500 )
				throw new RuntimeException( responseCode + " Error" );
			return readAll( new BufferedReader( new InputStreamReader( connection.getInputStream(), Charset.forName( "UTF-8" ) ) ) );
		}catch( IOException ioe ){
			responseCode = -1;
			return null;
		}catch( RuntimeException re ){
			return null;
		}finally{
			if( connection != null )
				connection.disconnect();
		}
	}

	public String post( String[] headers, String parameters ){
		HttpURLConnection connection = null;
		try{
			URL url = new URL( urlString );
			connection = ( HttpURLConnection ) url.openConnection();
			connection.setRequestProperty( "Authorization", headers[ 0 ] );
			connection.setRequestMethod( "POST" );
			connection.setRequestProperty( "Content-type", "application/x-www-form-urlencoded" );
			connection.setDoOutput( true );
			connection.connect();
			try( DataOutputStream wr = new DataOutputStream( connection.getOutputStream() ) ){
				wr.write( parameters.getBytes( "UTF-8" ) );
			}
			responseCode = connection.getResponseCode();
			if( responseCode == 400 || responseCode == 401 || responseCode == 500 ){
				throw new RuntimeException( responseCode + " Error" );
			}
			return readAll( new BufferedReader( new InputStreamReader( connection.getInputStream(), Charset.forName( "UTF-8" ) ) ) );
		}catch( IOException ioe ){
			responseCode = -1;
			return null;
		}catch( RuntimeException re ){
			return null;
		}finally{
			if( connection != null )
				connection.disconnect();
		}
	}

	public String post( String parameters ){
		return post( new String[]{ "" }, parameters );
	}

	/*private HttpURLConnection startConnection( String requestType ) throws IOException{
		URL url = new URL( urlString );
		HttpURLConnection connection = ( HttpURLConnection ) url.openConnection();
		connection.setRequestMethod( requestType );
		connection.setRequestProperty( "Content-type", "application/x-www-form-urlencoded" );
		connection.setDoInput( true );
		connection.setDoOutput( true );
		connection.connect();
		return connection;
	}

	public String getFromUrl(){
		HttpURLConnection connection = null;
		try{
			URL url = new URL( urlString );
			connection = ( HttpURLConnection ) url.openConnection();
			connection.setRequestMethod( "GET" );
			connection.connect();
			responseCode = connection.getResponseCode();
			if( responseCode == 200 || responseCode == 201 || responseCode == 204 )
				return readAll( new BufferedReader( new InputStreamReader( connection.getInputStream(), Charset.forName( "UTF-8" ) ) ) );
			throw new RuntimeException( "Failed : HTTP error code : " + responseCode );
		}catch( RuntimeException e ){
			return null;
		}catch( IOException ioe ){
			responseCode = -1;
			return null;
		}finally{
			if( connection != null )
				connection.disconnect();
		}
	}

	public void postToUrl( String parameters ){
		HttpURLConnection connection = null;
		try{
			connection = startConnection( "POST" );
			try( DataOutputStream wr = new DataOutputStream( connection.getOutputStream() ) ){
				wr.write( parameters.getBytes( "UTF-8" ) );
			}
			responseCode = connection.getResponseCode();
		}catch( IOException ioe ){
			responseCode = -1;
		}finally{
			if( connection != null )
				connection.disconnect();
		}
	}

	public String putToUrl( String parameters ){
		HttpURLConnection connection = null;
		try{
			connection = startConnection( "PUT" );
			try( DataOutputStream wr = new DataOutputStream( connection.getOutputStream() ) ){
				wr.write( parameters.getBytes( "UTF-8" ) );
			}
			responseCode = connection.getResponseCode();
			if( responseCode == 200 || responseCode == 201 || responseCode == 204 )
				return readAll( new BufferedReader( new InputStreamReader( connection.getInputStream(), Charset.forName( "UTF-8" ) ) ) );
			throw new RuntimeException( "Failed : HTTP error code : " + responseCode );
		}catch( RuntimeException e ){
			return null;
		}catch( IOException ioe ){
			responseCode = -1;
			return null;
		}finally{
			if( connection != null )
				connection.disconnect();
		}
	}

	public String deleteFromUrl(){
		HttpURLConnection connection = null;
		try{
			connection = startConnection( "DELETE" );
			responseCode = connection.getResponseCode();
			if( responseCode == 200 || responseCode == 201 || responseCode == 204 )
				return readAll( new BufferedReader( new InputStreamReader( connection.getInputStream(), Charset.forName( "UTF-8" ) ) ) );
			throw new RuntimeException( "Failed : HTTP error code : " + responseCode );
		}catch( RuntimeException e ){
			return null;
		}catch( IOException ioe ){
			responseCode = -1;
			return null;
		}finally{
			if( connection != null )
				connection.disconnect();
		}
	}*/
}
