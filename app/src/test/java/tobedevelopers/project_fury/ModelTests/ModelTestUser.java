package tobedevelopers.project_fury.ModelTests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import tobedevelopers.project_fury.model.Model;
import tobedevelopers.project_fury.model.ModelContract;
import tobedevelopers.project_fury.model.Response;
import tobedevelopers.project_fury.model.UsernameResponse;

/**
 * Created by Macro303 on 27/09/2016.
 */
@FixMethodOrder( MethodSorters.NAME_ASCENDING )
public class ModelTestUser{
	private ModelContract model;
	private String username = "Model Test";
	private String password = "Password";
	private String email = "Test@Test.com";
	private boolean admin = false;

	@Before
	public void setUp() throws Exception{
		model = new Model();
	}

	@After
	public void tearDown() throws Exception{
		model = null;
	}

	@Test
	public void test01Register(){
		Response response = model.registerUser( username, password, email, admin );
		Assert.assertTrue( "Registration Successful.".equals( response.getMessage() ) );
	}

	@Test
	@Ignore
	public void test02RegisterInvalid(){
		Response response = model.registerUser( username, password, email, admin );
		Assert.assertTrue( "400 Error".equals( response.getMessage() ) );
	}

	@Test
	public void test03Login(){
		Response response = model.login( username, password );
		Assert.assertTrue( "Success".equals( response.getMessage() ) );
	}

	@Test
	public void test04GetAllUsers(){
		UsernameResponse response = model.getAllUsers();
		Assert.assertTrue( "Success".equals( response.getMessage() ) );
	}
}