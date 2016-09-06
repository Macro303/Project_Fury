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
		System.out.println( "Test Register:\n" + response.toString() );
		assertTrue( value.equals( response.toString() ) );
	}

	public void testLogin(){
		Model model = new Model();
		String value = "Response{message=\'Success\'}";
		Response response = model.login( "Username", "Password" );
		System.out.println( "Test Login:\n" + response.toString() );
		assertTrue( value.equals( response.toString() ) );
	}

	public void testCreateProject(){
		Model model = new Model();
		String value = "Response{message=\'Project creation successful.\'}";
		assertTrue( "Success".equals( model.login( "Username", "Password" ).getMessage() ) );
		Response response = model.createProject( "Project", "Description" );
		System.out.println( "Test Create Project:\n" + response.toString() );
		assertTrue( value.equals( response.toString() ) );
	}

	public void testGetAllProjects(){
		Model model = new Model();
		assertTrue( "Success".equals( model.login( "Username", "Password" ).getMessage() ) );
		ProjectResponse response = model.getAllProjects();
		System.out.println( "Test Get Projects:\n" + response.toString() );
	}

	public void testGetProject(){
		Model model = new Model();
		assertTrue( "Success".equals( model.login( "Username", "Password" ).getMessage() ) );
		ProjectResponse response = model.getProject( "57c4f83466d8de03003bea62" );
		System.out.println( "Test Get Project:\n" + response.toString() );
	}

	public void testGetAllUsers(){
		Model model = new Model();
		assertTrue( "Success".equals( model.login( "Username", "Password" ).getMessage() ) );
		UsernameResponse response = model.getAllUsers();
		System.out.println( "Test Get All Users:\n" + response.toString() );
	}

	public void testUpdateProject(){
		Model model = new Model();
		String value = "Response{message='Update successful.'}";
		assertTrue( "Success".equals( model.login( "Username", "Password" ).getMessage() ) );
		Response response = model.updateProject( "57c4f83466d8de03003bea62", "New Name", "New Description" );
		System.out.println( "Test Update Project:\n" + response.toString() );
		assertTrue( value.equals( response.toString() ) );
	}

	public void testDeleteProject(){
		Model model = new Model();
		String value = "Response{message='Delete successful.'}";
		assertTrue( "Success".equals( model.login( "Username", "Password" ).getMessage() ) );
		Response response = model.deleteProject( "57c4f83466d8de03003bea62" );
		System.out.println( "Test Delete Project:\n" + response.toString() );
		assertTrue( value.equals( response.toString() ) );
	}

	public void testCreateTask(){
		Model model = new Model();
		String value = "Response{message='Task creation successful.'}";
		assertTrue( "Success".equals( model.login( "Username", "Password" ).getMessage() ) );
		Response response = model.createTask( "57c4f83466d8de03003bea62", "Task", "Description", "Username" );
		System.out.println( "Test Create Task:\n" + response.toString() );
		assertTrue( value.equals( response.toString() ) );
	}

	public void testGetAllProjectTasks(){
		Model model = new Model();
		String value = model.login( "Username", "Password" ).getMessage();
		System.out.println( value );
		assertTrue( "Success".equals( value ) );
		TaskResponse response = model.getAllProjectTasks( "57c4f83466d8de03003bea62" );
		System.out.println( "Test Get All Project Tasks:\n" + response.toString() );
	}

	public void testGetAllUserTasks(){
		Model model = new Model();
		assertTrue( "Success".equals( model.login( "Username", "Password" ).getMessage() ) );
		TaskResponse response = model.getAllUserTasks();
		System.out.println( "Test Get All User Task:\n" + response.toString() );
	}

	public void testGetTask(){
		Model model = new Model();
		assertTrue( "Success".equals( model.login( "Username", "Password" ).getMessage() ) );
		TaskResponse response = model.getTask( "57c4f83466d8de03003bea62", "57c503d711d6150300bff578" );
		System.out.println( "Test Get Task:\n" + response.toString() );
	}

	public void testDeleteTask(){
		Model model = new Model();
		String value = "Response{message='Delete successful.'}";
		assertTrue( "Success".equals( model.login( "Username", "Password" ).getMessage() ) );
		Response response = model.deleteTask( "57ccea916fd76c030068e2c6", "57cceaae6fd76c030068e2c7" );
		System.out.println( "Test Delete Task:\n" + response.toString() );
		assertTrue( value.equals( response.toString() ) );
	}
}
