package mediator;

import teamEntities.Team;
import userEntities.User;

public class Mediator implements IMediator {
	private Team[] teamList;
	private User[] userList;

	public Mediator()
	{
		
	}
	public boolean addTeam(User author,String teamName)
	{
		if(true) //author.getClass() == "Instructor"   this not how you check. fix it 
		{
			int id = MediatorUtil.generateId(teamList);
			return true;
		}
		else
			//throw new UnauthorizedUserOperationException(); exception handling
			return false;
	}
	@Override
	public boolean findTeam() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean removeTeam(Team team) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean addMeetingChannel(Team team,String publicity,String name) {
		return team.addChannel(publicity,name);
	}
	@Override
	public boolean removeMeetingChannel() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean updateMeetingChannelTime() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean updateMeetingChannelParticipants() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean addMememberToTeam() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean removeMemberToTeam() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean numberOfStudents() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean numberOfInstructors() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean numberOfTeachingAssistants() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean showMeetingChanels() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean showMeetingTime() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean showParticipants() {
		// TODO Auto-generated method stub
		return false;
	}
	public void promoteUser(Team team) {
		/**
		 * find teaching assistants 
		 * ask them whitch one is gonna be teamowner
		 * take the choosen one , update its owned teams, update team
		 */
	}
}
