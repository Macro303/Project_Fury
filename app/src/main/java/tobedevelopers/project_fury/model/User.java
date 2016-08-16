package tobedevelopers.project_fury.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Macro303 on 16/08/2016.
 */
public class User{

	private int userID;
	@SerializedName( "first-name" )
	private String firstName;
	@SerializedName( "last-name" )
	private String lastName;

	public User( int userID, String firstName, String lastName ){
		this.userID = userID;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public int getUserID(){
		return userID;
	}

	public String getFirstName(){
		return firstName;
	}

	public String getLastName(){
		return lastName;
	}

	@Override
	public String toString(){
		return "User ID: " + userID +
			       "\nFirst Name: " + firstName +
			       "\nLast Name: " + lastName;
	}
}
