package tobedevelopers.project_fury;

import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import tobedevelopers.project_fury.model.Model;
import tobedevelopers.project_fury.model.Response;
import tobedevelopers.project_fury.model.UsernameResponse;

/**
 * Created by Macro303 on 27/09/2016.
 */
@FixMethodOrder( MethodSorters.NAME_ASCENDING )
public class UserModelTest{

	private Model model;
	private String username;
	private String password;
	private String email;
	private boolean admin;

	@Before
	public void setUp() throws Exception{
		model = new Model();
		username = "ModelTest";
		password = "Password";
		email = "Email@Email.com";
		admin = false;
	}

	/**
	 * Test for Registering
	 */
	@Test
	public void test01(){
		Response response = model.registerUser( username, password, email, admin );
		System.out.println( "Test Register: " + response.toString() );
		Assert.assertTrue( "Registration Successful.".equals( response.getMessage() ) );
	}

	/**
	 * Test for Registering with a name that already exists
	 */
	@Test
	public void test02(){
		Response response = model.registerUser( username, password, email, admin );
		System.out.println( "Test Register: " + response.toString() );
		Assert.assertTrue( "400 Error".equals( response.getMessage() ) );
	}

	/**
	 * Test for Logging in
	 */
	@Test
	public void test03(){
		Response response = model.login( username, password );
		System.out.println( "Test Login: " + response.toString() );
		Assert.assertTrue( "Success".equals( response.getMessage() ) );
	}

	/**
	 * Test for Getting All the Usernames in the database
	 */
	@Test
	public void test04(){
		UsernameResponse response = model.getAllUsers();
		System.out.println( "Test Get All Users: " + response.toString() );
		Assert.assertTrue( "Success".equals( response.getMessage() ) );
	}
}