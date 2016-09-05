package tobedevelopers.project_fury.model;

/**
 * Created by Macro303 on 19/08/2016.
 */
public interface ModelContract{

	Response registerUser( String username, String password, String email, boolean admin );

	Response login( String username, String password );

	UsernameResponse getAllUsers();

	ProjectResponse getAllProjects();

	ProjectResponse getProject( String projectID );

	Response createProject( String projectName, String projectDescription );

	Response updateProject( String projectID, String projectName, String projectDescription );

	Response deleteProject( String projectID );

	TaskResponse getTask( String projectID, String taskID );

	TaskResponse getAllUserTasks();

	TaskResponse getAllProjectTasks( String projectID );

	Response createTask( String projectID, String taskName, String taskDescription, String assignee );

	Response updateTask( String projectID, String taskID, String taskName, String taskDescription, String taskAssignee, String priority );
}
