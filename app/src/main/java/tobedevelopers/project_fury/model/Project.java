package tobedevelopers.project_fury.model;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by Macro303 on 26/08/2016.
 */
public class Project{

	public static final Comparator< Project > comparator = new Comparator< Project >(){
		@Override
		public int compare( Project p1, Project p2 ){
			if( p1.name.compareToIgnoreCase( p2.name ) != 0 )
				return p1.name.compareToIgnoreCase( p2.name );
			return p1.projectID.compareToIgnoreCase( p2.projectID );
		}
	};

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
