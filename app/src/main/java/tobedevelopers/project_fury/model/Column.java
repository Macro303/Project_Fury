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

	public String getColumnID(){
		return columnID;
	}

	public String getName(){
		return name;
	}

	public String getProjectID(){
		return projectID;
	}

	@Override
	public String toString(){
		return "Column{" +
			       "columnID='" + columnID + '\'' +
			       ", name='" + name + '\'' +
			       ", projectID='" + projectID + '\'' +
			       '}';
	}
}
