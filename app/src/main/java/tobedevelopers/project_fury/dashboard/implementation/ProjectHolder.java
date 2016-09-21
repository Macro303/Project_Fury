package tobedevelopers.project_fury.dashboard.implementation;

import java.util.Arrays;
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

	public Column[] getColumns( String projectName ){
		Column[] columns = this.columns.get( projectName );
		Arrays.sort( columns );
		return columns;
	}

	public Project[] getProjects(){
		Arrays.sort( projects );
		return projects;
	}

	public Task[] getTasks( String projectName ){
		Task[] tasks = this.tasks.get( projectName );
		Arrays.sort( tasks );
		return tasks;
	}
}
