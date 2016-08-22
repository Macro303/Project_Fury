package tobedevelopers.project_fury.model;

import com.google.gson.Gson;

/**
 * Created by Macro303 on 16/08/2016.
 */
public class Model implements ModelContract{

	private static UrlReader urlReader;
	private static String apiAddress = "https://young-stream-51673.herokuapp.com/api/";

	public Model(){
	}

	private Response convertToJson( String response ) throws Exception{
		return new Gson().fromJson( "{ \"error\": \"Passed\", \"reason\": \"null\", \"results\": " + response + "}", Response.class );
	}

	@Override
	public Response createUser( String username, String password, String email ){
		urlReader = new UrlReader( apiAddress + "users" );
		String parameters = "username=" + username + "&password=" + password + "&email=" + email;
		String response = urlReader.postToUrl( parameters );
		if( urlReader.getResponseCode() == 201 || urlReader.getResponseCode() == 204 )
			response = "[]";
		System.out.println( "Response: " + response );
		try{
			return convertToJson( response );
		}catch( Exception e ){
			return new Response( "Http 500 Error" );
		}
	}

	@Override
	public Response createUser( String username, String password, String email, boolean admin ){
		urlReader = new UrlReader( apiAddress + "users" );
		String parameters = "username=" + username + "&password=" + password + "&email=" + email + "&admin=" + admin;
		String response = urlReader.postToUrl( parameters );
		if( urlReader.getResponseCode() == 201 || urlReader.getResponseCode() == 204 )
			response = "[]";
		System.out.println( "Response: " + response );
		try{
			return convertToJson( response );
		}catch( Exception e ){
			return new Response( "Http 500 Error" );
		}
	}

	public Response getAllUsers(){
		urlReader = new UrlReader( apiAddress + "users" );
		String response = urlReader.getFromUrl();
		try{
			return convertToJson( response );
		}catch( Exception e ){
			return new Response( "Http 500 Error" );
		}
	}

	public Response getUser( String username ){
		urlReader = new UrlReader( apiAddress + "users/" + username );
		String response = urlReader.getFromUrl();
		try{
			return convertToJson( response );
		}catch( Exception e ){
			return new Response( "Http 500 Error" );
		}
	}

	public Response updateUser( String username, String password, String email ){
		urlReader = new UrlReader( apiAddress + "users/" + username );
		String parameters = "username=" + username + "&password=" + password + "&email=" + email;
		urlReader.putToUrl( parameters );
		if( urlReader.getResponseCode() == 204 )
			return getUser( username );
		return new Response( "Http 500 Error" );
	}

	public Response deleteUser( String username ){
		urlReader = new UrlReader( apiAddress + "users/" + username );
		urlReader.deleteFromUrl();
		if( urlReader.getResponseCode() == 204 )
			return new Response( "Passed" );
		return new Response( "Http 500 Error" );
	}

/*	public Response getAllUsers(){
		urlReader = new UrlReader( apiAddress + "users" );
		return new Gson().fromJson( urlReader.getFromUrl(), Response.class );
	}

	public User postUser( String[] values ){
		urlReader = new UrlReader( apiAddress + "users" );
		String parameters = "firstName=" + values[ 0 ] + "&lastName=" + values[ 1 ];
		return new Gson().fromJson( urlReader.postToUrl( parameters ), User.class );
	}

	public User getUserId( int id ){
		urlReader = new UrlReader( apiAddress + "users/" + id );
		return new Gson().fromJson( urlReader.getFromUrl(), User.class );
	}

	public Response putUserId( int id ){
		urlReader = new UrlReader( apiAddress + "users/" + id );
		return new Gson().fromJson( urlReader.putToUrl(), Response.class );
	}

	public Response deleteUserId( int id ){
		urlReader = new UrlReader( apiAddress + "users/" + id );
		return new Gson().fromJson( urlReader.deleteFromUrl(), Response.class );
	}*/

}
