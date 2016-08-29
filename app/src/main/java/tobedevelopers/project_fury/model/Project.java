package tobedevelopers.project_fury.model;

import java.util.Arrays;

/**
 * Created by Macro303 on 26/08/2016.
 */
public class Project{

	private String name;
	private String description;
	private String[] usersOnProject;

	public Project( String description, String name, String[] usersOnProject ){
		this.description = description;
		this.name = name;
		this.usersOnProject = usersOnProject;
	}

	@Override
	public String toString(){
		return "Project{" +
			       "description='" + description + '\'' +
			       ", name='" + name + '\'' +
			       ", usersOnProject=" + Arrays.toString( usersOnProject ) +
			       '}';
	}
}
