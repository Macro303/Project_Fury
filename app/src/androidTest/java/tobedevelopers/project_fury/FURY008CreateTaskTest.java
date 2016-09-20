package tobedevelopers.project_fury;

import android.support.design.widget.TextInputEditText;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import tobedevelopers.project_fury.create_task.implementation.CreateTaskView;
import tobedevelopers.project_fury.model.Model;
import tobedevelopers.project_fury.model.Project;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsNot.not;

/**
 * Created by A on 8/29/2016.
 */
@RunWith( AndroidJUnit4.class )
public class FURY008CreateTaskTest{

//	public WeakReference<BacklogContract.View> viewWeakReference;
//	public WeakReference<BacklogContract.Navigation> navigationWeakReference;
//	public ModelContract model;
//
//	public void testing(){
//		BacklogContract.View view = viewWeakReference.get();
//		BacklogContract.Navigation navigation = navigationWeakReference.get();
//
//		new AsyncTask< String, Void, ProjectResponse >(){
//
//			@Override
//			protected void onPreExecute(){
//				viewWeakReference.get().loadingProjectsInProgress();
//			}
//
//			@Override
//			protected ProjectResponse doInBackground( String... strings ){
//				return model.getAllProjects();
//			}
//
//			@Override
//			protected void onPostExecute( ProjectResponse response ){
//				BacklogContract.View view = viewWeakReference.get();
//				BacklogContract.Navigation navigation = navigationWeakReference.get();
//
//				switch( response.getMessage() ){
//					case "Success":
//						Model.setSelectedProject( response.getProjects()[ 0 ] );
//						navigation.navigateToCreateTask();
//						break;
//					case "No Internet Access":
//						view.noInternetAccessValidation();
//						break;
//					default:
//						break;
//				}
//			}
//		}.executeOnExecutor( AsyncTask.THREAD_POOL_EXECUTOR );
//	}

	@Rule
	public ActivityTestRule< CreateTaskView > createTaskViewActivityTestRule = new ActivityTestRule<>( CreateTaskView.class );

	private static Matcher< View > withError( final String expected ){
		return new TypeSafeMatcher< View >(){
			@Override
			public void describeTo( Description description ){

			}

			@Override
			protected boolean matchesSafely( View view ){
				if( !( view instanceof TextInputEditText ) ){
					return false;
				}
				TextInputEditText editText = ( TextInputEditText ) view;
				return editText.getError().toString().equals( expected );
			}
		};
	}

	@Test
	public void testCreateTaskTextWording(){
		String[] users = { "Andrea" };
		Project projectTest = new Project( "Test", "not null", users );
		Model.setSelectedProject( projectTest );
		Model.getSelectedProject().getUsersOnProject();

		onView( withText( "Create a Task" ) ).check( matches( isDisplayed() ) );
	}

	@Test
	public void testEditTextValidInputDescriptionNull(){
		//Task Name
		onView( withId( R.id.createTaskActivity_taskNameEditText ) ).perform( click(), replaceText( "Update a Project" ) );
		onView( withId( R.id.createTaskActivity_taskNameEditText ) ).check( matches( withText( "Update a Project" ) ) );

		//Task Description - Null
		onView( withId( R.id.createTaskActivity_taskDescriptionEditText ) ).perform( click(), replaceText( "" ) );
		onView( withId( R.id.createTaskActivity_taskDescriptionEditText ) ).check( matches( withText( "" ) ) );

		//Create Task enabled
		onView( withId( R.id.createTaskActivity_createTaskButton ) ).check( matches( isEnabled() ) );
	}

	@Test
	public void testEditTextValidInput(){
		//Task Name
		onView( withId( R.id.createTaskActivity_taskNameEditText ) ).perform( click(), replaceText( "Update a Project" ) );
		onView( withId( R.id.createTaskActivity_taskNameEditText ) ).check( matches( withText( "Update a Project" ) ) );

		//Task Description - Null
		onView( withId( R.id.createTaskActivity_taskDescriptionEditText ) ).perform( click(), replaceText( "This project is about the awesomeness of Android" ) );
		onView( withId( R.id.createTaskActivity_taskDescriptionEditText ) ).check( matches( withText( "This project is about the awesomeness of Android" ) ) );

		//Create Task enabled
		onView( withId( R.id.createTaskActivity_createTaskButton ) ).check( matches( isEnabled() ) );
	}

	@Test
	public void testInvalidInputTooShortErrorMessage(){
		//Task Name
		onView( withId( R.id.createTaskActivity_taskNameEditText ) ).perform( click(), replaceText( "Up" ) );
		onView( withId( R.id.createTaskActivity_taskNameEditText ) ).check( matches( withText( "Up" ) ) );
		onView( withId( R.id.createTaskActivity_taskNameEditText ) ).check( matches( withError( "Minimum of 3 characters" ) ) );

		//Task Description - Null
		onView( withId( R.id.createTaskActivity_taskDescriptionEditText ) ).perform( click(), replaceText( "" ) );
		onView( withId( R.id.createTaskActivity_taskDescriptionEditText ) ).check( matches( withText( "" ) ) );

		//Create Task enabled
		onView( withId( R.id.createTaskActivity_createTaskButton ) ).check( matches( not( isEnabled() ) ) );
	}

	@Test
	public void testInvalidInputTooLongErrorMessage(){
		//Task Name
		onView( withId( R.id.createTaskActivity_taskNameEditText ) ).perform( click(), replaceText( "12345678901234567890" ) );
		onView( withId( R.id.createTaskActivity_taskNameEditText ) ).check( matches( withText( "12345678901234567890" ) ) );
		onView( withId( R.id.createTaskActivity_taskNameEditText ) ).check( matches( withError( "Maximum of 20 characters" ) ) );

		//Task Description - Null
		onView( withId( R.id.createTaskActivity_taskDescriptionEditText ) ).perform( click(), replaceText( "Lorem ipsum dolor1 sit amet consectetur adipiscing elit Morbi a lectus sit amet elit egestas hendrerit ut a metus1 Nulla eu sed1" ) );
		onView( withId( R.id.createTaskActivity_taskDescriptionEditText ) ).check( matches( withText( "Lorem ipsum dolor1 sit amet consectetur adipiscing elit Morbi a lectus sit amet elit egestas hendrerit ut a metus1 Nulla eu sed1" ) ) );
		onView( withId( R.id.createTaskActivity_taskDescriptionEditText ) ).check( matches( withError( "Maximum of 128 characters" ) ) );

		//Create Task enabled
		onView( withId( R.id.createTaskActivity_createTaskButton ) ).check( matches( isEnabled() ) );
	}

	@Test
	public void testInvalidCharacterInput(){
		onView( withId( R.id.createTaskActivity_taskNameEditText ) ).perform( typeText( "':|][?" ), closeSoftKeyboard() );
		onView( withId( R.id.createTaskActivity_taskNameEditText ) ).perform( click() );
		onView( withId( R.id.createTaskActivity_taskNameEditText ) ).check( matches( withText( "" ) ) );

		onView( withId( R.id.createTaskActivity_taskDescriptionEditText ) ).perform( typeText( "':|][?" ), closeSoftKeyboard() );
		onView( withId( R.id.createTaskActivity_taskDescriptionEditText ) ).perform( click() );
		onView( withId( R.id.createTaskActivity_taskDescriptionEditText ) ).check( matches( withText( "" ) ) );
	}

//	public class TestingModel{
//		public void testRegister(){
//			Model model = new Model();
//			String value = "Response{message=\'Registration Successful.\'}";
//			Response response = model.registerUser( "Username", "Password", "Email@Email.com" );
//			System.out.println( "Test Register:\n" + response.toString() );
//			assertTrue( value.equals( response.toString() ) );
//		}
//
//		public void testLogin(){
//			Model model = new Model();
//			String value = "Response{message=\'Success\'}";
//			Response response = model.login( "Username", "Password" );
//			System.out.println( "Test Login:\n" + response.toString() );
//			assertTrue( value.equals( response.toString() ) );
//		}
//
//		public void testCreateProject(){
//			Model model = new Model();
//			String value = "Response{message=\'Project creation successful.\'}";
//			assertTrue( "Success".equals( model.login( "Username", "Password" ).getMessage() ) );
//			Response response = model.createProject( "Project", "Description" );
//			System.out.println( "Test Create Project:\n" + response.toString() );
//			assertTrue( value.equals( response.toString() ) );
//		}
//
//		public void testGetAllProjects(){
//			Model model = new Model();
//			assertTrue( "Success".equals( model.login( "Username", "Password" ).getMessage() ) );
//			ProjectResponse response = model.getAllProjects();
//			System.out.println( "Test Get Projects:\n" + response.toString() );
//		}
//
//		public void testGetProject(){
//			Model model = new Model();
//			assertTrue( "Success".equals( model.login( "Username", "Password" ).getMessage() ) );
//			ProjectResponse response = model.getProject( "57c4f83466d8de03003bea62" );
//			System.out.println( "Test Get Project:\n" + response.toString() );
//		}
//
//		public void testGetAllUsers(){
//			Model model = new Model();
//			assertTrue( "Success".equals( model.login( "Username", "Password" ).getMessage() ) );
//			UsernameResponse response = model.getAllUsers();
//			System.out.println( "Test Get All Users:\n" + response.toString() );
//		}
//
//		public void testUpdateProject(){
//			Model model = new Model();
//			String value = "Response{message='Update successful.'}";
//			assertTrue( "Success".equals( model.login( "Username", "Password" ).getMessage() ) );
//			Response response = model.updateProject( "57c4f83466d8de03003bea62", "New Name", "New Description" );
//			System.out.println( "Test Update Project:\n" + response.toString() );
//			assertTrue( value.equals( response.toString() ) );
//		}
//
//		public void testCreateTask(){
//			Model model = new Model();
//			String value = "Response{message='Task creation successful.'}";
//			assertTrue( "Success".equals( model.login( "Username", "Password" ).getMessage() ) );
//			Response response = model.createTask( "57c4f83466d8de03003bea62", "Task", "Description", "Username" );
//			System.out.println( "Test Create Task:\n" + response.toString() );
//			assertTrue( value.equals( response.toString() ) );
//		}
//
//		public void testGetAllProjectTasks(){
//			Model model = new Model();
//			String value = model.login( "Username", "Password" ).getMessage();
//			System.out.println( value );
//			assertTrue( "Success".equals( value ) );
//			TaskResponse response = model.getAllProjectTasks( "57c4f83466d8de03003bea62" );
//			System.out.println( "Test Get All Project Tasks:\n" + response.toString() );
//		}
//	}
}


