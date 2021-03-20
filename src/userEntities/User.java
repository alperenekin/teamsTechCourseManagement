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
	private String email;
	private String passwd;
	private String department;
	private List<Team> teams;
	
	public User() {
		
	}
	public User(String userName)
	{
		teams = new ArrayList<Team>();
		this.name = userName;
		this.id = UserUtil.generateId();		
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
}
