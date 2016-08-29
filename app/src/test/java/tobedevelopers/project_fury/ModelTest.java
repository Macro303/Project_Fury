package tobedevelopers.project_fury;

import junit.framework.TestCase;

import tobedevelopers.project_fury.model.Model;
import tobedevelopers.project_fury.model.ProjectResponse;
import tobedevelopers.project_fury.model.Response;
import tobedevelopers.project_fury.model.UsernameResponse;

/**
 * Created by Macro303 on 16/08/2016.
 */
public class ModelTest extends TestCase{

	public void testRegister(){
		String value = "Response{message=\'Registration Successful.\'}";
		Response response = new Model().registerUser( "Username", "Password", "Email@Email.com" );
		System.out.println( "Test Register:\n" + response.toString() );
		assertTrue( value.equals( response.toString() ) );
	}

	public void testLogin(){
		String responseValue = "Response{message=\'Success\'}";
		Model model = new Model();
		String token = model.getToken();
		System.out.println( "Token Before:\n" + token );
		Response response = model.login( "Username", "Password" );
		System.out.println( "Test Login:\n" + response.toString() );
		token = model.getToken();
		System.out.println( "Token After:\n" + token );
		assertTrue( responseValue.equals( response.toString() ) );
	}

	public void testGetProjects(){
		Model model = new Model();
		String value = "ProjectResponse{message=\'Success\', projects=[]}";
		assertTrue( "Success".equals( model.login( "Username", "Password" ).getMessage() ) );
		ProjectResponse response = model.getAllProjects();
		System.out.println( "Test Get Projects:\n" + response.toString() );
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

	public void testGetProject(){
		Model model = new Model();
		String value = "ProjectResponse{message=\'Success\', projects=[Project{description=\'Description\', name=\'project\', usersOnProject=[username]}]}";
		assertTrue( "Success".equals( model.login( "Username", "Password" ).getMessage() ) );
		ProjectResponse response = model.getProject( "Project" );
		System.out.println( "Test Get Project:\n" + response.toString() );
		assertTrue( value.equals( response.toString() ) );
	}

	public void testGetAllUsers(){
		Model model = new Model();
		String value = "UsernameResponse{message='Success', usernames=[starlord, drax, gamorra, rocket, iamgroot, Username, shinychrome, shinychrom, sjsjshshsh, ShinyChrome, shshdhdh, shinychromew, sjsjsjsjs, 12345678901234567890, username]}";
		assertTrue( "Success".equals( model.login( "Username", "Password" ).getMessage() ) );
		UsernameResponse response = model.getAllUsers();
		System.out.println( "Test Get All Users:\n" + response.toString() );
		assertTrue( value.equals( response.toString() ) );
	}

	/**
	 * Test that creates a user in the DB and checks if a user was created
	 public void testCreateUser(){
	 String value = "Passed";
	 Response response = new Model().createUser( "Username", "Password", "Email@Email.com" );
	 System.out.println( "Test Create User:\n" + response.toString() );
	 assertTrue( value.equals( response.getError() ) );
	 }

	 *//**
	 * Test that checks if it can get all the users from the DB
	 *//*
	public void testGetAllUsers(){
		String value = "Passed";
		Response response = new Model().getAllUsers();
		System.out.println( "Test Get All Users:\n" + response.toString() );
		assertTrue( value.equals( response.getError() ) );
	}

	*//**
	 * Test that checks if it can get a select user from the DB by using the users username
	 *//*
	public void testGetSelectedUser(){
		String value = "Passed";
		Response response = new Model().getUser( "Username" );
		System.out.println( "Test Get Selected User:\n" + response.toString() );
		assertTrue( value.equals( response.getError() ) );
	}

	*//**
	 * Test that updates the select user by its username, and returns the new user information
	 *//*
	public void testUpdateSelectedUser(){
		String value = "Passed";
		Response response = new Model().updateUser( "Username", "Password", "EmailMe@EmailMe.com" );
		System.out.println( "Test Update Selected User:\n" + response.toString() );
		assertTrue( value.equals( response.getError() ) );
	}

	*//**
	 * Test that deletes the select user by its username from the DB
	 *//*
	public void testDeleteSelectedUser(){
		String value = "Passed";
		Response response = new Model().deleteUser( "Username" );
		System.out.println( "Test Delete User Model:\n" + response.toString() );
		assertTrue( value.equals( response.getError() ) );
	}*/
}
