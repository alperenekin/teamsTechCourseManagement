package mediator;

import java.util.ArrayList;
import java.util.List;

import teamEntities.Team;
import userEntities.Instructor;
import userEntities.Student;
import userEntities.TeachingAsistant;
import userEntities.User;

public class Mediator implements IMediator {
	private ArrayList<Team> teamList;
	private ArrayList<User> userList;

	public Mediator()
	{
		teamList = new ArrayList<Team>();
		userList = new ArrayList<User>();
	}
	public boolean addTeam(User author,String teamName, Team team)
	{
		if(true) //author.getClass() == "Instructor"   this not how you check. fix it 
		{
			teamList.add(team);
			return true;
		}
		else
			//throw new UnauthorizedUserOperationException(); exception handling
			return false;
	}
	
	public boolean addUser(String userType, String username, List<String> teamIDs,String userId){
		if(true) //author.getClass() == "Instructor"   this not how you check. fix it 
		{
			int id;
			if(userId.isEmpty()) {
				id = MediatorUtil.generateId(userList);
			}else {
				id = Integer.parseInt(userId);
			}
			if(userType.toUpperCase().equals("INSTRUCTOR")) { //instructor vs enum yapýlabilri
				
				Instructor instructor = new Instructor(username,id);
				if(teamIDs != null) {
					for(String teamName : teamIDs) {
						Team team = findTeamOfUser(teamName);
						instructor.addTeam(team);
					}
				}
				userList.add(instructor);

			}
			else if(userType.toUpperCase().equals("TEACHÝNG ASSÝSTANT")) { // türkçe karakter olarak upper case yaptý?
				TeachingAsistant teachingAsistant = new TeachingAsistant(username,id);
				if(teamIDs != null) {
					for(String teamName : teamIDs) {
						Team team = findTeamOfUser(teamName);
						teachingAsistant.addTeam(team);
					}
				}
				userList.add(teachingAsistant);
			}
			else{
				Student student = new Student(username,id);
				if(teamIDs != null) {
					for(String teamName : teamIDs) {
						Team team = findTeamOfUser(teamName);
						student.addTeam(team);
					}
				}
				userList.add(student);

			}
		}
		return true;
	}
	
	private Team findTeamOfUser(String teamID) {
		for(Team team : teamList) {
			if(teamID.equals(team.getId())){
				return team;
			}		
		}
		return null;
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
		return false;
		//return team.addChannel(publicity,name);
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
	@Override
	public boolean addTeam(User author, String teamName) {
		// TODO Auto-generated method stub
		return false;
	}
	public User findUser(String userName,String passwd) {
		
		for(int i = 0 ; i < userList.size(); i++)
		{
			
			if(userList.get(i).getEmail().contentEquals(userName) && userList.get(i).);
		}
		return null;
		
	}
}
