package teamTechApp;

import java.util.ArrayList;
import java.util.List;

import fileOperations.FileIO;
import teamEntities.Channel;
import teamEntities.Meeting;
import teamEntities.Team;
import userEntities.Instructor;
import userEntities.Student;
import userEntities.TeachingAsistant;
import userEntities.User;

public class TeamsTechApp {
	List<User> userList = new ArrayList<User>();
	List<Team> teamList = new ArrayList<Team>();
	public void init() {
		FileIO fileIO = new FileIO();
		ArrayList<ArrayList<String>> userStrings = fileIO.readCsv("userList");
		ArrayList<ArrayList<String>> teamStrings = fileIO.readCsv("teamList");
		for(int i=1; i<userStrings.size() ; i++) {
			String userType = userStrings.get(i).get(0);
			String userName = userStrings.get(i).get(1);
			List<String> teamIDs;
			if(userStrings.get(i).size() > 5) { // it means this student has a team/teams
				teamIDs = userStrings.get(i).subList(5, userStrings.get(i).size());
			}
			//BURADA USER YARATTIK ANCAK TEAMIDs LERI KULLANMADIK
			User newUser = createUser(userType,userName);
			userList.add(newUser);
		}
		
		for(int i=1; i< teamStrings.size() ; i++) {
			String teamName = teamStrings.get(i).get(0);
			String teamId = teamStrings.get(i).get(1);
			String defaultChannelName = teamStrings.get(i).get(2);
			String meetingTime = teamStrings.get(i).get(3);
			
			Meeting meeting = new Meeting(meetingTime);
			Channel channel = new Channel(defaultChannelName,meeting);
			Team team = new Team(teamName,teamId,channel);
			teamList.add(team);
		}
	}
	
	private User createUser(String userType, String username) {
		if(userType.toUpperCase().equals("INSTRUCTOR")) { //instructor vs enum yapýlabilri
			Instructor instructor = new Instructor(username);
			return instructor;
		}
		if(userType.toUpperCase().equals("TEACHÝNG ASSÝSTANT")) { // türkçe karakter olarak upper case yaptý?
			TeachingAsistant teachingAsistant = new TeachingAsistant(username);
			return teachingAsistant;
		}
		else{
			Student student = new Student(username);
			//System.out.print(student.getEmail());
			return student;
		}
	}
}
