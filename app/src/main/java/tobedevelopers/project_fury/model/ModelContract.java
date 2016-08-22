package tobedevelopers.project_fury.model;

/**
 * Created by Macro303 on 19/08/2016.
 */
public interface ModelContract{

	Response createUser( String username, String password, String email );

	Response createUser( String username, String password, String email, boolean admin );

	Response getUser( String username );
}
