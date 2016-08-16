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

	public String get( String callMessage ){
		urlReader = new UrlReader( apiAddress + "users" );
		Response response = new Gson().fromJson( urlReader.getFromUrl(), Response.class );
		return response.getMessage();
	}

}
