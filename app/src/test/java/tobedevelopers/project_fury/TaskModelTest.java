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
import tobedevelopers.project_fury.model.TaskResponse;

/**
 * Created by Macro303 on 27/09/2016.
 */
@FixMethodOrder( MethodSorters.NAME_ASCENDING )
public class TaskModelTest{

	private static String taskID;
	private static String columnID;
	private Model model;
	private String username;
	private String password;
	private String projectID;
	private String taskName;
	private String taskDescription;
	private String taskAssignee;
	private String taskPriority;

	@Before
	public void setUp() throws Exception{
		model = new Model();
		username = "ModelTest";
		password = "Password";
		login();
		getProject();
		taskName = "Model Test Task Name";
		taskDescription = "Model Test Task Description";
		taskAssignee = username.toLowerCase();
		taskPriority = "normal";
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

	/**
	 * Test for Creating a Task
	 */
	@Test
	public void test01(){
		Response response = model.createTask( projectID, taskName, taskDescription, taskAssignee );
		System.out.println( "Test Create Task: " + response.toString() );
		Assert.assertTrue( "Task creation successful.".equals( response.getMessage() ) );
	}

	/**
	 * Test for Getting all the Tasks assigned to the user
	 */
	@Test
	public void test02(){
		TaskResponse response = model.getAllUserTasks();
		System.out.println( "Test Get All User Tasks: " + response.toString() );
		Assert.assertTrue( "Success".equals( response.getMessage() ) );
		taskID = response.getTasks()[ response.getTasks().length - 1 ].getTaskID();
		columnID = response.getTasks()[ response.getTasks().length - 1 ].getColumnID();
	}

	/**
	 * Test for Getting all the Tasks on a project
	 */
	@Test
	public void test03(){
		TaskResponse response = model.getAllProjectTasks( projectID );
		System.out.println( "Test Get All Project Tasks: " + response.toString() );
		Assert.assertTrue( "Success".equals( response.getMessage() ) );
		taskID = response.getTasks()[ response.getTasks().length - 1 ].getTaskID();
		columnID = response.getTasks()[ response.getTasks().length - 1 ].getColumnID();
	}

	/**
	 * Test for Getting all the Tasks on a Column
	 */
	@Test
	public void test04(){
		TaskResponse response = model.getAllColumnTasks( projectID, columnID );
		System.out.println( "Test Get All Column Tasks: " + response.toString() );
		Assert.assertTrue( "Success".equals( response.getMessage() ) );
	}

	/**
	 * Test for Getting the Selected Task
	 */
	@Test
	public void test05(){
		TaskResponse response = model.getTask( projectID, taskID );
		System.out.println( "Test Get Task: " + response.toString() );
		Assert.assertTrue( "Success".equals( response.getMessage() ) );
	}

	/**
	 * Test for Updating the Selected Task
	 */
	@Test
	public void test06(){
		Response response = model.updateTask( projectID, taskID, columnID, taskName, taskDescription, taskAssignee, taskPriority );
		System.out.println( "Test Update Task: " + response.toString() );
		Assert.assertTrue( "Update successful.".equals( response.getMessage() ) );
	}

	/**
	 * Test for Deleting the Selected Task
	 */
	@Test
	@Ignore
	public void test07(){
		Response response = model.deleteTask( projectID, taskID );
		System.out.println( "Test Delete Task: " + response.toString() );
		Assert.assertTrue( "Delete successful.".equals( response.getMessage() ) );
	}
}
