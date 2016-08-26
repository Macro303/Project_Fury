package tobedevelopers.project_fury.model;

import java.util.Arrays;

/**
 * Created by Macro303 on 26/08/2016.
 */
public class ProjectResponse extends Response{

	private Project[] projects;

	public ProjectResponse( String message ){
		this( message, new Project[]{} );
	}

	public ProjectResponse( String message, Project[] projects ){
		super( message );
		this.projects = projects;
	}

	public Project[] getProjects(){
		return projects;
	}

	@Override
	public String toString(){
		return "ProjectResponse{" +
			       "projects=" + Arrays.toString( projects ) +
			       '}';
	}
}
