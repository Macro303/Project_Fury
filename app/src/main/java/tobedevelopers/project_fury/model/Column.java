package tobedevelopers.project_fury.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Macro303 on 7/09/2016.
 */
public class Column{

	@SerializedName( "_id" )
	private String columnID;
	private String name;
	@SerializedName( "projectParent" )
	private String projectID;
	private boolean userDeletable;

	public String getColumnID(){
		return columnID;
	}

	public String getName(){
		return name;
	}

	public String getProjectID(){
		return projectID;
	}

	public boolean isUserDeletable(){
		return userDeletable;
	}

	@Override
	public String toString(){
		return "Column{" +
			       "columnID='" + columnID + '\'' +
			       ", name='" + name + '\'' +
			       ", projectID='" + projectID + '\'' +
			       ", userDeletable=" + userDeletable +
			       '}';
	}
}
