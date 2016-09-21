package tobedevelopers.project_fury.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Macro303 on 30/08/2016.
 */
public class Task implements Comparable< Task >{

	@SerializedName( "_id" )
	private String taskID;
	private String name;
	private String description;
	@SerializedName( "userAssigned" )
	private String assignee;
	@SerializedName( "projectParent" )
	private String projectID;
	private Priority priority = Priority.UNASSIGNED;
	@SerializedName( "columnIn" )
	private String columnID;

	@Override
	public int compareTo( Task other ){
		if( priority.compareTo( other.priority ) != 0 )
			return priority.compareTo( other.priority );
		if( name.compareToIgnoreCase( other.name ) != 0 )
			return name.compareToIgnoreCase( other.name );
		return taskID.compareToIgnoreCase( other.taskID );
	}

	public String getAssignee(){
		return assignee;
	}

	public String getColumnID(){
		return columnID;
	}

	public String getDescription(){
		return description;
	}

	public String getName(){
		return name;
	}

	public Priority getPriority(){
		return priority;
	}

	public String getProjectID(){
		return projectID;
	}

	public String getTaskID(){
		return taskID;
	}

	@Override
	public String toString(){
		return "Task{" +
			       "assignee='" + assignee + '\'' +
			       ", columnID='" + columnID + '\'' +
			       ", description='" + description + '\'' +
			       ", name='" + name + '\'' +
			       ", priority=" + priority +
			       ", projectID='" + projectID + '\'' +
			       ", taskID='" + taskID + '\'' +
			       '}';
	}

	public enum Priority{
		HIGH( 3, "High" ),
		NORMAL( 2, "Normal" ),
		LOW( 1, "Low" ),
		UNASSIGNED( 0, "Unassigned" );

		private int value;
		private String nameValue;

		Priority( int value, String nameValue ){
			this.value = value;
			this.nameValue = nameValue;
		}

		public int getValue(){
			return value;
		}

		public String getNameValue(){
			return nameValue;
		}

		@Override
		public String toString(){
			return "Priority{" +
				       "value=" + value +
				       '}';
		}
	}
}