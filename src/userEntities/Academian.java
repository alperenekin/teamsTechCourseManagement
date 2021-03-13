package userEntities;

import teamEntities.Team;

public abstract class Academian extends User {
	final String domain = "iyte.edu.tr";

	//private Team ownedTeams;
	public Academian(String userName)
	{
		super(userName);
		String[] nameSurname = userName.split(" ");
		String name = nameSurname[0].toLowerCase();
		String surname = nameSurname[1].toLowerCase();
		super.setEmail(name+surname+"@"+domain);
	}
	public void addUser()
	{
		// TODO 
	}
}
