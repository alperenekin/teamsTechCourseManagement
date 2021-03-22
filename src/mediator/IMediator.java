package mediator;

import teamEntities.Channel;
import teamEntities.Team;
import userEntities.User;

public interface IMediator {
	public boolean findTeam();
	public boolean removeTeam(Team team);
	public boolean removeMeetingChannel(Team team, Channel channel);
	public boolean updateMeetingChannelTime(String meetingTime, Channel channel, Team team);
	public boolean addMememberToTeam();
	public boolean removeMemberToTeam();
	public int numberOfStudents();
	public int numberOfInstructors();
	public int numberOfTeachingAssistants();
	boolean addTeam(Team team, boolean isFromFile,User currentUser);
	boolean addMeetingChannel(Team team,String channelName,String meetingTime, boolean isPrivate, String creator);
	
}
	