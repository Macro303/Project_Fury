package tobedevelopers.project_fury.model;

import com.google.gson.Gson;

/**
 * Created by Macro303 on 16/08/2016.
 */
public class Model{

	private static UrlReader urlReader;
	private static String apiAddress = "https://young-stream-51673.herokuapp.com/api/";

	public Model(){
	}

	public Response get(){
		urlReader = new UrlReader( apiAddress );
		return new Gson().fromJson( urlReader.getFromUrl(), Response.class );
	}

	public Response getUser(){
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
	}

}
