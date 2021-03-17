package userEntities;

import mediator.IIdentifiable;
import mediator.Mediator;
import teamEntities.Team;

public abstract class User implements IIdentifiable{
	
	private Mediator mediator;
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
	public int getId() {
		return id;
	}
	public Mediator getMediator() {
		return mediator;
	}
}
