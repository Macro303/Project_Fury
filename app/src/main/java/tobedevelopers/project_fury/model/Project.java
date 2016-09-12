package tobedevelopers.project_fury.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

/**
 * Created by Macro303 on 26/08/2016.
 */
public class Project implements Parcelable{

	public static final Parcelable.Creator< Project > CREATOR = new Parcelable.Creator< Project >(){
		@Override
		public Project createFromParcel( Parcel parcel ){
			return new Project( parcel );
		}

		@Override
		public Project[] newArray( int size ){
			return new Project[ size ];
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

	private Project( Parcel parcel ){
		projectID = parcel.readString();
		name = parcel.readString();
		description = parcel.readString();
		usersOnProject = parcel.createStringArray();
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

	@Override
	public int describeContents(){
		return 0;
	}

	@Override
	public void writeToParcel( Parcel parcel, int i ){
		parcel.writeString( projectID );
		parcel.writeString( name );
		parcel.writeString( description );
		parcel.writeStringArray( usersOnProject );
	}
}
