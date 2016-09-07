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
	private static Project selectedProject;
	private static Task selectedTask;
	private static Column selectedColumn;

	public Model(){
	}

	public static Project getSelectedProject(){
		return selectedProject;
	}

	public static void setSelectedProject( Project _selectedProject ){
		selectedProject = _selectedProject;
	}

	public static Task getSelectedTask(){
		return selectedTask;
	}

	public static void setSelectedTask( Task _selectedTask ){
		selectedTask = _selectedTask;
	}

	public static Column getSelectedColumn(){
		return selectedColumn;
	}

	public static void setSelectedColumn( Column _selectedColumn ){
		selectedColumn = _selectedColumn;
	}

	public String getToken(){
		return token;
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

	/*
	Project Readers
	 */

	@Override
	public Response createProject( String projectName, String projectDescription ){
		urlReader = new UrlReader( apiAddress + "projects" );
		String[] headers = new String[]{ "Bearer " + token };
		String parameters = "name=" + projectName + "&description=" + projectDescription;
		String response = urlReader.post( headers, parameters );
		if( urlReader.getResponseCode() == -1 )
			return new Response( "No Internet Access" );
		if( urlReader.getResponseCode() == 200 || urlReader.getResponseCode() == 201 )
			return new Gson().fromJson( response, Response.class );
		return new Response( urlReader.getResponseCode() + " Error" );
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
	public ProjectResponse getProject( String projectID ){
		urlReader = new UrlReader( apiAddress + "projects/" + projectID );
		String[] headers = new String[]{ "Bearer " + token };
		String response = urlReader.get( headers );
		if( urlReader.getResponseCode() == -1 )
			return new ProjectResponse( "No Internet Access" );
		if( urlReader.getResponseCode() == 200 || urlReader.getResponseCode() == 201 )
			return new ProjectResponse( "Success", new Gson().fromJson( response, Project[].class ) );
		return new ProjectResponse( urlReader.getResponseCode() + " Error" );
	}

	@Override
	public Response updateProject( String projectID, String projectName, String projectDescription ){
		urlReader = new UrlReader( apiAddress + "projects/" + projectID );
		String[] headers = new String[]{ "Bearer " + token };
		String parameters = "name=" + projectName + "&description=" + projectDescription;
		String response = urlReader.put( headers, parameters );
		if( urlReader.getResponseCode() == -1 )
			return new Response( "No Internet Access" );
		if( urlReader.getResponseCode() == 200 || urlReader.getResponseCode() == 201 )
			return new Gson().fromJson( response, Response.class );
		return new Response( urlReader.getResponseCode() + " Error" );
	}

	@Override
	public Response deleteProject( String projectID ){
		urlReader = new UrlReader( apiAddress + "projects/" + projectID );
		String[] headers = new String[]{ "Bearer " + token };
		String response = urlReader.delete( headers );
		if( urlReader.getResponseCode() == -1 )
			return new Response( "No Internet Access" );
		if( urlReader.getResponseCode() == 200 || urlReader.getResponseCode() == 201 )
			return new Gson().fromJson( response, Response.class );
		return new Response( urlReader.getResponseCode() + " Error" );
	}

	/*
	Task Readers
	 */

	@Override
	public Response createTask( String projectID, String taskName, String taskDescription, String assignee ){
		urlReader = new UrlReader( apiAddress + "projects/" + projectID + "/tasks" );
		String[] headers = new String[]{ "Bearer " + token };
		String parameters = "name=" + taskName + "&description=" + taskDescription + "&user=" + assignee.toLowerCase();
		String response = urlReader.post( headers, parameters );
		if( urlReader.getResponseCode() == -1 )
			return new Response( "No Internet Access" );
		if( urlReader.getResponseCode() == 200 || urlReader.getResponseCode() == 201 )
			return new Gson().fromJson( response, Response.class );
		return new Response( urlReader.getResponseCode() + " Error" );
	}

	@Override
	public TaskResponse getTask( String projectID, String taskID ){
		urlReader = new UrlReader( apiAddress + "projects/" + projectID + "/tasks/" + taskID );
		String[] headers = new String[]{ "Bearer " + token };
		String response = urlReader.get( headers );
		if( urlReader.getResponseCode() == -1 )
			return new TaskResponse( "No Internet Access" );
		if( urlReader.getResponseCode() == 200 || urlReader.getResponseCode() == 201 )
			return new TaskResponse( "Success", new Gson().fromJson( response, Task[].class ) );
		return new TaskResponse( urlReader.getResponseCode() + " Error" );
	}

	@Override
	public TaskResponse getAllUserTasks(){
		urlReader = new UrlReader( apiAddress + "users/tasks" );
		String[] headers = new String[]{ "Bearer " + token };
		String response = urlReader.get( headers );
		if( urlReader.getResponseCode() == -1 )
			return new TaskResponse( "No Internet Access" );
		if( urlReader.getResponseCode() == 200 || urlReader.getResponseCode() == 201 )
			return new TaskResponse( "Success", new Gson().fromJson( response, Task[].class ) );
		return new TaskResponse( urlReader.getResponseCode() + " Error" );
	}

	@Override
	public TaskResponse getAllProjectTasks( String projectID ){
		urlReader = new UrlReader( apiAddress + "projects/" + projectID + "/tasks" );
		String[] headers = new String[]{ "Bearer " + token };
		String response = urlReader.get( headers );
		if( urlReader.getResponseCode() == -1 )
			return new TaskResponse( "No Internet Access" );
		if( urlReader.getResponseCode() == 200 || urlReader.getResponseCode() == 201 )
			return new TaskResponse( "Success", new Gson().fromJson( response, Task[].class ) );
		return new TaskResponse( urlReader.getResponseCode() + " Error" );
	}

	@Override
	public Response updateTask( String projectID, String taskID, String taskName, String taskDescription, String taskAssignee, String taskPriority ){
		urlReader = new UrlReader( apiAddress + "projects/" + projectID + "/tasks/" + taskID );
		String[] headers = new String[]{ "Bearer " + token };
		String parameters = "name=" + taskName + "&description=" + taskDescription + "&user=" + taskAssignee + "&priority=" + taskPriority;
		String response = urlReader.put( headers, parameters );
		if( urlReader.getResponseCode() == -1 )
			return new Response( "No Internet Access" );
		if( urlReader.getResponseCode() == 200 || urlReader.getResponseCode() == 201 )
			return new Gson().fromJson( response, Response.class );
		return new Response( urlReader.getResponseCode() + " Error" );
	}

	@Override
	public Response deleteTask( String projectID, String taskID ){
		urlReader = new UrlReader( apiAddress + "projects/" + projectID + "/tasks/" + taskID );
		String[] headers = new String[]{ "Bearer " + token };
		String response = urlReader.delete( headers );
		if( urlReader.getResponseCode() == -1 )
			return new Response( "No Internet Access" );
		if( urlReader.getResponseCode() == 200 || urlReader.getResponseCode() == 201 )
			return new Gson().fromJson( response, Response.class );
		return new Response( urlReader.getResponseCode() + " Error" );
	}

	/*
	Column Readers
	 */

	@Override
	public Response createColumn( String projectID, String columnName ){
		urlReader = new UrlReader( apiAddress + "projects/" + projectID + "/columns" );
		String[] headers = new String[]{ "Bearer " + token };
		String parameters = "name=" + columnName;
		String response = urlReader.post( headers, parameters );
		if( urlReader.getResponseCode() == -1 )
			return new Response( "No Internet Access" );
		if( urlReader.getResponseCode() == 200 || urlReader.getResponseCode() == 201 )
			return new Gson().fromJson( response, Response.class );
		return new Response( urlReader.getResponseCode() + " Error" );
	}

	@Override
	public ColumnResponse getAllProjectColumns( String projectID ){
		urlReader = new UrlReader( apiAddress + "projects/" + projectID + "/columns" );
		String[] headers = new String[]{ "Bearer " + token };
		String response = urlReader.get( headers );
		if( urlReader.getResponseCode() == -1 )
			return new ColumnResponse( "No Internet Access" );
		if( urlReader.getResponseCode() == 200 || urlReader.getResponseCode() == 201 )
			return new ColumnResponse( "Success", new Gson().fromJson( response, Column[].class ) );
		return new ColumnResponse( urlReader.getResponseCode() + " Error" );
	}

	@Override
	public ColumnResponse getColumn( String projectID, String columnID ){
		urlReader = new UrlReader( apiAddress + "projects/" + projectID + "/columns/" + columnID );
		String[] headers = new String[]{ "Bearer " + token };
		String response = urlReader.get( headers );
		if( urlReader.getResponseCode() == -1 )
			return new ColumnResponse( "No Internet Access" );
		if( urlReader.getResponseCode() == 200 || urlReader.getResponseCode() == 201 )
			return new ColumnResponse( "Success", new Gson().fromJson( response, Column[].class ) );
		return new ColumnResponse( urlReader.getResponseCode() + " Error" );
	}

	@Override
	public Response updateColumn( String projectID, String columnID, String columnName ){
		urlReader = new UrlReader( apiAddress + "projects/" + projectID + "/columns/" + columnID );
		String[] headers = new String[]{ "Bearer " + token };
		String paramters = "name=" + columnName;
		String response = urlReader.put( headers, paramters );
		if( urlReader.getResponseCode() == -1 )
			return new Response( "No Internet Access" );
		if( urlReader.getResponseCode() == 200 || urlReader.getResponseCode() == 201 )
			return new Gson().fromJson( response, Response.class );
		return new Response( urlReader.getResponseCode() + " Error" );
	}

	@Override
	public Response deleteColumn( String projectID, String columnID ){
		urlReader = new UrlReader( apiAddress + "projects/" + projectID + "/columns/" + columnID );
		String[] headers = new String[]{ "Bearer " + token };
		String response = urlReader.delete( headers );
		if( urlReader.getResponseCode() == -1 )
			return new Response( "No Internet Access" );
		if( urlReader.getResponseCode() == 200 || urlReader.getResponseCode() == 201 )
			return new Gson().fromJson( response, Response.class );
		return new Response( urlReader.getResponseCode() + " Error" );
	}
}
