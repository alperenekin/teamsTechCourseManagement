package userEntities;

import java.util.ArrayList;

import teamEntities.Team;

public abstract class Academian extends User {
	final String domain = "iyte.edu.tr";

	private ArrayList<Team> ownedTeams;
	public Academian(String userName, int userId, String password)
	{
		super(userName,userId,password);
		String[] nameSurname = userName.split(" ");
		String name = nameSurname[0].toLowerCase();
		String surname = nameSurname[1].toLowerCase();
		super.setEmail(name+surname+"@"+domain);
	}
	public void addUser()
	{
		// TODO 
	}
	public void addOwnedTeams(Team team) {
		ownedTeams.add(team);
	}
}
