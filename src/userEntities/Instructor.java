package userEntities;

import teamEntities.Team;

public class Instructor extends Academian {
	public	Instructor(String userName) {
		super(userName);
	}
	public void createTeam(String teamName)
	{
		this.getMediator().addTeam(this,teamName);
	}
	public void addTeamOwner(Team team)
	{
		this.getMediator().promoteUser(team);
	}
}
