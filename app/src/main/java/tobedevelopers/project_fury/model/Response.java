package tobedevelopers.project_fury.model;

/**
 * Created by Macro303 on 16/08/2016.
 */
public class Response{

	private String message;

	public Response( String message ){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	@Override
	public String toString(){
		return "Message: " + message;
	}
}