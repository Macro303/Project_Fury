package tobedevelopers.project_fury.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Macro303 on 30/08/2016.
 */
public class Task{

	@SerializedName( "_id" )
	private String taskID;
	private String name;
	private String description;
	@SerializedName( "userAssigned" )
	private String assignee;
	private String projectParent;
	private Priority priority = Priority.UNASSIGNED;
	private String columnIn;

	public String getAssignee(){
		return assignee;
	}

	public String getColumnIn(){
		return columnIn;
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

	public String getProjectParent(){
		return projectParent;
	}

	public String getTaskID(){
		return taskID;
	}

	@Override
	public String toString(){
		return "Task{" +
			       "assignee='" + assignee + '\'' +
			       ", columnIn='" + columnIn + '\'' +
			       ", description='" + description + '\'' +
			       ", name='" + name + '\'' +
			       ", priority=" + priority +
			       ", projectParent='" + projectParent + '\'' +
			       ", taskID='" + taskID + '\'' +
			       '}';
	}

	public enum Priority{
		UNASSIGNED( 0, "Unassigned" ),
		LOW( 1, "Low" ),
		NORMAL( 2, "Normal" ),
		HIGH( 3, "High" );

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