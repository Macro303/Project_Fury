package tobedevelopers.project_fury.model;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * Created by Macro303 on 16/08/2016.
 */
public class Model implements ModelContract{

	private static UrlReader urlReader;
	//	private static String apiAddress = "https://young-stream-51673.herokuapp.com/api/";
	private static String apiAddress = "https://fury-test.herokuapp.com/api/";
	private static String token;

	public Model(){
	}

	public String getToken(){
		return token;
	}

	@Override
	public Response createProject( String projectName ){
		return createProject( projectName, "" );
	}

	@Override
	public Response registerUser( String username, String email, String password ){
		return registerUser( username, email, password, false );
	}

	@Override
	public Response registerUser( String username, String password, String email, boolean admin ){
		urlReader = new UrlReader( apiAddress + "register" );
		String parameters = "username=" + username.toLowerCase() + "&password=" + password + "&email=" + email + "&admin=" + admin;
		String response = urlReader.post( parameters );
		if( urlReader.getResponseCode() == -1 )
			return new Response( "No Internet Access" );
		if( urlReader.getResponseCode() == 201 || urlReader.getResponseCode() == 400 )
			return new Gson().fromJson( response, Response.class );
		return new Response( "500 Error" );
	}

	@Override
	public Response login( String username, String password ){
		urlReader = new UrlReader( apiAddress + "login" );
		String parameters = "username=" + username.toLowerCase() + "&password=" + password;
		String response = urlReader.post( parameters );
		if( urlReader.getResponseCode() == -1 )
			return new Response( "No Internet Access" );
		if( urlReader.getResponseCode() == 200 ){
			token = new Gson().fromJson( response, JsonObject.class ).get( "token" ).getAsString();
			return new Response( "Success" );
		}
		return new Response( urlReader.getResponseCode() + " Error" );
	}

	@Override
	public UsernameResponse getAllUsers( String username ){
		return null;
	}

	@Override
	public ProjectResponse getAllProjects(){
		urlReader = new UrlReader( apiAddress + "projects" );
		String[] headers = new String[]{ "Bearer " + token };
		String response = urlReader.get( headers );
		if( urlReader.getResponseCode() == -1 )
			return new ProjectResponse( "No Internet Access" );
		if( urlReader.getResponseCode() == 200 )
			return new ProjectResponse( "Success", new Gson().fromJson( response, Project[].class ) );
		if( urlReader.getResponseCode() == 400 )
			return new ProjectResponse( new Gson().fromJson( response, JsonObject.class ).get( "message" ).getAsString() );
		return new ProjectResponse( "500 Error" );
	}

	@Override
	public ProjectResponse getProject( String projectName ){
		urlReader = new UrlReader( apiAddress + "projects/" + projectName );
		String[] headers = new String[]{ "Bearer " + token };
		String response = urlReader.get( headers );
		if( urlReader.getResponseCode() == -1 )
			return new ProjectResponse( "No Internet Access" );
		if( urlReader.getResponseCode() == 200 )
			return new ProjectResponse( "Success", new Gson().fromJson( "[" + response + "]", Project[].class ) );
		if( urlReader.getResponseCode() == 400 )
			return new ProjectResponse( new Gson().fromJson( response, JsonObject.class ).get( "message" ).getAsString() );
		return new ProjectResponse( "500 Error" );
	}

	@Override
	public Response createProject( String projectName, String projectDescription ){
		urlReader = new UrlReader( apiAddress + "projects" );
		String[] headers = new String[]{ "Bearer " + token };
		String parameters = "name=" + projectName.toLowerCase() + "&description=" + projectDescription;
		String response = urlReader.post( headers, parameters );
		if( urlReader.getResponseCode() == -1 )
			return new Response( "No Internet Access" );
		if( urlReader.getResponseCode() == 201 || urlReader.getResponseCode() == 400 )
			return new Gson().fromJson( response, Response.class );
		return new Response( "500 Error" );
	}
/*@Override
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
	}*/

}
