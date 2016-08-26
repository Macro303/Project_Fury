package tobedevelopers.project_fury.model;

/**
 * Created by Macro303 on 19/08/2016.
 */
public interface ModelContract{

	Response registerUser( String username, String email, String password );

	Response registerUser( String username, String email, String password, boolean admin );

	Response login( String username, String password );

	UsernameResponse getAllUsers( String username );

	ProjectResponse getAllProjects();

	ProjectResponse getProject( String projectName );

	Response createProject( String projectName );

	Response createProject( String projectName, String projectDescription );
}
