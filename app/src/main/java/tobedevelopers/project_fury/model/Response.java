package tobedevelopers.project_fury.model;

import java.util.Arrays;

/**
 * Created by Macro303 on 16/08/2016.
 */
public class Response{

	private String error;
	@Deprecated
	private String reason;
	private User[] results;

	@Deprecated
	public Response( String error, String reason ){
		this( error, reason, new User[]{} );
	}

	@Deprecated
	public Response( String error, String reason, User[] results ){
		this.error = error;
		this.reason = reason;
		this.results = results;
	}

	public Response( String error ){
		this( error, new User[]{} );
	}

	public Response( String error, User[] results ){
		this.error = error;
		this.results = results;
	}

	public String getError(){
		return error;
	}

	@Deprecated
	public String getReason(){
		return reason;
	}

	public User[] getResults(){
		return results;
	}

	@Override
	public String toString(){
		return "Error: " + error + "\nReason: " + reason + "\nResults: " + Arrays.toString( results );
	}
}