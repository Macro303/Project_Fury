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
}
