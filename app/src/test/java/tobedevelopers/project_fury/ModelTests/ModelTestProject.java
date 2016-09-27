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
import tobedevelopers.project_fury.model.ProjectResponse;
import tobedevelopers.project_fury.model.Response;

/**
 * Created by Macro303 on 27/09/2016.
 */
@FixMethodOrder( MethodSorters.NAME_ASCENDING )
public class ModelTestProject{
	private static String projectID;

	private ModelContract model;
	private String username = "Model Test";
	private String password = "Password";
	private String projectName = "Test Project Name";
	private String projectDescription = "Test Project Description";

	@Before
	public void setUp() throws Exception{
		model = new Model();
		login();
	}

	@After
	public void tearDown() throws Exception{
		model = null;
	}

	private void login(){
		Response response = model.login( username, password );
		Assert.assertTrue( "Success".equals( response.getMessage() ) );
	}

	@Test
	public void test01Create(){
		Response response = model.createProject( projectName, projectDescription );
		Assert.assertTrue( "Project creation successful.".equals( response.getMessage() ) );
	}

	@Test
	@Ignore
	public void test02CreateInvalid(){
		Response response = model.createProject( projectName, projectDescription );
		Assert.assertTrue( "400 Error".equals( response.getMessage() ) );
	}

	@Test
	public void test03GetAll(){
		ProjectResponse response = model.getAllProjects();
		Assert.assertTrue( "Success".equals( response.getMessage() ) );
		projectID = response.getProjects()[ response.getProjects().length - 1 ].getProjectID();
	}

	@Test
	public void test04Get(){
		ProjectResponse response = model.getProject( projectID );
		Assert.assertTrue( "Success".equals( response.getMessage() ) );
	}

	@Test
	public void test05Update(){
		Response response = model.updateProject( projectID, projectName, projectDescription );
		Assert.assertTrue( "Update successful.".equals( response.getMessage() ) );
	}

	@Test
	@Ignore
	public void test06Delete(){
		Response response = model.deleteProject( projectID );
		Assert.assertTrue( "Delete successful.".equals( response.getMessage() ) );
	}
}
