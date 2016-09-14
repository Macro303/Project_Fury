package tobedevelopers.project_fury.model;

import com.google.gson.annotations.SerializedName;

import java.util.Comparator;

/**
 * Created by Macro303 on 7/09/2016.
 */
public class Column{

	public static final Comparator< Column > comparator = new Comparator< Column >(){
		@Override
		public int compare( Column c1, Column c2 ){
			if( ( c1.position - c2.position ) != 0 )
				return c1.position - c2.position;
			else if( c1.name.compareTo( c2.name ) != 0 )
				return c1.name.compareTo( c2.name );
			return c1.columnID.compareTo( c2.columnID );
		}
	};
	@SerializedName( "_id" )
	private String columnID;
	private String name;
	@SerializedName( "projectParent" )
	private String projectID;
	private int position;

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
