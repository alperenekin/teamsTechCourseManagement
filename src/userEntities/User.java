package userEntities;

import teamEntities.Team;

public abstract class User {

	private String name;
	private int id;
	private String email;
	private String passwd;
	private String department;
	private Team[] teams;
	
	public User() {
		
	}
	public User(String userName)
	{
		this.name = userName;
		this.id = UserUtil.generateId();		
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Team[] getTeams() {
		return teams;
	}
}
