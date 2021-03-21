package userEntities;

import java.util.ArrayList;
import java.util.List;

import mediator.IIdentifiable;
import mediator.Mediator;
import teamEntities.Team;

public abstract class User{
	
	private Mediator mediator;
	private String name;
	private int id;
	private String email; //also used as login username
	private String passwd;
	private String department;
	private List<Team> teams;
	
	public User() {
		
	}
	public User(String userName, int userId,String password)
	{
		teams = new ArrayList<Team>();
		this.setName(userName);
		this.id = userId;
		this.setPasswd(password);
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public int getId() {
		return id;
	}
	public Mediator getMediator() {
		return mediator;
	}
	public List<Team> getTeams() {
		return teams;
	}
	public void addTeam(Team team) {
		this.teams.add(team);
	}
	
	abstract public String toString();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
}
