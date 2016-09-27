package tobedevelopers.project_fury;

import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import tobedevelopers.project_fury.model.ColumnResponse;
import tobedevelopers.project_fury.model.Model;
import tobedevelopers.project_fury.model.ProjectResponse;
import tobedevelopers.project_fury.model.Response;

/**
 * Created by Macro303 on 27/09/2016.
 */
@FixMethodOrder( MethodSorters.NAME_ASCENDING )
public class ColumnModelTest{

	private static String columnID;
	private static int columnPosition;
	private Model model;
	private String username;
	private String password;
	private String projectID;
	private String columnName;

	@Before
	public void setUp() throws Exception{
		model = new Model();
		username = "ModelTest";
		password = "Password";
		login();
		getProject();
		columnName = "Model Test Column Name";
	}

	private void login(){
		Response response = model.login( username, password );
		Assert.assertTrue( "Success".equals( response.getMessage() ) );
	}

	private void getProject(){
		ProjectResponse response = model.getAllProjects();
		Assert.assertTrue( "Success".equals( response.getMessage() ) );
		projectID = response.getProjects()[ response.getProjects().length - 1 ].getProjectID();
	}

	@Test
	public void test01(){
		Response response = model.createColumn( projectID, columnName );
		System.out.println( "Test Create Column: " + response.toString() );
		Assert.assertTrue( "Column creation successful.".equals( response.getMessage() ) );
	}

	@Test
	public void test02(){
		ColumnResponse response = model.getAllProjectColumns( projectID );
		System.out.println( "Test Get All Project Columns: " + response.toString() );
		Assert.assertTrue( "Success".equals( response.getMessage() ) );
		columnID = response.getColumns()[ response.getColumns().length - 1 ].getColumnID();
		columnPosition = response.getColumns().length - 1;
	}

	@Test
	public void test03(){
		ColumnResponse response = model.getColumn( projectID, columnID );
		System.out.println( "Test Get Column: " + response.toString() );
		Assert.assertTrue( "Success".equals( response.getMessage() ) );
	}

	@Test
	public void test04(){
		Response response = model.updateColumn( projectID, columnID, columnName, columnPosition );
		System.out.println( "Test Update Column: " + response.toString() );
		Assert.assertTrue( "Update successful.".equals( response.getMessage() ) );
	}


	@Test
	@Ignore( "Needs to be run separately or all hell breaks loose\n\n" )
	public void test05(){
		Response response = model.deleteColumn( projectID, columnID );
		System.out.println( "Test Delete Column: " + response.toString() );
		Assert.assertTrue( "Delete successful.".equals( response.getMessage() ) );
	}
}
