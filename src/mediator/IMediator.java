package mediator;

import teamEntities.Channel;
import teamEntities.PrivateChannel;
import teamEntities.Team;
import userEntities.User;

import java.util.ArrayList;
import java.util.List;

public interface IMediator {
	public boolean removeTeam(Team team);
	public boolean removeMeetingChannel(Team team, Channel channel);
	public boolean updateMeetingChannelTime(String meetingTime, Channel channel, Team team);
	public boolean addMememberToTeam(String id,User currentUser,Team chosenTeam1);
	public int numberOfStudents();
	public int numberOfInstructors();
	public int numberOfTeachingAssistants();
	public void monitorTeamsOfUser(User currentUser);
	public void monitorTeamDetailsOfUser(int index, User currentUser, ArrayList<PrivateChannel> userPrivateChannels);
	public void monitorPrivateChanelsOfUser(ArrayList<PrivateChannel> userPrivateChannels, int count);
	public boolean addMeetingChannel(Team team,String channelName,String meetingTime, boolean isPrivate, String creator);
	public boolean addUser(String userType, String username, List<String> teamIDs, String userId, String password);
	public boolean addTeam(Team team, boolean isFromFile,User currentUser);
	public boolean addParticipantToChannel(String  userId, PrivateChannel channel, Team team);
}
	