package tobedevelopers.project_fury.model;

import java.util.Arrays;

/**
 * Created by Macro303 on 30/08/2016.
 */
public class TaskResponse extends Response{

	private Task[] tasks;

	public TaskResponse( String message ){
		this( message, new Task[]{} );
	}

	public TaskResponse( String message, Task[] tasks ){
		super( message );
		this.tasks = tasks;
	}

	public Task[] getTasks(){
		return tasks;
	}

	@Override
	public String toString(){
		return "TaskResponse{" +
			       "message='" + message + '\'' +
			       ", tasks=" + Arrays.toString( tasks ) +
			       '}';
	}
}
