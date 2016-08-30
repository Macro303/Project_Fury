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

	private enum Priority{
		UNASSIGNED( 0 ),
		LOW( 1 ),
		NORMAL( 2 ),
		HIGH( 3 );

		private int value;

		Priority( int value ){
			this.value = value;
		}

		public int getValue(){
			return value;
		}

		@Override
		public String toString(){
			return "Priority{" +
				       "value=" + value +
				       '}';
		}
	}
}