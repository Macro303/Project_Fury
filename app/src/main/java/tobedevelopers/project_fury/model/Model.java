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

	@Override
	public Response createUser( String username, String password, String email ){
		return createUser( username, password, email, false );
	}

	@Override
	public Response createUser( String username, String password, String email, boolean admin ){
		urlReader = new UrlReader( apiAddress + "users" );
		String parameters = "username=" + username + "&password=" + password + "&email=" + email + "&admin=" + admin;
		urlReader.postToUrl( parameters );
		if( urlReader.getResponseCode() == 200 || urlReader.getResponseCode() == 201 || urlReader.getResponseCode() == 204 )
			return new Response( "Passed", "Passed" );
		if( urlReader.getResponseCode() == -1 )
			return new Response( "No Internet Access", "Error" );
		return new Response( "500", "Error" );
	}

	@Override
	public Response getUser( String username ){
		urlReader = new UrlReader( apiAddress + "users/" + username );
		String response = urlReader.getFromUrl();
		if( urlReader.getResponseCode() == 200 || urlReader.getResponseCode() == 201 || urlReader.getResponseCode() == 204 )
			return new Response( "Passed", "Passed", new Gson().fromJson( response, User[].class ) );
		if( urlReader.getResponseCode() == -1 )
			return new Response( "No Internet Access", "Error" );
		return new Response( "500", "Error" );
	}

	public Response getAllUsers(){
		urlReader = new UrlReader( apiAddress + "users" );
		String response = urlReader.getFromUrl();
		if( urlReader.getResponseCode() == 200 || urlReader.getResponseCode() == 201 || urlReader.getResponseCode() == 204 )
			return new Response( "Passed", "Passed", new Gson().fromJson( response, User[].class ) );
		if( urlReader.getResponseCode() == -1 )
			return new Response( "No Internet Access", "Error" );
		return new Response( "500", "Error" );
	}

	public Response updateUser( String username, String password, String email ){
		urlReader = new UrlReader( apiAddress + "users/" + username );
		String parameters = "password=" + password + "&email=" + email;
		urlReader.putToUrl( parameters );
		if( urlReader.getResponseCode() == 200 || urlReader.getResponseCode() == 201 || urlReader.getResponseCode() == 204 )
			return getUser( username );
		if( urlReader.getResponseCode() == -1 )
			return new Response( "No Internet Access", "Error" );
		return new Response( "500", "Error" );
	}

	public Response deleteUser( String username ){
		urlReader = new UrlReader( apiAddress + "users/" + username );
		urlReader.deleteFromUrl();
		if( urlReader.getResponseCode() == 200 || urlReader.getResponseCode() == 201 || urlReader.getResponseCode() == 204 )
			return new Response( "Passed", "Passed" );
		if( urlReader.getResponseCode() == -1 )
			return new Response( "No Internet Access", "Error" );
		return new Response( "500", "Error" );
	}

}
