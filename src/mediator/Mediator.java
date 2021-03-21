package mediator;

import java.util.ArrayList;
import java.util.List;

import fileOperations.FileIO;
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
	public boolean addTeam(Team team) // team in componentlerý(channel meeting gibi þeyler parametrede alýnýp burada da oluþturulabilir
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
	
	public boolean addUser(String userType, String username, List<String> teamIDs,String userId, String password){
		if(true) //author.getClass() == "Instructor"   this not how you check. fix it 
		{
			FileIO file = new FileIO();
			String oldLineElement = username; // this will help us to choose which line we will update.
			int id;
			String pwd;
			if(userId.isEmpty()) {
				id = MediatorUtil.generateId(userList);
				pwd = MediatorUtil.generatePassword();
			}else {
				id = Integer.parseInt(userId);
				pwd = password;
			}
			if(userType.toUpperCase().equals("INSTRUCTOR")) { //instructor vs enum yapýlabilri
				Instructor instructor = new Instructor(username,id,pwd);
				if(teamIDs != null) {
					for(String teamName : teamIDs) {
						Team team = findTeamOfUser(teamName);
						instructor.addTeam(team);
					}
					String newLine = instructor.toString();
					file.replaceLines(oldLineElement, teamIDs.get(0), newLine, "userList");
				}else {
					String newLine = instructor.toString();
					file.replaceLines(oldLineElement, null, newLine, "userList");
				}
				userList.add(instructor);
			}
			else if(userType.toUpperCase().equals("TEACHÝNG ASSÝSTANT")) { // türkçe karakter olarak upper case yaptý?
				TeachingAsistant teachingAsistant = new TeachingAsistant(username,id,pwd);
				if(teamIDs != null) {
					for(String teamName : teamIDs) {
						Team team = findTeamOfUser(teamName);
						teachingAsistant.addTeam(team);
					}
					String newLine = teachingAsistant.toString();
					file.replaceLines(oldLineElement, teamIDs.get(0), newLine, "userList");
				}else {
					String newLine = teachingAsistant.toString();
					file.replaceLines(oldLineElement, null, newLine, "userList");
				}
				userList.add(teachingAsistant);
				
			}
			else{
				Student student = new Student(username,id,pwd);
				if(teamIDs != null) {
					for(String teamName : teamIDs) {
						Team team = findTeamOfUser(teamName);
						student.addTeam(team);
					}
					String newLine = student.toString();
					file.replaceLines(oldLineElement, teamIDs.get(0), newLine, "userList");
				}else {
					String newLine = student.toString();
					file.replaceLines(oldLineElement, null, newLine, "userList");
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
	
	public void addTeamOwner() { // this is for first time adding of team owners, only instructors can be team owners.
		for(User user: userList) {
			if(user instanceof Instructor) {
				for(Team team : teamList) {
					System.out.println(team.toString());
					for(Team userTeam : user.getTeams()) {
						if(userTeam.getId().equals(team.getId())) {
							team.addOwner(user);
						}
					}
				}
			}
		}
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
