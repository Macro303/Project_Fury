package tobedevelopers.project_fury;

import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import tobedevelopers.project_fury.model.Model;
import tobedevelopers.project_fury.model.ProjectResponse;
import tobedevelopers.project_fury.model.Response;

/**
 * Created by Macro303 on 27/09/2016.
 */
@FixMethodOrder( MethodSorters.NAME_ASCENDING )
public class ProjectModelTest{

	private static String projectID;
	private Model model;
	private String username;
	private String password;
	private String projectName;
	private String projectDescription;

	@Before
	public void setUp() throws Exception{
		model = new Model();
		username = "ModelTest";
		password = "Password";
		login();
		projectName = "Model Test Project Name";
		projectDescription = "Model Test Project Description";
	}

	private void login(){
		Response response = model.login( username, password );
		Assert.assertTrue( "Success".equals( response.getMessage() ) );
	}

	/**
	 * Test for Creating a Project
	 */
	@Test
	public void test01(){
		Response response = model.createProject( projectName, projectDescription );
		System.out.println( "Test Create Project: " + response.toString() );
		Assert.assertTrue( "Project creation successful.".equals( response.getMessage() ) );
	}

	/**
	 * Test for Creating a Project with a name that already exists
	 */
	@Test
	public void test02(){
		Response response = model.createProject( projectName, projectDescription );
		System.out.println( "Test Create Project: " + response.toString() );
		Assert.assertTrue( "400 Error".equals( response.getMessage() ) );
	}

	/**
	 * Test for Getting All Projects associated with the user
	 */
	@Test
	public void test03(){
		ProjectResponse response = model.getAllProjects();
		System.out.println( "Test Get All Projects: " + response.toString() );
		Assert.assertTrue( "Success".equals( response.getMessage() ) );
		projectID = response.getProjects()[ response.getProjects().length - 1 ].getProjectID();
	}

	/**
	 * Test for Getting a Selected Project
	 */
	@Test
	public void test04(){
		ProjectResponse response = model.getProject( projectID );
		System.out.println( "Test Get Project: " + response.toString() );
		Assert.assertTrue( "Success".equals( response.getMessage() ) );
	}

	/**
	 * Test for Updating a Selected Project
	 */
	@Test
	public void test05(){
		Response response = model.updateProject( projectID, projectName, projectDescription );
		System.out.println( "Test Update Project: " + response.toString() );
		Assert.assertTrue( "Update successful.".equals( response.getMessage() ) );
	}

	/**
	 * Test for Deleting a Selected Project
	 */
	@Test
	@Ignore
	public void test06(){
		Response response = model.deleteProject( projectID );
		System.out.println( "Test Delete Project: " + response.toString() );
		Assert.assertTrue( "Delete successful.".equals( response.getMessage() ) );
	}
}
