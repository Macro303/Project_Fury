package tobedevelopers.project_fury.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Macro303 on 7/09/2016.
 */

public class Column implements Comparable< Column >{

	@SerializedName( "_id" )
	private String columnID;
	private String name;
	@SerializedName( "projectParent" )
	private String projectID;
	private int position;

	@Override
	public int compareTo( Column other ){
		if( position - other.position != 0 )
			return position - other.position;
		if( name.compareToIgnoreCase( other.name ) != 0 )
			return name.compareToIgnoreCase( other.name );
		return columnID.compareToIgnoreCase( other.columnID );
	}

	public String getColumnID(){
		return columnID;
	}

	public String getName(){
		return name;
	}

	public String getProjectID(){
		return projectID;
	}

	public int getPosition(){
		return position;
	}

	public void setPosition( int position ){
		this.position = position;
	}

	@Override
	public String toString(){
		return "Column{" +
			       "columnID='" + columnID + '\'' +
			       ", name='" + name + '\'' +
			       ", position=" + position +
			       ", projectID='" + projectID + '\'' +
			       '}';
	}
}
