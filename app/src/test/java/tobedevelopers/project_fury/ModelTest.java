package tobedevelopers.project_fury;

import junit.framework.TestCase;

import java.util.Arrays;

import tobedevelopers.project_fury.model.Model;

/**
 * Created by Macro303 on 16/08/2016.
 */
public class ModelTest extends TestCase{

	public void testPostUserModel(){
		String value = "Error: Passed\nReason: " + null;
		String response = new Model().createUser( "Username14", "Password14", "Email14@Email14.com" ).toString();
		System.out.println( "Test Post User Model: " + response );
		assertTrue( value.equals( response ) );
	}

	public void testPostUserModel2(){
		String value = "Error: Passed\nReason: " + null;
		String response = new Model().createUser( "Username10", "Password10", "Email10@Email10.com", true ).toString();
		System.out.println( "Test Post User Model 2: " + response );
		assertTrue( value.equals( response ) );
	}

	public void testGetAllUsersModel(){
		String value = "Error: Passed\nReason: " + null;
		String response = new Model().getAllUsers().toString();
		System.out.println( "Test Get All Users Model: " + response );
		assertTrue( value.equals( response ) );
	}

	public void testGetUserModel(){
		String value = "[Username: Username\nPassword: Password\nEmail: Email@Email.com\nAdmin: true\nCreated At: Fri Aug 19 16:09:09 NZST 2016\nUpdated At: Fri Aug 19 16:09:09 NZST 2016]";
		String response = Arrays.toString( new Model().getUser( "Username" ).getResults() );
		System.out.println( "Test Get User Model: " + response );
		assertTrue( value.equals( response ) );
	}

	public void testUpdateUserModel(){
		String value = "[Username: Username\nPassword: Password\nEmail: Email@Email.com\nAdmin: true\nCreated At: Fri Aug 19 16:09:09 NZST 2016\nUpdated At: Fri Aug 19 17:26:30 NZST 2016]";
		String response = Arrays.toString( new Model().updateUser( "Username", "Password", "EmailMe@EmailMe.com" ).getResults() );
		System.out.println( "Test Update User Model: " + response );
		assertTrue( value.equals( response ) );
	}
}
