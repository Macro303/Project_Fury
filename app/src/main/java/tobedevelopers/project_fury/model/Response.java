package tobedevelopers.project_fury.model;

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
		this.error = error;
		this.reason = reason;
	}

	public Response( String error ){
		this.error = error;
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
		return "Error: " + error + "\nReason: " + reason;
	}
}