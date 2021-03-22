package userInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fileOperations.FileIO;
import mediator.Mediator;
import teamEntities.Channel;
import teamEntities.DefaultChannel;
import teamEntities.Meeting;
import teamEntities.PrivateChannel;
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
		createTeamsFromFile(mediator,teamStrings);
		createUsersFromFile(mediator,userStrings);
		mediator.addTeamOwner();
	}
	
	private static void createTeamsFromFile(Mediator mediator, ArrayList<ArrayList<String>> teamStrings) {
		for(int i=1; i< teamStrings.size() ; i++) {
			ArrayList<Channel> channelsOfATeam = new ArrayList<Channel>();
			String teamName = teamStrings.get(i).get(0); //şuralar optimize edilebilir.
			String teamId = teamStrings.get(i).get(1);
			String defaultChannelName = teamStrings.get(i).get(2);
			String meetingTime = teamStrings.get(i).get(3);
			Meeting meeting = new Meeting(meetingTime);
			DefaultChannel defaultChannel = new DefaultChannel(defaultChannelName,meeting);
			Team team = new Team(teamName,teamId);
			createPrivateChannelsFromFile(teamStrings,i,team);

			team.addDefaultChannel(defaultChannel);
			mediator.addTeam(team,true,null);
		}
	}
	
	private static void createPrivateChannelsFromFile(ArrayList<ArrayList<String>> teamStrings, int i, Team team) {
		if(teamStrings.get(i).size() > 4) {
			for(int k = 4; k<teamStrings.get(i).size(); k++) {
				String privateChannelName = teamStrings.get(i).get(k);
				String newMeetingTime = teamStrings.get(i).get(k+1);
				ArrayList<String> participants = new ArrayList<String>();
				int numberOfShift = createParticipantsForChannel(participants, teamStrings,i,k+2);
				if(newMeetingTime.isEmpty()) {
					PrivateChannel privateChannel = new PrivateChannel(privateChannelName,null);
					for(String p : participants) {
						privateChannel.addParticipant(p);
					}
					participants.clear();
					team.addPrivateChannel(privateChannel);
				}else {
					Meeting newMeeting = new Meeting(newMeetingTime);
					PrivateChannel privateChannel = new PrivateChannel(privateChannelName,newMeeting);
					for(String p : participants) {
						privateChannel.addParticipant(p);
					}
					participants.clear();
					team.addPrivateChannel(privateChannel);
				}
				k += numberOfShift-1+2;
			}
		}
	}
	private static void createUsersFromFile(Mediator mediator, ArrayList<ArrayList<String>> userStrings) {
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
	
	private static int createParticipantsForChannel(ArrayList<String> participants, ArrayList<ArrayList<String>> line ,int i,int x) {
		int count = 0 ;
		for(int j = 0; j<line.get(i).size(); j++) {
			if((x+j) <line.get(i).size()) {
				int len = line.get(i).get(x+j).length(); // TODO 6 lar değişmeli
				if(line.get(i).get(x+j).startsWith("\"")){
					if(line.get(i).get(x+j).endsWith("\"")) {
						participants.add(line.get(i).get(x+j).substring(1,len-1));
						count++;
						return count;
					}else {
						participants.add(line.get(i).get(x+j).substring(1,len));
						count++;
					}
				}
				else {
					if(line.get(i).get(x+j).endsWith("\"")) {
						participants.add(line.get(i).get(x+j).substring(0,len-1));
						count++;
						return count;
					}else {
						participants.add(line.get(i).get(x+j).substring(0,len));
						count++;
					}
				}
			}
		}
		return 0;
	}
	
}
