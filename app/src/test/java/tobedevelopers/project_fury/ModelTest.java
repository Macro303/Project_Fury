package tobedevelopers.project_fury;

import junit.framework.TestCase;

import tobedevelopers.project_fury.model.Model;
import tobedevelopers.project_fury.model.ProjectResponse;
import tobedevelopers.project_fury.model.Response;
import tobedevelopers.project_fury.model.TaskResponse;
import tobedevelopers.project_fury.model.UsernameResponse;

/**
 * Created by Macro303 on 16/08/2016.
 */
public class ModelTest extends TestCase{

	public void testRegister(){
		Model model = new Model();
		Response response = model.registerUser( "Username", "Password", "Email@Email.com", false );
		System.out.println( "Test Register: " + response.toString() );
		assertTrue( "Registration Successful.".equals( response.getMessage() ) );
	}

	public void testLogin(){
		Model model = new Model();
		Response response = model.login( "Username", "Password" );
		System.out.println( "Test Login: " + response.toString() );
		assertTrue( "Success".equals( response.getMessage() ) );
	}

	public void testGetAllUsers(){
		Model model = new Model();
		Response loginResponse = model.login( "Username", "Password" );
		assertTrue( "Success".equals( loginResponse.getMessage() ) );
		UsernameResponse response = model.getAllUsers();
		System.out.println( "Test Get All Users: " + response.toString() );
		assertTrue( "Success".equals( response.getMessage() ) );
	}

	public void testCreateProject(){
		Model model = new Model();
		Response loginResponse = model.login( "Username", "Password" );
		assertTrue( "Success".equals( loginResponse.getMessage() ) );
		Response response = model.createProject( "Project Name", "Project Description" );
		System.out.println( "Test Create Project: " + response.toString() );
		assertTrue( "Project creation successful.".equals( response.getMessage() ) );
	}

	public void testGetAllProjects(){
		Model model = new Model();
		Response loginResponse = model.login( "Username", "Password" );
		assertTrue( "Success".equals( loginResponse.getMessage() ) );
		ProjectResponse response = model.getAllProjects();
		System.out.println( "Test Get Projects: " + response.toString() );
		assertTrue( "Success".equals( response.getMessage() ) );
	}

	//TODO Requires a ProjectID
	public void testGetProject(){
		Model model = new Model();
		Response loginResponse = model.login( "Username", "Password" );
		assertTrue( "Success".equals( loginResponse.getMessage() ) );
		ProjectResponse response = model.getProject( "57cfbb36e04d040300a04ac5" );
		System.out.println( "Test Get Project: " + response.toString() );
		assertTrue( "Success".equals( response.getMessage() ) );
	}

	//TODO Requires a ProjectID
	public void testUpdateProject(){
		Model model = new Model();
		Response loginResponse = model.login( "Username", "Password" );
		assertTrue( "Success".equals( loginResponse.getMessage() ) );
		Response response = model.updateProject( "57cfbb36e04d040300a04ac5", "Updated Project Name", "Updated Project Description" );
		System.out.println( "Test Update Project: " + response.toString() );
		assertTrue( "Update successful.".equals( response.getMessage() ) );
	}

	//TODO Requires a ProjectID
	public void testDeleteProject(){
		Model model = new Model();
		Response loginResponse = model.login( "Username", "Password" );
		assertTrue( "Success".equals( loginResponse.getMessage() ) );
		Response response = model.deleteProject( "" );
		System.out.println( "Test Delete Project: " + response.toString() );
//		assertTrue( "".equals( response.getMessage() ) );
	}

	//TODO Requires a ProjectID
	public void testCreateTask(){
		Model model = new Model();
		Response loginResponse = model.login( "Username", "Password" );
		assertTrue( "Success".equals( loginResponse.getMessage() ) );
		Response response = model.createTask( "57cfbb36e04d040300a04ac5", "Task Name", "Task Description", "Username" );
		System.out.println( "Test Create Task: " + response.toString() );
		assertTrue( "Task creation successful.".equals( response.getMessage() ) );
	}

	//TODO Requires a ProjectID
	//TODO Requires a TaskID
	public void testGetTask(){
		Model model = new Model();
		Response loginResponse = model.login( "Username", "Password" );
		assertTrue( "Success".equals( loginResponse.getMessage() ) );
		TaskResponse response = model.getTask( "57cfbb36e04d040300a04ac5", "57cfc75be04d040300a04acd" );
		System.out.println( "Test Get Task: " + response.toString() );
		assertTrue( "Success".equals( response.getMessage() ) );
	}

	public void testGetAllUserTasks(){
		Model model = new Model();
		Response loginResponse = model.login( "Username", "Password" );
		assertTrue( "Success".equals( loginResponse.getMessage() ) );
		TaskResponse response = model.getAllUserTasks();
		System.out.println( "Test Get All User Tasks: " + response.toString() );
		assertTrue( "Success".equals( response.getMessage() ) );
	}

	//TODO Requires a ProjectID
	public void testGetAllProjectTasks(){
		Model model = new Model();
		Response loginResponse = model.login( "Username", "Password" );
		assertTrue( "Success".equals( loginResponse.getMessage() ) );
		TaskResponse response = model.getAllProjectTasks( "57cfbb36e04d040300a04ac5" );
		System.out.println( "Test Get All Project Tasks: " + response.toString() );
		assertTrue( "Success".equals( response.getMessage() ) );
	}
	//TODO Requires a ProjectID
	//TODO Requires a ColumnID
	public void testGetAllColumnTasks(){
		Model model = new Model();
		Response loginResponse = model.login( "Username", "Password" );
		assertTrue( "Success".equals( loginResponse.getMessage() ) );
		TaskResponse response = model.getAllColumnTasks( "57cfbb36e04d040300a04ac5","57cfc87ee04d040300a04ace" );
		System.out.println( "Test Get All Column Tasks: " + response.toString() );
		assertTrue( "Success".equals( response.getMessage() ) );
	}

	//TODO Requires a ProjectID
	//TODO Requires a TaskID
	public void testUpdateTask(){
		Model model = new Model();
		Response loginResponse = model.login( "Username", "Password" );
		assertTrue( "Success".equals( loginResponse.getMessage() ) );
		Response response = model.updateTask( "57cfbb36e04d040300a04ac5", "57cfc75be04d040300a04acd", "Updated Task Name", "Updated Task Description", "Unassigned", "NORMAL" );
		System.out.println( "Test Update Task: " + response.toString() );
		assertTrue( "Update successful.".equals( response.getMessage() ) );
	}

	//TODO Requires a ProjectID
	//TODO Requires a TaskID
	public void testDeleteTask(){
		Model model = new Model();
		Response loginResponse = model.login( "Username", "Password" );
		assertTrue( "Success".equals( loginResponse.getMessage() ) );
		Response response = model.deleteTask( "", "" );
		System.out.println( "Test Delete Task: " + response.toString() );
//		assertTrue( "".equals( response.getMessage() ) );
	}

	//TODO Requires a ProjectID
	public void testCreateColumn(){
		Model model = new Model();
		Response loginResponse = model.login( "Username", "Password" );
		assertTrue( "Success".equals( loginResponse.getMessage() ) );
		Response response = model.createColumn( "57cfbb36e04d040300a04ac5", "Column Name" );
		System.out.println( "Test Create Column: " + response.toString() );
		assertTrue( "Column creation successful.".equals( response.getMessage() ) );
	}

	//TODO Requires a ProjectID
	public void testGetAllProjectColumns(){
		Model model = new Model();
		Response loginResponse = model.login( "Username", "Password" );
		assertTrue( "Success".equals( loginResponse.getMessage() ) );
		Response response = model.getAllProjectColumns( "57cfbb36e04d040300a04ac5" );
		System.out.println( "Test Get All Project Columns: " + response.toString() );
		assertTrue( "Success".equals( response.getMessage() ) );
	}

	//TODO Requires a ProjectID
	//TODO Requires a ColumnID
	public void testGetColumn(){
		Model model = new Model();
		Response loginResponse = model.login( "Username", "Password" );
		assertTrue( "Success".equals( loginResponse.getMessage() ) );
		Response response = model.getColumn( "57cfbb36e04d040300a04ac5", "57cfc87ee04d040300a04ace" );
		System.out.println( "Test Get Column: " + response.toString() );
		assertTrue( "Success".equals( response.getMessage() ) );
	}

	//TODO Requires a ProjectID
	//TODO Requires a ColumnID
	public void testUpdateColumn(){
		Model model = new Model();
		Response loginResponse = model.login( "Username", "Password" );
		assertTrue( "Success".equals( loginResponse.getMessage() ) );
		Response response = model.updateColumn( "57cfbb36e04d040300a04ac5", "57cfc87ee04d040300a04ace", "Updated Column Name" );
		System.out.println( "Test Update Column: " + response.toString() );
		assertTrue( "Update successful.".equals( response.getMessage() ) );
	}


	//TODO Requires a ProjectID
	//TODO Requires a ColumnID
	public void testDeleteColumn(){
		Model model = new Model();
		Response loginResponse = model.login( "Username", "Password" );
		assertTrue( "Success".equals( loginResponse.getMessage() ) );
		Response response = model.deleteColumn( "", "" );
		System.out.println( "Test Delete Column: " + response.toString() );
//		assertTrue( "".equals( response.getMessage() ) );
	}
}
