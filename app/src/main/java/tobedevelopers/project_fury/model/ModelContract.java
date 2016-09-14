package tobedevelopers.project_fury.model;

/**
 * Created by Macro303 on 19/08/2016.
 */
public interface ModelContract{

	Response registerUser( String username, String password, String email, boolean admin );

	Response login( String username, String password );

	UsernameResponse getAllUsers();

	Response createProject( String projectName, String projectDescription );

	ProjectResponse getAllProjects();

	ProjectResponse getProject( String projectID );

	Response updateProject( String projectID, String projectName, String projectDescription );

	Response deleteProject( String projectID );

	Response createTask( String projectID, String taskName, String taskDescription, String assignee );

	TaskResponse getTask( String projectID, String taskID );

	TaskResponse getAllUserTasks();

	TaskResponse getAllProjectTasks( String projectID );

	Response updateTask( String projectID, String taskID, String taskName, String taskDescription, String taskAssignee, String priority );

	Response deleteTask( String projectID, String taskID );

	Response createColumn( String projectID, String columnName );

	ColumnResponse getAllProjectColumns( String projectID );

	ColumnResponse getColumn( String projectID, String columnID );

	Response updateColumn( String projectID, String columnID, String columnName, int columnPosition );

	Response deleteColumn( String projectID, String columnID );
}
