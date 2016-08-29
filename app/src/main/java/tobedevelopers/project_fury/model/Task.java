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
	private String userAssigned;
	private String projectParent;
	private Priority priority = Priority.UNASSIGNED;

	@Override
	public String toString(){
		return "Task{" +
			       "description='" + description + '\'' +
			       ", taskID='" + taskID + '\'' +
			       ", name='" + name + '\'' +
			       ", userAssigned='" + userAssigned + '\'' +
			       ", projectParent='" + projectParent + '\'' +
			       ", priority=" + priority +
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
