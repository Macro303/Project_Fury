package tobedevelopers.project_fury;

import junit.framework.TestCase;

import tobedevelopers.project_fury.model.Model;
import tobedevelopers.project_fury.model.Response;

/**
 * Created by Macro303 on 16/08/2016.
 */
public class ModelTest extends TestCase{

	public void testCreateUser(){
		String value = "Passed";
		Response response = new Model().createUser( "Username", "Password", "Email@Email.com" );
		System.out.println( "Test Create User:\n" + response.toString() );
		assertTrue( value.equals( response.getError() ) );
	}

	public void testGetAllUsers(){
		String value = "Passed";
		Response response = new Model().getAllUsers();
		System.out.println( "Test Get All Users:\n" + response.toString() );
		assertTrue( value.equals( response.getError() ) );
	}

	public void testGetSelectedUser(){
		String value = "Passed";
		Response response = new Model().getUser( "Username" );
		System.out.println( "Test Get Selected User: " + response.toString() );
		assertTrue( value.equals( response.getError() ) );
	}

	public void testUpdateSelectedUser(){
		String value = "Passed";
		Response response = new Model().updateUser( "Username", "Password", "EmailMe@EmailMe.com" );
		System.out.println( "Test Update Selected User: " + response.toString() );
		assertTrue( value.equals( response.getError() ) );
	}

	public void testDeleteSelectedUser(){
		String value = "Passed";
		Response response = new Model().deleteUser( "Username" );
		System.out.println( "Test Delete User Model: " + response.toString() );
		assertTrue( value.equals( response.getError() ) );
	}
}
