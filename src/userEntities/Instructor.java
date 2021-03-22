package userEntities;

import mediator.UnauthorizedUserOperationException;
import teamEntities.Team;

public class Instructor extends Academian {
	public	Instructor(String userName, int userId,String password) {
		super(userName,userId,password);
	}
	
	public void addTeamOwner(Team team,User user) throws UnauthorizedUserOperationException
	{
		this.getMediator().promoteUser(team,user,this);
	}
	@Override
	public String toString() { //this method needed to convert an object to file line
		String type = "Instructor";
		String username = super.getName();
		String userid = String.valueOf(super.getId());
		String email = super.getEmail();
		String password = super.getPasswd();
		String teams = "";
		if(super.getTeams().size() > 0) { // if has teams
			for(Team t: super.getTeams()) {
				teams += t.getId()+",";
			}
			teams = teams.substring(0,teams.length()-1);
		}
		
		return type + "," + username + "," + userid +"," + email + "," + password +"," + teams;
	}
	public void addOwnedTeams(Team team) {
		super.addOwnedTeams(team);
	}
}
