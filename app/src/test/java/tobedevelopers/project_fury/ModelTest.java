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
		String value = "Response{message=\'Registration Successful.\'}";
		Response response = model.registerUser( "Username", "Password", "Email@Email.com", false );
		System.out.println( "Test Register: " + response.toString() );
		assertTrue( value.equals( response.toString() ) );
	}

	public void testLogin(){
		Model model = new Model();
		String value = "Response{message=\'Success\'}";
		Response response = model.login( "Username", "Password" );
		System.out.println( "Test Login: " + response.toString() );
		assertTrue( value.equals( response.toString() ) );
	}

	public void testGetAllUsers(){
		Model model = new Model();
		String value = "";
		Response loginResponse = model.login( "Username", "Password" );
		assertTrue( "Success".equals( loginResponse.getMessage() ) );
		UsernameResponse response = model.getAllUsers();
		System.out.println( "Test Get All Users: " + response.toString() );
	}

	public void testCreateProject(){
		Model model = new Model();
		String value = "";
		Response loginResponse = model.login( "Username", "Password" );
		assertTrue( "Success".equals( loginResponse.getMessage() ) );
		Response response = model.createProject( "Project Name", "Project Description" );
		System.out.println( "Test Create Project: " + response.toString() );
	}

	public void testGetAllProjects(){
		Model model = new Model();
		String value = "";
		Response loginResponse = model.login( "Username", "Password" );
		assertTrue( "Success".equals( loginResponse.getMessage() ) );
		ProjectResponse response = model.getAllProjects();
		System.out.println( "Test Get Projects: " + response.toString() );
	}

	public void testGetProject(){
		Model model = new Model();
		String value = "";
		Response loginResponse = model.login( "Username", "Password" );
		assertTrue( "Success".equals( loginResponse.getMessage() ) );
		ProjectResponse response = model.getProject( "" );
		System.out.println( "Test Get Project: " + response.toString() );
	}

	public void testUpdateProject(){
		Model model = new Model();
		String value = "";
		Response loginResponse = model.login( "Username", "Password" );
		assertTrue( "Success".equals( loginResponse.getMessage() ) );
		Response response = model.updateProject( "", "Updated Project Name", "Updated Project Description" );
		System.out.println( "Test Update Project: " + response.toString() );
	}

	public void testDeleteProject(){
		Model model = new Model();
		String value = "";
		Response loginResponse = model.login( "Username", "Password" );
		assertTrue( "Success".equals( loginResponse.getMessage() ) );
		Response response = model.deleteProject( "" );
		System.out.println( "Test Delete Project: " + response.toString() );
	}

	public void testCreateTask(){
		Model model = new Model();
		String value = "";
		Response loginResponse = model.login( "Username", "Password" );
		assertTrue( "Success".equals( loginResponse.getMessage() ) );
		Response response = model.createTask( "", "Task Name", "Task Description", "Username" );
		System.out.println( "Test Create Task: " + response.toString() );
	}

	public void testGetTask(){
		Model model = new Model();
		String value = "";
		Response loginResponse = model.login( "Username", "Password" );
		assertTrue( "Success".equals( loginResponse.getMessage() ) );
		TaskResponse response = model.getTask( "", "" );
		System.out.println( "Test Get Task: " + response.toString() );
	}

	public void testGetAllUserTasks(){
		Model model = new Model();
		String value = "";
		Response loginResponse = model.login( "Username", "Password" );
		assertTrue( "Success".equals( loginResponse.getMessage() ) );
		TaskResponse response = model.getAllUserTasks();
		System.out.println( "Test Get All User Tasks: " + response.toString() );
	}

	public void testGetAllProjectTasks(){
		Model model = new Model();
		String value = "";
		Response loginResponse = model.login( "Username", "Password" );
		assertTrue( "Success".equals( loginResponse.getMessage() ) );
		TaskResponse response = model.getAllProjectTasks( "" );
		System.out.println( "Test Get All Project Tasks: " + response.toString() );
	}

	public void testUpdateTask(){
		Model model = new Model();
		String value = "";
		Response loginResponse = model.login( "Username", "Password" );
		assertTrue( "Success".equals( loginResponse.getMessage() ) );
		Response response = model.updateTask( "", "", "Updated Task Name", "Updated Task Description", "Unassigned", "NORMAL" );
		System.out.println( "Test Update Task: " + response.toString() );
	}

	public void testDeleteTask(){
		Model model = new Model();
		String value = "";
		Response loginResponse = model.login( "Username", "Password" );
		assertTrue( "Success".equals( loginResponse.getMessage() ) );
		Response response = model.deleteTask( "", "" );
		System.out.println( "Test Delete Task: " + response.toString() );
	}

	public void testCreateColumn(){
		Model model = new Model();
		String value = "";
		Response loginResponse = model.login( "Username", "Password" );
		assertTrue( "Success".equals( loginResponse.getMessage() ) );
		Response response = model.createColumn( "", "Column Name" );
		System.out.println( "Test Create Column: " + response.toString() );
	}

	public void testGetAllProjectColumns(){
		Model model = new Model();
		String value = "";
		Response loginResponse = model.login( "Username", "Password" );
		assertTrue( "Success".equals( loginResponse.getMessage() ) );
		Response response = model.getAllProjectColumns( "" );
		System.out.println( "Test Get All Project Columns: " + response.toString() );
	}

	public void testGetColumn(){
		Model model = new Model();
		String value = "";
		Response loginResponse = model.login( "Username", "Password" );
		assertTrue( "Success".equals( loginResponse.getMessage() ) );
		Response response = model.getColumn( "", "" );
		System.out.println( "Test Get Column: " + response.toString() );
	}

	public void testDeleteColumn(){
		Model model = new Model();
		String value = "";
		Response loginResponse = model.login( "Username", "Password" );
		assertTrue( "Success".equals( loginResponse.getMessage() ) );
		Response response = model.deleteColumn( "", "" );
		System.out.println( "Test Delete Column: " + response.toString() );
	}
}
