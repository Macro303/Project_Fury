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
import tobedevelopers.project_fury.model.TaskResponse;

/**
 * Created by Macro303 on 27/09/2016.
 */
@FixMethodOrder( MethodSorters.NAME_ASCENDING )
public class ModelTestTask{
	private static String taskID;
	private static String columnID;

	private ModelContract model;
	private String username = "Model Test";
	private String password = "Password";
	private String projectID;
	private String taskName = "Test Task Name";
	private String taskDescription = "Test Task Description";
	private String taskAssignee = username.toLowerCase();
	private String taskPriority = "normal";

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
		Response response = model.createTask( projectID, taskName, taskDescription, taskAssignee );
		Assert.assertTrue( "Task creation successful.".equals( response.getMessage() ) );
	}

	@Test
	public void test02GetAllUser(){
		TaskResponse response = model.getAllUserTasks();
		Assert.assertTrue( "Success".equals( response.getMessage() ) );
		taskID = response.getTasks()[ response.getTasks().length - 1 ].getTaskID();
		columnID = response.getTasks()[ response.getTasks().length - 1 ].getColumnID();
	}

	@Test
	public void test03GetAllProject(){
		TaskResponse response = model.getAllProjectTasks( projectID );
		Assert.assertTrue( "Success".equals( response.getMessage() ) );
		taskID = response.getTasks()[ response.getTasks().length - 1 ].getTaskID();
		columnID = response.getTasks()[ response.getTasks().length - 1 ].getColumnID();
	}

	@Test
	public void test04GetAllColumn(){
		TaskResponse response = model.getAllColumnTasks( projectID, columnID );
		Assert.assertTrue( "Success".equals( response.getMessage() ) );
	}

	@Test
	public void test05Get(){
		TaskResponse response = model.getTask( projectID, taskID );
		Assert.assertTrue( "Success".equals( response.getMessage() ) );
	}

	@Test
	public void test06Update(){
		Response response = model.updateTask( projectID, taskID, columnID, taskName, taskDescription, taskAssignee, taskPriority );
		Assert.assertTrue( "Update successful.".equals( response.getMessage() ) );
	}

	@Test
	@Ignore
	public void test07Delete(){
		Response response = model.deleteTask( projectID, taskID );
		Assert.assertTrue( "Delete successful.".equals( response.getMessage() ) );
	}
}
