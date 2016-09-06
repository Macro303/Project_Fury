package tobedevelopers.project_fury.model;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

/**
 * Created by Macro303 on 26/08/2016.
 */
public class Project{

	@SerializedName( "_id" )
	private String projectID;
	private String name;
	private String description;
	private String[] usersOnProject;

	public Project( String description, String name, String[] usersOnProject ){
		this.description = description;
		this.name = name;
		this.usersOnProject = usersOnProject;
	}

	public String getDescription(){
		return description;
	}

	public String getName(){
		return name;
	}

	public String getProjectID(){
		return projectID;
	}

	public String[] getUsersOnProject(){
		return usersOnProject;
	}

	@Override
	public String toString(){
		return "Project{" +
			       "description='" + description + '\'' +
			       ", name='" + name + '\'' +
			       ", projectID='" + projectID + '\'' +
			       ", usersOnProject=" + Arrays.toString( usersOnProject ) +
			       '}';
	}
}
