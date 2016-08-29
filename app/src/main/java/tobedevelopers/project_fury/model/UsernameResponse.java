package tobedevelopers.project_fury.model;

import java.util.Arrays;

/**
 * Created by Macro303 on 26/08/2016.
 */
public class UsernameResponse extends Response{

	private String[] usernames;

	public UsernameResponse( String message ){
		this( message, new String[]{} );
	}

	public UsernameResponse( String message, String[] usernames ){
		super( message );
		this.usernames = usernames;
	}

	public String[] getUsernames(){
		return usernames;
	}

	@Override
	public String toString(){
		return "UsernameResponse{" +
			       "message='" + message + '\'' +
			       ", usernames=" + Arrays.toString( usernames ) +
			       '}';
	}
}
