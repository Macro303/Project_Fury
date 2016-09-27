package tobedevelopers.project_fury.ModelTests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import tobedevelopers.project_fury.model.ColumnResponse;
import tobedevelopers.project_fury.model.Model;
import tobedevelopers.project_fury.model.ModelContract;
import tobedevelopers.project_fury.model.ProjectResponse;
import tobedevelopers.project_fury.model.Response;

/**
 * Created by Macro303 on 27/09/2016.
 */
@FixMethodOrder( MethodSorters.NAME_ASCENDING )
public class ModelTestColumn{
	private static String columnID;
	private static int columnPosition;

	private ModelContract model;
	private String username = "Model Test";
	private String password = "Password";
	private String projectID;
	private String columnName = "Test Column Name";

	@Before
	public void setUp() throws Exception{
		model = new Model();
		login();
		getProject();
	}

	@After
	public void tearDown() throws Exception{
		model = null;
		projectID = "";
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
	public void test01Create(){
		Response response = model.createColumn( projectID, columnName );
		Assert.assertTrue( "Column creation successful.".equals( response.getMessage() ) );
	}

	@Test
	public void test02GetAll(){
		ColumnResponse response = model.getAllProjectColumns( projectID );
		Assert.assertTrue( "Success".equals( response.getMessage() ) );
		columnID = response.getColumns()[ response.getColumns().length - 1 ].getColumnID();
		columnPosition = response.getColumns().length - 1;
	}

	@Test
	public void test03Get(){
		ColumnResponse response = model.getColumn( projectID, columnID );
		Assert.assertTrue( "Success".equals( response.getMessage() ) );
	}

	@Test
	public void test04Update(){
		Response response = model.updateColumn( projectID, columnID, columnName, columnPosition );
		Assert.assertTrue( "Update successful.".equals( response.getMessage() ) );
	}


	@Test
	@Ignore
	public void test05Delete(){
		Response response = model.deleteColumn( projectID, columnID );
		Assert.assertTrue( "Delete successful.".equals( response.getMessage() ) );
	}
}
