package tobedevelopers.project_fury;

import junit.framework.TestCase;

import tobedevelopers.project_fury.model.Model;
import tobedevelopers.project_fury.model.ProjectResponse;
import tobedevelopers.project_fury.model.Response;

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
		String tokenValue = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI1N2JmZWQzYmRjMzQ0NjAzMDBmOWE2NzEiLCJ1c2VybmFtZSI6InVzZXJuYW1lIiwiZXhwIjoxNDczMDI2MDUzLCJpYXQiOjE0NzI0MjEyNTN9.6NIqlD8SF-lMyzzHhjImhf5oZ9jjceZspZhNVDQdV_s";
		Model model = new Model();
		String token = model.getToken();
		System.out.println( "Token Before:\n" + token );
		Response response = model.login( "Username", "Email@Email.com" );
		System.out.println( "Test Login:\n" + response.toString() );
		token = model.getToken();
		System.out.println( "Token After:\n" + token );
		assertTrue( responseValue.equals( response.toString() ) );
		assertTrue( tokenValue.equals( token ) );
	}

	public void testGetProjects(){
		Model model = new Model();
		String value = "ProjectResponse{projects=[]}";
		ProjectResponse response = model.getAllProjects();
		System.out.println( "Test Get Projects:\n" + response.toString() );
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
