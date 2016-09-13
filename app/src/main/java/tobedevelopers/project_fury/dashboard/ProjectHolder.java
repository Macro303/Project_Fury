package tobedevelopers.project_fury.dashboard;

import java.util.HashMap;

import tobedevelopers.project_fury.model.Column;
import tobedevelopers.project_fury.model.Project;
import tobedevelopers.project_fury.model.Task;

/**
 * Created by Macro303 on 7/09/2016.
 */
public class ProjectHolder{

	private Project[] projects;
	private HashMap< String, Task[] > tasks;
	private HashMap< String, Column[] > columns;

	public ProjectHolder( Project[] projects ){
		this.projects = projects;
		tasks = new HashMap<>();
		columns = new HashMap<>();
	}

	public void addTasks( String projectName, Task[] tasks ){
		this.tasks.put( projectName, tasks );
	}

	public void addColumns( String projectName, Column[] columns ){
		this.columns.put( projectName, columns );
	}

	public HashMap< String, Column[] > getColumns(){
		return columns;
	}

	public Project[] getProjects(){
		return projects;
	}

	public HashMap< String, Task[] > getTasks(){
		return tasks;
	}
}
