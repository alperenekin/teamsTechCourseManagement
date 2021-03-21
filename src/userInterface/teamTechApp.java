package userInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fileOperations.FileIO;
import mediator.Mediator;
import teamEntities.Channel;
import teamEntities.Meeting;
import teamEntities.Team;
import userEntities.Instructor;
import userEntities.User;

public class teamTechApp {
	public static void main(String[] args) {
		Mediator mediator = new Mediator();
		/*Instructor i = new Instructor("TUGKAN TUGLULAR",11,"abc");
		i.addTeam(new Team("AAA","CENG211",null));
		i.addTeam(new Team("BBB","CENG431",null));
		System.out.println(i.toString());*/
		initializeApp(mediator);
		User currentUser  = UIUtil.loginScreen(mediator);
		while(true) {
			UIUtil.performCurrentUserTasks(mediator,currentUser);
		}
			
	}
	
	private static void initializeApp(Mediator mediator) {
		FileIO fileIO = new FileIO();
		ArrayList<ArrayList<String>> userStrings = fileIO.readCsv("userList");
		ArrayList<ArrayList<String>> teamStrings = fileIO.readCsv("teamList");

		for(int i=1; i< teamStrings.size() ; i++) {
			ArrayList<Channel> channelsOfATeam = new ArrayList<Channel>();
			String teamName = teamStrings.get(i).get(0);
			String teamId = teamStrings.get(i).get(1);
			String defaultChannelName = teamStrings.get(i).get(2);
			String meetingTime = teamStrings.get(i).get(3);
			if(teamStrings.get(i).size() > 4) {
				String newChannelName = teamStrings.get(i).get(4);
				String newMeetingTime = teamStrings.get(i).get(5);
				List<String> participants = Arrays.asList(teamStrings.get(i).get(6).split(","));
				
				if(newMeetingTime.isEmpty()) {
					Channel newChannel = new Channel(newChannelName,"private",null);
					channelsOfATeam.add(newChannel);
				}else {
					Meeting newMeeting = new Meeting(newMeetingTime);
					Channel newChannel = new Channel(newChannelName,"private",newMeeting);
					channelsOfATeam.add(newChannel);
				}

			}
			Meeting meeting = new Meeting(meetingTime);
			Channel channel = new Channel(defaultChannelName,"public",meeting);
			channelsOfATeam.add(channel);
			
			Team team = new Team(teamName,teamId,channelsOfATeam);
			mediator.addTeam(null, null, team);
		}
		
		for(int i=1; i<userStrings.size() ; i++) {
			String userType = userStrings.get(i).get(0);
			String userName = userStrings.get(i).get(1);
			String userId;
			String password;
			if(userStrings.get(i).size() == 2) {
				userId = "";	
				password ="";
			}else {
				userId = userStrings.get(i).get(2);
				password = userStrings.get(i).get(4);
			}

			List<String> teamIDs;
			if(userStrings.get(i).size() > 5) { // it means this student has a team/teams
				teamIDs = userStrings.get(i).subList(5, userStrings.get(i).size());
				mediator.addUser(userType, userName,teamIDs,userId,password);
			}else {
				mediator.addUser(userType, userName,null,userId,password);
			}
		}
	}
}
