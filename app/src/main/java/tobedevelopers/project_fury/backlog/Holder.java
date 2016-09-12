package tobedevelopers.project_fury.backlog;

import java.util.HashMap;

import tobedevelopers.project_fury.model.Project;
import tobedevelopers.project_fury.model.Task;

/**
 * Created by Macro303 on 12/09/2016.
 */
public class Holder{

	private Project[] projects;
	private HashMap< String, Task[] > tasks;

	public Holder( Project[] projects ){
		this.projects = projects;
		tasks = new HashMap<>();
	}

	public void addTasks( String projectName, Task[] tasks ){
		this.tasks.put( projectName, tasks );
	}

	public Project[] getProjects(){
		return projects;
	}

	public HashMap< String, Task[] > getTasks(){
		return tasks;
	}
}
