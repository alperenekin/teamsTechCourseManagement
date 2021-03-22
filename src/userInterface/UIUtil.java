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
import userEntities.TeachingAsistant;
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
				mediator.monitorTeamsOfUser(currentUser);
				System.out.print("Choose the team by entering number:");
				int index = Integer.parseInt(scanner.nextLine())-1;
				Team team = currentUser.getTeams().get(index);
				ArrayList<Channel> channels = team.getChannels();
				ArrayList<PrivateChannel> userPrivateChannels = new ArrayList<PrivateChannel>();
				for(Channel userChannel : channels) {
					if(userChannel instanceof PrivateChannel) {
						if(((PrivateChannel) userChannel).isAuserParticipant(String.valueOf(currentUser.getId()))){
							userPrivateChannels.add((PrivateChannel) userChannel);
						}
					}
				}
				mediator.monitorTeamDetailsOfUser(index, currentUser, userPrivateChannels);
				int count = 1;
				mediator.monitorPrivateChanelsOfUser(userPrivateChannels,count);
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
						mediator.monitorPrivateChanelsOfUser(userPrivateChannels,count);
						if(!userPrivateChannels.isEmpty()) {
							System.out.println("Choose channel you want to update meeting time");
							int channelNumber = Integer.parseInt(scanner.nextLine()) -1;
							System.out.println("Enter new meeting time (Monday 9.45 am)");
							String meetTime = scanner.nextLine();
							mediator.updateMeetingChannelTime(meetTime,userPrivateChannels.get(channelNumber),team);
						}
						break;
					case "3":
						mediator.monitorPrivateChanelsOfUser(userPrivateChannels,count);
						if(!userPrivateChannels.isEmpty()) {
							System.out.println("Choose channel you want to remove by entering number");
							int channelNumber = Integer.parseInt(scanner.nextLine()) -1;
							mediator.removeMeetingChannel(team, userPrivateChannels.get(channelNumber));
						}
						break;
					case "4":
						mediator.monitorPrivateChanelsOfUser(userPrivateChannels,count);
						if(!userPrivateChannels.isEmpty()) {
							System.out.println("Choose channel you want to add participant by entering number");
							int channelNumber = Integer.parseInt(scanner.nextLine()) -1;
							System.out.println("Enter unique participant Id:");
							String id = scanner.nextLine();
							mediator.addParticipantToChannel(id, userPrivateChannels.get(channelNumber),team);
							
						}
						break;
					case "5":
						mediator.monitorPrivateChanelsOfUser(userPrivateChannels,count);
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
				System.out.println("Choose an operation by entering a number");
				System.out.println("0-promote User");
				System.out.println("1-Add User");
				System.out.println("2-Create Private Channel");
				System.out.println("3-Update meeting day and time of private channel");
				System.out.println("4-Remove Private Channel");
				System.out.println("5-Add Participant to Private Channel");
				System.out.println("6-Remove a participant from Private Channel");
				String choice2 = scanner.nextLine();
				switch (choice2)
				{
				case "0":
					for(int i=0;i< ((Academian) currentUser).getOwnedTeams().size();i++) {
						System.out.print(i+1);
						System.out.println("-" + ((Academian) currentUser).getOwnedTeams().get(i).getId());
					}
					System.out.print("Choose the team for promoting by entering number:");
					int promoteIndex = Integer.parseInt(scanner.nextLine())-1;
					ArrayList<TeachingAsistant> tempAsistants = new ArrayList<TeachingAsistant>();
					Team chosenTeam = ((Academian) currentUser).getOwnedTeams().get(promoteIndex);
					for(int j = 0 ; j < chosenTeam.getMembers().size();j++) {
						if(chosenTeam.getMembers().get(j) instanceof TeachingAsistant) {
							tempAsistants.add((TeachingAsistant) chosenTeam.getMembers().get(j));
						}
					}
					for(int i=0;i<tempAsistants.size();i++) {
						System.out.print(i+1);
						System.out.println("-" + tempAsistants.get(i).getName());
						
					}
					System.out.print("Choose the teaching assistant for promoting by entering number:");
					int promoteAsistantIndex = Integer.parseInt(scanner.nextLine())-1;
					mediator.promoteUser(chosenTeam, tempAsistants.get(promoteAsistantIndex), currentUser);					
				case "1":
					for(int i=0;i< ((Academian) currentUser).getOwnedTeams().size();i++) {
						System.out.print(i+1);
						System.out.println("-" + ((Academian) currentUser).getOwnedTeams().get(i).getId());
					}
					System.out.print("Choose the team for adding user by entering number:");
					int addingUserIndex = Integer.parseInt(scanner.nextLine())-1;
					Team chosenTeam1 = ((Academian) currentUser).getOwnedTeams().get(addingUserIndex);					
					System.out.println("Enter unique participant Id:");
					String id = scanner.nextLine();
					mediator.addMememberToTeam(id,currentUser,chosenTeam1);
				case "2":
					mediator.monitorTeamsOfUser(currentUser);
                    int index2 = Integer.parseInt(scanner.nextLine()) - 1;
                    Team team2 = currentUser.getTeams().get(index2);
                    System.out.print("Enter Channel name");
                    String newChannelName = scanner.nextLine();
                    System.out.print("Enter Meeting time");
                    String newMeetingTime = scanner.nextLine();
                    mediator.addMeetingChannel(team2, newChannelName, newMeetingTime, true, String.valueOf(currentUser.getId()));
				case "3":
					mediator.monitorTeamsOfUser(currentUser);
                    int index3 = Integer.parseInt(scanner.nextLine()) - 1;
                    Team team3 = currentUser.getTeams().get(index3);
                    ArrayList<Channel> channels = team3.getChannels();
                    ArrayList<PrivateChannel> userPrivateChannels = new ArrayList<PrivateChannel>();
                    for(Channel userChannel : channels) {
                        if(userChannel instanceof PrivateChannel) {
                            if(((PrivateChannel) userChannel).isAuserParticipant(String.valueOf(currentUser.getId()))){
                                userPrivateChannels.add((PrivateChannel) userChannel);
                            }
                        }
                    }
                    int count = 1;
                    mediator.monitorPrivateChanelsOfUser(userPrivateChannels,count);
                    if(!userPrivateChannels.isEmpty()) {
                        System.out.println("Choose channel you want to update meeting time");
                        int channelNumber = Integer.parseInt(scanner.nextLine()) -1;
                        System.out.println("Enter new meeting time (Monday 9.45 am)");
                        String meetTime = scanner.nextLine();
                        mediator.updateMeetingChannelTime(meetTime,userPrivateChannels.get(channelNumber),team3);
                    }
				case "4":
					mediator.monitorTeamsOfUser(currentUser);
                    int index4 = Integer.parseInt(scanner.nextLine()) - 1;
                    Team team4 = currentUser.getTeams().get(index4);
                    ArrayList<Channel> channels4 = team4.getChannels();
                    ArrayList<PrivateChannel> userPrivateChannels4 = new ArrayList<PrivateChannel>();
                    for(Channel userChannel : channels4) {
                        if(userChannel instanceof PrivateChannel) {
                            if(((PrivateChannel) userChannel).isAuserParticipant(String.valueOf(currentUser.getId()))){
                                userPrivateChannels4.add((PrivateChannel) userChannel);
                            }
                        }
                    }
                    int count4 = 1;
                    mediator.monitorPrivateChanelsOfUser(userPrivateChannels4,count4);
                    if(!userPrivateChannels4.isEmpty()) {
                        System.out.println("Choose channel you want to remove by entering number");
                        int channelNumber = Integer.parseInt(scanner.nextLine()) -1;
                        mediator.removeMeetingChannel(team4, userPrivateChannels4.get(channelNumber));
                    }
				case "5":
					mediator.monitorTeamsOfUser(currentUser);
                    int index5 = Integer.parseInt(scanner.nextLine()) - 1;
                    Team team5 = currentUser.getTeams().get(index5);
                    ArrayList<Channel> channels5 = team5.getChannels();
                    ArrayList<PrivateChannel> userPrivateChannels5 = new ArrayList<PrivateChannel>();
                    for (Channel userChannel : channels5) {
                        if (userChannel instanceof PrivateChannel) {
                            if (((PrivateChannel) userChannel).isAuserParticipant(String.valueOf(currentUser.getId()))) {
                                userPrivateChannels5.add((PrivateChannel) userChannel);
                            }
                        }
                    }
                    int count5 = 1;
                    mediator.monitorPrivateChanelsOfUser(userPrivateChannels5, count5);
                    if (!userPrivateChannels5.isEmpty()) {
                        System.out.println("Choose channel you want to add participant by entering number");
                        int channelNumber = Integer.parseInt(scanner.nextLine()) - 1;
                        System.out.println("Enter unique participant Id:");
                        String id5 = scanner.nextLine();
                        mediator.addParticipantToChannel(id5, userPrivateChannels5.get(channelNumber), team5);

                    }
				case "6":
					mediator.monitorTeamsOfUser(currentUser);
                    int index6 = Integer.parseInt(scanner.nextLine()) - 1;
                    Team team6 = currentUser.getTeams().get(index6);
                    ArrayList<Channel> channels6 = team6.getChannels();
                    ArrayList<PrivateChannel> userPrivateChannels6 = new ArrayList<PrivateChannel>();
                    for(Channel userChannel : channels6) {
                        if(userChannel instanceof PrivateChannel) {
                            if(((PrivateChannel) userChannel).isAuserParticipant(String.valueOf(currentUser.getId()))){
                                userPrivateChannels6.add((PrivateChannel) userChannel);
                            }
                        }
                    }
                    int count6 = 1;
                    mediator.monitorPrivateChanelsOfUser(userPrivateChannels6,count6);
                    if(!userPrivateChannels6.isEmpty()) {
                        System.out.println("Choose channel you want to remove by entering number");
                        int channelNumber = Integer.parseInt(scanner.nextLine()) -1;
                        mediator.removeMeetingChannel(team6, userPrivateChannels6.get(channelNumber));
                    }
				default:
					System.out.println("There is no such a operation");
				
				}
				
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


}
