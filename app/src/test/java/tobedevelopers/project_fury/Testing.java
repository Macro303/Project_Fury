package tobedevelopers.project_fury;

import com.google.gson.Gson;

import junit.framework.TestCase;

import tobedevelopers.project_fury.model.Task;

/**
 * Created by Macro303 on 30/08/2016.
 */
public class Testing extends TestCase{

	public void testTest(){
		Task task = new Task();
		System.out.println( task.toString() );
		String gson = new Gson().toJson( task );
		System.out.println( gson );
		Task task1 = new Gson().fromJson( gson, Task.class );
		System.out.println( task1.toString() );
	}
}
