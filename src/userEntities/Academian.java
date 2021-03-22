package userEntities;

import java.util.ArrayList;

import teamEntities.Team;

public abstract class Academian extends User {
	final String domain = "iyte.edu.tr";

	public ArrayList<Team> ownedTeams;
	public Academian(String userName, int userId, String password)
	{
		super(userName,userId,password);
		String[] nameSurname = userName.split(" ");
		String name = nameSurname[0].toLowerCase();
		String surname = nameSurname[1].toLowerCase();
		super.setEmail(name+surname+"@"+domain);
		ownedTeams = new ArrayList<Team>();
	}
	
	public ArrayList<Team>  getOwnedTeams() {
		return this.ownedTeams;
	}
	public void addOwnedTeams(Team team) {
		this.ownedTeams.add(team);
	}
	public void removeOwnedTeams(Team team){
		ownedTeams.remove(team);
	}
}
