package tobedevelopers.project_fury;

import junit.framework.TestCase;

import tobedevelopers.project_fury.model.Model;
import tobedevelopers.project_fury.model.Response;

/**
 * Created by Macro303 on 16/08/2016.
 */
public class ModelTest extends TestCase{

	public void testRegister(){
		Response response = new Model().registerUser( "Username", "Email@Email.com", "Password" );
		System.out.println( "Test Register:\n" + response.toString() );
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
