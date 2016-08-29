package tobedevelopers.project_fury.model;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;

/**
 * Created by Macro303 on 16/08/2016.
 */
public class Model implements ModelContract{

	private static UrlReader urlReader;
	private static String apiAddress = "https://young-stream-51673.herokuapp.com/api/";
	//	private static String apiAddress = "https://fury-test.herokuapp.com/api/";
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
	public Response registerUser( String username, String password, String email ){
		return registerUser( username, password, email, false );
	}

	@Override
	public Response registerUser( String username, String password, String email, boolean admin ){
		urlReader = new UrlReader( apiAddress + "register" );
		String parameters = "username=" + username.toLowerCase() + "&password=" + password + "&email=" + email + "&admin=" + admin;
		String response = urlReader.post( parameters );
		if( urlReader.getResponseCode() == -1 )
			return new Response( "No Internet Access" );
		if( urlReader.getResponseCode() == 200 || urlReader.getResponseCode() == 201 )
			return new Gson().fromJson( response, Response.class );
		return new Response( urlReader.getResponseCode() + " Error" );
	}

	@Override
	public Response login( String username, String password ){
		urlReader = new UrlReader( apiAddress + "login" );
		String parameters = "username=" + username.toLowerCase() + "&password=" + password;
		String response = urlReader.post( parameters );
		if( urlReader.getResponseCode() == -1 )
			return new Response( "No Internet Access" );
		if( urlReader.getResponseCode() == 200 || urlReader.getResponseCode() == 201 ){
			token = new Gson().fromJson( response, JsonObject.class ).get( "token" ).getAsString();
			return new Response( "Success" );
		}
		return new Response( urlReader.getResponseCode() + " Error" );
	}

	@Override
	public UsernameResponse getAllUsers(){
		urlReader = new UrlReader( apiAddress + "users" );
		String[] headers = new String[]{ "Bearer " + token };
		String response = urlReader.get( headers );
		if( urlReader.getResponseCode() == -1 )
			return new UsernameResponse( "No Internet Access" );
		if( urlReader.getResponseCode() == 200 || urlReader.getResponseCode() == 201 ){
			ArrayList< String > temp = new ArrayList<>();
			for( JsonElement object : new Gson().fromJson( response, JsonArray.class ) )
				temp.add( ( ( JsonObject ) object ).get( "username" ).getAsString() );
			temp.trimToSize();
			return new UsernameResponse( "Success", temp.toArray( new String[ temp.size() ] ) );
		}
		return new UsernameResponse( urlReader.getResponseCode() + " Error" );
	}

	@Override
	public ProjectResponse getAllProjects(){
		urlReader = new UrlReader( apiAddress + "projects" );
		String[] headers = new String[]{ "Bearer " + token };
		String response = urlReader.get( headers );
		if( urlReader.getResponseCode() == -1 )
			return new ProjectResponse( "No Internet Access" );
		if( urlReader.getResponseCode() == 200 || urlReader.getResponseCode() == 201 )
			return new ProjectResponse( "Success", new Gson().fromJson( response, Project[].class ) );
		return new ProjectResponse( urlReader.getResponseCode() + " Error" );
	}

	@Override
	public ProjectResponse getProject( String projectName ){
		urlReader = new UrlReader( apiAddress + "projects/" + projectName.toLowerCase() );
		String[] headers = new String[]{ "Bearer " + token };
		String response = urlReader.get( headers );
		if( urlReader.getResponseCode() == -1 )
			return new ProjectResponse( "No Internet Access" );
		if( urlReader.getResponseCode() == 200 || urlReader.getResponseCode() == 201 )
			return new ProjectResponse( "Success", new Gson().fromJson( response, Project[].class ) );
		return new ProjectResponse( urlReader.getResponseCode() + " Error" );
	}

	@Override
	public Response createProject( String projectName, String projectDescription ){
		urlReader = new UrlReader( apiAddress + "projects" );
		String[] headers = new String[]{ "Bearer " + token };
		String parameters = "name=" + projectName.toLowerCase() + "&description=" + projectDescription;
		String response = urlReader.post( headers, parameters );
		if( urlReader.getResponseCode() == -1 )
			return new Response( "No Internet Access" );
		if( urlReader.getResponseCode() == 200 || urlReader.getResponseCode() == 201 )
			return new Gson().fromJson( response, Response.class );
		return new Response( urlReader.getResponseCode() + " Error" );
	}

	@Override
	public TaskResponse getTask( String projectName, String taskID ){
		return null;
	}

	@Override
	public TaskResponse createTask( String projectName, String taskName, String taskDescription ){
		return null;
	}

}
