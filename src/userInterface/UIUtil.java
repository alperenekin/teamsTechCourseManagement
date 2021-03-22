package userInterface;

import java.util.ArrayList;
import java.util.Scanner;

import mediator.Mediator;
import teamEntities.Channel;
import teamEntities.DefaultChannel;
import teamEntities.Meeting;
import teamEntities.PrivateChannel;
import teamEntities.Team;
import userEntities.Academian;
import userEntities.Instructor;
import userEntities.Student;
import userEntities.User;

public class UIUtil {

	public static User loginScreen(Mediator mediator) {
		printLoginScreen();
		return login(mediator);
	}

	private static User login(Mediator mediator) {
		Scanner scanner= new Scanner(System.in);    //System.in is a standard input stream  
		System.out.print("Username(email):  ");  
		String userName = scanner.nextLine();  
		System.out.print("Passwd:  ");
		String passwd = scanner.nextLine();
		
		User returnUser = mediator.findUser(userName,passwd);
		if(returnUser == null)
		{
			System.out.println("Incorrect user name or password. Try Again.");
			returnUser = login(mediator);
		}
		//if not found call login. 
		return returnUser;
		
	}

	private static void printLoginScreen() {
		System.out.println("Welcome to the teamsTechApp\n");
	}

	public static void performCurrentUserTasks(Mediator mediator,User currentUser) {
		System.out.println("What would you like to do? 	\n");
		printAuthorizedTasks(mediator,currentUser);
		
		
	}

	private static void printAuthorizedTasks(Mediator mediator,User currentUser) {
		if(currentUser instanceof Student) {
			System.out.println("1- View Your Teams(Enter 1)");
			Scanner scanner= new Scanner(System.in);    //System.in is a standard input stream  
			String choice = scanner.nextLine();
			if(choice.equals("1")) {
				for(int i=0;i< currentUser.getTeams().size();i++) {
					System.out.print(i+1);
					System.out.println("-" + currentUser.getTeams().get(i).getId());
				}
				System.out.print("Choose the team by entering number:");
				int index = Integer.parseInt(scanner.nextLine())-1;
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
				int count = 1;
				ArrayList<PrivateChannel> userPrivateChannels = new ArrayList<PrivateChannel>();
				for(Channel userChannel : channels) {
					if(userChannel instanceof PrivateChannel) {
						if(((PrivateChannel) userChannel).isAuserParticipant(String.valueOf(currentUser.getId()))){
							userPrivateChannels.add((PrivateChannel) userChannel);
						}
					}
				}
				showUserPrivateChannel(userPrivateChannels,count,mediator);
				System.out.println("Choose an operation by entering a number");
				System.out.println("1-Create Private Channel");
				System.out.println("2-Update meeting day and time of private channel");
				System.out.println("3-Remove Private Channel");
				System.out.println("4-Add Participant to Private Channel");
				System.out.println("5-Remove a participant from Private Channel");
				String choice2 = scanner.nextLine();
				switch(choice2) {
					case "1":
						System.out.print("Enter Channel name");
						String newChannelName = scanner.nextLine();
						System.out.print("Enter Meeting time");
						String newMeetingTime = scanner.nextLine();
						mediator.addMeetingChannel(team, newChannelName, newMeetingTime,true, String.valueOf(currentUser.getId()));
						break;
					case "2":
						showUserPrivateChannel(userPrivateChannels,count,mediator);
						if(!userPrivateChannels.isEmpty()) {
							System.out.println("Choose channel you want to update meeting time");
							int channelNumber = Integer.parseInt(scanner.nextLine()) -1;
							System.out.println("Enter new meeting time (Monday 9.45 am)");
							String meetTime = scanner.nextLine();
							mediator.updateMeetingChannelTime(meetTime,userPrivateChannels.get(channelNumber),team);
						}
						break;
					case "3":
						showUserPrivateChannel(userPrivateChannels,count,mediator);
						if(!userPrivateChannels.isEmpty()) {
							System.out.println("Choose channel you want to remove by entering number");
							int channelNumber = Integer.parseInt(scanner.nextLine()) -1;
							mediator.removeMeetingChannel(team, userPrivateChannels.get(channelNumber));
						}
						break;
					case "4":
						showUserPrivateChannel(userPrivateChannels,count,mediator);
						if(!userPrivateChannels.isEmpty()) {
							System.out.println("Choose channel you want to add participant by entering number");
							int channelNumber = Integer.parseInt(scanner.nextLine()) -1;
							System.out.println("Enter unique participant Id:");
							String id = scanner.nextLine();
							mediator.addParticipantToChannel(id, userPrivateChannels.get(channelNumber),team);
							
						}
						break;
					case "5":
						showUserPrivateChannel(userPrivateChannels,count,mediator);
						if(!userPrivateChannels.isEmpty()) {
							System.out.println("Choose channel you want to remove participant from by entering number");
							int channelNumber = Integer.parseInt(scanner.nextLine()) -1;
							System.out.println("Enter unique participant Id:");
							String id = scanner.nextLine();
							boolean success = mediator.removeParticipantFromChannel(id, userPrivateChannels.get(channelNumber),team);
							if(success)
							{
								System.out.println("Participant removed succesfully \n");
							}
							else
							{
								System.out.println("There is no participant with id: "+ (String) id);
							}
							
						}
						break;
					default:
						System.out.println("There is no such an action");

						
				}
				
			}
			
		}
		else if (currentUser instanceof Academian) {
			System.out.println("1- Add a team");
			System.out.println("2- Remove a team");
			System.out.println("3- Update a team");
			System.out.println("Please enter a number");
			Scanner scanner= new Scanner(System.in);    //System.in is a standard input stream  
			String choice = scanner.nextLine();
			switch (choice)
			{
			case "1":				
				System.out.print("Enter team name");
				String newTeamName = scanner.nextLine();
				System.out.print("Enter id(only lecture code. Ceng will be added automaticly.)");
				String newTeamlId = scanner.nextLine();
				mediator.addTeam(new Team(newTeamName,newTeamlId,currentUser), false,currentUser);
				//System.out.println(((Instructor) currentUser).getOwnedTeams());
				break;
			case "2":
				
				for(int i=0;i< ((Academian) currentUser).getOwnedTeams().size();i++) {
					System.out.print(i+1);
					System.out.println("-" + ((Academian) currentUser).getOwnedTeams().get(i).getId());
				}
				System.out.print("Choose the team by entering number:");
				int index = Integer.parseInt(scanner.nextLine())-1;
				Team team = ((Academian) currentUser).getOwnedTeams().get(index);
				mediator.removeTeam(team);
				
				break;
			case "3":
				break;
			default:
				System.out.println("There is no such an action");
			}
			//mediator.removeTeam(currentUser.getTeams().get(0)); Böyle siliyoruz
			
			
			/*Team t = new Team("deneme","deneme101");
			t.addDefaultChannel(new DefaultChannel("defaultChannel",new Meeting("sabah 10")));
			mediator.addTeam(t, false);*/ //Yeni Team böyle eklendiğinde  dosya direk üstüne yazıyor.	
		}
	}
	private static void showUserPrivateChannel(ArrayList<PrivateChannel> userPrivateChannels,int count,Mediator mediator) {
		if(userPrivateChannels.isEmpty()) {
			System.out.println("You dont have any private channel yet");
		}else {
			for(Channel privateChannels : userPrivateChannels) {
				System.out.println("PRIVATE CHANNEL: " + count +"-"+ privateChannels.getName());
				if(privateChannels.getMeeting() != null) {
					System.out.println("MEETING TIME" +":"+ privateChannels.getMeeting().getMeetingTime());
				}
				System.out.println("PARTICIPANTS" +":"+ findNamesOfParticipants(mediator,((PrivateChannel) privateChannels).getParticipants()));
				count++;
			}
		}
	}
	private static String findNamesOfParticipants(Mediator mediator, ArrayList<String> participants) {
		String output = "";
		for(String userId : participants) {
			for(User user : mediator.getUserList()) {
				if(userId.equals(String.valueOf(user.getId()))) {
					output += (user.getName() + "");
				}
			}
		}
		return output;
	}

}
