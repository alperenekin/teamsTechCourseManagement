package mediator;

import java.util.ArrayList;
import java.util.List;

import fileOperations.FileIO;
import teamEntities.*;
import userEntities.Academian;
import userEntities.Instructor;
import userEntities.Student;
import userEntities.TeachingAsistant;
import userEntities.User;

public class Mediator implements IMediator {
	private ArrayList<Team> teamList;
	private ArrayList<User> userList;
	private FileIO file = new FileIO();

	public ArrayList<User> getUserList() { // silinebilir
		return userList;
	}
	public Mediator()
	{
		teamList = new ArrayList<Team>();
		userList = new ArrayList<User>();
	}
	@Override
	public boolean addTeam(Team team, boolean isFromFile,User currentUser) { //We also add teams while reading from file which is different from user operation.
		if(isFromFile) //author.getClass() == "Instructor"   this not how you check. fix it 
		{
			teamList.add(team);
			return true;
		}
		else {
			if(currentUser instanceof Instructor) {
				teamList.add(team);
				((Instructor) currentUser).addOwnedTeams(team);
				currentUser.addTeam(team);
				file.addLine(team.toString(),"teamList");
			return true;
			}
			else {
				try {
					throw new UnauthorizedUserOperationException();
				} catch (UnauthorizedUserOperationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return true;

	}

	@Override
	public boolean addUser(String userType, String username, List<String> teamIDs,String userId, String password){
		if(true)
		{
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
						if(team != null) {
							instructor.addTeam(team);// we first teams then users so it is best to add team members as users created.
							instructor.addOwnedTeams(team);
							team.addMember(instructor);
							team.addOwner(instructor);
						}				
					}
					String newLine = instructor.toString();
					file.replaceLines(oldLineElement, teamIDs.get(0), newLine, "userList");
				}else {
					String newLine = instructor.toString();
					file.replaceLines(oldLineElement, null, newLine, "userList");
				}
				userList.add(instructor);
			}
			else if(userType.toUpperCase().equals("TEACHİNG ASSİSTANT")) { // türkçe karakter olarak upper case yaptý?
				TeachingAsistant teachingAsistant = new TeachingAsistant(username,id,pwd);
				if(teamIDs != null) {
					for(String teamName : teamIDs) {
						Team team = findTeamOfUser(teamName);
						if(team != null) {
							team.addMember(teachingAsistant);// we first teams then users so it is best to add team members as users created.
							teachingAsistant.addTeam(team);
						}
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
						if(team != null) {
							team.addMember(student);
							student.addTeam(team); // we first teams then users so it is best to add team members as users created.
						}
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

	@Override
	public void monitorTeamsOfUser(User currentUser){
		for(int i=0;i< currentUser.getTeams().size();i++) {
			System.out.print(i+1);
			System.out.println("-" + currentUser.getTeams().get(i).getId());
		}
	}

	@Override
	public void monitorTeamDetailsOfUser(int index, User currentUser,ArrayList<PrivateChannel> userPrivateChannels){
		Team team = currentUser.getTeams().get(index);
		String teamName = team.getTeamName();
		String teamId = team.getId();
		ArrayList<Channel> channels = team.getChannels();

		DefaultChannel defaultChannel = team.findDefaultChannel();
		String channelName = defaultChannel.getName();
		String meetingTime = defaultChannel.getMeeting().getMeetingTime();
		System.out.println("Team Name:".toUpperCase() + teamName);
		System.out.println("Team Id:".toUpperCase() + teamId);
		System.out.println("Default Channel Name:".toUpperCase() + channelName);
		System.out.println("Default Channel Meeting Time:".toUpperCase() + meetingTime);
	}

	public void monitorPrivateChanelsOfUser(ArrayList<PrivateChannel> userPrivateChannels,int count) {
		if(userPrivateChannels.isEmpty()) {
			System.out.println("You dont have any private channel yet");
		}else {
			for(Channel privateChannels : userPrivateChannels) {
				System.out.println("PRIVATE CHANNEL: " + count +"-"+ privateChannels.getName());
				if(privateChannels.getMeeting() != null) {
					System.out.println("MEETING TIME" +":"+ privateChannels.getMeeting().getMeetingTime());
				}
				System.out.println("PARTICIPANTS" +":"+ findNamesOfParticipants(((PrivateChannel) privateChannels).getParticipants()));
				count++;
			}
		}
	}


	private String findNamesOfParticipants(ArrayList<String> participants) {
		String output = "";
		for(String userId : participants) {
			for(User user : userList) {
				if(userId.equals(String.valueOf(user.getId()))) {
					output += (user.getName() + "");
				}
			}
		}
		return output;
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
	public boolean removeTeam(Team team) {
		boolean isSuccess = teamList.remove(team);
		for(User user: userList) {
			if(user.getTeams().contains(team)) {
				user.getTeams().remove(team);
				if(user instanceof Academian)
				{
					((Academian) user).getOwnedTeams().remove(team);
				}
				file.replaceLines(String.valueOf(user.getId()), user.getName(), user.toString(),"userList");
			}
		}
		file.deleteLines(team.getId(), "teamList");
		return false;
	}
	@Override
	public boolean addMeetingChannel(Team team,String channelName,String meetingTime, boolean isPrivate, String creator) {
		if(isPrivate) {
			PrivateChannel privateChannel = new PrivateChannel(channelName, new Meeting(meetingTime));
			privateChannel.addParticipant(creator);
			team.addPrivateChannel(privateChannel);
			file.replaceLines(team.getId(), null, team.toString(), "teamList"); //add new info to file
			return true;
		}
		return false;
		//return team.addChannel(publicity,name);
	}
	@Override
	public boolean removeMeetingChannel(Team team, Channel channel) {
		team.removeChannel(channel);
		file.replaceLines(team.getId(), null, team.toString(), "teamList"); //add new info to file
		return true;
	}

	@Override
	public boolean updateMeetingChannelTime(String meetingTime, Channel channel, Team team) {
		channel.getMeeting().update(meetingTime);
		file.replaceLines(team.getId(), null, team.toString(), "teamList"); //add new info to file
		return true;
	}

	@Override
	public boolean addParticipantToChannel(String  userId, PrivateChannel channel, Team team) {
		User user = findUserFromId(userId);
		if(user != null){
			if(user.getTeams().contains(team)) {
				channel.addParticipant(userId);
				file.replaceLines(team.getId(),  null, team.toString(), "teamList"); //add new info to file
				return true;
			}else {
				System.out.println("This user does not belong to team");
				return false;
			}
		}else{
			System.out.println("User not exist");
			return false;
		}
	}
	public boolean removeParticipantFromChannel(String  userId, PrivateChannel channel, Team team)
	{
		boolean result = channel.removeParticipant(userId);
		file.replaceLines(team.getId(),  null, team.toString(), "teamList");
		return result;
		
	}

	@Override
	public boolean addMememberToTeam(String id,User currentUser,Team chosenTeam1) {
		if(currentUser instanceof Academian)
		{
			for (int i = 0 ; i< ((Academian) currentUser).getOwnedTeams().size();i++) {
				if( ((Academian) currentUser).getOwnedTeams().get(i).equals(chosenTeam1)) {
					User chosenUser = findUserFromId(id);
					chosenTeam1.addMember(chosenUser);
					chosenUser.addTeam(chosenTeam1);
					file.replaceLines(chosenTeam1.getId(), null, chosenTeam1.toString(), "teamList");
					file.replaceLines(String.valueOf(chosenUser.getId()), chosenUser.getName(), chosenUser.toString(), "userList");
					
				}
			}
		}
		
		return false;
	}

	@Override
	public int numberOfStudents() {
		int totalStudents = 0;
		for(int i = 0 ; i < userList.size();i++)
		{			
			if(userList.get(i) instanceof Student)
			{
				totalStudents++;
			}
		}
		return totalStudents;
	}
	@Override
	public int numberOfInstructors() {
		int totalInstructors = 0;
		for(int i = 0 ; i < userList.size();i++)
		{			
			if(userList.get(i) instanceof Instructor)
			{
				totalInstructors++;
			}
		}
		return totalInstructors;
	}
	@Override
	public int numberOfTeachingAssistants() {
		int totalTeachingAssistants = 0;
		for(int i = 0 ; i < userList.size();i++)
		{			
			if(userList.get(i) instanceof TeachingAsistant)
			{
				totalTeachingAssistants++;
			}
		}
		return totalTeachingAssistants;
	}

	public void promoteUser(Team team,User user,User promoter) throws UnauthorizedUserOperationException {
		try{
			if(promoter instanceof Instructor && user instanceof TeachingAsistant)
			{
				team.addOwner(user);
			}
			else if(promoter instanceof Instructor && user instanceof Student){
				throw new UnauthorizedUserOperationException("Students can not be team owner!");
			}
			else if(promoter instanceof TeachingAsistant && user instanceof TeachingAsistant){
				if(team.getOwners().contains(promoter)){
					team.addOwner(user);
				}else{
					throw new UnauthorizedUserOperationException("This assistant is not team owner!");
				}
			}
			else if(promoter instanceof TeachingAsistant && user instanceof Student){
				throw new UnauthorizedUserOperationException("Students can not be team owner!");
			}
		}catch(UnauthorizedUserOperationException e){
			System.out.println(e.getMessage());
		}

	}
	
	public void addTeamOwner() { // this is for first time adding of team owners, only instructors can be team owners.
		for(User user: userList) {
			if(user instanceof Instructor) {
				for(Team team : teamList) {
					for(Team userTeam : user.getTeams()) {
						if(userTeam.getId().equals(team.getId())) {
							team.addOwner(user);
						}
					}
				}
			}
		}
	}
	
	public User findUser(String userName,String passwd) {
		User returnUser= null; 
		for(int i = 0 ; i < userList.size(); i++)
		{
			
			if(userList.get(i).getEmail().contentEquals(userName) && userList.get(i).getPasswd().equals(passwd)) {
				returnUser = userList.get(i);
			}
		}
		return returnUser;
	}
	
	private User findUserFromId(String id) {
		User foundUser = null;
		for(User user : userList) {
			if(user.getId() == Integer.parseInt(id)){
				foundUser = user;
			}
		}
		return foundUser;
	}
}
