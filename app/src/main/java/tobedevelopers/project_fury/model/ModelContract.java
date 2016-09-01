package tobedevelopers.project_fury.model;

/**
 * Created by Macro303 on 19/08/2016.
 */
public interface ModelContract{

//	void setSelectedProject( Project _selectedProject );
//
//	Project getSelectedProject();

	Response registerUser( String username, String password, String email );

	Response registerUser( String username, String password, String email, boolean admin );

	Response login( String username, String password );

	UsernameResponse getAllUsers();

	ProjectResponse getAllProjects();

	ProjectResponse getProject( String projectID );

	Response createProject( String projectName );

	Response createProject( String projectName, String projectDescription );

	Response updateProject( String projectID, String projectDescription );

	TaskResponse getTask( String projectID, String taskID );

	TaskResponse getAllUserTasks();

	TaskResponse getAllProjectTasks( String projectID );

	Response createTask( String projectID, String taskName );

	Response createTaskNoAssignee( String projectID, String taskName, String taskDescription );

	Response createTaskNoDescription( String projectID, String taskName, String assignee );

	Response createTask( String projectID, String taskName, String taskDescription, String assignee );
}
