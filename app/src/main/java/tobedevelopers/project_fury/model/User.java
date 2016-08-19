package tobedevelopers.project_fury.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by Macro303 on 16/08/2016.
 */
public class User{

	private String username;
	private String password;
	private Boolean admin;
	private String email;
	@SerializedName( "created_at" )
	private Date createdAt;
	@SerializedName( "updated_at" )
	private Date updatedAt;
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

	public User( Boolean admin, Date createdAt, String email, Date updatedAt, String password, String username ){
		this.admin = admin;
		this.createdAt = createdAt;
		this.email = email;
		this.updatedAt = updatedAt;
		this.password = password;
		this.username = username;
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

	public Boolean getAdmin(){
		return admin;
	}

	public Date getCreatedAt(){
		return createdAt;
	}

	public String getEmail(){
		return email;
	}

	public String getPassword(){
		return password;
	}

	public Date getUpdatedAt(){
		return updatedAt;
	}

	public String getUsername(){
		return username;
	}

	/*@Override
	public String toString(){
		return "User ID: " + userID +
			       "\nFirst Name: " + firstName +
			       "\nLast Name: " + lastName;
	}*/

	@Override
	public String toString(){
		return "Username: " + username +
			       "\nPassword: " + password +
			       "\nEmail: " + email +
			       "\nAdmin: " + admin +
			       "\nCreated At: " + createdAt +
			       "\nUpdated At: " + updatedAt;
	}
}
