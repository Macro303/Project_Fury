package tobedevelopers.project_fury;

import junit.framework.TestCase;

import tobedevelopers.project_fury.model.Model;

/**
 * Created by Macro303 on 16/08/2016.
 */
public class ModelTest extends TestCase{

	public void testModel(){
		String value = "Message: Howdy pardner, Test sucessful! Good job pal!";
		assertTrue( value.equals( new Model().get().toString() ) );
	}

	public void testGetUserModel(){
		String value = "Message: get all users";
		assertTrue( value.equals( new Model().getUser().toString() ) );
	}

	public void testPostUserModel(){
		String value = "User ID: 1337\nFirst Name: Jonah\nLast Name: Jackson";
		assertTrue( value.equals( new Model().postUser( new String[]{ "Jonah", "Jackson" } ).toString() ) );
	}

	public void testGetUserIdModel(){
		String value = "User ID: 112\nFirst Name: Seymour\nLast Name: Butts";
		assertTrue( value.equals( new Model().getUserId( 112 ).toString() ) );
	}

	public void testPutUserIdModel(){
		String value = "Message: update a user for user_id";
		assertTrue( value.equals( new Model().putUserId( 112 ).toString() ) );
	}

	public void testDeleteUserIdModel(){
		String value = "Message: delete user for user_id";
		assertTrue( value.equals( new Model().deleteUserId( 112 ).toString() ) );
	}
}
